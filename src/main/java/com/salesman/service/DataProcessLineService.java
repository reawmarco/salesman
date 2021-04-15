package com.salesman.service;

import com.salesman.configuration.Properties;
import com.salesman.model.Report;
import com.salesman.service.customer.CustomerService;
import com.salesman.service.sale.SalesService;
import com.salesman.service.salesman.SalesmanService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataProcessLineService {

    private final static int TYPE_POSITION = 0;
    private final CustomerService customerService;
    private final SalesmanService salesmanService;
    private final SalesService salesService;
    private final Properties properties;

    public DataProcessLineService(CustomerService customerService,
                                  SalesmanService salesmanService,
                                  SalesService salesService,
                                  Properties properties) {
        this.customerService = customerService;
        this.salesmanService = salesmanService;
        this.salesService = salesService;
        this.properties = properties;
    }

    public Report processData(String source) {
        List<String> list = Arrays.asList(source.split(System.lineSeparator()));
        list.forEach(this::processLine);

        return Report
                .builder()
                .customerTotal(customerService.getTotalCustomers())
                .mostExpensiveSaleId(salesService.mostExpensiveSaleId())
                .salesmanTotal(salesmanService.getTotalSalesmen())
                .worstSalesmanName(salesService.worstSalesmanName())
                .build();
    }

    private void processLine(String line) {
        String[] str = line.split(properties.getSeparator());
        String type = str[TYPE_POSITION];

        switch (type) {
            case "001":
                processSalesmanLine(str);
                break;
            case "002":
                processCustomerLine(str);
                break;
            case "003":
                processSalesLine(str);
                break;
        }
    }

    private void processSalesmanLine(String[] line) {
        salesmanService.processLine(line);
    }

    private void processCustomerLine(String[] line) {
        customerService.processLine(line);
    }

    private void processSalesLine(String[] line) {
        salesService.processLine(line);
    }
}
