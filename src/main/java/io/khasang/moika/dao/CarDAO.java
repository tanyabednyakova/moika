package io.khasang.moika.dao;

import io.khasang.moika.entity.Car;

import java.util.List;

public interface CarDAO {

    void addCar(Car Car);

    void updateCar(Car Car);

    void deleteCar(Car Car);

    Car getCarById(long id);

    Car getCarByName(String name);

    List<Car> getCarList();

    List<Car> getCarHqlList();
}
