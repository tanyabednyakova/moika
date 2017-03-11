package io.khasang.moika.dao;

import io.khasang.moika.entity.ABaseMoikaEntity;

import java.util.List;

/**
 * БАЗОВЫЙ Интерфейс DAO для CRUD операций
 * @author Skvortsov Pavel
 *
 */
public interface IMoikaDaoCrud<T extends ABaseMoikaEntity> {
    T addEntity(T entity) throws MoikaDaoException;    //Create
    T getEntityById(int id)  throws MoikaDaoException;     //Read
    void updateEntity(T entity)  throws MoikaDaoException;//Update
    void deleteEntity(T entity)  throws MoikaDaoException; //Delete
    List<T> getAllEntities()  throws MoikaDaoException;
}
