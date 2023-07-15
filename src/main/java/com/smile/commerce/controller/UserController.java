package com.smile.commerce.controller;

import com.smile.commerce.entity.BaseController;
import com.smile.commerce.entity.User;
import com.smile.commerce.service.IUserService;
import com.smile.commerce.service.ex.InsertException;
import com.smile.commerce.service.ex.UsernameDuplicateException;
import com.smile.commerce.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user){
        JsonResult<Void> result = new JsonResult<>();
//        try {
//            userService.reg(user);
//            result.setState(200);
//        } catch (UsernameDuplicateException e) {
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        } catch (InsertException e) {
//            result.setState(5000);
//            result.setMessage("注册失败，请联系系统管理员");
//        }
        userService.reg(user);
        return new JsonResult<>(OK);
    }
}
