package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.RoleDAO;
import io.khasang.moika.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Базовая реализация DAO ролей пользователей
 *
 * @since 2017-03-01
 * @author Rostislav Dublin
 */
@Service("roleDao")
@Transactional
public class RoleDAOImpl implements RoleDAO {
    public RoleDAOImpl() {

    }

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public GrantedAuthority getAuthority(Role role) {
        return  new SimpleGrantedAuthority(role.getName());
    }
}
