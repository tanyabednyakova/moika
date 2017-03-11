package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.MoikaDaoException;

import io.khasang.moika.entity.BaseMoikaService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Абстрактный класс для всех потомков моечный услуг BaseMoikaService
 * @param <T>
 */

@Repository
public abstract class AllServiceFieldsDaoImpl<T extends BaseMoikaService> extends MoikaDaoCrudImpl<T> implements BaseMoikaServiceDao<T> {


    public List<T> getAllActualServices() throws MoikaDaoException {
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(daoType);
        criteria.add(Restrictions.eq("idStatus", 0));
        return session.createCriteria(daoType).list();
    }

}
