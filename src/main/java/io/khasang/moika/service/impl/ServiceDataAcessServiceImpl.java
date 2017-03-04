package io.khasang.moika.service.impl;


import io.khasang.moika.dao.AllServicesDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;
import io.khasang.moika.entity.MoikaAllService;
import io.khasang.moika.service.ServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ServiceDataAcessServiceImpl")
@Transactional
public abstract class ServiceDataAcessServiceImpl implements ServiceDataAccessService {

    @Autowired
    private AllServicesDao allServicesDao;


    public ServiceDataAcessServiceImpl() {
    }


    @Override
    public void addEntity(ABaseMoikaServiceAdditionalInfo entity) throws MoikaDaoException {
        allServicesDao.addEntity((MoikaAllService) entity);
    }

    @Override
    public MoikaAllService getEntityById(int id) throws MoikaDaoException {
        return allServicesDao.getEntityById(id);
    }

    @Override
    public void updateEntity(ABaseMoikaServiceAdditionalInfo entity) throws MoikaDaoException {
        allServicesDao.updateEntity((MoikaAllService) entity);
    }

    @Override
    public void deleteEntity(ABaseMoikaServiceAdditionalInfo entity) throws MoikaDaoException {
        allServicesDao.updateEntity((MoikaAllService) entity);
    }

    @Override
    public List getAllEntities() throws MoikaDaoException {
        return allServicesDao.getAllEntities();
    }
}
