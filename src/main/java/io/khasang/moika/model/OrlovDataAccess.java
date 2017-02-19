package io.khasang.moika.model;


import io.khasang.moika.entity.Car;

import java.util.List;

public interface OrlovDataAccess {
    List<Car> select();
}
