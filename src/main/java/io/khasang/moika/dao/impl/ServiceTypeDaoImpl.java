package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.ServiceTypeDao;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.ServiceType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("ServiveTypeDao")
public class ServiceTypeDaoImpl implements ServiceTypeDao {
    private SessionFactory sessionFactory;

    @Autowired
    public ServiceTypeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ServiceTypeDaoImpl() {
    }

    @Override
    public ServiceType addEntity(ServiceType entity) throws MoikaDaoException {
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public void updateEntity(ServiceType entity) throws MoikaDaoException{
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void deleteEntity(ServiceType entity) throws MoikaDaoException{
        final Session session =  sessionFactory.getCurrentSession();
        session.delete(entity);
        session.flush();
    }

    @Override
    public ServiceType getEntityById(int id) throws MoikaDaoException{
        Criteria criteria =  sessionFactory.
                getCurrentSession().
                createCriteria(BaseMoikaService.class);
        criteria.add(Restrictions.eq("id", id));
        return (ServiceType) criteria.uniqueResult();
    }

    @Override
    public ServiceType getEntityByCode(String code) throws MoikaDaoException{
        Criteria criteria =  sessionFactory.
                getCurrentSession().
                createCriteria(BaseMoikaService.class);
        criteria.add(Restrictions.eq("id", code));
        return (ServiceType) criteria.uniqueResult();
    }


    @Override
    public List<ServiceType> getAllEntities() throws MoikaDaoException{
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session =  sessionFactory.getCurrentSession();
        return session.createCriteria(ServiceType.class).list();
    }


    public List<ServiceType> getAllActualServices() throws MoikaDaoException {
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ServiceType.class);
        criteria.add(Restrictions.eq("idStatus", 0));
        return session.createCriteria(ServiceType.class).list();
    }

}
