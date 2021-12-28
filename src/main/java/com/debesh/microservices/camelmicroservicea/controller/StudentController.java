package com.debesh.microservices.camelmicroservicea.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<?> getStudent() {
        return new ResponseEntity<>("debesh", HttpStatus.OK);
    }
}
