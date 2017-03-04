package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CarServiceImpl")
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    CarDao carDao;

    public CarServiceImpl() {
    }

    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    @Override
    public Car getCarById(long id) {
        return carDao.getCarById(id);
    }

    @Override
    public Car getCarByType(String type) {
        return carDao.getCarByType(type);
    }

    @Override
    public Car getCarByNumber(String number) {
        return carDao.getCarByNumber(number);
    }

    @Override
    public List<Car> getCarList() {
        return carDao.getCarList();
    }

    @Override
    public void deleteCar(long id) {
        Car car = new Car();
        car.setId(id);
        carDao.deleteCar(car);
    }

    @Override
    public void updateCar(Car car) {
        carDao.updateCar(car);
    }
}
