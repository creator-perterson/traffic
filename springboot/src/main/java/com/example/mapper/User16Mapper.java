package com.example.mapper;

import com.example.entity.User16;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User16Mapper {
    @Select("select * from user16")
    List<User16> findAll();
}
