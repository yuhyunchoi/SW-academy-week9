package com.nhnacademy.student.day4.di;

import com.nhnacademy.student.day4.reflection.User;

public class DiTest {
    public static void main(String[] args) {
        UserService userService = InjectUtil.getObject(UserService.class);
        User user = new User("marco1",10);
        userService.addUser(user);
        System.out.println(userService.getUser("marco1"));
    }
}
