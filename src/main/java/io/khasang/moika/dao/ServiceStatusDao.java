package io.khasang.moika.dao;

import io.khasang.moika.entity.ServiceStatus;

import java.util.List;

public interface ServiceStatusDao {
    ServiceStatus addEntity(ServiceStatus entity) throws MoikaDaoException;    //Create
    ServiceStatus getEntityById(int id)  throws MoikaDaoException;     //Read
    ServiceStatus getEntityByCode(String Code)  throws MoikaDaoException;     //Read
    void updateEntity(ServiceStatus entity)  throws MoikaDaoException;//Update
    void deleteEntity(ServiceStatus entity)  throws MoikaDaoException; //Delete
    List<ServiceStatus> getAllEntities()  throws MoikaDaoException;

}
