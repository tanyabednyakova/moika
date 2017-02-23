package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.CarTypeDao;
import io.khasang.moika.entity.CarType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository("carTypeDao")
public class CarTypeDaoImpl implements CarTypeDao {
    private final SessionFactory sessionFactory;


    public CarTypeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addCarType(CarType carType) {
        sessionFactory.getCurrentSession().save(carType);
    }

    @Override
    public void updateCarType(CarType carType) {
        sessionFactory.getCurrentSession().update(carType);
    }

    @Override
    public void deleteCarType(CarType carType) {
        sessionFactory.getCurrentSession().delete(carType);
    }

    @Override
    public CarType getCarTypeById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(CarType.class);
        criteria.add(Restrictions.eq("id_type", id));
        return (CarType) criteria.uniqueResult();
    }

    @Override
    public CarType getCarTypeByCode(String code) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(CarType.class);
        criteria.add(Restrictions.eq("code", code));
        return (CarType) criteria.uniqueResult();
    }

    @Override
    @Transactional(readOnly=true)
    public List<CarType> getAllCarTypes(){
        return  sessionFactory.getCurrentSession().createQuery("from car_types ct").list();
    }
}
