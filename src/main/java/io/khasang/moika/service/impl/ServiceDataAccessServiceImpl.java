package io.khasang.moika.service.impl;


import io.khasang.moika.dao.AllServicesDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.MoikaAllService;
import io.khasang.moika.service.ServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("serviceDataAccessServiceImpl")
public class ServiceDataAccessServiceImpl implements ServiceDataAccessService {

    @Autowired
    @Qualifier("allServiceDao")
    private AllServicesDao allServicesDao;

    public ServiceDataAccessServiceImpl() {
    }

    @Override
    public MoikaAllService addService(BaseMoikaService entity) throws MoikaDaoException {
        return allServicesDao.addEntity((MoikaAllService) entity);
    }

    @Override
    public MoikaAllService getServiceByID(int id) throws MoikaDaoException {
        return  allServicesDao.getEntityById(id);
    }

    @Override
    public void updateService(BaseMoikaService entity) throws MoikaDaoException {
        allServicesDao.updateEntity((MoikaAllService) entity);
    }

    @Override
    public void deleteService(BaseMoikaService entity) throws MoikaDaoException {
        allServicesDao.updateEntity((MoikaAllService) entity);
    }

    @Override
    public List getAllServices() throws MoikaDaoException {
        return allServicesDao.getAllEntities();
    }
}
