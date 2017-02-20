package io.khasang.moika.service.impl;

import io.khasang.moika.model.RostislavDataAccess;
import io.khasang.moika.service.RostislavDataAccessService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RostislavDataAccessServiceImpl implements RostislavDataAccessService {

    @Autowired
    RostislavDataAccess rostislavDataAccess;

    public RostislavDataAccessServiceImpl() {
    }

    @Override
    public List<Map<String, Object>> getAllCars() {
        return rostislavDataAccess.getSelectedDataFromDbEntity("Cars", null);
    }

    @Override
    public List<Map<String, Object>> getCars(String carModel) {

        Map<String, Pair<String, String>> filter = new HashMap<>();
        filter.put("carmodel", new Pair<>("=", carModel));

        return rostislavDataAccess.getSelectedDataFromDbEntity("Cars", filter);
    }

    @Override
    public List<Map<String, Object>> getCarToCarCrossData() {

        return rostislavDataAccess.getSelectedDataFromJoinedDbEntities("Cars car1", "Cars car2", "CROSS", null, null);
    }


}
