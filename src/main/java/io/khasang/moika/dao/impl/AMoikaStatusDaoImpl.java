package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.IMoikaDaoCrud;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaStatusReference;
import io.khasang.moika.entity.ABaseMoikaTypeReference;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("moikaStatusDaoImpl")
public abstract class AMoikaStatusDaoImpl<T extends ABaseMoikaStatusReference>  extends MoikaSessionFactory implements IMoikaDaoCrud<T>{

    private Class<T> type;

    public AMoikaStatusDaoImpl() {
        this.type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AMoikaStatusDaoImpl.class);
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
        final Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
        session.flush();
    }

    @Override
    public T getEntityById(int id) throws MoikaDaoException{
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(type);
        criteria.add(Restrictions.eq("id", id));
        return (T) criteria.uniqueResult();
    }


    @Override
    public List<T> getAllEntities() throws MoikaDaoException{
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(type).list();
    }


}
