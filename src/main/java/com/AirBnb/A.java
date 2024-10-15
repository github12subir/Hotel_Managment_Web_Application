package com.AirBnb;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {

    public static void main(String[] args) {
        //Create Admin-Password & Encrypt It & sore it as a String
        String adminPassword = BCrypt.hashpw("rony345", BCrypt.gensalt(10));
        //Print the Password on Console for copy & paste it into Db as Admin-password
        System.out.println("Admin Password= "+adminPassword);
    }
}
