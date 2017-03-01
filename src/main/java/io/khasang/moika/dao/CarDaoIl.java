package io.khasang.moika.dao;

import io.khasang.moika.entity.Car;

public interface CarDaoIl {
    void addCar(Car car);
    Car getCarById(long id);
    void deleteCar(Car car);
    void updateCar(Car car);
}
