package com.example.openpayd_client.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String str){
        return encoder.encode(str);
    }
}
