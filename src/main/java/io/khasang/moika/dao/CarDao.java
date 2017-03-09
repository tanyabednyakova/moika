package io.khasang.moika.dao;

import io.khasang.moika.entity.Car;

import java.util.List;

/**
 * Интерфейс DAO автомобилей
 *
 * @author Nikolay Ilichev, Lyubarev Aleksandr
 * @since 2017-03-01
 */

public interface CarDao {
    void addCar(Car Car);

    void updateCar(Car Car);

    void deleteCar(Car Car);

    /**
     * Найти автомобиль по id
     *
     * @param id искомого автомобиля
     * @return автомобиль
     */
    Car getCarById(long id);

    /**
     * Найти автомобиль по названию
     *
     * @param name искомого автомобиля
     * @return автомобиль
     */

    Car getCarByType(String name);



    List getCarList();
    /**
     * Найти автомобиль по номеру
     *
     * @param number искомого автомобиля
     * @return список автомобилей
     */
    Car getCarByNumber(String number);
}
