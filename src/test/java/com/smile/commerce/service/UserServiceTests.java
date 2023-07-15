package com.smile.commerce.service;

import com.smile.commerce.entity.User;
import com.smile.commerce.service.ex.ServiceException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    @Resource
    private IUserService userService;

    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("smile03");
            user.setPassword("1234");
            userService.reg(user);
            System.out.println("0k");
        }catch (ServiceException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }




}
