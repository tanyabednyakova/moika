package io.khasang.moika.dao.impl;

import com.sun.istack.internal.NotNull;
import io.khasang.moika.dao.RoleDao;
import io.khasang.moika.dao.UserDao;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Базовая реализация DAO ролей пользователей
 *
 * @since 2017-03-01
 * @author Rostislav Dublin
 */
@Service("roleDao")
@Transactional
public class RoleDaoImpl implements RoleDao {

    @Override
    public Role findByName(String role) {
        return null;
    }

    @Override
    public GrantedAuthority getAuthority(Role role) {
        return  new SimpleGrantedAuthority(role.getName());
    }
}
