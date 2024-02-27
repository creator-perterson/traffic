package com.example.mapper;

import com.example.entity.User48;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User48Mapper {
    @Select("select * from user48")
    List<User48> findAll();
}
