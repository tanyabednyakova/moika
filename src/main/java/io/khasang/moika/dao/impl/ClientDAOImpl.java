package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ClientDAO;
import io.khasang.moika.entity.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ClientDAOImpl implements ClientDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addClient(Client client) {
        sessionFactory.getCurrentSession().save(client);
    }

    @Override
    public void updateClient(Client client) {
        sessionFactory.getCurrentSession().update(client);
    }

    @Override
    public List<Client> getAllClients() {
        return sessionFactory.getCurrentSession().createQuery("from Client").list();
    }

    @Override
    public Client getClientById(long id) {
        return sessionFactory.getCurrentSession().get(Client.class,id);
    }

    @Override
    public void deleteClient(Client client) {
        sessionFactory.getCurrentSession().delete(client);
    }

    @Override
    public boolean containClientById(long id) {
        Long countClients = sessionFactory.getCurrentSession().
                createQuery("select COUNT(c.id) from Client c where c.id=:id", Long.class).
                setParameter("id", id).
                getSingleResult();
        return countClients != 0;
    }
}
