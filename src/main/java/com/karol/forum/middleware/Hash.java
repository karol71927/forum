package com.karol.forum.middleware;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


public class Hash {
    public static String hash(String password){
        Argon2 argon2 = Argon2Factory.create();
        char[] passwordChar = password.toCharArray();
        String hash;
        try {
            hash = argon2.hash(22, 65536,1,passwordChar);
        } finally {
            argon2.wipeArray(passwordChar);
        }
        return hash;
    }

    public static boolean verify(String passwordDB, String password){
        Argon2 argon2 = Argon2Factory.create();
        char[] passwordChar = password.toCharArray();
        return argon2.verify(passwordDB, passwordChar);
    }
}
