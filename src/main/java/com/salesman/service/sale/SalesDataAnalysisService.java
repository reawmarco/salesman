package com.salesman.service.sale;

import com.salesman.model.Item;
import com.salesman.model.Sale;
import com.salesman.model.Salesman;
import com.salesman.service.IProcessLine;
import com.salesman.service.salesman.SalesmanService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesDataAnalysisService implements IProcessLine {

    private static final int SALE_POSITION = 1;
    private static final int SALESMAN_POSITION = 3;
    private static final int QUANTITY_POSITION = 1;
    private static final int ITEM_ID_POSITION = 0;
    private static final int ITENS_POSITION = 2;
    private static final int PRICE_POSITION = 2;

    private static final String ITENS_SEPARATOR = ",";
    private static final String ITENS_SEPARATOR_VALUES = "-";

    private final SalesmanService salesmanService;

    public SalesDataAnalysisService(SalesmanService salesmanService) {
        this.salesmanService = salesmanService;
    }

    @Override
    public Object processLine(String[] line) {
        long id = Long.parseLong(line[SALE_POSITION]);
        String strItem = line[ITENS_POSITION];
        String salesmanName = line[SALESMAN_POSITION];

        Salesman salesman = salesmanService.getSalesmanByName(salesmanName);
        Sale sales = new Sale(id, salesman, salesmanName);
        List<Item> listItens = convertToItens(strItem);

        return processItens(sales, listItens);
    }

    private List<Item> convertToItens(String rawItens) {
        return Arrays.stream(removeBrackets(rawItens).split(ITENS_SEPARATOR)).map(this::convertToItem).collect(Collectors.toList());
    }

    private String removeBrackets(String rawItens) {
        return rawItens.replaceAll("\\[|\\]", "");
    }

    private Item convertToItem(String rawItem) {
        String[] rawItemArray = rawItem.split(ITENS_SEPARATOR_VALUES);
        long id = Long.parseLong(rawItemArray[ITEM_ID_POSITION]);
        int quantity = Integer.parseInt(rawItemArray[QUANTITY_POSITION]);
        BigDecimal price = new BigDecimal(rawItemArray[PRICE_POSITION]);
        return new Item(id, quantity, price);
    }

    private Sale processItens(Sale sales, List<Item> listItens) {
        for (Item item : listItens) {
            long id = item.getId();
            int quantity = item.getQuantity();
            BigDecimal price = item.getPrice();
            sales.addSaleItem(new Item(id, quantity, price));
        }
        return sales;
    }
}
