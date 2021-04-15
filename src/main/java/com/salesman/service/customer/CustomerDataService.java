package com.salesman.service.customer;

import com.salesman.model.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CustomerDataService {

    private final HashMap<String, Customer> customerHashMap;

    public CustomerDataService() {
        customerHashMap = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customerHashMap.put(customer.getCnpj(), customer);
    }

    public int getTotalCustomers() {
        return customerHashMap.size();
    }

    public void clearList() {
        customerHashMap.clear();
    }
}
