package com.example.vo;

import lombok.Data;

import java.util.List;

@Data
public class Line<T> {
    public String name;  //line1
    public String line_id;  //线路id：line1
    public String children[];  //包含站点
    public List<T> data;  //返回数据类型
}
