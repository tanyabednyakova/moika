package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.entity.BoxStatus;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Transactional
@Repository("boxStatusDao")
public class BoxStatusDaoImpl implements BoxStatusDao {
    private final SessionFactory sessionFactory;


    public BoxStatusDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addBoxStatus(BoxStatus boxStatus) {
        sessionFactory.getCurrentSession().save(boxStatus);
    }

    @Override
    public void updateBoxStatus(BoxStatus boxStatus) {
        sessionFactory.getCurrentSession().update(boxStatus);
    }

    @Override
    public void deleteBoxStatus(BoxStatus boxStatus) {
        sessionFactory.getCurrentSession().delete(boxStatus);
    }

    @Override
    public BoxStatus getBoxStatusById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(BoxStatus.class);
        criteria.add(Restrictions.eq("id_status", id));
        return (BoxStatus) criteria.uniqueResult();
    }

    @Override
    @Transactional(readOnly=true)
    public BoxStatus getBoxStatusByCode(String code) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(BoxStatus.class);
        criteria.add(Restrictions.eq("code", code));
        return (BoxStatus) criteria.uniqueResult();
    }

    @Override
    @Transactional(readOnly=true)
    public List<BoxStatus> getAllBoxStatuses(){
        return  sessionFactory.getCurrentSession().createQuery("from box_status с").list();
    }
}
