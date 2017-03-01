package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.UserDAO;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserById(long id) {
        return sessionFactory.getCurrentSession().get(User.class,id);
    }

    @Override
    public User getUserByLogin(String login) {
        return sessionFactory.getCurrentSession().createQuery("from User where User.login = :login",User.class).
                        setParameter("login",login).uniqueResult();
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User",User.class).list();
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        String sql = "from User join User.roles r where r.name = :role";
        return sessionFactory.getCurrentSession().createQuery(sql,User.class).setParameter("role",role.getName()).list();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
    @Override
    public boolean containUser(long id){
        long countUsers = sessionFactory.getCurrentSession()
                .createQuery("select count(User.id) from User where User.id=:id",Long.class)
                .setParameter("id",id).uniqueResult();
        return countUsers>0?true:false;
    }
    @Override
    public boolean containUserLogin(String login){
        long countUsers = sessionFactory.getCurrentSession()
                .createQuery("select count(User.id) from User where User.login=:login",Long.class)
                .setParameter("login",login).uniqueResult();
        return countUsers>0?true:false;
    }
}
