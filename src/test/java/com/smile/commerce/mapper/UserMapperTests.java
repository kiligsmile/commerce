package com.smile.commerce.mapper;

import com.smile.commerce.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//标注当前类是测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("smile1");
        user.setPassword("123456");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("smile");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(22,"123","管理员",new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(22));
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(27);
        user.setPhone("18892223433");
        user.setEmail("1833@qq.com");
        user.setGender(1);
        user.setPassword("123456");
        userMapper.updateInfoByUid(user);
    }

}
