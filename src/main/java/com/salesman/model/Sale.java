package com.salesman.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private long id;
    private List<Item> items;
    private BigDecimal amount;
    private Salesman salesman;
    private String salesmanName;

    public Sale(long id, Salesman salesman, String salesmanName) {
        this.id = id;
        this.salesman = salesman;
        this.salesmanName = salesmanName;
        amount = BigDecimal.ONE;
        items = new ArrayList<>();
    }

    public long getId() {
        return id;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public List<Item> getSaleItems() {
        return items;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void addSaleItem(Item item) {
        items.add(item);
        amount = amount.add(item.getTotalPrice());
    }
}
