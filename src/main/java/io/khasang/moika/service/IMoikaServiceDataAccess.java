package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;

import java.util.List;

public interface IMoikaServiceDataAccess <T extends BaseMoikaService>{
    T addService(T entity) throws MoikaDaoException;    //Create
    T getServiceById(int id)  throws MoikaDaoException;     //Read
    void updateService(T entity)  throws MoikaDaoException;//Update
    void deleteService(T entity)  throws MoikaDaoException; //Delete
    List<T> getAllServices()  throws MoikaDaoException;
    List<T> getAllActualServices() throws MoikaDaoException;
}
