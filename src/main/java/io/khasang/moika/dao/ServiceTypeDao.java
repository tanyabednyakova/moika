package io.khasang.moika.dao;

import io.khasang.moika.entity.ServiceType;

import java.util.List;

public interface ServiceTypeDao {
    ServiceType addEntity(ServiceType entity) throws MoikaDaoException;    //Create
    ServiceType getEntityById(int id)  throws MoikaDaoException;     //Read
    ServiceType getEntityByCode(String code)  throws MoikaDaoException;     //Read
    void updateEntity(ServiceType entity)  throws MoikaDaoException;//Update
    void deleteEntity(ServiceType entity)  throws MoikaDaoException; //Delete
    List<ServiceType> getAllEntities()  throws MoikaDaoException;

}
