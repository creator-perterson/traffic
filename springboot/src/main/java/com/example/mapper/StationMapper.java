package com.example.mapper;

import com.example.entity.Station;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StationMapper {
    @Select("select * from allstation")
    List<Station> findAll();

    @Select("select * from allstation where id=#{id}")
    Station getStationId(Integer id);


}
