package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    @Override
    public List<Car> getCarList() {
        return carDao.getCarList();
    }

    @Override
    public void updateCar(Car car) {
        carDao.update(car);
    }

    @Override
    public Car updateCar(long carId, Map<String, Object> fieldValueMap) {
        return carDao.updateById(carId, fieldValueMap);
    }

    @Override
    public Car getCarById(long carId) {
        return carDao.getById(carId);
    }

}
