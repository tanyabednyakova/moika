package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BaseMoikaStatusDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaStatusReference;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Базовый абстрактный класс для всех стправочников статусов (xxx_status)
 * @param <T> наследники ABaseMoikaTypeReference
 */

@Transactional
public abstract class AllStatusDaoImpl<T extends ABaseMoikaStatusReference> implements BaseMoikaStatusDao<T> {
    protected SessionFactory sessionFactory;

    @Autowired
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Class<? extends T> daoType;

    /**
     * By defining this class as abstract, we prevent Spring from creating
     * instance of this class If not defined as abstract,
     * getClass().getGenericSuperClass() would return Object. There would be
     * exception because Object class does not hava constructor with parameters.
     */

    public AllStatusDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }


    @Override
    public T addEntity(T entity) throws MoikaDaoException {
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public void updateEntity(T entity) throws MoikaDaoException {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void deleteEntity(T entity) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
        session.flush();
    }

    @Override
    public T getEntityById(int id) throws MoikaDaoException {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(daoType);
        criteria.add(Restrictions.eq("id", id));
        return (T) criteria.uniqueResult();
    }

    @Override
    public T getEntityByCode(String code) throws MoikaDaoException {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(daoType);
        criteria.add(Restrictions.eq("code", code));
        return (T) criteria.uniqueResult();
    }

    @Override
    public List<T> getAllEntities() throws MoikaDaoException {
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session = sessionFactory.getCurrentSession();
        List<T> list = session.createCriteria(daoType).list();
        return list;
    }
}
