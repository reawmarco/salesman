package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesmanDataServiceTest {

    private final SalesmanDataService salesmanData;

    public SalesmanDataServiceTest() {
        salesmanData = new SalesmanDataService();
    }

    @Test
    public void addSalesmanTest() {
        Salesman salesman = new Salesman("1234567891234", "Pedro", new BigDecimal("40000.99"));

        salesmanData.addSalesman(salesman);

        int result = salesmanData.getTotalSalesmen();
        Salesman salesmanResult = salesmanData.getSalesmanByName("Pedro");

        assertEquals(1, result);
        assertEquals("Pedro", salesmanResult.getName());
        assertEquals("1234567891234", salesmanResult.getCpf());
    }

    @Test
    public void getSalesmanByNameTest() {
        Salesman salesman = new Salesman("1234567891234", "Pedro", new BigDecimal("40000.99"));

        salesmanData.addSalesman(salesman);

        Salesman salesmanResult = salesmanData.getSalesmanByName("Pedro");

        assertEquals("Pedro", salesmanResult.getName());
        assertEquals("1234567891234", salesmanResult.getCpf());
    }

    @Test
    public void getTotalSalesmenTest() {
        Salesman salesman = new Salesman("1234567891234", "Pedro", new BigDecimal("40000.99"));
        Salesman salesman2 = new Salesman("1234567891222", "Marco", new BigDecimal("5000"));
        Salesman salesman3 = new Salesman("1234567891221", "Joao", new BigDecimal("1000"));

        salesmanData.addSalesman(salesman);
        salesmanData.addSalesman(salesman2);
        salesmanData.addSalesman(salesman3);

        int result = salesmanData.getTotalSalesmen();

        assertEquals(3, result);
    }

    @Test
    public void clearListTest() {
        Salesman salesman = new Salesman("1234567891234", "Pedro", new BigDecimal("40000.99"));
        salesmanData.addSalesman(salesman);
        salesmanData.clearList();
        int result = salesmanData.getTotalSalesmen();
        assertEquals(0, result);
    }
}
