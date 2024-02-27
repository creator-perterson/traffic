package com.example.controller;

import com.example.entity.Age;
import com.example.mapper.AgeMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/age")
public class AgeController {
    @Resource
    AgeMapper ageMapper;

    @GetMapping
    public List<Age> getAge(){
        return ageMapper.findAll();
    }

    @GetMapping("/{id}")
    public Age getAgeId(@PathVariable("id") Integer id){
        return ageMapper.findAgeId(id);
    }
}
