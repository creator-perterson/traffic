package com.example.controller;

import com.example.entity.Model;
import com.example.mapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Resource
    ModelMapper modelMapper;

    @GetMapping
    public List<Model> getModel(){
        return modelMapper.findAll();
    }

    @GetMapping("/{id}")
    public Model getModelById(@PathVariable("id")Integer id){
        return modelMapper.findModelId(id);
    }
}
