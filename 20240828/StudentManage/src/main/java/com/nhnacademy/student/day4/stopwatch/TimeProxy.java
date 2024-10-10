package com.nhnacademy.student.day4.stopwatch;

import com.nhnacademy.student.controller.Command;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class TimeProxy implements Command {

    private final Command command;

    public TimeProxy(Command command){
        this.command = command;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        StopWatch stopWatch = command.getClass().getAnnotation(StopWatch.class);
        if(Objects.isNull(stopWatch)){
            long start = System.currentTimeMillis();
            String view = command.execute(req, resp);
            long end = System.currentTimeMillis();
            long result = end - start;
            log.info("result: {}", result);
            return view;
        }
        return command.execute(req, resp);
    }
}
