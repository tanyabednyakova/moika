package io.khasang.moika.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Утилиты для удобного доступа к данным через Hibernate
 *
 * @author Rostislav Dublin
 * @since 2017-03-05
 */
@Service("dataAccessUtil")
public class DataAccessUtil {

    private final SessionFactory sessionFactory;

    @Autowired
    public DataAccessUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Возвращает готовый к выполнению запрос всех сущностей указанного типа
     *
     * @param entityClass класс запрашиваемых сущностей
     * @return готовый к выполнению запрос
     */
    public <E> TypedQuery<E> getQueryOfEntity(Class<E> entityClass) {

        return getQueryOfEntityWithComplexEqualCondition(entityClass, null);
    }


    /**
     * Возвращает готовый к выполнению запрос сущностей указанного типа по единственному условию "поле=значение"
     *
     * @param entityClass класс запрашиваемых сущностей
     * @param fieldName   имя поля для запроса
     * @param value       значение поля для запроса
     * @return готовый к выполнению запрос
     */
    public <E> TypedQuery<E> getQueryOfEntityWithSoleEqualCondition(Class<E> entityClass, String fieldName, Object value) {

        return getQueryOfEntityWithComplexEqualCondition(entityClass, Collections.singletonMap(fieldName, value));
    }

    /**
     * Возвращает готовый к выполнению запрос сущностей указанного типа по сборному условию "поле=значение И поле=значение..."
     *
     * @param entityClass   класс запрашиваемых сущностей
     * @param fieldValueMap имя поля для запроса
     * @return готовый к выполнению запрос
     */
    public <E> TypedQuery<E> getQueryOfEntityWithComplexEqualCondition(Class<E> entityClass, Map<String, Object> fieldValueMap) {

        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = builder.createQuery(entityClass);
        Root<E> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        TypedQuery<E> query;
        if (fieldValueMap != null && !fieldValueMap.isEmpty()) {
            Predicate where = null;
            List<Object> values = new ArrayList<>();

            for (Map.Entry<String, Object> attr : fieldValueMap.entrySet()) {
                Object value = attr.getValue();

                ParameterExpression params;
                if (value == null) {
                    params = builder.parameter(String.class);
                } else {
                    params = builder.parameter(value.getClass());
                }
                if(where==null){
                    where = builder.equal(root.get(attr.getKey()), params);
                }else{
                    where = builder.and(where, builder.equal(root.get(attr.getKey()), params));
                }
                values.add(attr.getValue());
            }
            criteriaQuery.where(where);
            query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
            for (int i = 0; i < values.size(); i++) {
                query.setParameter("param"+i, values.get(i));
            }

        } else {
            query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        }

        return query;
    }

    /**
     * Возвращает экземпляр сущности указанного типа по её Id
     *
     * @param clazz класс сущности
     * @param id    id
     * @return экземпляр сущности
     */
    public <T> T getEntityById(Class<T> clazz, long id) {
        return sessionFactory.getCurrentSession().get(clazz, id);
    }


    /**
     * Обновляет экземпляр объекта новыми значениями указанных полей. Валидирует объект при обновлении.
     *
     * @param bean          - обновляемый объект
     * @param fieldValueMap - карта поле->новое значение
     * @return обновлённый объект
     */
    public <T> T setNewValuesToBean(@NotNull T bean, @NotNull Map<String, Object> fieldValueMap) {
        BeanWrapperImpl bw = new BeanWrapperImpl();
        bw.setWrappedInstance(bean);

        bw.setPropertyValues(fieldValueMap);
        return bean;
    }
}