package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("carDao")
public class CarDaoImpl implements CarDao {
    private SessionFactory sessionFactory;

    public CarDaoImpl() {
    }

    @Autowired
    public CarDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }


    @Override
    public void updateCar(Car car) {
        sessionFactory.getCurrentSession().update(car);
    }

    @Override
    public void deleteCar(Car car) {
        //TODO связка с r_client_car
        final Session session = sessionFactory.getCurrentSession();
        session.delete(car);
        session.flush();
    }

    @Override
    public Car getCarById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Car.class);
        criteria.add(Restrictions.eq("id", id));
        return (Car) criteria.uniqueResult();
    }

    @Override
    public Car getCarByNum(String num) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Car.class);
        criteria.add(Restrictions.eq("carNumber", num));
        return (Car) criteria.uniqueResult();
    }

    @Override
    public List<Car> getCarsByClient(Client client) {
        return null;
    }

    @Override
    public List<Car> getCarList() {
        // Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from cars;");
        // query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        // return query.list();
        return sessionFactory.getCurrentSession().createQuery("from cars").list();
    }

    @Override
    public List<Client> getClientsByCar(Car car) {
        Query query = sessionFactory.getCurrentSession().createQuery("from cars where clients = ?");
        query.setParameter(0, car.getId());
        return query.list();
    }

    @Override
    public List<Car> getCarListByStatus(int status) {
        // Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from cars where status = ?;");
        // query.setParameter(1, status);
        // query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        Query query = sessionFactory.getCurrentSession().createQuery("from cars where status = ?");
        query.setParameter(0, status);
        return query.list();
    }
}
