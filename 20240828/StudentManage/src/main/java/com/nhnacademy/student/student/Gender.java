package com.nhnacademy.student.student;

import java.util.Random;

public enum Gender {
    M, F;

    private static final Random rand = new Random();

    public static Gender randomGender() {
        Gender[] genders = values();
        return genders[rand.nextInt(genders.length)];
    }
}
