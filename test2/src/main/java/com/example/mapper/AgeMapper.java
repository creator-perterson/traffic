package com.example.mapper;

import com.example.entity.Age;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AgeMapper {
    @Select("select * from allage")
    List<Age> findAll();

    @Select("select * from allage where id=#{id}")
    Age findAgeId(Integer id);
}
