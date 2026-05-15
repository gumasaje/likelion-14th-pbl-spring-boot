package com.likelion.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Likelion!";
    }

    // Bonus 1
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "Hello, " + name + "!";
    }
}
