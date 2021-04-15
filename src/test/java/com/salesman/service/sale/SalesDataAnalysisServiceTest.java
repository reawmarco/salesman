package com.salesman.service.sale;

import com.salesman.model.Sale;
import com.salesman.model.Salesman;
import com.salesman.stub.SalesStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SalesDataAnalysisServiceTest {

    @Mock
    private SalesDataAnalysisService salesDataAnalysis;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processLineTest() {
        String[] strSales = SalesStub.createOneLine();
        Salesman salesman = new Salesman("3245678865434", "Paulo", new BigDecimal("40000.99"));
        Sale sales = new Sale(8, salesman, "Paulo");

        when(salesDataAnalysis.processLine(strSales)).thenReturn(sales);
        Sale result = (Sale) salesDataAnalysis.processLine(strSales);

        assertEquals(result, sales);
    }
}
