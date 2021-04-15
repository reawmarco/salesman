package com.salesman.service.sale;

import com.salesman.model.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SalesService {

    private final SalesDataService salesData;
    private final SalesDataAnalysisService salesDataAnalysis;
    private Logger logger = LoggerFactory.getLogger(SalesService.class);

    public SalesService(SalesDataService salesData, SalesDataAnalysisService salesDataAnalysis) {
        this.salesData = salesData;
        this.salesDataAnalysis = salesDataAnalysis;
    }

    public Sale processLine(String[] line) {
        Sale sales = (Sale) salesDataAnalysis.processLine(line);
        return addSales(sales);
    }

    public Sale addSales(Sale sales) {
        return salesData.addSale(sales);
    }

    public long mostExpensiveSaleId() {
        logger.info("Getting the most expensive sale id");
        return salesData.mostExpensiveSaleId();
    }

    public String worstSalesmanName() {
        logger.info("Getting the worst salesman");
        return salesData.worstSalesmanName();
    }

    public void clearList() {
        logger.info("Clearing list  Sales");
        salesData.clearList();
    }
}
