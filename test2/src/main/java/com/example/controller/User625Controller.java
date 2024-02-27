package com.example.controller;

import com.example.entity.User625;
import com.example.mapper.User625Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user625")
public class User625Controller {
    @Resource
    User625Mapper user625Mapper;

    @GetMapping
    public List<User625> findUser625(){
        return user625Mapper.findAll();
    }
}
