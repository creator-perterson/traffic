package com.example.mapper;

import com.example.entity.StationDay;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StationDayMapper {

    @Select("select * from allstationday")
    List<StationDay> findAll();

    @Select("select * from allstationday where sta_num=#{staNum}")
    List<StationDay> findStationDaynum(Integer staNum);
}
