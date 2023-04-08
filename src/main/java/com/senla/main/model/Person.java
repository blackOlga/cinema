package com.senla.main.model;


import com.senla.main.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@AllArgsConstructor
@Slf4j
@Data
@Builder
public class Person {

    private Integer id;
    private String username;
    private String password;
    private String role;

 //  public void hash() throws InvalidKeySpecException, NoSuchAlgorithmException {
 //      SecureRandom random = new SecureRandom();
 //      byte[] salt = new byte[16];
 //      random.nextBytes(salt);
 //      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
 //      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
 //      byte[] hash = factory.generateSecret(spec).getEncoded();
 //  }

}


