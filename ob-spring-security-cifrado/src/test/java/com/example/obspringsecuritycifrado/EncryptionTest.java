package com.example.obspringsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    }
}
