package com.example.mapper;

import com.example.entity.NightPeek;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NightPeekMapper {
    @Select("select * from allnighttime")
    List<NightPeek> findAll();

    @Select("select * from allnighttime where id=#{id}")
    NightPeek findNightPeekById(Integer id);
}
