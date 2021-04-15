package com.salesman.stub;

import com.salesman.model.Report;

public class ReportDTOStub {
    public static Report createOneResult() {
        return Report.builder()
                .worstSalesmanName("Paulo")
                .salesmanTotal(2)
                .mostExpensiveSaleId(8)
                .customerTotal(2)
                .build();
    }
}
