package io.khasang.moika.adapter;

import org.springframework.validation.BeanPropertyBindingResult;

import java.util.HashMap;
import java.util.Map;

public class RestBindingResultT extends BeanPropertyBindingResult {
    public RestBindingResultT(Object target, String objectName) {
        super(target, objectName);
    }

    public RestBindingResultT(Object target, String objectName, boolean autoGrowNestedPaths, int autoGrowCollectionLimit) {
        super(target, objectName, autoGrowNestedPaths, autoGrowCollectionLimit);
    }

    public Map<String,String> getMapForJSONParser(){
        Map<String,String> result = new HashMap<>();

        return result;
    }
}
