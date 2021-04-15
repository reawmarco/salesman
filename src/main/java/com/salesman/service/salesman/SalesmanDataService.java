package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SalesmanDataService {

    private final HashMap<String, Salesman> salesmanHashMap;

    public SalesmanDataService() {
        salesmanHashMap = new HashMap<>();
    }

    public Salesman addSalesman(Salesman salesman) {
        return salesmanHashMap.put(salesman.getCpf(), salesman);
    }

    public Salesman getSalesmanByName(String name) {
        return salesmanHashMap.values()
                .stream()
                .filter(salesman -> salesman.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public int getTotalSalesmen() {
        return salesmanHashMap.size();
    }

    public void clearList() {
        salesmanHashMap.clear();
    }
}
