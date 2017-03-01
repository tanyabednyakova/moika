package io.khasang.moika.dao.impl;

import io.khasang.moika.entity.Car;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CarDaoIlImpl implements io.khasang.moika.dao.CarDaoIl {
    private final SessionFactory sessionFactory;

    @Autowired
    public CarDaoIlImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);

    }

    @Override
    public Car getCarById(long id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Car.class);
        criteria.add(Restrictions.eq("id", id));
        return (Car) criteria.uniqueResult();
    }

    @Override
    public void deleteCar(Car car) {
       final Session session = sessionFactory.getCurrentSession();
       session.delete(car);
       session.flush();

    }

    @Override
    public void updateCar(Car car) {
        sessionFactory.getCurrentSession().update(car);
    }
}
