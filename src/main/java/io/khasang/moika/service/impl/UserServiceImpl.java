package io.khasang.moika.service.impl;

import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public void removeUser(long id) {
        //TODO
    }

    @Override
    public User updateUser(User user) {
        //TODO
        return null;
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
