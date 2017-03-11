package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.BasicDao;
import io.khasang.moika.util.DataAccessUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public abstract class BasicDaoImpl<T> implements BasicDao<T> {

    private final Class<T> entityClass;

    @Autowired
    protected DataAccessUtil dataAccessUtil;
    @Autowired
    protected SessionFactory sessionFactory;

    protected BasicDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T create(T entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public T getById(long id) {
        return getCurrentSession().get(entityClass, id);
    }

    @Override
    public T update(T entity) {
        getCurrentSession().update(entity);
        return entity;
    }

    @Override
    public List<T> getList() {
        return dataAccessUtil.getQueryOfEntity(entityClass).getResultList();
    }

    @Override
    public T updateById(long id, Map<String, Object> fieldValueMap) {
        T entity = getById(id);
        dataAccessUtil.setNewValuesToBean(entity, fieldValueMap);
        return update(entity);
    }

    @Override
    public T delete(T entity) {
        getCurrentSession().delete(entity);
        return entity;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
