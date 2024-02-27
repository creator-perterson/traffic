package com.example.controller;

import com.example.entity.Station;
import com.example.entity.User;
import com.example.mapper.StationMapper;
import com.example.vo.Line;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {
    @Resource
    StationMapper stationMapper;

    @GetMapping
    public List<Station> getUser(){
        //所有站点总数据
        return stationMapper.findAll();
    }
    @GetMapping("/{id}")
    public Station getStationId(@PathVariable("id") Integer id){
        //传参找单条总数据
        return stationMapper.getStationId(id);
    }

//    @GetMapping("/line")
//    public Line<>
}
