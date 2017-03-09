package io.khasang.moika.dao;

import io.khasang.moika.entity.BaseMoikaService;

import java.util.List;

public interface BaseMoikaServiceDao<T extends BaseMoikaService> extends IMoikaDaoCrud<T> {
     List<T> getAllActualServices() throws MoikaDaoException;
}
