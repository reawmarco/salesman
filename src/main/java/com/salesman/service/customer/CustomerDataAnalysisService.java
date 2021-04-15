package com.salesman.service.customer;

import com.salesman.model.Customer;
import com.salesman.service.IProcessLine;
import org.springframework.stereotype.Service;

@Service
public class CustomerDataAnalysisService implements IProcessLine {

    private static final int CNPJ_POSITION = 1;
    private static final int NAME_POSITION = 2;
    private static final int BUSINESS_AREA_POSITION = 3;

    @Override
    public Object processLine(String[] line) {
        String cnpj = line[CNPJ_POSITION];
        String name = line[NAME_POSITION];
        String businessArea = line[BUSINESS_AREA_POSITION];
        return new Customer(cnpj, name, businessArea);
    }
}
