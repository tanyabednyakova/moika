package io.khasang.moika.model;


import io.khasang.moika.entity.Car;

import java.util.List;

public interface OrlovDataAccess {
    /** Select all cars **/
    List<Car> select();
    /** Select car by id
     * @param id**/
    Car selectById(int id);
}
