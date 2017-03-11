package io.khasang.moika.dao;

import io.khasang.moika.entity.BaseMoikaService;

import java.util.List;
/**
 * Базовый интерфейс DAO для всех услуг
 * @author Skvortsov Pavel
 *
 */
public interface BaseMoikaServiceDao<T extends BaseMoikaService> extends IMoikaDaoCrud<T> {
     List<T> getAllActualServices() throws MoikaDaoException;
}
