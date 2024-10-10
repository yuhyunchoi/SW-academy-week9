package com.nhnacademy.hello;

import javax.servlet.ServletContext;
import java.util.Optional;

public final class CounterUtils {
    private CounterUtils(){
        throw new IllegalStateException("Utility clas");
    }

    public static void increaseCounter(ServletContext servletContext){
        Long counter = Optional.ofNullable((Long) servletContext.getAttribute("counter")).orElse(0l);
        counter = counter + 1;
        servletContext.setAttribute("counter", counter);
    }
}
