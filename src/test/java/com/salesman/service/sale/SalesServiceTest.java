package com.salesman.service.sale;

import com.salesman.model.Sale;
import com.salesman.model.Salesman;
import com.salesman.service.salesman.SalesmanDataAnalysisService;
import com.salesman.service.salesman.SalesmanDataService;
import com.salesman.service.salesman.SalesmanService;
import com.salesman.stub.SalesStub;
import com.salesman.stub.SalesmanStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SalesServiceTest {
    private static final String SEPARATOR = "ç";

    @Mock
    private SalesmanDataService salesmanData;
    @Mock
    private SalesmanDataAnalysisService salesmanDataAnalysis;
    @Mock
    private SalesDataService salesData;
    @Mock
    private SalesDataAnalysisService salesDataAnalysis;
    @Mock
    private SalesService salesService;
    @Mock
    private SalesmanService salesmanService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processLineTest() {
        String[] salesList = SalesStub.createTwoLines();
        Salesman salesman = new Salesman("1234567891234", "Pedro", new BigDecimal("40000.99"));
        Salesman salesman2 = new Salesman("3245678865434", "Paulo", new BigDecimal("40000.99"));
        Sale sales = new Sale(8, salesman, "Pedro");

        String[] salesmanList = SalesmanStub.createTwoLines();

        when(salesmanService.processLine(salesmanList[0].split(SEPARATOR))).thenReturn(salesman);
        when(salesmanService.processLine(salesmanList[1].split(SEPARATOR))).thenReturn(salesman2);
        when(salesService.processLine(salesList[0].split(SEPARATOR))).thenReturn(sales);
        when(salesService.mostExpensiveSaleId()).thenReturn(8L);
        when(salesService.worstSalesmanName()).thenReturn("Paulo");

        salesmanService.processLine(salesmanList[0].split(SEPARATOR));
        salesmanService.processLine(salesmanList[1].split(SEPARATOR));

        Sale result = salesService.processLine(salesList[0].split(SEPARATOR));
        salesService.processLine(salesList[1].split(SEPARATOR));

        long resultMostExpansive = salesService.mostExpensiveSaleId();
        String resultWorsSalesman = salesService.worstSalesmanName();

        assertEquals(sales.getId(), result.getId());
        assertEquals(sales.getSalesmanName(), result.getSalesmanName());
        assertEquals(sales.getAmount(), result.getAmount());
        assertEquals(8, resultMostExpansive);
        assertEquals("Paulo", resultWorsSalesman);
    }
}
