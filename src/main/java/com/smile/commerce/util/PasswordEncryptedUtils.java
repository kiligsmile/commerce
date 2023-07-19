package com.smile.commerce.util;

import org.springframework.util.DigestUtils;

public class PasswordEncryptedUtils {
    public static String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
//            三次加密
            password= DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
