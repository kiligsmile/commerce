package com.smile.commerce.controller;

import com.smile.commerce.entity.User;
import com.smile.commerce.service.IUserService;
import com.smile.commerce.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
//        JsonResult<Void> result = new JsonResult<>();
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
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);
//        向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        System.out.println(getUserIdFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> get_by_uid(HttpSession session){
        User data = userService.getByUid(getUserIdFromSession(session));
        return new JsonResult<User>(OK,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> change_info(User user,HttpSession session){
        userService.changeInfo(getUserIdFromSession(session),getUsernameFromSession(session),user);
        return new JsonResult<>(OK);
    }
}
