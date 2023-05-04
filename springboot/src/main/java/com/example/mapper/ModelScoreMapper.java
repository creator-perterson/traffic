package com.example.mapper;

import com.example.entity.ModelScore;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModelScoreMapper {
    @Select("select * from alllmodelscore")
    List<ModelScore> findAll();

    @Select("select * from alllmodelscore where id=#{id}")
    ModelScore findModelId(Integer id);
}
