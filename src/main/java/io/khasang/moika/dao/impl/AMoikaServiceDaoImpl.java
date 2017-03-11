package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.IMoikaDaoCrud;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository("moikaServiceDaoImpl")
public abstract class AMoikaServiceDaoImpl<T extends BaseMoikaService> extends MoikaSessionFactory implements IMoikaDaoCrud<T> {

    protected Class<? extends T> type;

    public AMoikaServiceDaoImpl() {
        // this.type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AMoikaServiceDaoImpl.class);
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
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
                createCriteria(type);
        criteria.add(Restrictions.eq("id", id));
        return (T) criteria.uniqueResult();
    }


    @Override
    public List<T> getAllEntities() throws MoikaDaoException {
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        //final Session session = sessionFactory.getCurrentSession();
        List objects = null;
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from " + type);
        objects = query.list();
        return objects;
        //return  session.createCriteria(type).list();
    }


    public List<T> getAllActualServices() throws MoikaDaoException {
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(type);
        criteria.add(Restrictions.eq("idStatus", 0));
        return session.createCriteria(type).list();
    }

}
