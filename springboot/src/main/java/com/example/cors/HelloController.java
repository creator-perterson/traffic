package com.example.cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
//    @CrossOrigin(origins = "http://localhost:63342")
    public String getHello(){
        return "HelloWorld";
    }
}
