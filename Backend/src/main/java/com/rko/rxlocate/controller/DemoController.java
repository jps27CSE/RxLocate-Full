package com.rko.rxlocate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-status")
public class DemoController {
    @GetMapping("")
    public ResponseEntity<?> sayHello(){
        return ResponseEntity.ok("Hello World!");
    }

}
