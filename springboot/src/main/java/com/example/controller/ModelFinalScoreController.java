package com.example.controller;

import com.example.entity.ModelFinalScore;
import com.example.mapper.ModelFinalScoreMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/finalscore")
public class ModelFinalScoreController {
    @Resource
    ModelFinalScoreMapper modelfinalScoreMapper;

    @GetMapping
    public List<ModelFinalScore> getModelFinalScore(){
        return modelfinalScoreMapper.findAll();
    }

    @GetMapping("/{id}")
    public ModelFinalScore getModelFinalScoreById(@PathVariable("id") Integer id){
        return modelfinalScoreMapper.findFinalModelId(id);
    }

}
