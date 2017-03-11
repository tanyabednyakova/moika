package io.khasang.moika.dao;

import io.khasang.moika.entity.ABaseMoikaTypeReference;
import io.khasang.moika.entity.BaseMoikaService;

import java.util.List;
/**
 * Базовый интерфейс DAO для всех справрчников типов чего-либо
 * @author Skvortsov Pavel
 *
 */
public interface BaseMoikaTypeDao<T extends ABaseMoikaTypeReference> extends IMoikaDaoCrud<T> {
     T getEntityByCode(String code) throws MoikaDaoException;
}
