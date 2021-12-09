package com.auth.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@RestController
public class HealthCheckController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Logger LOGGER = LoggerFactory.getLogger(HealthCheckController.class);

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Up and Running");
    }

    @GetMapping("/encode")
    public ResponseEntity<String> encode(@RequestHeader String username, @RequestHeader String password) {
        String authenticationString = username + ":" + password;
        String encodedString = Base64.getEncoder().encodeToString(authenticationString.getBytes(StandardCharsets.UTF_8));
        return ResponseEntity.ok("Basic " + encodedString);
    }

    @GetMapping("/bcrypt/encode")
    public ResponseEntity<String> encodeToBcrypt(@RequestHeader String password){
        return ResponseEntity.ok(passwordEncoder.encode(password));
    }
}
