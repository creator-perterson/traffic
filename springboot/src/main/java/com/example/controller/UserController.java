package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.vo.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//JSON渲染
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapper;

    @GetMapping
    public List<User> getUser(){
        return userMapper.findAll();
    }
    @PostMapping
    public String addUser(@RequestBody User user){
        //RequestBody将json数据解析成实体对象
        userMapper.save(user);
        return "success";
    }

    @PutMapping
    public String updateUser(@RequestBody User user){
        userMapper.updateById(user);
        return "success!";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        //@PathVariable允许前台传入id删除(与上面/{id}对应)
        userMapper.deleteById(id);
        return "success!!";
    }
    @GetMapping("/{id}")
    public User getUserId(@PathVariable("id") Integer id){
        //@PathVariable允许前台传入id删除(与上面/{id}对应)
        return userMapper.findById(id);
    }
    @GetMapping("/page")
    public Page<User> findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize){
        //@RequestParam相当于在往之后加上pageNum=&pageSize=,页码，每页
        Integer offset = (pageNum-1) * pageSize; //offset偏移量(从第几条开始查询)
        List<User> userdata = userMapper.findByPage(offset, pageSize);
        Page<User> page = new Page<>();
        page.setData(userdata);//查询的数据

        Integer total = userMapper.countUser();//统计总数
        page.setTotal(total);//总数
        page.setPageNum(pageNum);//页码
        page.setPageSize(pageSize);//每页多少个
        return page;
    }
}
