package com.fms.dao;

import com.fms.entity.Statistics;

import java.util.List;
import java.util.Map;

public interface StatisticsDao {
    public Statistics getAllCount(int userId);
    public List<Map<String,Object>> getTrafficCount(int userId);
    public List<Map<String,Object>> getPropertyCount(int userId);
    public List<Map<String,Object>> getTravelMap(int userId);
}
