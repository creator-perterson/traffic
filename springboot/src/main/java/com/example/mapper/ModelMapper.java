package com.example.mapper;

import com.example.entity.Model;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModelMapper {
    @Select("select * from allmodel")
    List<Model> findAll();

    @Select("select * from allmodel where id=#{id}")
    Model findModelId(Integer id);
}
