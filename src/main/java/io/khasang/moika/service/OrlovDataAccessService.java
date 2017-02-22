package io.khasang.moika.service;

import io.khasang.moika.entity.Car;

import java.util.List;

public interface OrlovDataAccessService {
    List<Car> select();
    Car selectById(int id);
}
