package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.service.BaseMoikaServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "baseMoikaServiceDataAccessService")
@Transactional
public class BaseMoikaServiceDataAccessServiceImpl implements BaseMoikaServiceDataAccessService {
    @Autowired
    BaseMoikaServiceDao baseMoikaServiceDao;

    public BaseMoikaServiceDataAccessServiceImpl() {
    }

    @Override
    public BaseMoikaService addService(BaseMoikaService entity) throws MoikaDaoException {
        return baseMoikaServiceDao.addEntity((BaseMoikaService) entity);
    }

    @Override
    public void updateService(BaseMoikaService entity) throws MoikaDaoException {
            baseMoikaServiceDao.updateEntity((BaseMoikaService) entity);
    }

    @Override
    public void deleteService(BaseMoikaService entity) throws MoikaDaoException {
        baseMoikaServiceDao.deleteEntity((BaseMoikaService) entity);
    }

    @Override
    public BaseMoikaService getServiceByID(int id) throws MoikaDaoException {
        return baseMoikaServiceDao.getEntityById(id);
    }

    @Override
    public List<BaseMoikaService> getAllServices() throws MoikaDaoException {
        return baseMoikaServiceDao.getAllEntities();
    }
}
