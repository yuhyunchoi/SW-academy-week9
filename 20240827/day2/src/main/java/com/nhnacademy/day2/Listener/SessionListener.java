package com.nhnacademy.day2.Listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class SessionListener implements HttpSessionListener {
    private static final Logger log = Logger.getLogger(SessionListener.class.getName());
    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se){
        HttpSessionListener.super.sessionCreated(se);
        int sessionCounter = atomicInteger.incrementAndGet();
        log.info("session-counter:" + sessionCounter);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se){
        HttpSessionListener.super.sessionDestroyed(se);
        int sessionCounter = atomicInteger.decrementAndGet();
        log.info("session-counter:" + sessionCounter);
    }
}
