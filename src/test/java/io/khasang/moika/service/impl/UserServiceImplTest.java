package io.khasang.moika.service.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.config_for_test_purposes.TestAppConfig;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.CarService;
import io.khasang.moika.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class UserServiceImplTest {

    static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    UserService userService;

    @Test
    public void performUserCRUD() throws Exception {
        String login = "admin";
        String phone = "9272170718";
        String email = "abcd@mail.ru";

        User user;

        //Delete if exists
        user = userService.findByLogin(login);
        if(user != null){
            userService.deleteUser(user);
            LOGGER.debug("Existed User deleted");
        }


        //Create
        user = new User();
        user.setLogin(login);
        user.setFirstName("Петруха");
        user.setLastName("Кулебякин");
        user.setEmail(email);
        user.setPassword("admin");
        user.setPhone(phone);
        userService.createUser(user);
        LOGGER.debug("New User created");

        long id = user.getId();
        Assert.assertTrue(id > 0);

        //Read by login
        user = null;
        user = userService.findByLogin(login);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(user.getId(), id);
        LOGGER.debug("User found by id");

        //Update
        String middleName = "Контрабасович";
        user.setMiddleName(middleName);
        userService.updateUser(user);
        LOGGER.debug("User updated by new middleName");

        //Read by email
        user = null;
        user = userService.findByEmail(email);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(user.getId(), id);

        Assert.assertEquals(user.getMiddleName(), middleName);
        LOGGER.debug("MiddleName checked after update");

//        //Delete
//        userService.deleteUser(user);
//        LOGGER.debug("User deleted");

//        //Read by id
//        user = null;
//        user = userService.findById(id);
//        Assert.assertNull(user);
//        LOGGER.debug("User absent");
    }

/*
    @Test
    public void deleteUser() throws Exception {
        User user =
    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void isLoginFree() throws Exception {

    }

    @Test
    public void isEmailFree() throws Exception {

    }

    @Test
    public void getEncodedPassword() throws Exception {

    }
*/

}