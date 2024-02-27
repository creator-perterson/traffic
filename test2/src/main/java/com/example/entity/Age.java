package com.example.entity;

import lombok.Data;

//单站的年龄分类
@Data
public class Age {
    private int id;
    private String station;
    private int age_20;
    private int age_20_30;
    private int age_30_40;
    private int age_40_50;
    private int age_50;
    private int male;
    private int female;
}
