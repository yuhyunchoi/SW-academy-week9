package com.nhnacademy.student.di;

import com.nhnacademy.student.day4.di.InjectUtil;
import com.nhnacademy.student.day4.di.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InjectUtilTest {

    @Test
    void injectTest(){
//        //InjectUtil.getObject()
//        UserRepository userRepository = new UserRepository();
//        UserService userService = new UserService(userRepository);

        UserService userService2 = InjectUtil.getObject(UserService.class);
        assertInstanceOf(UserService.class, userService2);

    }
}