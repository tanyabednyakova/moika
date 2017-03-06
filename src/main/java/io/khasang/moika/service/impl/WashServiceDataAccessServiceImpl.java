package io.khasang.moika.service.impl;


import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.impl.WashServiceDaoImpl;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "washServiceDataAccessServiceImpl")
@Transactional
public class WashServiceDataAccessServiceImpl implements WashServiceDataAccessService {

    @Autowired
    @Qualifier("washServiceDaoImpl")
    private WashServiceDaoImpl washServiceDao;

    public WashServiceDataAccessServiceImpl() {
    }

    @Override
    public WashService addService(WashService service) throws MoikaDaoException {
        return washServiceDao.addEntity((WashService) service);
    }

    @Override
    public void updateService(WashService service) throws MoikaDaoException {
        washServiceDao.updateEntity((WashService) service);
    }

    @Override
    public void deleteService(WashService service) throws MoikaDaoException {
        washServiceDao.updateEntity((WashService) service);
    }


    @Override
    public WashService getServiceByID(int id) throws MoikaDaoException {
        return washServiceDao.getEntityById(id);
    }

    @Override
    public List getAllServices() throws MoikaDaoException {
        return washServiceDao.getAllEntities();
    }

}
