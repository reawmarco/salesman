package com.salesman.service.customer;

import com.salesman.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerDataService customerData;
    private final CustomerDataAnalysisService customerDataAnalysis;

    public CustomerService(CustomerDataService customerData, CustomerDataAnalysisService customerDataAnalysis) {
        this.customerData = customerData;
        this.customerDataAnalysis = customerDataAnalysis;
    }

    public Customer processLine(String[] line) {
        Customer customer = (Customer) customerDataAnalysis.processLine(line);
        addCustomer(customer);
        return customer;
    }

    private void addCustomer(Customer customer) {
        customerData.addCustomer(customer);
    }

    public int getTotalCustomers() {
        return customerData.getTotalCustomers();
    }

    public void clearList() {
        customerData.clearList();
    }
}
