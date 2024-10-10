package com.nhnacademy.day2.Listener;

import lombok.extern.slf4j.Slf4j;


import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import javax.servlet.FilterChain;
import java.util.logging.Logger;

@Slf4j
public class CounterFilter implements Filter {
    private static final Logger log = Logger.getLogger(CounterFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CounterUtils.increaseCounter(servletRequest.getServletContext());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("counter:" + servletRequest.getServletContext().getAttribute("counter:"));
    }
}
