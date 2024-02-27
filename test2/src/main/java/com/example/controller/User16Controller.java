package com.example.controller;

import com.example.entity.User16;
import com.example.mapper.User16Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user16")
public class User16Controller {
    @Resource
    User16Mapper user16Mapper;

    @GetMapping
    public List<User16> findUser16(){
        return user16Mapper.findAll();
    }
}
