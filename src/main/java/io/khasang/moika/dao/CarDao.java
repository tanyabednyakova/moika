package io.khasang.moika.dao;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import io.khasang.moika.entity.Company;

import java.util.List;

public interface CarDao {
    void addCar(Car car);
    void updateCar(Car car);
    void deleteCar(Car car);
    Car getCarById(int id);
    Car getCarByNum(String num);
    List<Car> getCarsByClient(Client client);
    List<Car> getCarList();
    List<Client> getClientsByCar(Car car);
    List<Car> getCarListByStatus(int status);
}
