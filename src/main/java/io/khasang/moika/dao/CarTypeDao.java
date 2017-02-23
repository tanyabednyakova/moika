package io.khasang.moika.dao;

import io.khasang.moika.entity.CarType;

import java.util.List;

public interface CarTypeDao {
    void addCarType(CarType carType);
    void updateCarType(CarType carType);
    void deleteCarType(CarType carType);
    CarType getCarTypeById(int id);
    CarType getCarTypeByCode(String code);
    List<CarType> getAllCarTypes();
}
