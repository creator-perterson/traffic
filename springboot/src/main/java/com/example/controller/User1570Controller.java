package com.example.controller;

import com.example.entity.User1570;
import com.example.mapper.User1570Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user1570")
public class User1570Controller {
    @Resource
    User1570Mapper user1570Mapper;

    @GetMapping
    public List<User1570> findUser1570(){
        return user1570Mapper.findAll();
    }
}
