package io.khasang.moika.service.impl;


import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.ServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("ServiceDataAcessServiceImpl")
@Transactional
public abstract class WashServiceDataAcessServiceImpl implements ServiceDataAccessService {

    @Autowired
    private WashServiceDao washServiveDao;


    public WashServiceDataAcessServiceImpl() {
    }
    

    @Override
    public void addEntity(ABaseMoikaServiceAdditionalInfo entity) throws MoikaDaoException {
        washServiveDao.addEntity((WashService) entity);
    }

    @Override
    public WashService getEntityById(int id) throws MoikaDaoException {
        return washServiveDao.getEntityById(id);
    }

    @Override
    public void updateEntity(ABaseMoikaServiceAdditionalInfo entity) throws MoikaDaoException {
        washServiveDao.updateEntity((WashService) entity);
    }

    @Override
    public void deleteEntity(ABaseMoikaServiceAdditionalInfo entity) throws MoikaDaoException {
        washServiveDao.updateEntity((WashService) entity);
    }

    @Override
    public List getAllEntities() throws MoikaDaoException {
        return washServiveDao.getAllEntities();
    }
}
