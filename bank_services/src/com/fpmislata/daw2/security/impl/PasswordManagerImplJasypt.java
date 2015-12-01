
package com.fpmislata.daw2.security.impl;

import com.fpmislata.daw2.security.PasswordManager;

import org.jasypt.util.password.*;

import org.springframework.beans.factory.annotation.Autowired;

public class PasswordManagerImplJasypt implements PasswordManager {
    //@Autowired
    PasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
    
    @Override
    public String encrypt(String userPassword) {
        return passwordEncryptor.encryptPassword(userPassword);
    }

    @Override
    public Boolean check(String inputPassword, String encryptedPassword) {
        return (passwordEncryptor.checkPassword(inputPassword, encryptedPassword));
    }

}
