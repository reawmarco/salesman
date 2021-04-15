package com.salesman.service;

import com.salesman.model.Report;
import org.springframework.stereotype.Service;

@Service
public class DataResultService {

    public String saveResult(Report report) {
        return report.toString();
    }
}
