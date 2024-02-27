package com.example.mapper;

import com.example.entity.User1570;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User1570Mapper {
    @Select("select * from user1570")
    List<User1570> findAll();
}
