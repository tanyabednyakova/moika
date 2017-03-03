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
        return false;
    }

    @Override
    public void updateUser(User user) {
        return false;
    }

    @Override
    public boolean hasFreeUserLogin(String login) {
        return false;
    }

    @Override
    public boolean hasFreeUserEmail(String email) {
        return false;
    }

    private void checkUser(User user,  BindingResult bindingResult){
        bindingResult.getAllErrors().get(0).
        if(StringUtils.isEmpty(user.getPassword())){
            //return false;
        }

        //возможно стоит проверять так же содержимое через regex выражение
        if(StringUtils.isEmpty(user.getLogin())||!user.getLogin().matches("\\w")){
            //return false;
        }

        if(StringUtils.isEmpty(user.getFirstName())||!user.getFirstName().matches("^[A-Za-z]+|^[А-Яа-я]+")){
            //return false;
        }

    }

    @Override
    public String getEncodedPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
