package io.khasang.moika.controller;

import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import io.khasang.moika.util.BindingResultToMapParser;
import io.khasang.moika.util.DataAccessUtil;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Контроллер интерфейсов пользователя
 *
 * @author Rostislav Dublin, Kovalev Aleksandr
 * @since 2017-03-01
 */

@RequestMapping(path = "/user")
@Controller
public class UserController {
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private UserService userService;

   @Autowired
   private Validator mvcValidator;

   @Autowired
   private DataAccessUtil dataAccessUtil;


    private User getCurrentUser() {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            return userService.findByLogin(currentLogin);

    }


/*    @RequestMapping("/createTestUser")
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
*/

    @RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object createUser(@RequestBody @Valid User user, BindingResult result) {
        if(result.hasErrors()){
            return BindingResultToMapParser.getMap(result);
        }
        userService.createUser(user);
        return BindingResultToMapParser.getSuccess("All good!!! =)");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object loginUser(@RequestBody User user) {
        //возвращаем null если пользователь уже залогирован
        if(getCurrentUser()!=null){
            return null;
        }
        //TODO проверить логику входа
        try {
            AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
            Authentication request = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return new Pair<>("redirect","/") ;//TODO добавить актуальную ссылку
        } catch(Exception e) {
            return new Pair<>("error","Authentication failed: " + e.getMessage());
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateUser(@RequestBody Map<String,Object> user, BindingResult result) {
        User currentUser = getCurrentUser();
        if(user.containsKey("password")&&currentUser.getPassword().equals(user.get("password"))){
            user.replace("password", userService.getEncodedPassword(user.get("password").toString()));
        }
        dataAccessUtil.setNewValuesToBean(currentUser,user);
        mvcValidator.validate(currentUser,result);
        if(result.hasErrors()){
            return BindingResultToMapParser.getMap(result);
        }
        userService.createUser(currentUser);
        return BindingResultToMapParser.getSuccess("All good!!! =)");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object loginUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        return user;
    }

    //Функции управления ролями
    @RequestMapping(value = "/{id}/role", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object grantRole(@RequestBody Role role, @PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.grantRole(user,role);
        return user;
    }

    @RequestMapping(value = "/{id}/role", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object revokeRole(@RequestBody Role role, @PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.revokeRole(user,role);
        return user;
    }


}
