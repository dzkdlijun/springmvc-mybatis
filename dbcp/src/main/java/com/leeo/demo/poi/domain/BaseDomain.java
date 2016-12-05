package com.leeo.demo.poi.domain;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijun on 2016/12/5.
 */
public class BaseDomain {
    private static final Field[] fields = BaseDomain.class.getDeclaredFields();
    private Long id;
    private String name;

    public BaseDomain() {
    }

    public BaseDomain(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map<String,String> getPropertyMap(BaseDomain domain) throws IllegalAccessException {
        Map<String,String> map = new HashMap<String, String>();
        for(Field field:fields){
            field.setAccessible(true);
            if(field.getType().toString().contains("String")){
                map.put(field.getName(), (String) field.get(domain));
            }
        }

        return map;
    }
}
