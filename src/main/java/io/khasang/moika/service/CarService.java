package io.khasang.moika.service;

import io.khasang.moika.entity.Car;

import java.util.List;
import java.util.Map;

public interface CarService {
    void addCar(Car car);

    List<Car> getCarList();

    void updateCar(Car car);

    Car updateCar(long carId, Map<String, Object> fieldValueMap);

    Car getCarById(long carId);


}