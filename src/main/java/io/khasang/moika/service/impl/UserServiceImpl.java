package io.khasang.moika.service.impl;

import io.khasang.moika.dao.RoleDAO;
import io.khasang.moika.dao.UserDAO;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public void createUser(User user) {

    }

    @Override
    public void removeUser(long id) {
        //TODO
    }

    @Override
    public void updateUser(User user) {
        //TODO
    }

    @Override
    public boolean hasFreeUserLogin(String login) {
        return false;
    }

    @Override
    public boolean hasFreeUserEmail(String email) {
        return false;
    }

    @Override
    public String getEncodedPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
