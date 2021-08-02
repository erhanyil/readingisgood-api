package com.example.readingisgood.service;

import com.example.readingisgood.dto.statistics.StatisticsDto;

public interface StatisticsService {

    StatisticsDto get(String year);

}
