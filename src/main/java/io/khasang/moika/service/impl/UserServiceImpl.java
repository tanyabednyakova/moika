package io.khasang.moika.service.impl;

import io.khasang.moika.dao.RoleDao;
import io.khasang.moika.dao.UserDao;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.HashSet;

public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public boolean createUser(User user, boolean isAdmin) {
        //данная проверка возможно необязательна
        if(user.getRoles()==null){
            user.setRoles(new HashSet<>());
        }
        if(!isAdmin){
            if(user.getRoles().size()==0){
                Role role = roleDao.findByName("Client");
                user.getRoles().add(role);
            }
            if(StringUtils.isEmpty(user.getPassword())){
                return false;
            }
        }
        //возможно стоит проверять так же содержимое через regex выражение
        if(StringUtils.isEmpty(user.getLogin())){
            return false;
        }

        if(StringUtils.isEmpty(user.getFirstName())||user.getFirstName().matches("^[A-Za-z]+|^[А-Яа-я]+")){
            return false;
        }



        return true;

    }

    @Override
    public boolean removeUser(long id) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
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

    @Override
    public String getEncodedPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
