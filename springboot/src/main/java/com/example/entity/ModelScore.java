package com.example.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ModelScore {
    private int id;
    private String model;
    private BigDecimal mse;
    private BigDecimal mape;
}
