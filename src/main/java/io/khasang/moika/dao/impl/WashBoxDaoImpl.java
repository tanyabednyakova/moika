package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.WashBoxDao;
import io.khasang.moika.entity.WashBox;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("washBoxDao")
public class WashBoxDaoImpl implements WashBoxDao{
    private  SessionFactory sessionFactory;

    public WashBoxDaoImpl() {
    }

    @Autowired
    public WashBoxDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addWashBox(WashBox washBox) {
        sessionFactory.getCurrentSession().save(washBox);        
    }

    @Override
    public void updateWashBox(WashBox washBox) {
        sessionFactory.getCurrentSession().update(washBox);
    }

    @Override
    public void deleteWashBox(WashBox washBox) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(washBox);
        session.flush();
    }

    @Override
    public WashBox getWashBoxById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(WashBox.class);
        criteria.add(Restrictions.eq("id", id)); //propertyName это имя поля в Entity, а не в БД!!!
        return (WashBox) criteria.uniqueResult();
    }

    @Override
    public WashBox getWashBox(int idFacility, String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(WashBox.class);
        criteria.add(Restrictions.eq("washFacility", idFacility));
        criteria.add(Restrictions.eq("boxName", name));
        return (WashBox) criteria.uniqueResult();
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFacility) {
       // Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from wash_box where id_fclt = ?;")
      //          .addEntity(WashBox.class);
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where idFacility = ?");
        query.setParameter(0, idFacility);
     //   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @Override
    public List<WashBox> getAllWashBoxes() {
        return  sessionFactory.getCurrentSession().createQuery("from wash_boxes ").list();
    }

    @Override
    public List<WashBox> getWashBoxesByType(int boxType) {
     //   Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from wash_box where id_type = ?;")
     //           .addEntity(WashBox.class);
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where idType = ?");
        query.setParameter(0, boxType);
      //  query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @Override
    public List<WashBox> getWashBoxesByStatus(int boxStatus) {
     //   Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from wash_box where status = ?;")
      //          .addEntity(WashBox.class);
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where boxStatus = ?");
        query.setParameter(0, boxStatus);
     //   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

}
