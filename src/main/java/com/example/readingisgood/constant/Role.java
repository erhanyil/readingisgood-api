package com.example.readingisgood.constant;

public class Role {

    public Role() {
        throw new IllegalStateException("Constant class");
    }

    public static final String USER = "USER";
    public static final String ANONYMOUS = "ANONYMOUS";
    private static String ROLE_ = "ROLE_";
    public static final String ROLE_USER = ROLE_ + USER;
    public static final String ROLE_ANONYMOUS = ROLE_ + ANONYMOUS;
}
