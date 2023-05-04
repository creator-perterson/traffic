package com.example.controller;

import com.example.entity.DayLine;
import com.example.mapper.DayLineMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dayline")
public class DayLineController {
    @Resource
    DayLineMapper dayLineMapper;

    @GetMapping
    public List<DayLine> findAll(){
        return dayLineMapper.getDayLine();
    }

    @GetMapping("/id")
    public DayLine findDayLineById(@PathVariable("id")Integer id){
        return dayLineMapper.getDayLineById(id);
    }
}
