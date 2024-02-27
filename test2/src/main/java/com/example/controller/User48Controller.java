package com.example.controller;

import com.example.entity.User48;
import com.example.mapper.User48Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user48")
public class User48Controller {
    @Resource
    User48Mapper user48Mapper;

    @GetMapping
    public List<User48> findUser48(){
        return user48Mapper.findAll();
    }
}
