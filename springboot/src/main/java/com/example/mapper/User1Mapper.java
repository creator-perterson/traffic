package com.example.mapper;

import com.example.entity.User1;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User1Mapper {
    @Select("select * from user1")
    List<User1> findAll();
}
