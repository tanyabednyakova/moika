package io.khasang.moika.controller;

import io.khasang.moika.dao.impl.UserDAOImpl;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import io.khasang.moika.util.BindingResultToMapParser;
import io.khasang.moika.util.DataAccessUtil;
import io.khasang.moika.validator.UserUtilValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
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
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private UserService userService;
    @Autowired
    private Validator mvcValidator;
    @Autowired
    private UserUtilValidator userUtilValidator;
    @Autowired
    private DataAccessUtil dataAccessUtil;

    private User getCurrentUser() {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByLogin(currentLogin);

    }

    //TODO Возможно стоит добавить функционал подтверждения регистрации через email (?!phone?!)
    @RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object createUser(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return Collections.singletonMap("errors",BindingResultToMapParser.getMap(result));
        }
        user.setEnabled(true);
        userService.createClientUser(user);
        return Collections.singletonMap("redirect", " ");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object loginUser(@RequestBody User user) {
        //возвращаем null если пользователь уже залогирован
        if (getCurrentUser() != null) {
            return null;
        }
        try {
            AuthenticationManager authenticationManager = authenticationManagerBuilder.getOrBuild();
            Authentication request = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return Collections.singletonMap("redirect", " ");
        } catch (AuthenticationException e) {
            return Collections.singletonMap("errorMsg", "Authentication failed: " + e.getMessage());
        }
        /*
        The solution is the following:
        Check user's password
        Authorize user:

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

        Autowire rememberMeService and call:
        rememberMeServices.onLoginSuccess(request, response, authenticatedUser);
    */
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
        if (getCurrentUser() != null) {
            new SecurityContextLogoutHandler().logout(request, response,
                    SecurityContextHolder.getContext().getAuthentication());
        }
        return "redirect: /";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateUser(@RequestBody Map<String, Object> user, BindingResult result) {
        User currentUser = getCurrentUser();
        if (user.containsKey("password") && currentUser.getPassword().equals(user.get("password"))) {
            user.replace("password", userService.getEncodedPassword(user.get("password").toString()));
        }
        dataAccessUtil.setNewValuesToBean(currentUser, user);
        mvcValidator.validate(currentUser, result);
        if (result.hasErrors()) {
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

    @RequestMapping(value = "/util", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object utilUser(@RequestBody Map<String, String> param, BindingResult bindingResult) {
        //TODO переделать на работу с javax.validation.Validator и создать две доп. аннотации
        boolean result = false;
        userUtilValidator.validate(param, bindingResult);
        String error = null;
        if (bindingResult.hasErrors()) {
            error = bindingResult.getAllErrors().get(0).getDefaultMessage();
        } else if (param.containsKey("login")) {
            result = userService.isLoginFree(param.get("login"));
            error = result ? null : "Такой логин уже занят";
        } else if (param.containsKey("email")) {
            result = userService.isEmailFree(param.get("email"));
            error = result ? null : "Такой email уже занят";
        }
        //Map<String,Object> resultMap = Collections.singletonMap("success",result);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("success", result);
        if (error != null) {
            resultMap.put("error", error);
        }
       /* javax.validation.Validator validator = (javax.validation.Validator) mvcValidator;
        Map<String, Object> resultMap = new HashMap();
        Map<String, String> errorMessageMap = new HashMap<>();
        errorMessageMap.put("valid.email", "Неверный формат email адреса");
        errorMessageMap.put("valid.login", "Неверный формат логина");
        errorMessageMap.put("login", "Такой логин уже занят");
        errorMessageMap.put("email", "Такой email уже занят");
        boolean result = false;
        try {
            param.forEach((key, val) -> {
                Set<ConstraintViolation<User>> constraintViolations = validator.validateValue(User.class, key, val);
                if (constraintViolations.size() > 0) {
                    String validKey = "valid."+key;
                    resultMap.put("error", errorMessageMap.containsKey(validKey)?errorMessageMap.get(validKey):
                            constraintViolations.iterator().next().getMessage());
                }else if(errorMessageMap.containsKey(key)&&!){

                }
            });
        } catch (IllegalArgumentException e) {
            LOGGER.debug(e.getMessage());
            return Collections.singletonMap("error", "Invalid data");
        }

         if (param.containsKey("login")&&!userService.isLoginFree(param.get("login"))){
            resultMap.put("error", "Такой логин уже занят");
        } else if (param.containsKey("email")&&!userService.isEmailFree(param.get("email"))) {
            resultMap.put("error", "Такой email уже занят");

        }

       userUtilValidator.validate(param, bindingResult);
        String error = null;
        if (bindingResult.hasErrors()) {
            Set<ConstraintViolation<User>> constraintViolations =
                    validator.validateValue(User.class, "email", param.get("email"));
            error = bindingResult.getAllErrors().get(0).getDefaultMessage();
        } else if (param.containsKey("login")) {
            result = userService.isLoginFree(param.get("login"));
            error = result ? null : "Такой логин уже занят";
        } else if (param.containsKey("email")) {
            result = userService.isEmailFree(param.get("email"));
            error = result ? null : "Такой email уже занят";
        }
        //Map<String,Object> resultMap = Collections.singletonMap("success",result);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("success", result);
        if (error != null) {
            resultMap.put("error", error);
        }*/
        return resultMap;
    }

    //Функции управления ролями
    @RequestMapping(value = "/{id}/role", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object grantRole(@RequestBody Role role, @PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.grantRole(user, role);
        return user;
    }

    @RequestMapping(value = "/{id}/role", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object revokeRole(@RequestBody Role role, @PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.revokeRole(user, role);
        return user;
    }

}
