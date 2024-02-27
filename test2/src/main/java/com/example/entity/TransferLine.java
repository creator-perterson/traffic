package com.example.entity;

import lombok.Data;

@Data
public class TransferLine {
    private int id;
    private String line;
    private int line_num;
    private int transfer_volume;
    private int passenger_flow;
}
