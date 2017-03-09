package io.khasang.moika.service.impl;

import io.khasang.moika.dao.RoleDAO;
import io.khasang.moika.dao.UserDAO;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public User findById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        return userDAO.createUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User updateUser(User user) {
        userDAO.updateUser(user);
        return user;
    }

    @Override
    public boolean isLoginFree(String login) {
        return userDAO.findByLogin(login) != null;
    }

    @Override
    public boolean isEmailFree(String email) {
        return userDAO.findByEmail(email) != null;
    }

    @Override
    public String getEncodedPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
