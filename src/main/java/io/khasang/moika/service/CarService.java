package io.khasang.moika.service;

import io.khasang.moika.entity.Car;

public interface CarService {
    void addCar(Car car);
    Car getCarById(long id);
    void deleteCar(long id);
    void updateCar(Car car);
}
