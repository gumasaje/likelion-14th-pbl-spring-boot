package com.likelion.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Bonus 3
@Controller
public class HelloWithController {
    @GetMapping("/hellowithcontroller")
    @ResponseBody
    public String hello() {
        return "Hello, Likelion!";
    }
}
