package com.example.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Model {
    private Integer id;
    private String timedate;
    private BigDecimal line11;
    private BigDecimal linearRegression;
    private BigDecimal quadraticPolynomial;
    private BigDecimal cubicPolynomial;
    private BigDecimal ridgeRegression;
    private BigDecimal randomForest;
    private BigDecimal lightGBM;
    private BigDecimal Xgboost;
    private BigDecimal Lstm;
}
