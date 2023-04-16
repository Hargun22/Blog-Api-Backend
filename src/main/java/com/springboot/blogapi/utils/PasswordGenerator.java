package com.springboot.blogapi.utils;

import static com.springboot.blogapi.config.SecurityConfig.passwordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        System.out.println(passwordEncoder().encode("user"));
        System.out.println(passwordEncoder().encode("admin"));
    }
}
