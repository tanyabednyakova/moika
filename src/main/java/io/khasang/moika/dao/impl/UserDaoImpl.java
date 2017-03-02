package io.khasang.moika.dao.impl;

import com.sun.istack.internal.NotNull;
import io.khasang.moika.dao.RoleDao;
import io.khasang.moika.dao.UserDao;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Базовая реализация DAO пользователей
 *
 * @since 2017-03-01
 * @author Rostislav Dublin
 */
@Service("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User findById(Long id) {
        return sessionFactory.getCurrentSession().byId(User.class).load(id);
    }

    @Override
    public User findByLogin(String login) {
        return sessionFactory.getCurrentSession().bySimpleNaturalId(User.class).load(login);
    }

    @Override
    public void createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void grantRole(@NotNull User user, @NotNull Role role) {

        user.getRoles().add(role);
        sessionFactory.getCurrentSession().update(user);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void revokeRole(User user, Role role) {
        user.getRoles().remove(role);
        sessionFactory.getCurrentSession().update(user);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> grantedAuthoritySet = user.getRoles().stream()
                .map(r -> roleDao.getAuthority(r))
                .collect(Collectors.toSet());

        return grantedAuthoritySet;
    }

    @Override
    public String getEncodedPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
