package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.QueueDAO;
import io.khasang.moika.entity.Queue_t;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("QueueDAO")
public class QueueDAOImpl implements QueueDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public QueueDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public QueueDAOImpl() {
    }

    @Override
    public void createQueue(Queue_t queue_t) {
        sessionFactory.getCurrentSession().save(queue_t);
    }

    @Override
    public void updateQueue(Queue_t queue_t) {
        sessionFactory.getCurrentSession().update(queue_t);
    }

    @Override
    public void deleteQueue(Queue_t queue_t) {
        sessionFactory.getCurrentSession().delete(queue_t);
    }

    @Override
    public Queue_t getQueue_id(int id) {
        // TODO
        return null;
    }

    @Override
    public List<Queue_t> getAllQueue() {
        return sessionFactory.getCurrentSession().createQuery("from Queue_t").list();
    }
}
