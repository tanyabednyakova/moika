package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CarDaoImpl implements CarDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public CarDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCar(Car Car) {
        sessionFactory.getCurrentSession().save(Car);
    }

    @Override
    public void updateCar(Car Car) {
        sessionFactory.getCurrentSession().update(Car);
    }

    @Override
    public void deleteCar(Car Car) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(Car);
        session.flush();
    }

    @Override
    public Car getCarById(long id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }

    @Override
    public Car getCarByType(String type) {
        return  sessionFactory.getCurrentSession().get(Car.class, type);
    }

    @Override
    public List<Car> getCarList() {
        return sessionFactory.getCurrentSession().createQuery("FROM Car").list();
    }
    
    @Override
    public Car getCarByNumber(String number) {
        return  sessionFactory.getCurrentSession().get(Car.class, number);
    }

}
