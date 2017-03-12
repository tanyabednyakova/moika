package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ClientDao;
import io.khasang.moika.entity.Client;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ClientDaoImpl implements ClientDao {
    private SessionFactory sessionFactory;

    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client getClientById(long id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("id", id));
        return (Client) criteria.uniqueResult();
    }
}
