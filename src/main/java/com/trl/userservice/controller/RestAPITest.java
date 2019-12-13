package com.trl.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello")
public class RestAPITest {

    @GetMapping()
    public ResponseEntity<String> getById(@PathVariable Long userId) {
        ResponseEntity<String> response = null;


        response = ResponseEntity.ok("Hello Roman.");

        return response;
    }
}
