package com.salesman.model;

import java.math.BigDecimal;

public class Item {

    private long id;
    private int quantity;
    private BigDecimal price;

    public Item(long id, int quantity, BigDecimal price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(quantity));
    }
}
