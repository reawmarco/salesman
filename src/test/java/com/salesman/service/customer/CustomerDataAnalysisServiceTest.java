package com.salesman.service.customer;

import com.salesman.model.Customer;
import com.salesman.stub.CustomerStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerDataAnalysisServiceTest {

    @Mock
    private CustomerDataAnalysisService customerDataAnalysis;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processLineTest() {
        String[] strCustomer = CustomerStub.createOneLine();
        Customer customer = new Customer("2345675434544345", "Jose da Silva", "Rural");

        when(customerDataAnalysis.processLine(strCustomer)).thenReturn(customer);

        Customer result = (Customer) customerDataAnalysis.processLine(strCustomer);

        assertThat(result, instanceOf(Customer.class));
        assertEquals(result, customer);
    }
}
