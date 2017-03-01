package io.khasang.moika.dao;

import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Интерфейс DAO ролей пользователей
 *
 * @since 2017-03-01
 * @author Rostislav Dublin
 */
public interface RoleDao {

    /**
     * Найти роль по названию
     * @param role
     * @return
     */
    Role findByName(String role);


    /**
     * Полномочие, определяемое ролью
     * @param role роль
     * @return полномочие
     *
     */
    public GrantedAuthority getAuthority(Role role);

}
