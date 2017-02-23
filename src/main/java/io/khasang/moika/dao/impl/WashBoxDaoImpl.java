package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.WashBoxDao;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashBox;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("washBoxDao")
public class WashBoxDaoImpl implements WashBoxDao{

    private final SessionFactory sessionFactory;

    public WashBoxDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createWashBox(WashBox washBox) {
        sessionFactory.getCurrentSession().save(washBox);        
    }

    @Override
    public void updateWashBox(WashBox washBox) {
        sessionFactory.getCurrentSession().update(washBox);
    }

    @Override
    public void deleteWashBox(WashBox washBox) {
        sessionFactory.getCurrentSession().delete(washBox);
    }

    @Override
    public WashBox getWashBox(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(WashBox.class);
        criteria.add(Restrictions.eq("id_box", id));
        return (WashBox) criteria.uniqueResult();
    }

    @Override
    public WashBox getWashBox(int idFacility, String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(WashBox.class);
        criteria.add(Restrictions.eq("id_fclt", idFacility));
        criteria.add(Restrictions.eq("name", name));
        return (WashBox) criteria.uniqueResult();
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFacility) {
        Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from wash_box where id_fclt = ?;");
        query.setParameter(1, idFacility);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @Override
    public List<WashBox> getAllWashBoxes() {
        return  sessionFactory.getCurrentSession().createQuery("from wash_box wb").list();
    }

}
