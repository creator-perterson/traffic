package com.example.controller;

import com.example.entity.StationDay;
import com.example.mapper.StationDayMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/stationday")
public class StationDayController {
    @Resource
    StationDayMapper stationDayMapper;

    @GetMapping
    public List<StationDay> getStationDay(){
        return stationDayMapper.findAll();
    }

    @GetMapping("/{staNum}")
    public List<StationDay> getStationDayById(@PathVariable("staNum") Integer staNum){
        return stationDayMapper.findStationDaynum(staNum);
    }
}
