package com.example.mapper;

import com.example.entity.MonthWeekday;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MonthWeekdayMapper {
    @Select("select * from allweekday")
    List<MonthWeekday> findAll();

    @Select("select * from allweekday where id=#{id}")
    MonthWeekday getMonthWeekdayId(Integer id);
}
