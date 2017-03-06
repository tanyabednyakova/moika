package io.khasang.moika.controller;

import io.khasang.moika.dao.RoleDAO;
import io.khasang.moika.dao.UserDAO;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Контроллер интерфейсов пользователя
 *
 * @author Rostislav Dublin
 * @since 2017-03-01
 */

@RequestMapping(path = "/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleDAO roleDAO;


    private User getCurrentUser() {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            return userService.findByLogin(currentLogin);

    }


    @RequestMapping("/createTestUser")
    public String createTestUser(Model model) {
        return createUser(model,
                "TestUser-" + DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss").format(LocalDateTime.now()),
                "123456Qw",
                "Тест",
                "Тестович",
                "Тестов",
                "test@mail.ru"
        );
    }


    @RequestMapping("/createUser")
    public String createUser(
            Model model,
            String login,
            String password,
            String firstName,
            String middleName,
            String lastName,
            String email

    ) {
        String result = "";
        if (StringUtils.isEmpty(login)) {
            result = "Error: UserName must be set";
        }
        if (StringUtils.isEmpty(password)) {
            result = "Error: Password must be set";
        }

        if (!result.startsWith("Error")) {

            User user = new User();

            user.setLogin(login);
            user.setPassword(password);

            user.setFirstName(firstName);
            user.setMiddleName(middleName);
            user.setLastName(lastName);
            user.setEmail(email);

            userService.createUser(user);

            result = "Success: user " + user.getLogin() + " created with ID " + user.getId();
        }
        model.addAttribute("result", result);
        return "manageUser";
    }


}
