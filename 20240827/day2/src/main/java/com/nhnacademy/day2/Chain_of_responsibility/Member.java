package com.nhnacademy.day2.Chain_of_responsibility;

public class Member {
    public enum Role{
        ADMIN, USER, MANAGER, NONE
    }

    private final String id;
    private final String name;
    private final String password;
    private final Role role;

    private Member(String id, String name, String password, Role role){
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public static Member createAdmin(String id, String name, String password){
        return new Member(id, name, password, Role.ADMIN);
    }

    public static Member createUser(String id, String name, String password){
        return new Member(id, name, password, Role.USER);
    }

    public static Member createManager(String id, String name, String password){
        return new Member(id, name, password, Role.MANAGER);
    }

    public static Member createUncertificatedMember(String id, String name, String password){
        return new Member(id, name, password, Role.NONE);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Boolean hasRole(Role role){
        return this.role.equals(role) ? true : false;
    }
}
