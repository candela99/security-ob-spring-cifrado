package com.example.obspringsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class EncryptionTest {
    /**
     * BCrypt genera su propio Salt de 16 bytes
     *
     * El resultado de cifrar con BCrypt será un String de 60 caracteres
     *
     * $a versión
     * $10 fuerza (valor que va de 4 a 31 y por defecto vale 10)
     * Los 22 siguientes caracteres son el salt generado
     */
    @Test
    void bcryptTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin");
        System.out.println(hashedPassword);

        boolean matches = passwordEncoder.matches("admin", hashedPassword);
        System.out.println(matches);
    }

    /**
     * Se verifica que aunque la contraseña sea la misma, al encriptarla ninguna es igual a la otra
     */
    @Test
    void bcryptCheckMultiplePasswords() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for(int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));
        }
    }

    /**
     * Esto es lo que hace Spring para poder cambiar de passwordEncoder en el caso que alguno se rompa
     */
    @Test
    void springPasswordEncoders() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        /*
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("argon2", new Argon2PasswordEncoder());
        */

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);

        String hashedPassword = passwordEncoder.encode("admin");
        System.out.println(hashedPassword);
    }

    /*
    @Test
    void pbkdf2() {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
        for(int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));
        }
    }

    @Test
    void argon() {
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        for(int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));
        }
    }

    @Test
    void scrypt() {
        SCryptPasswordEncoder passwordEncoder = new SCryptPasswordEncoder();
        for(int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));
        }
    }
    */
}
