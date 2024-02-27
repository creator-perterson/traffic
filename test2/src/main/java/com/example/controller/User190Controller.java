package com.example.controller;

import com.example.entity.User190;
import com.example.mapper.User190Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("user190")
public class User190Controller {
    @Resource
    User190Mapper user190Mapper;

    @GetMapping
    public List<User190> findUser190(){
        return user190Mapper.findAll();
    }
}
