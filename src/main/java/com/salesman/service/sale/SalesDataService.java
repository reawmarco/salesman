package com.salesman.service.sale;

import com.salesman.model.Item;
import com.salesman.model.Sale;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesDataService {

    private final static long DEFAULT_ID_BIGGER_SALE = 0;
    private final List<Sale> sales;

    public SalesDataService() {
        this.sales = new ArrayList<>();
    }

    public Sale addSale(Sale sale) {
        sales.add(sale);
        return sale;
    }

    public long mostExpensiveSaleId() {
        return sales.stream()
                .max(Comparator.comparing(Sale::getAmount))
                .map(Sale::getId)
                .orElse(DEFAULT_ID_BIGGER_SALE);
    }

    String worstSalesmanName() {
        Map<String, BigDecimal> map = sales.stream()
                .collect(Collectors.toMap(Sale::getSalesmanName, x -> getSalePrice(x.getSaleItems()), BigDecimal::add));
        return Collections.min(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private BigDecimal getSalePrice(List<Item> saleItems) {
        return saleItems.stream()
                .map(x -> x.getPrice()
                        .multiply(new BigDecimal(x.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clearList() {
        sales.clear();
    }
}
