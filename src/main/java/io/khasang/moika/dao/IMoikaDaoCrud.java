package io.khasang.moika.dao;

import io.khasang.moika.entity.ABaseMoikaEntity;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;
import io.khasang.moika.entity.BaseMoikaService;

import java.util.List;

public interface IMoikaDaoCrud<T extends ABaseMoikaEntity> {
/**
 *
 */
    T addEntity(T entity) throws MoikaDaoException;    //Create
    T getEntityById(int id)  throws MoikaDaoException;     //Read
    void updateEntity(T entity)  throws MoikaDaoException;//Update
    void deleteEntity(T entity)  throws MoikaDaoException; //Delete
    List<T> getAllEntities()  throws MoikaDaoException;
}
