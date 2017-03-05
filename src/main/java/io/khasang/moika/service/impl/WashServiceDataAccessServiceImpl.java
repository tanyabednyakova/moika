package io.khasang.moika.service.impl;


import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.ServiceDataAccessService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component(value = "washServiceDataAccessServiceImpl")
@Transactional
public class WashServiceDataAccessServiceImpl implements WashServiceDataAccessService {

    @Autowired
    private WashServiceDao washServiveDao;

    public WashServiceDataAccessServiceImpl() {
    }

    @Override
    public WashService addService(ABaseMoikaServiceAdditionalInfo service) throws MoikaDaoException {
        return washServiveDao.addEntity((WashService) service);
    }

    @Override
    public void updateService(ABaseMoikaServiceAdditionalInfo service) throws MoikaDaoException {
        washServiveDao.updateEntity((WashService) service);
    }

    @Override
    public void deleteService(ABaseMoikaServiceAdditionalInfo service) throws MoikaDaoException {
        washServiveDao.updateEntity((WashService) service);
    }


    @Override
    public WashService getServiceByID(int id) throws MoikaDaoException {
        return washServiveDao.getEntityById(id);
    }

    @Override
    public List getAllServices() throws MoikaDaoException {
        return washServiveDao.getAllEntities();
    }

}
