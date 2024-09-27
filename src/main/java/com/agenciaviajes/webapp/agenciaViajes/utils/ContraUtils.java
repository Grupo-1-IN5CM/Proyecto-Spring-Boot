package com.agenciaviajes.webapp.agenciaViajes.utils;

import org.mindrot.jbcrypt.BCrypt;


import org.springframework.stereotype.Component;

@Component
public class ContraUtils {


    public String encryptedPassword(String passWord) {
        return BCrypt.hashpw(passWord, BCrypt.gensalt());
    }


    public boolean checkPassword(String password, String passwordEncrypted) {
        return BCrypt.checkpw(password, passwordEncrypted);
    }
}
