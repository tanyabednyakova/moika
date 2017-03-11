package io.khasang.moika.service;

/**
 * Реализация Service пользователей
 *
 * @author Kovalev Aleksandr
 * @since 2017-03-02
 */

import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * Найти пользователя по ID
     *
     * @param id ID искомого пользователя
     * @return пользователь
     */
    User findById(Long id);

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
     * Создать учетную запись пользователя
     *
     * @param user - Данные в объекте user
     */
    User createUser(User user);

    /**
     * Удалить учетную запись пользователя
     *
     * @param user пользователь
     */
    void deleteUser(User user);

    /**
     * Обновить поля учетной записи пользователя
     *
     * @param user - Данные в объекте user
     */
    User updateUser(User user);

    /**
     * Проверить является ли данный логин пользователя свободным
     *
     * @param login - Логин пользолвателя
     * @return возвращает булевое значение, true - если данный логин свободен, false - если нет
     */
    boolean isLoginFree(String login);

    /**
     * Проверить является ли данный email пользователя свободным
     *
     * @param email - Логин пользолвателя
     * @return возвращает булевое значение, true - если данный email свободен, false - если нет
     */
    boolean isEmailFree(String email);
    /**
     * Вернуть закодированную версию исходного пароля.
     *
     * @param rawPassword незакодированный пароль
     * @return закодированный пароль
     */
    String getEncodedPassword(String rawPassword);

    void grantRole(User user, Role role);

    void revokeRole(User user, Role role);

}
