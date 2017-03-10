package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CarDAO;
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
    CarDAO carDAO;

    public CarServiceImpl() {
    }

    @Override
    public void addCar(Car car) {
        carDAO.addCar(car);
    }

    @Override
    public Car getCarById(long id) {
        return carDAO.getCarById(id);
    }

    @Override
    public Car getCarByType(String type) {
        return carDAO.getCarByType(type);
    }

    @Override
    public Car getCarByNumber(String number) {
        return carDAO.getCarByNumber(number);
    }

    @Override
    public List<Car> getCarList() {
        return carDAO.getCarList();
    }

    @Override
    public void deleteCar(long id) {
        Car car = new Car();
        car.setId(id);
        carDAO.deleteCar(car);
    }

    @Override
    public void updateCar(Car car) {
        carDAO.updateCar(car);
    }
}
