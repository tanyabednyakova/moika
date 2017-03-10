package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.IMoikaDaoCrud;
import io.khasang.moika.dao.WashFacilityDao;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("washFacilityDao")
public class WashFacilityDaoImpl extends MoikaDaoCrudImpl<WashFacility> implements WashFacilityDao {

    @Override
    public List<WashFacility> getWashFacilitiesOnNet(int idNet) {
        return null;
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(WashFacility washFacility) {
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where idFacility = ?");
        query.setParameter(0, washFacility.getId());
        return query.list();
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFacility){
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where idFacility = ?");
        query.setParameter(0, idFacility);
        return query.list();
    }
}
