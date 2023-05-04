package com.example.vo;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private List<T> data; //返回的数据类型
}
