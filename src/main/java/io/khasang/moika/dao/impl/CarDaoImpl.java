package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
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
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Car.class);
        criteria.add(Restrictions.eq("id", id));
        return (Car) criteria.uniqueResult();
    }

    @Override
    public Car getCarByType(String type) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Car.class);
        criteria.add(Restrictions.eq("type", type));
        return (Car) criteria.uniqueResult();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getCarList() {
        Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from Car;");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Car> getCarHqlList() {
        List<Car> CarList = sessionFactory.getCurrentSession().createQuery("FROM Car").list();
        return CarList;
    }

    @Override
    public Car getCarByNumber(String number) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Car.class);
        criteria.add(Restrictions.eq("number", number));
        return (Car) criteria.uniqueResult();
    }

}
