package io.khasang.moika.dao.impl;

import com.sun.istack.internal.NotNull;
import io.khasang.moika.dao.RoleDAO;
import io.khasang.moika.dao.UserDAO;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
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
 * @since 2017-03-01
 * @author Rostislav Dublin, Kovalev Aleksandr
 */
@Service("userDao")
@Transactional
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RoleDAO roleDAO;

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
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
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
        //sessionFactory.getCurrentSession().flush();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> grantedAuthoritySet = user.getRoles().stream()
                .map(r -> roleDAO.getAuthority(r))
                .collect(Collectors.toSet());

        return grantedAuthoritySet;
    }


    @Override
    public boolean containUser(long id) {
        long countUsers = sessionFactory.getCurrentSession()
                .createQuery("select count(User.id) from User where User.id=:id",Long.class)
                .setParameter("id",id).uniqueResult();
        return countUsers>0?true:false;
    }

    @Override
    public boolean containLoginUser(String login) {
        long countUsers = sessionFactory.getCurrentSession()
                .createQuery("select count(User.id) from User where User.login=:login",Long.class)
                .setParameter("login",login).uniqueResult();
        return countUsers>0?true:false;
    }

    @Override
    public boolean containLoginEmail(String email) {
        long countUsers = sessionFactory.getCurrentSession()
                .createQuery("select count(User.id) from User where User.email=:email",Long.class)
                .setParameter("email",email).uniqueResult();
        return countUsers>0?true:false;
    }
}
