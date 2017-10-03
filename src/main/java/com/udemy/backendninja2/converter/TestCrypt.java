package com.udemy.backendninja2.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {
    public static void main(String[] args){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("user"));
    }
}
