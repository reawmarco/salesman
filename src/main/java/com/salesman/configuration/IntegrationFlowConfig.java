package com.salesman.configuration;

import com.salesman.model.Report;
import com.salesman.service.DataProcessLineService;
import com.salesman.service.DataResultService;
import com.salesman.service.customer.CustomerService;
import com.salesman.service.sale.SalesService;
import com.salesman.service.salesman.SalesmanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.FileInboundChannelAdapterSpec;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.transformer.GenericTransformer;

import java.nio.file.Paths;
import java.util.Objects;

@Configuration
@EnableIntegration
public class IntegrationFlowConfig {

    private final Properties properties;
    private final CustomerService customerService;
    private final SalesmanService salesmanService;
    private final SalesService salesService;
    private final DataProcessLineService dataProcessLineService;
    private final DataResultService dataResultService;

    public IntegrationFlowConfig(DataProcessLineService dataProcessLineService,
                                 DataResultService dataResultService,
                                 CustomerService customerService,
                                 SalesmanService salesmanService,
                                 SalesService salesService,
                                 Properties properties) {
        this.dataProcessLineService = dataProcessLineService;
        this.dataResultService = dataResultService;
        this.customerService = customerService;
        this.salesmanService = salesmanService;
        this.salesService = salesService;
        this.properties = properties;
    }

    @Bean
    IntegrationFlow integrationFlow() {
        return IntegrationFlows
                .from(createFlowBuiler(), spec -> spec.poller(Pollers.fixedRate(properties.getPollerTimerate()).maxMessagesPerPoll(properties.getPollerMaxmessages())))
                .filter(new SimplePatternFileListFilter(properties.getFileInType()))
                .log(LoggingHandler.Level.INFO, "Processing file")
                .transform(Files.toStringTransformer())
                .transform(processData())
                .handle(generateMetricHandler())
                .log(LoggingHandler.Level.INFO, "Finished file read process ")
                .handle(fileWritingMessageHandler())
                .get();
    }

    private FileInboundChannelAdapterSpec createFlowBuiler() {
        return Files.inboundAdapter(Paths.get(properties.getBasePath(), properties.getFileInFolder()).toFile())
                .useWatchService(Boolean.TRUE);
    }

    private GenericTransformer<String, Report> processData() {
        clearLists();
        return dataProcessLineService::processData;
    }

    public GenericHandler<Report> generateMetricHandler() {
        return (message, headers) -> dataResultService.saveResult(message);
    }

    private FileWritingMessageHandler fileWritingMessageHandler() {
        return Files.outboundAdapter(Paths.get(properties.getBasePath(), properties.getFileOutFolder()).toFile())
                .deleteSourceFiles(true)
                .fileExistsMode(FileExistsMode.REPLACE)
                .fileNameGenerator(nameGenerator())
                .get();
    }

    private FileNameGenerator nameGenerator() {
        return fileName -> Objects.requireNonNull(fileName.getHeaders()
                .get(FileHeaders.FILENAME, String.class))
                .replaceFirst(properties.getFileOutRegex(), properties.getFileOutReplace());
    }

    private void clearLists() {
        salesmanService.clearList();
        salesService.clearList();
        customerService.clearList();
    }
}
