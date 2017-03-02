package io.khasang.moika.dao;


import io.khasang.moika.entity.Car;

import java.util.List;
import java.util.Map;

public interface CarDao {
    void addCar(Car car);

    List<Car> getCarList();

    void updateCar(Car car);

    /**
     * Обновить автомобиль с переданным ID переданными сведениями и вернуть обновлённый объект.
     * @param carId ID автомобиля
     * @param fieldValueMap карта "свойство->значение"
     * @return обновлённый автомобиль
     */
    Car updateCar(long carId, Map<String, Object> fieldValueMap);

    Car getCarById(long carId);


}
