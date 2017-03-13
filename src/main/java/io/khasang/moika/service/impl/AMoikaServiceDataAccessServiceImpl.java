package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.service.IMoikaServiceDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public abstract class AMoikaServiceDataAccessServiceImpl<T extends BaseMoikaService> implements IMoikaServiceDataAccess<T> {
    @Autowired
    BaseMoikaServiceDao<T> baseMoikaServiceDao;


    public AMoikaServiceDataAccessServiceImpl(BaseMoikaServiceDao<T> baseMoikaServiceDao) {
        this.baseMoikaServiceDao = baseMoikaServiceDao;
    }

    public AMoikaServiceDataAccessServiceImpl() {
    }

    @Override
    public T addService(T service) throws MoikaDaoException {
        return baseMoikaServiceDao.create(service);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T getServiceById(int id) throws MoikaDaoException {
        return baseMoikaServiceDao.get(id);
    }

    @Override
    public void updateService(T service) throws MoikaDaoException {
        baseMoikaServiceDao.update(service);
    }

    @Override
    public void deleteService(T service) throws MoikaDaoException {
        baseMoikaServiceDao.delete(service);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> getAllServices() throws MoikaDaoException {
        return baseMoikaServiceDao.getAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> getAllActualServices() throws MoikaDaoException {
        return baseMoikaServiceDao.getAllActualServices();
    }

}
