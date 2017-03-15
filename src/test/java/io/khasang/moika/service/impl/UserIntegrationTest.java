package io.khasang.moika.service.impl;


import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class UserIntegrationTest {

    static final Logger logger = LoggerFactory.getLogger(UserIntegrationTest.class);

    @Autowired
    UserService userService;

    @Ignore
    @Test
    public void createUser() {

        String login = "assistant";
        String phone = "9272170718";
        String email = "abcd@mail.ru";

        User user;

        //Delete if exists
        user = userService.findByLogin(login);
        if (user != null) {
            userService.deleteUser(user);
            logger.debug("Existed User deleted");
        }


        //Create
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        user = new User();
        user.setLogin(login);
        user.setFirstName("Петруха");
        user.setLastName("Кулебякин");
        user.setEmail(email);
        user.setPassword("123456Qw");
        user.setPhone(phone);

        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> resultMap = restTemplate
                .exchange("http://localhost:8080/user/reg",
                        HttpMethod.POST,
                        httpEntity,
                        new ParameterizedTypeReference<Map<String, Object>>() {
                        })
                .getBody();

        Assert.assertNotNull(resultMap);
        Assert.assertTrue(resultMap.containsKey("success"));

        List<User> resultList = restTemplate.exchange(
                "http://localhost:8080/user/admin",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        ).getBody();

        Assert.assertNotNull(resultList);
        Assert.assertNotEquals(0, resultList.size());

        long currentId = resultList.stream().filter((x) -> login.equals(x.getLogin())).map(User::getId)
                .findAny().orElse(0L);
        Assert.assertNotEquals(0, currentId);

        User resultUser = restTemplate.exchange(
                "http://localhost:8080/user/admin/{id}",
                HttpMethod.GET,
                null,
                User.class,
                currentId
        ).getBody();

        Assert.assertNotNull(resultUser);
        Assert.assertEquals(resultUser.getPhone(), phone);

        logger.debug("New User created");
    }

    @Test
    public void updateUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                "http://localhost:8080/User/{id}",
                HttpMethod.GET,
                null,
                User.class,
                37
        );
        User resultUser = responseEntity.getBody();
        Assert.assertNotNull(resultUser);

        HttpEntity<User> httpEntity = new HttpEntity<>(resultUser, headers);
        resultUser.setLastName("Копыта и рога");
        User resultUpdUser = restTemplate.exchange
                ("http://localhost:8080/User/update",
                        HttpMethod.PUT,
                        httpEntity,
                        User.class)
                .getBody();

        Assert.assertNotNull(resultUpdUser);
        Assert.assertEquals("Копыта и рога", resultUpdUser.getLastName());
        Assert.assertNotNull(resultUpdUser.getId());
    }


}
