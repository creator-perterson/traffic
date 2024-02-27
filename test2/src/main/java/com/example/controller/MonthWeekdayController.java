package com.example.controller;

import com.example.entity.MonthWeekday;
import com.example.mapper.MonthWeekdayMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/monthweekday")
public class MonthWeekdayController {
    @Resource
    MonthWeekdayMapper monthWeekdayMapper;

    @GetMapping
    public List<MonthWeekday> getMonthWeekday(){
        return monthWeekdayMapper.findAll();
    }

    @GetMapping("/{id}")
    public MonthWeekday getMonthWeekdayId(@PathVariable("id") Integer id){
        return monthWeekdayMapper.getMonthWeekdayId(id);
    }
}
