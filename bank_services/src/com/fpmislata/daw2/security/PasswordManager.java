
package com.fpmislata.daw2.security;

public interface PasswordManager {
    String encrypt(String userPassword);
    Boolean check(String inputPassword, String encryptedPassword);
}
