package com.smile.commerce.controller;

import com.smile.commerce.service.ex.*;
import com.smile.commerce.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

//控制类的基类
public class BaseController {
//    操作成功的状态码
    public static final int OK=200;

//    这里的setMessage是给JsonResult的Message赋值，并不是给具体的异常赋值，这里是判断传来的异常是否是具体异常的实例（传给前端的）
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if(e instanceof UsernameDuplicateException){
            result.setState(4000);
            result.setMessage("用户名被占用异常");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户数据不存在的异常");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("用户名密码错误异常");
        }else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("插入数据时产生未知异常");
        }
        else if(e instanceof InsertException){
            result.setState(5001);
            result.setMessage("更新数据时产生未知异常");
        }
        return result;
    }


    public final Integer getUserIdFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * @Description:  从HttpSession对象中获取用户名
     * @Author: kiligsmile
     * @Date: 2023/7/16 11:02 AM
     * @Return: j当前登录的用户名
    */


    public final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }


}
