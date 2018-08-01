package com.info.repository;

import com.info.model.CanvasjsChartData;
import com.info.repository.CanvasjsChartDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {
    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {
        return CanvasjsChartData.getCanvasjsDataList();
    }
}
