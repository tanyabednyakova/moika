package io.khasang.moika.dao;

import io.khasang.moika.entity.BaseMoikaService;

import java.util.List;

public interface BaseMoikaServiceDao {
    BaseMoikaService addEntity(BaseMoikaService entity) throws MoikaDaoException;    //Create
    BaseMoikaService getEntityById(int id)  throws MoikaDaoException;     //Read
    void updateEntity(BaseMoikaService entity)  throws MoikaDaoException;//Update
    void deleteEntity(BaseMoikaService entity)  throws MoikaDaoException; //Delete
    List<BaseMoikaService> getAllEntities()  throws MoikaDaoException;


}
