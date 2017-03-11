package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.QueueDAO;
import io.khasang.moika.entity.Queue;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class QueueDaoImpl implements QueueDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public QueueDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addQueue(Queue queue) {
        sessionFactory.getCurrentSession().save(queue);
    }

    @Override
    public void updateQueue(Queue queue) {
        sessionFactory.getCurrentSession().update(queue);
    }

    @Override
    public void deleteQueue(Queue queue) {
        sessionFactory.getCurrentSession().delete(queue);
    }

    @Override
    public List<Queue> getAllQueue() {
        return sessionFactory.getCurrentSession().createQuery("from Queue ").list();
    }

    @Override
    public Queue getQueueById(long id) {
        return sessionFactory.getCurrentSession().get(Queue.class,id);
    }
}
