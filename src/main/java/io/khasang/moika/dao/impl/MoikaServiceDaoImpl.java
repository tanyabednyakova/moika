package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.BaseMoikaConcreatServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.MoikaServiceDao;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;
import io.khasang.moika.entity.IBaseMoikaServiceAddInfo;
import io.khasang.moika.entity.MoikaService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("moikaServiceDao")
public class MoikaServiceDaoImpl extends MoikaDaoCrudImpl<MoikaService> implements MoikaServiceDao {

    @Autowired
    private MoikaServiceAddInfoDaoFabrica moikaServiceAddInfoDaoFabrica;

    public MoikaServiceDaoImpl() {
    }

    @Override
    public MoikaService delete(MoikaService entity) throws MoikaDaoException {
        List<? extends IBaseMoikaServiceAddInfo> addServiceInfoList = entity.getServiceAddInfo();
        for (IBaseMoikaServiceAddInfo child : addServiceInfoList) {
            BaseMoikaConcreatServiceDao concreatServiceDao = moikaServiceAddInfoDaoFabrica.
                    getMoikaConcreatServiceDao(entity.getId(), entity.getTypeCode());
            concreatServiceDao.delete((ABaseMoikaServiceAdditionalInfo) child);
        }
        getCurrentSession().delete(entity);
        //DRS session.flush();
        return entity;
    }

    @Override
    public MoikaService get(long id) throws MoikaDaoException {
        MoikaService entity = getCurrentSession().get(daoType, id);
        entity.setServiceAddInfo(moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode()));
        return getCurrentSession().get(daoType, id);
    }

    @Override
    public List<MoikaService> getAll() throws MoikaDaoException {
        List<MoikaService> services = super.getAll();//dataAccessUtil.getQueryOfEntity(MoikaService.class).getResultList();
        for (MoikaService entity : services) {
            List<IBaseMoikaServiceAddInfo> list = moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode());
            entity.setServiceAddInfo(list);
        }
        return services;
    }


    @Override
    public List<MoikaService> getServicesByStatus(int idStatus) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(daoType);
        criteria.add(Restrictions.eq("idStatus", 0));
        List<MoikaService> services = session.createCriteria(daoType).list();
        for (MoikaService entity : services) {
            entity.setServiceAddInfo(moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode()));
        }
        return services;
    }

    @Override
    public List<MoikaService> getServicesByType(int idType) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(daoType);
        criteria.add(Restrictions.eq("idType", 0));
        List<MoikaService> services = session.createCriteria(daoType).list();
        for (MoikaService entity : services) {
            entity.setServiceAddInfo(moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode()));
        }
        return services;
    }

    @Override
    public List<MoikaService> getActualServices() throws MoikaDaoException {
        return getServicesByStatus(0);
    }
}
