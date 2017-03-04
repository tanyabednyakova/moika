package io.khasang.moika.service;

import io.khasang.moika.entity.Car;

import java.util.List;

public interface CarService {
    void addCar(Car car);

    Car getCarById(long id);

    Car getCarByType(String type);

    Car getCarByNumber(String number);

    List<Car> getCarList();

    void deleteCar(long id);

    void updateCar(Car car);
}
