package com.auth.server.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class HealthCheckControllerTest {

    @InjectMocks
    private HealthCheckController healthCheckController;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void pingTest() {
        ResponseEntity<String> response = healthCheckController.ping();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Up and Running", response.getBody());
    }

    @Test
    public void encodeTest() {
        ResponseEntity<String> response = healthCheckController.encode("Rohan", "ILYew@1998");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String encodedString = Base64.getEncoder().encodeToString("Rohan:ILYew@1998".getBytes(StandardCharsets.UTF_8));
        assertEquals("Basic " + encodedString, response.getBody());
    }

    @Test
    public void encodeToBcryptTest() {
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
        ResponseEntity<String> response = healthCheckController.encodeToBcrypt("password");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("encodedPassword", response.getBody());
    }
}
