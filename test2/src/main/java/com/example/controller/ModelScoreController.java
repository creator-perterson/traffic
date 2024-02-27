package com.example.controller;

import com.example.entity.ModelScore;
import com.example.mapper.ModelScoreMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/modelscore")
public class ModelScoreController {
    @Resource
    ModelScoreMapper modelScoreMapper;

    @GetMapping
    public List<ModelScore> getModelScore(){
        return modelScoreMapper.findAll();
    }

    @GetMapping("/{id}")
    public ModelScore getModelScoreById(@PathVariable("id") Integer id){
        return modelScoreMapper.findModelId(id);
    }
}
