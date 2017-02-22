package io.khasang.moika.service.impl;

import io.khasang.moika.entity.Car;
import io.khasang.moika.model.OrlovDataAccess;
import io.khasang.moika.service.OrlovDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrlovDataAccessServiceImpl implements OrlovDataAccessService {
    @Autowired
    private OrlovDataAccess orlovDataAccess;

    public OrlovDataAccessServiceImpl() {

    }

    @Override
    public List<Car> select() {
        return orlovDataAccess.select();
    }

    @Override
    public Car selectById(int id) {
        return orlovDataAccess.selectById(id);
    }
}
