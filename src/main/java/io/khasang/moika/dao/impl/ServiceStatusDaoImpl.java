package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.ServiceStatusDao;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.ServiceStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("ServiveStatusDao")
public class ServiceStatusDaoImpl implements ServiceStatusDao {
    private SessionFactory sessionFactory;

    @Autowired
    public ServiceStatusDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ServiceStatusDaoImpl() {
    }

    @Override
    public ServiceStatus addEntity(ServiceStatus entity) throws MoikaDaoException {
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public void updateEntity(ServiceStatus entity) throws MoikaDaoException{
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void deleteEntity(ServiceStatus entity) throws MoikaDaoException{
        final Session session =  sessionFactory.getCurrentSession();
        session.delete(entity);
        session.flush();
    }

    @Override
    public ServiceStatus getEntityById(int id) throws MoikaDaoException{
        Criteria criteria =  sessionFactory.
                getCurrentSession().
                createCriteria(BaseMoikaService.class);
        criteria.add(Restrictions.eq("id", id));
        return (ServiceStatus) criteria.uniqueResult();
    }

    @Override
    public ServiceStatus getEntityByCode(String code) throws MoikaDaoException{
        Criteria criteria =  sessionFactory.
                getCurrentSession().
                createCriteria(BaseMoikaService.class);
        criteria.add(Restrictions.eq("code", code));
        return (ServiceStatus) criteria.uniqueResult();
    }
    @Override
    public List<ServiceStatus> getAllEntities() throws MoikaDaoException{
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session =  sessionFactory.getCurrentSession();
        return session.createCriteria(ServiceStatus.class).list();
    }


}
