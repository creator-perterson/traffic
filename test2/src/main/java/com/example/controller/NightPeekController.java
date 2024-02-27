package com.example.controller;

import com.example.entity.NightPeek;
import com.example.mapper.NightPeekMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/nightpeek")
public class NightPeekController {
    @Resource
    NightPeekMapper nightPeekMapper;

    @GetMapping
    public List<NightPeek> findAll(){
        return nightPeekMapper.findAll();
    }

    @GetMapping("/id")
    public NightPeek findNightPeekById(@PathVariable("id")Integer id){
        return nightPeekMapper.findNightPeekById(id);
    }
}
