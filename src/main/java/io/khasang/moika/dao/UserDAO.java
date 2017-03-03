package io.khasang.moika.dao;

import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


/**
 * Интерфейс DAO пользователей
 *
 * @since 2017-03-01
 * @author Rostislav Dublin, Kovalev Aleksandr
 */

public interface UserDAO {

    /**
     * Найти пользователя по ID
     * @param id ID искомого пользователя
     * @return пользователь
     */
    User findById(Long id);

    /**
     * Найти пользователя по логину
     * @param login ID искомого пользователя
     * @return пользователь
     */

    User findByLogin(String login);

    /**
     * Создать пользователя
     * @param user данные пользователя (поле id игнорируется)
     */
    void createUser(User user);

    /**
     * Обновить поля пользователя
     * @param user данные пользователя
     */
    void updateUser(User user);

    /**
     * Наделить пользователя ролью
     * @param user пользователь
     * @param role роль
     */
    void grantRole(User user, Role role);

    /**
     * Отозвать роль у пользователя
     * @param user пользователь
     * @param role роль
     */
    void revokeRole(User user, Role role);

    /**
     * Полномочия пользователя, определяемые имеющимися ролями
     * @param user пользователь
     * @return Полномочия пользователя
     *
     */
    Collection<? extends GrantedAuthority> getAuthorities(User user);

    /**
     * Проверить наличие пользователя с таким id
     * @param id
     * @return true, если содержиться, false если нет
     */
    boolean containUser(long id);

    /**
     * Проверить наличие пользователя с таким логином
     * @param login
     * @return true, если содержиться, false если нет
     */
    boolean containLoginUser(String login);

    /**
     * Проверить наличие пользователя с таким email
     * @param email
     * @return true, если содержиться, false если нет
     */
    boolean containLoginEmail(String email);

}
