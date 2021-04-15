package com.salesman.service;

import com.salesman.model.Report;
import com.salesman.stub.ReportDTOStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DataResultServiceTest {

    @Mock
    private DataResultService dataResultService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveResultTest() {
        Report report = ReportDTOStub.createOneResult();
        String reportResult = report.toString();

        when(dataResultService.saveResult(any())).thenReturn(report.toString());
        String result = dataResultService.saveResult(report);

        assertNotNull(result);
        assertEquals(reportResult, result);
    }
}
