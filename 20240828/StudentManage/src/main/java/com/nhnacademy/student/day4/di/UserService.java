package com.nhnacademy.student.day4.di;

import com.nhnacademy.student.day4.reflection.User;

public class UserService {


        @Autowired
        private UserRepository userRepository;

        public User getUser(String userName){
            return userRepository.findByName(userName);
        }
        public void addUser(User user){
            userRepository.save(user);
        }

}