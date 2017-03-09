package io.khasang.moika.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
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
     * Возвращает готовый к выполнению запрос сущностей указанного типа по единственному условию "поле=значение"
     *
     * @param entityClass класс запрашиваемых сущностей
     * @param fieldName   имя поля для запроса
     * @param value       значение поля для запроса
     * @return готовый к выполнению запрос
     */
    public <E> TypedQuery<E> getQueryOfEntityWithSoleEqualCondition(Class<E> entityClass, String fieldName, Object value) {

        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<E> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        ParameterExpression params;
        if (value == null) {
            params = criteriaBuilder.parameter(String.class);
        } else {
            params = criteriaBuilder.parameter(value.getClass());
        }
        criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), params));

        TypedQuery<E> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        query.setParameter(params, value);

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
     * @param bean - обновляемый объект
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
