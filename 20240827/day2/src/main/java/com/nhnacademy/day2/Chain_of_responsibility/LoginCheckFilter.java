package com.nhnacademy.day2.Chain_of_responsibility;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final Logger log = Logger.getLogger(LoginCheckFilter.class.getName());
    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude-urls");
        log.info("exclude-urls:"+urls);
        Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .forEach(excludeUrls::add);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
        // excludeUrls에 포함되지 않는다면 ..
        if(!excludeUrls.contains(requestUri)){
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
            if(Objects.isNull(session)){
                ((HttpServletResponse) servletResponse).sendRedirect("/login.html");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}