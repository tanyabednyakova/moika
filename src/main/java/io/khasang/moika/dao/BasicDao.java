package io.khasang.moika.dao;

import io.khasang.moika.entity.Car;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

/**
 * Общие методы, полезные для любого DAO-наследника
 *
 * @author Rostislav Dublin
 * @since 2017-03-09
 */
public interface BasicDao<T> {

    /**
     * @return текущая Hibernate-сессия
     */
    Session getCurrentSession();

    /**
     * Создаёт сущность в БД
     *
     * @param entity создаваемая сущность
     * @return созданная сущность
     */
    T create(T entity);

    /**
     * Возвращает экземпляр сущности указанного типа по её Id
     *
     * @param id    id
     * @return экземпляр сущности
     */
    T getById(long id);

    /**
     *
     * @return полный список сущностей
     */
    List<T> getList();

    /**
     * Обновляет имеющуюся сущность в БД
     *
     * @param entity сущность
     * @return обновлённая сущность
     */
    T update(T entity);

    /**
     * Обновляет имеющуюся сущность в БД
     *
     * @param id    id обновялемой сущности
     * @param fieldValueMap - карта поле->новое значение
     * @return обновлённая сущность
     */
    T updateById(long id, Map<String, Object> fieldValueMap);

    /**
     * Удаляет имеющуюся сущность из БД
     * @param entity удаляемая сущность
     * @return удалённая сущность
     */
    T delete(T entity);
}
