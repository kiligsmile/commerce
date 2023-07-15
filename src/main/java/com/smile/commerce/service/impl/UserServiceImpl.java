package com.smile.commerce.service.impl;

//用户模块业务层的实现类

import com.smile.commerce.entity.User;
import com.smile.commerce.mapper.UserMapper;
import com.smile.commerce.service.IUserService;
import com.smile.commerce.service.ex.InsertException;
import com.smile.commerce.service.ex.UsernameDuplicateException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

//用户模块业务层的实现类
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
//        判断用户是否被注册过
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if(result!=null){
            throw new UsernameDuplicateException("用户名被占用");
        }
        //        补全其余字段
//        密码加密
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
//        将盐值和密码作为一个整体进行加密处理，忽略原有密码提升了数据的安全性
        String md5Password = getMD5Password(oldPassword, salt);
//        需要保存盐值
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer rows = userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("用户注册过程中产生了未知错误");
        }
    }

    private String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
//            三次加密
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
