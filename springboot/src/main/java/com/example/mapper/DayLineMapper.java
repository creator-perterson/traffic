package com.example.mapper;

import com.example.entity.DayLine;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DayLineMapper {
    @Select("select * from alldayline")
    List<DayLine> getDayLine();

    @Select("select * from alldayline where id=#{id}")
    DayLine getDayLineById(Integer id);
}
