package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDAO;
import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.QueryParameter;
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

    //TODO: реализовать
    @Override
    public List getCarByType(String type) {
        return  sessionFactory.getCurrentSession().createQuery("from cars where carType = ?").
                setParameter(0, type).list();
    }

    //TODO: реализовать
    @Override
    public List getCarByNumber(String number) {
        return  sessionFactory.getCurrentSession().createQuery("from cars as c where c.carNumber = ?").
                setParameter(0, number).list();
    }

    //TODO: реализовать
    @Override
    public List getCarByModel(String model) {
        return  sessionFactory.getCurrentSession().createQuery("from cars as c where c.carModel = ?").
                setParameter(0, model).list();
    }

    @Override
    public List getCarList() {
        return sessionFactory.getCurrentSession().createQuery("from cars").list();
    }

}
