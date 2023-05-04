package com.example.controller;

import com.example.entity.TransferLine;
import com.example.mapper.TransferLineMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/transferline")
public class TransferLineController {
    @Resource
    TransferLineMapper transferLineMapper;

    @GetMapping
    public List<TransferLine> findAll(){
        return transferLineMapper.findAll();
    }

    @GetMapping("/{line_num}")
    public TransferLine findTransferLineById(@PathVariable("line_num") Integer line_num){
        return transferLineMapper.findTransferLineById();
    }
}
