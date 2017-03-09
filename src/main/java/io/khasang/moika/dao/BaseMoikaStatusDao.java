package io.khasang.moika.dao;

import io.khasang.moika.entity.ABaseMoikaStatusReference;

import java.util.List;

public interface BaseMoikaStatusDao<T extends ABaseMoikaStatusReference> extends IMoikaDaoCrud<T> {
      T getEntityByCode(String code) throws MoikaDaoException;
}
