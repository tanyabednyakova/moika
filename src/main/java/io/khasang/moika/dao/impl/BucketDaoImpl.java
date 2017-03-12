package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.BucketDao;
import io.khasang.moika.entity.Bucket;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class BucketDaoImpl implements BucketDao {
    private SessionFactory sessionFactory;

    @Autowired
    public BucketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Bucket> getClientBucket(long clientId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Bucket.class);
        criteria.add(Restrictions.eq("clientId",clientId));
        return (List<Bucket>) criteria.list();
    }

    @Override
    public Bucket getBucketByClientAndProduct(long clientId, long productId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Bucket.class);
        criteria.add(Restrictions.eq("clientId", clientId));
        criteria.add(Restrictions.eq("productId", productId));
        return (Bucket) criteria.uniqueResult();
    }

    @Override
    public void addBucket(Bucket bucket) {
        sessionFactory.getCurrentSession().save(bucket);
    }

    @Override
    public void updateBucket(Bucket bucket) {
        sessionFactory.getCurrentSession().update(bucket);
    }

    @Override
    public void deleteBucket(Bucket bucket) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(bucket);
        session.flush();
    }
}
