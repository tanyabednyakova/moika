package io.khasang.moika.dao;

import io.khasang.moika.entity.Car;

import java.io.Serializable;
import java.util.List;

/**
 * Интерфейс DAO автомобилей
 *
 * @author Nikolay Ilichev, Lyubarev Aleksandr
 * @since 2017-03-01
 */

public interface CarDAO {

    /**
     * Добавление автомобиля
     */
    Serializable addCar(Car Car);

    /**
     * Обновление информации по автомобилю
     */
    Car updateCar(Car Car);

    /**
     * Удаление автомобиля
     */
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

    List getCarByType(String name);
    /**
     * Вывод списка автомобилей
     *
     */

    List getCarList();
    /**
     * Найти автомобиль по номеру
     *
     * @param number искомого автомобиля
     * @return список автомобилей
     */
    List getCarByNumber(String number);

    /**
     * Найти автомобиль по модели
     *
     * @param model искомого автомобиля
     * @return список автомобилей
     */
    List getCarByModel(String model);
}
