package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;

import java.util.List;

public interface BaseMoikaServiceDataAccessService {
    BaseMoikaService addService(BaseMoikaService entity) throws MoikaDaoException;
    void updateService(BaseMoikaService entity) throws MoikaDaoException;
    void deleteService(BaseMoikaService entity) throws MoikaDaoException;
    BaseMoikaService getServiceByID(int id) throws MoikaDaoException;
    List<BaseMoikaService> getAllServices() throws MoikaDaoException;

}
