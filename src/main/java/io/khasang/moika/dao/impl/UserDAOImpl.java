package io.khasang.moika.dao.impl;

import com.sun.istack.internal.NotNull;
import io.khasang.moika.dao.RoleDAO;
import io.khasang.moika.dao.UserDAO;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import io.khasang.moika.util.DataAccessUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Базовая реализация DAO пользователей
 *
 * @author Rostislav Dublin, Kovalev Aleksandr
 * @since 2017-03-01
 */
@Service("userDao")
@Transactional
public class UserDAOImpl implements UserDAO {
    @Autowired
    private DataAccessUtil dataAccessUtil;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public User findById(Long id) {
        return getCurrentSession().byId(User.class).load(id);
    }

    @Override
    public User findByLogin(String login) {
        return getCurrentSession().bySimpleNaturalId(User.class).load(login);
    }

    @Override
    public User findByEmail(String email) {
        return dataAccessUtil.getQueryOfEntityWithSoleEqualCondition(User.class, "email", email).getSingleResult();
    }

    @Override
    public User createUser(@NotNull User user) {
        getCurrentSession().save(user);
        return user;
    }

    @Override
    public User updateUser(@NotNull User user) {
        getCurrentSession().update(user);
        return user;
    }
    @Override
    public void deleteUser(@NotNull User user) {
        getCurrentSession().delete(user);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void grantRole(@NotNull User user, @NotNull Role role) {
        user.getRoles().add(role);
        getCurrentSession().update(user);
    }

    @Override
    public void revokeRole(@NotNull User user, @NotNull Role role) {
        user.getRoles().remove(role);
        getCurrentSession().update(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(@NotNull User user) {
        Set<GrantedAuthority> grantedAuthoritySet = user.getRoles().stream()
                .map(r -> roleDAO.getAuthority(r))
                .collect(Collectors.toSet());

        return grantedAuthoritySet;
    }

}