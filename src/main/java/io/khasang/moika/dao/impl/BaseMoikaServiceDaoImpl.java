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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("baseMoikaServiceDao")
public class BaseMoikaServiceDaoImpl implements BaseMoikaServiceDao {
    private SessionFactory sessionFactory;

    @Autowired
    public BaseMoikaServiceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public BaseMoikaServiceDaoImpl() {
    }

    @Override
    public BaseMoikaService addEntity(BaseMoikaService entity) throws MoikaDaoException {
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public void updateEntity(BaseMoikaService entity) throws MoikaDaoException{
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void deleteEntity(BaseMoikaService entity) throws MoikaDaoException{
        final Session session =  sessionFactory.getCurrentSession();
        session.delete(entity);
        session.flush();
    }

    @Override
    public BaseMoikaService getEntityById(int id) throws MoikaDaoException{
        Criteria criteria =  sessionFactory.
                getCurrentSession().
                createCriteria(BaseMoikaService.class);
        criteria.add(Restrictions.eq("id", id));
        return (BaseMoikaService) criteria.uniqueResult();
    }


    @Override
    public List<BaseMoikaService> getAllEntities() throws MoikaDaoException{
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session =  sessionFactory.getCurrentSession();
        return session.createCriteria(BaseMoikaService.class).list();
    }


    public List<BaseMoikaService> getAllActualServices() throws MoikaDaoException {
        //return sessionFactory.getCurrentSession().createQuery("from service ").list();
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(BaseMoikaService.class);
        criteria.add(Restrictions.eq("idStatus", 0));
        return session.createCriteria(BaseMoikaService.class).list();
    }

}
