package io.khasang.moika.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * Данный класс - это вспомогательный инструмент для преобразования List<FieldError>
 *
 * @since 2017-03-05
 * @author Kovalev Aleksandr
 */
public class BindingResultToMapParser {
    /**
     * Статический метод, который возвращает Map<String,String> где содержаться пары элементов:
     * 1. Поле класса в котором допущена ошибка
     * 2. Код ошибки
     * Данные в таком формате легко преобразовываются в JSON-объект для фронтэнда
     *
     * @param bindingResult объект в который передаются Ошибки в виде объектов FieldError {@link FieldError}
     * @return Map<String, String> - key = field name, value = error code
     */
    public static Map<String,String> getMap(BindingResult bindingResult){
        Map<String,String> err = new HashMap<>();
        bindingResult.getFieldErrors().forEach((error)-> err.put(error.getField(),error.getCode()));
        return err;
    }

    /**
     * Статический метод, возвращает Map-объект в котором содержится сообщение
     * об успешной валидации данных
     *
     * @param msg сообщение
     * @return Map<String,String> - объект
     */
    public static Map<String,String> getSuccess(String msg){
        Map<String,String> result = new HashMap<>();
        result.put("success",msg);
        return result;
    }


}
