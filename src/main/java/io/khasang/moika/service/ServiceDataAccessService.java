package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;

import java.util.List;


public interface ServiceDataAccessService<T extends BaseMoikaService> {
        T addService(T service) throws MoikaDaoException;
        void updateService(T service) throws MoikaDaoException;
        void deleteService(T service) throws MoikaDaoException;
        T getServiceByID(int id) throws MoikaDaoException;
        List<T> getAllServices() throws MoikaDaoException;
}
