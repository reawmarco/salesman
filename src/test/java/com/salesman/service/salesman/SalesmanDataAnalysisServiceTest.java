package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import com.salesman.stub.SalesmanStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SalesmanDataAnalysisServiceTest {

    @Mock
    private SalesmanDataAnalysisService salesmanDataAnalysis;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processLineTest() {
        String[] strSalesman = SalesmanStub.createOneLine();
        Salesman salesman = new Salesman("1234567891234", "Pedro", new BigDecimal("40000.99"));

        when(salesmanDataAnalysis.processLine(strSalesman)).thenReturn(salesman);

        Salesman result = (Salesman) salesmanDataAnalysis.processLine(strSalesman);
        assertEquals(result, salesman);
    }
}
