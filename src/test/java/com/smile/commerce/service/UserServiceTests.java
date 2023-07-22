package com.smile.commerce.service;

import com.smile.commerce.entity.User;
import com.smile.commerce.service.ex.ServiceException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    @Resource
    private IUserService userService;

    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("test");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("0k");
        }catch (ServiceException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        try {
            String username="test";
            String password="123456";
            User user = userService.login(username, password);
            System.out.println("登录成功！"+user);
        } catch (ServiceException e) {
            System.out.println("登录失败！"+e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changePassword(){
        userService.changePassword(23,"管理员","123456","123");
    }

    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(27));
    }

    @Test
    public void changeInfo(){
        try {
            Integer uid = 27;
            String username = "数据管理员";
            User user = new User();
            user.setPhone("15512328888");
            user.setEmail("admin03@cy.cn");
            user.setGender(2);
            userService.changeInfo(uid, username, user);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeAvatar(){
        try {
            Integer uid=27;
            String username="头像管理员";
            userService.changeAvatar(uid,"/upload/avatar.png",username);
            System.out.println("OK");
        }catch (Exception e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
