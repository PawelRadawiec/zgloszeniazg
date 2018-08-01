package com.info.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface CanvasjsChartService {

    List<List<Map<Object, Object>>> getCanvasjsChartData();

}
