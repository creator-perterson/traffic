package com.example.controller;

import com.example.entity.User1;
import com.example.mapper.User1Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user1")
public class User1Controller {
    @Resource
    User1Mapper user1Mapper;

    @GetMapping
    public List<User1> findUser1(){
        return user1Mapper.findAll();
    }
}
