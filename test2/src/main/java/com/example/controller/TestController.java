package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @GetMapping("/hello11")
    public String hello(@RequestParam("a") int a, @RequestParam("b") int b){
        int ret = pleaseTest(a,b);
        return "return: " + ret;
    }
    /*函数例子*/
    private int pleaseTest(int a,int b){
        return a*2+b;
    }

    @GetMapping("hello12")
    public String hello2(){
        int sum = pleaseTest2(100);
        return "return: " + sum;
    }
    private int pleaseTest2(int a){
        return a;
    }
}
