package com.example.demo.service.impl;

import com.example.readingisgood.service.impl.StatisticsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceImplTest {

    @InjectMocks
    StatisticsServiceImpl statisticsService;

    @Test(expected = UnsupportedOperationException.class)
    public void get() {
        statisticsService.get("");
    }
}