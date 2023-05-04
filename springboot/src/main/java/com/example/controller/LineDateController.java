package com.example.controller;

import com.example.entity.LineDate;
import com.example.mapper.LineDateMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/linedate")
public class LineDateController {
    @Resource
    LineDateMapper lineDateMapper;

    @GetMapping
    public List<LineDate> getLineDate(){
        //按时间排序的线路单天总客流
       return lineDateMapper.findAll();
    }

    @GetMapping("/{id}")
    public LineDate getLineDateId(@PathVariable("id") Integer id){
        return lineDateMapper.getLineDateId(id);
    }
}
