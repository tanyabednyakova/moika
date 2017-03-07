package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ClientDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import io.khasang.moika.entity.Client;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository("clientDao")
public class ClientDaoImpl implements ClientDao{
    private SessionFactory sessionFactory;

    public ClientDaoImpl() {
    }

    @Autowired
    public ClientDaoImpl(SessionFactory sessionFactory) {
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
    public void deleteClient(Client client) {
        //TODO связка с r_client_client
        final Session session = sessionFactory.getCurrentSession();
        session.delete(client);
        session.flush();
    }

    @Override
    public Client getClientById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Client.class);
        criteria.add(Restrictions.eq("id_client", id));
        return (Client) criteria.uniqueResult();
    }

    @Override
    public List<Client> getClientByName(String firstName, String middelName, String lastName) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Client.class);
        criteria.add(Restrictions.like("first_name", firstName));
        criteria.add(Restrictions.like("middel_Name", middelName));
        criteria.add(Restrictions.like("last_Name", lastName));
        return (List<Client>) criteria.list() ;
    }

    @Override
    public Client getClientByTel(String tel) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Client.class);
        criteria.add(Restrictions.like("tel", tel));
        return (Client) criteria.uniqueResult();
    }

    @Override
    public List<Car> getCarsByClient(Client client) {
        return null;
    }

    @Override
    public List<Client> getClientsList() {
        return  sessionFactory.getCurrentSession().createQuery("from clients ct").list();
    }

    @Override
    public List<Client> getClientsListByLastDateWash(Date dateStart, Date dateEnd) {
     //   Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from clients where date_last_wash between ? and ?;");
     //   query.setParameter(1, dateStart);
     //   query.setParameter(2, dateEnd);
     //   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        Query query  = sessionFactory.getCurrentSession().createQuery("from clients where dateLastWash between ? and ?");
        query.setParameter(0, dateStart);
        query.setParameter(1, dateEnd);
        return query.list();
    }


    @Override
    public List<Client> getClientListByStatus(int status) {
      //  Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from clients where status = ?;");
      //  query.setParameter(1, status);
      //  query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        Query query  = sessionFactory.getCurrentSession().createQuery("from clients where status = ?");
        query.setParameter(0, status);
        return query.list();
    }
}
