package com.example.mapper;

import com.example.entity.Model;
import com.example.entity.ModelFinalScore;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModelFinalScoreMapper {
    @Select("select * from allmodelscore")
    List<ModelFinalScore> findAll();

    @Select("select * from allmodelscore where id=#{id}")
    ModelFinalScore findFinalModelId(Integer id);
}
