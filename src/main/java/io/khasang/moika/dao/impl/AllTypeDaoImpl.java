package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BaseMoikaTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaTypeReference;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Базовый абстрактный класс для всех стправочников типов (xxx_type)
 *
 * @param <T> наследники ABaseMoikaTypeReference
 */

@Transactional
public abstract class AllTypeDaoImpl<T extends ABaseMoikaTypeReference> extends MoikaDaoCrudImpl<T> implements BaseMoikaTypeDao<T> {
    protected SessionFactory sessionFactory;


    @Override
    public T getEntityByCode(String code) throws MoikaDaoException {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(daoType);
        criteria.add(Restrictions.eq("code", code));
        return (T) criteria.uniqueResult();
    }


}
