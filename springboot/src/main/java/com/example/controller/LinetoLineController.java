package com.example.controller;

import com.example.entity.LinetoLine;
import com.example.mapper.LinetoLineMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/linetoline")
public class LinetoLineController {
    @Resource
    LinetoLineMapper linetoLineMapper;

    @GetMapping
    public List<LinetoLine> findLinetoLine(){
        return linetoLineMapper.findAll();
    }

    @GetMapping("/{line_num}")
    public LinetoLine findLinetoLineById(@PathVariable("line_num") Integer line_num){
        return linetoLineMapper.getLinetoLineById(line_num);
    }

}
