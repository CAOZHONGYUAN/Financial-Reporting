package com.fms.service.imp;

import com.fms.dao.StatisticsDao;
import com.fms.entity.Statistics;
import com.fms.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;
    @Override
    public Statistics getAllCount(int userId) {
        return statisticsDao.getAllCount(userId);
    }

    @Override
    public List<Map<String,Object>> getTrafficCount(int userId) {
        return statisticsDao.getTrafficCount(userId);
    }

    @Override
    public List<Map<String, Object>> getPropertyCount(int userId) {
        return statisticsDao.getPropertyCount(userId);
    }

    @Override
    public List<Map<String, Object>> getTravelMap(int userId) {
        return statisticsDao.getTravelMap(userId);
    }
}
