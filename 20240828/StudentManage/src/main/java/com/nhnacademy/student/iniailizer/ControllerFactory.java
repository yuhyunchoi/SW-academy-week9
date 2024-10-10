package com.nhnacademy.student.iniailizer;

import com.nhnacademy.student.controller.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ControllerFactory  {
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //todo beanMap에 key = method + servletPath, value = Controller instance
        for(Class<?> cls : c){
            RequestMapping requestMapping = cls.getAnnotation(RequestMapping.class);
            if(Objects.nonNull(requestMapping)){
                String path = requestMapping.value();
                String method = requestMapping.method().toString();
                try {
                    Object objectCommand = cls.getConstructor(null).newInstance(null);
                    beanMap.put(getKey(method, path), objectCommand);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private String getKey(String method, String path){
        return method + path;
    }

    public Object getBean(String method, String path){
        //todo beanMap 에서 method+servletPath을 key로 이용하여 Controller instance를 반환합니다.
        Object object = beanMap.get(getKey(method, path));
        if(Objects.isNull(object)){
            throw new RuntimeException("command not fuond");
        }
        return object;
    }
}