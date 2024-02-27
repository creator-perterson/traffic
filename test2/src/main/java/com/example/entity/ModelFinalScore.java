package com.example.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ModelFinalScore {
    private int id;
    private String model;
    private BigDecimal mae;
    private BigDecimal rmse;
    private BigDecimal mape;
}
