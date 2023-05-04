package com.example.mapper;

import com.example.entity.LinetoLine;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LinetoLineMapper {
    @Select("select * from alllinetoline")
    List<LinetoLine> findAll();

    @Select("select * from alllinetoline where line_num=#{line_num}")
    LinetoLine getLinetoLineById(Integer line_num);
}
