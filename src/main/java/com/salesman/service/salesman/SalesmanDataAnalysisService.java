package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import com.salesman.service.IProcessLine;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SalesmanDataAnalysisService implements IProcessLine {

    private static final int CPF_POSITION = 1;
    private static final int NAME_POSITION = 2;
    private static final int SALARY_POSITION = 3;

    @Override
    public Object processLine(String[] line) {
        String cpf = line[CPF_POSITION];
        String name = line[NAME_POSITION];
        BigDecimal salary = new BigDecimal(line[SALARY_POSITION]);
        return new Salesman(cpf, name, salary);
    }
}
