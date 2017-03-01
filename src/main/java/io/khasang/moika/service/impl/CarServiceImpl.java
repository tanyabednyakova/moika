package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CarDaoIl;
import io.khasang.moika.entity.Car;
import io.khasang.moika.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CarServiceImpl")
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    CarDaoIl carDaoIl;

    public CarServiceImpl() {
    }

    @Override
    public void addCar(Car car) {
        carDaoIl.addCar(car);
    }

    @Override
    public Car getCarById(long id) {
        return carDaoIl.getCarById(id);
    }

    @Override
    public void deleteCar(long id) {
        Car car = new Car();
        car.setId(id);
        carDaoIl.deleteCar(car);
    }

    @Override
    public void updateCar(Car car) {
        carDaoIl.updateCar(car);
    }
}
