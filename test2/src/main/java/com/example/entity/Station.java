package com.example.entity;

import lombok.Data;

@Data
public class Station {
    private Integer id;  //自增长id
    private String station;  //站点名称
    private int enter_station;  //进站总人数
    private int out_station;  //出站总人数
    private String line;  //所属线路
    private String administrative_region;  //所属区域
    private int line_num;
    private int administrative_num;
    private int kind;
}
