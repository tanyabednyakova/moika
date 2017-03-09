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

@Repository
public abstract class AllServiceFieldsDaoImpl<T extends BaseMoikaService>  implements BaseMoikaServiceDao<T> {

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

    public AllServiceFieldsDaoImpl() {
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
    public void updateEntity(T entity) throws MoikaDaoException{
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void deleteEntity(T entity) throws MoikaDaoException{
        final Session session =  sessionFactory.getCurrentSession();
        session.delete(entity);
        session.flush();
    }

    @Override
    public T getEntityById(int id) throws MoikaDaoException{
        Criteria criteria =  sessionFactory.
                getCurrentSession().
                createCriteria(daoType);
        criteria.add(Restrictions.eq("id", id));
        return (T) criteria.uniqueResult();
    }


    @Override
    public List<T> getAllEntities() throws MoikaDaoException{
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session =  sessionFactory.getCurrentSession();
        List<T> list =  session.createCriteria(daoType).list();
        return list;
    }


    public List<T> getAllActualServices() throws MoikaDaoException {
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(daoType);
        criteria.add(Restrictions.eq("idStatus", 0));
        return session.createCriteria(daoType).list();
    }

}
