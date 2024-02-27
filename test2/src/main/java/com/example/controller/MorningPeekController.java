package com.example.controller;

import com.example.entity.MorningPeek;
import com.example.mapper.MorningPeekMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/morningpeek")
public class MorningPeekController {
    @Resource
    MorningPeekMapper morningPeekMapper;

    @GetMapping
    public List<MorningPeek> findAll(){
        return morningPeekMapper.findAll();
    }

    @GetMapping("/id")
    public MorningPeek findMorningPeekById(@PathVariable("id") Integer id){
        return morningPeekMapper.findMorningPeekById(id);
    }
}
