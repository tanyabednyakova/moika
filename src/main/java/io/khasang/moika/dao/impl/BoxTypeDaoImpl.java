package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository("boxTypeDao")
public class BoxTypeDaoImpl implements BoxTypeDao {
    private SessionFactory sessionFactory;

    public BoxTypeDaoImpl() {
    }

    @Autowired
    public BoxTypeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBoxType(BoxType boxType) {
        sessionFactory.getCurrentSession().save(boxType);
    }

    @Override
    public void updateBoxType(BoxType boxType) {
        sessionFactory.getCurrentSession().update(boxType);
    }

    @Override
    public void deleteBoxType(BoxType boxType) {
        sessionFactory.getCurrentSession().delete(boxType);
    }

    @Override
    public BoxType getBoxTypeById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(BoxType.class);
        criteria.add(Restrictions.eq("id_type", id));
        return (BoxType) criteria.uniqueResult();
    }

    @Override
    public BoxType getBoxTypeByCode(String code) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(BoxType.class);
        criteria.add(Restrictions.eq("code", code));
        return (BoxType) criteria.uniqueResult();
    }

    @Override
    @Transactional(readOnly=true)
    public List<BoxType> getAllBoxTypes(){
        return  sessionFactory.getCurrentSession().createQuery("from box_types с").list();
    }
}
