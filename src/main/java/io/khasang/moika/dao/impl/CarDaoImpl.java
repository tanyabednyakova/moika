package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CarDaoImpl extends BasicDaoImpl<Car> implements CarDao {

    protected CarDaoImpl() {
        super(Car.class);
    }
}
