package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.util.DataAccessUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CarDaoImpl extends BasicDaoImpl<Car> implements CarDao {

    @Autowired
    public CarDaoImpl(SessionFactory sessionFactory, DataAccessUtil dataAccessUtil) {
        super(Car.class);
    }

}
