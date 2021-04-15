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

public class SalesmanServiceTest {
    private static final String SEPARATOR = "ç";

    @Mock
    private SalesmanService salesmanService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processLineTest() {
        String[] salesmanList = SalesmanStub.createOneLine();
        Salesman salesman = new Salesman("1234567891234", "Pedro", new BigDecimal("40000.99"));

        when(salesmanService.processLine(salesmanList)).thenReturn(salesman);
        when(salesmanService.getSalesmanByName("Pedro")).thenReturn(salesman);
        when(salesmanService.getTotalSalesmen()).thenReturn(1);

        String[] strSalesman = salesmanList[0].split(SEPARATOR);
        salesmanService.processLine(strSalesman);

        Salesman result = salesmanService.getSalesmanByName(salesman.getName());
        int resultSize = salesmanService.getTotalSalesmen();

        assertEquals(salesman.getName(), result.getName());
        assertEquals(salesman.getCpf(), result.getCpf());
        assertEquals(1, resultSize);
    }
}
