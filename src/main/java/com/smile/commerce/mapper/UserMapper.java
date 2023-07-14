package com.smile.commerce.mapper;

import com.smile.commerce.entity.User;

//用户模块持久层接口

public interface UserMapper {
    /***
     * @Description: 插入用户数
     * @Author: kiligsmile
     * @Date: 2023/7/14 5:38 PM
     * @Return: 受影响的行数（增、删、改，都受影响的行数作为返回值，可以根据返回值判断是否执行成功）
    */
    Integer insert(User user);

    /**
     * @Description: 根据用户名查询用户的数据
     * @Author: kiligsmile
     * @Date: 2023/7/14 8:09 PM
     * @Return: 如果找到对应的用户则返回这个用户的数据，如果没有就返回null
    */
    User findByUsername(String username);
}
