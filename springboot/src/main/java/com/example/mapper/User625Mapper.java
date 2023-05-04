package com.example.mapper;

import com.example.entity.User625;
import org.apache.ibatis.annotations.Select;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

public interface User625Mapper {
    @Select("select * from user625")
    List<User625> findAll();
}
