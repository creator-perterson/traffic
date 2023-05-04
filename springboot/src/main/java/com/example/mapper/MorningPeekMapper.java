package com.example.mapper;

import com.example.entity.MorningPeek;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MorningPeekMapper {

    @Select("select * from allmorningtime")
    List<MorningPeek> findAll();

    @Select("select * from allmorningtime where id=#{id}")
    MorningPeek findMorningPeekById(Integer id);
}
