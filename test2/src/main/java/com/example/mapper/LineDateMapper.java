package com.example.mapper;

import com.example.entity.LineDate;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LineDateMapper {
    @Select("select * from alltimeline")
    List<LineDate> findAll();

    @Select("select * from alltimeline where id=#{id}")
    LineDate getLineDateId(Integer id);
}
