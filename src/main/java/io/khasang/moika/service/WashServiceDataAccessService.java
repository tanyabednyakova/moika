package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.WashService;

import java.util.List;


public interface WashServiceDataAccessService {
        WashService addService(WashService service) throws MoikaDaoException;
        void updateService(WashService service) throws MoikaDaoException;
        void deleteService(WashService service) throws MoikaDaoException;
        WashService getServiceByID(int id) throws MoikaDaoException;
        List<WashService> getAllServices() throws MoikaDaoException;
}
