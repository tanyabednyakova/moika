package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDAO;
import io.khasang.moika.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Component
@Transactional
public class CarDAOImpl implements CarDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CarDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Serializable addCar(Car car) {
        return sessionFactory.getCurrentSession().
                save(car);
    }

    @Override
    public Car updateCar(Car car) {
        sessionFactory.getCurrentSession().update(car);
        return car;
    }

    @Override
    public void deleteCar(Car Car) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(Car);
        session.flush();
    }

    @Override
    public Car getCarById(long id) {
        return sessionFactory.getCurrentSession().
                get(Car.class, id);
    }

    @Override
    public Car getCarByType(String type) {
        return  sessionFactory.getCurrentSession().
                get(Car.class, type);
    }

    @Override
    public List<Car> getCarList() {
        return sessionFactory.getCurrentSession().
                createQuery("FROM Car").list();
    }
    
    @Override
    public Car getCarByNumber(String number) {
        return  sessionFactory.getCurrentSession().get(Car.class, number);
    }

}
