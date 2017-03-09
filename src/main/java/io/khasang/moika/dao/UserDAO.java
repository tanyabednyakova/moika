package io.khasang.moika.dao;

import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


/**
 * Интерфейс DAO пользователей
 *
 * @author Rostislav Dublin, Kovalev Aleksandr
 * @since 2017-03-01
 */

public interface UserDAO extends BasicDao<User>{

    /**
     * Найти пользователя по логину
     *
     * @param login ID искомого пользователя
     * @return пользователь
     */

    User findByLogin(String login);

    /**
     * Найти прользователя по Email
     *
     * @param email адрес электронной почты
     * @return пользователь
     */
    User findByEmail(String email);

    /**
     * Создать пользователя
     *
     * @param user данные пользователя (поле id игнорируется)
     */
    User createUser(User user);

    /**
     * Удалить пользователя
     *
     * @param user пользователь
     */
    void deleteUser(User user);

    /**
     * Обновить поля пользователя
     *
     * @param user данные пользователя
     */
    User updateUser(User user);

    /**
     * Наделить пользователя ролью
     *
     * @param user пользователь
     * @param role роль
     */
    void grantRole(User user, Role role);

    /**
     * Отозвать роль у пользователя
     *
     * @param user пользователь
     * @param role роль
     */
    void revokeRole(User user, Role role);

    /**
     * Полномочия пользователя, определяемые имеющимися ролями
     *
     * @param user пользователь
     * @return Полномочия пользователя
     */
    Collection<? extends GrantedAuthority> getAuthorities(User user);
}
