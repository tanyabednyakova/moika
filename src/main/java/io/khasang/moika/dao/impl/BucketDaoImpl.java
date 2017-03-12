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

import java.util.Date;
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
    public void addProductToClientBucket(long productId, long clientId, int amount) {
        Session session = sessionFactory.getCurrentSession();
        List<Bucket> bucketList = getBucketByClientAndProduct(productId, clientId);
        if (bucketList.size() == 0) {
            Bucket bucket = new Bucket();
            bucket.setClientId(clientId);
            bucket.setProductId(productId);
            bucket.setAmount(amount);
            Date expTime = new Date();
            expTime.setTime(expTime.getTime() + 60000);
            bucket.setExpireDatetime(expTime);
            session.save(bucket);
        }
        else {
            Bucket bucket = bucketList.get(0);
            bucket.setAmount(bucket.getAmount() + amount);
            session.update(bucket);
        }
    }

    private List<Bucket> getBucketByClientAndProduct(long productId, long clientId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Bucket.class);
        criteria.add(Restrictions.eq("clientId", clientId));
        criteria.add(Restrictions.eq("productId", productId));
        return (List<Bucket>) criteria.list();
    }

    @Override
    public void deleteProductFromClientBucket(long productId, long clientId, int amount) {
        Session session = sessionFactory.getCurrentSession();
        List<Bucket> bucketList = getBucketByClientAndProduct(productId, clientId);
        Bucket bucket = bucketList.get(0);
        int curAmount = bucket.getAmount();
        if (curAmount == amount) {
            session.delete(bucket);
        }
        else if (curAmount > amount) {
            bucket.setAmount(curAmount - amount);
            session.update(bucket);
        }
        else {
            //exception?
        }
    }

    @Override
    public void clearClientBucket(long clientId) {
        Session session = sessionFactory.getCurrentSession();
        List<Bucket> bucketList = getClientBucket(clientId);
        for (Bucket bucket : bucketList) {
            session.delete(bucket);
        }
        session.flush();
    }
}
