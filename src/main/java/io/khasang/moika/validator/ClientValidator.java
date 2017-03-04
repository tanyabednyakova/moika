package io.khasang.moika.validator;

import io.khasang.moika.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class ClientValidator implements Validator {

/*
    private javax.validation.Validator validator;

    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }
*/


    @Autowired
    CarValidator carValidator;
    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        //Set<ConstraintViolation<Object>> constraintViolations = validator.validate(client);

        if(StringUtils.isEmpty(client.getName())){
            errors.rejectValue("name","name_empty");
        }
        if(!client.getPhone().matches("^\\d{9}+")){
            errors.rejectValue("phone","phone_invalid");
        }
        if(!client.getLastname().matches("^[A-Za-z]*|^[А-Яа-я]*")){
            errors.rejectValue("lastname","lastname_invalid");
        }

        if(client.getCar() != null){
                errors.pushNestedPath("car");
                ValidationUtils.invokeValidator(carValidator, client.getCar(), errors);
        }

        errors.popNestedPath();
    }
}
