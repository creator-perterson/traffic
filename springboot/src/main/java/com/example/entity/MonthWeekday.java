package com.example.entity;

import lombok.Data;

@Data
public class MonthWeekday {
    private int id;
    private String sta_month;
    private String sta_workday;
    private String sta_weekend;
    private String sta_holiday;
}
