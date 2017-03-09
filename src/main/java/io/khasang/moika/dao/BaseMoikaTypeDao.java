package io.khasang.moika.dao;

import io.khasang.moika.entity.ABaseMoikaTypeReference;
import io.khasang.moika.entity.BaseMoikaService;

import java.util.List;

public interface BaseMoikaTypeDao<T extends ABaseMoikaTypeReference> extends IMoikaDaoCrud<T> {
     T getEntityByCode(String code) throws MoikaDaoException;
}
