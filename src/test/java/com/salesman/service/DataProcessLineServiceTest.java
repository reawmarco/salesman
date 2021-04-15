package com.salesman.service;

import com.salesman.configuration.Properties;
import com.salesman.model.Report;
import com.salesman.service.customer.CustomerDataAnalysisService;
import com.salesman.service.customer.CustomerDataService;
import com.salesman.service.customer.CustomerService;
import com.salesman.service.sale.SalesDataAnalysisService;
import com.salesman.service.sale.SalesDataService;
import com.salesman.service.sale.SalesService;
import com.salesman.service.salesman.SalesmanDataAnalysisService;
import com.salesman.service.salesman.SalesmanDataService;
import com.salesman.service.salesman.SalesmanService;
import com.salesman.stub.CustomerStub;
import com.salesman.stub.ReportDTOStub;
import com.salesman.stub.SalesStub;
import com.salesman.stub.SalesmanStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DataProcessLineServiceTest {

    @Mock
    private Properties properties;
    @Mock
    private SalesmanDataService salesmanData;
    @Mock
    private SalesmanDataAnalysisService salesmanDataAnalysis;
    @Mock
    private SalesmanService salesmanService;
    @Mock
    private CustomerDataService customerData;
    @Mock
    private CustomerDataAnalysisService customerDataAnalysis;
    @Mock
    private CustomerService customerService;
    @Mock
    private SalesDataService salesData;
    @Mock
    private SalesDataAnalysisService salesDataAnalysis;
    @Mock
    private SalesService salesService;
    @Mock
    private DataProcessLineService dataProcessLineService;
    @Mock
    private DataResultService dataResultService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processDataTest() {
        String[] salesmanList = SalesmanStub.createTwoLines();
        String[] customerList = CustomerStub.createTwoLines();
        String[] salesList = SalesStub.createTwoLines();
        Report reportTest = ReportDTOStub.createOneResult();

        String strTest = salesmanList[0] +
                "\n" +
                salesmanList[1] +
                "\n" +
                customerList[0] +
                "\n" +
                customerList[1] +
                "\n" +
                salesList[0] +
                "\n" +
                salesList[1];

        when(dataProcessLineService.processData(any())).thenReturn(reportTest);
        when(dataResultService.saveResult(any())).thenReturn(reportTest.toString());

        Report report = dataProcessLineService.processData(strTest);

        String resultReport = dataResultService.saveResult(report);

        assertEquals("Paulo", report.getWorstSalesmanName());
        assertEquals(2, report.getCustomerTotal());
        assertEquals(8, report.getMostExpensiveSaleId());
        assertEquals(2, report.getSalesmanTotal());
        assertEquals(report.toString(), resultReport);
    }

    @Test
    public void clearListsTest() {
        String[] salesmanList = SalesmanStub.createTwoLines();
        String[] customerList = CustomerStub.createTwoLines();
        String[] salesList = SalesStub.createTwoLines();

        String strTest = salesmanList[0] +
                "\n" +
                salesmanList[1] +
                "\n" +
                customerList[0] +
                "\n" +
                customerList[1] +
                "\n" +
                salesList[0] +
                "\n" +
                salesList[1];
        dataProcessLineService.processData(strTest);

        salesmanService.clearList();
        salesService.clearList();
        customerService.clearList();

        assertNull(salesService.worstSalesmanName());
        assertEquals(0, salesService.mostExpensiveSaleId());
        assertEquals(0, customerService.getTotalCustomers());
        assertEquals(0, salesmanService.getTotalSalesmen());
    }
}
