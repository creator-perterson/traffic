package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.beans.Transient;
import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Update("insert into `user` (`name`,`password`,`address`,`phone`) VALUES(#{name},#{password},#{address},#{phone});")
    @Transactional
    void save(User user);//Transcational事务

    @Update("update user set name=#{name},password=#{password},address=#{address},phone=#{phone} where id=#{id};")
    @Transactional
    void updateById(User user);

    @Delete("delete from user where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    @Select("select * from user limit #{offset},#{pageSize}")
    List<User> findByPage(Integer offset, Integer pageSize);

    @Select("select count(id) from user")
    Integer countUser();
}
