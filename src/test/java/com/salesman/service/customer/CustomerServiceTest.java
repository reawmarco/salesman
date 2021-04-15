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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private static final String SEPARATOR = "ç";

    @Mock
    private CustomerService customerService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processLineTest() {
        String[] customerList = CustomerStub.createOneLine();
        Customer customer = new Customer("2345675434544345", "Jose da Silva", "Rural");

        when(customerService.processLine(any())).thenReturn(customer);
        when(customerService.getTotalCustomers()).thenReturn(1);

        String[] strCustomer = customerList[0].split(SEPARATOR);
        Customer result = customerService.processLine(strCustomer);
        int resultSize = customerService.getTotalCustomers();

        assertThat(result, instanceOf(Customer.class));
        assertEquals(customer.getName(), result.getName());
        assertEquals(customer.getBusinessArea(), result.getBusinessArea());
        assertEquals(customer.getCnpj(), result.getCnpj());
        assertEquals(1, resultSize);
    }
}
