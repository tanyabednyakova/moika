package io.khasang.moika.validator;

import io.khasang.moika.entity.Client;
import io.khasang.moika.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
 /*       User user = (User) target;

        if(StringUtils.isEmpty(user.getName())){
            errors.rejectValue("name","name_empty");
        }
        if(!user.getPhone().matches("^\\d{9}+")){
            errors.rejectValue("phone","phone_invalid");
        }
        if(!user.getLastname().matches("^[A-Za-z]*|^[А-Яа-я]*")){
            errors.rejectValue("lastname","lastname_invalid");
        }
*/
    }

}
