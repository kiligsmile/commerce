package com.smile.commerce.service.impl;

//用户模块业务层的实现类

import com.smile.commerce.entity.User;
import com.smile.commerce.mapper.UserMapper;
import com.smile.commerce.service.IUserService;
import com.smile.commerce.service.ex.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

import static com.smile.commerce.util.PasswordEncryptedUtils.getMD5Password;

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

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if(result==null){
            throw new UserNotFoundException("用户数据不存在的错误");
        }
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户已删除");
        }
        String salt = result.getSalt();
        String md5Password = getMD5Password(password, salt);
        if(!result.getPassword().equals(md5Password)){
            throw new PasswordNotMatchException("密码有误");
        }
//        将当前用户数据返回，供其他页面数据展示，并且压缩数据
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
//        比较原始密码和数据库中的密码
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("原始密码错误");
        }
//        新密码增加到数据库中
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows!=1){
            throw new UpdateException("更新时产生未知的异常");
        }
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
//        session可能过期
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
//        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw new UpdateException("更新时产生未知的异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        User user=new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeAvatar(Integer uid, String avatar,String username) {
        User result = userMapper.findByUid(uid);
        if(result==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        if(result.getIsDelete().equals(1)){
            throw new UserNotFoundException("用户数据不存在");
        }
        Date now = new Date();
        Integer rows = userMapper.updataAvatarByUid(uid, avatar, username, now);
        if(rows!=1){
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }
}
