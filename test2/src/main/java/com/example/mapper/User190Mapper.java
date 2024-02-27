package com.example.mapper;

import com.example.entity.User190;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User190Mapper {
    @Select("select * from user190")
    List<User190> findAll();
}
