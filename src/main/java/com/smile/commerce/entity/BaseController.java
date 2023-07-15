package com.smile.commerce.entity;

import com.smile.commerce.service.ex.InsertException;
import com.smile.commerce.service.ex.ServiceException;
import com.smile.commerce.service.ex.UsernameDuplicateException;
import com.smile.commerce.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

//控制类的基类
public class BaseController {
//    操作成功的状态码
    public static final int OK=200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if(e instanceof UsernameDuplicateException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }
        else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }
        return result;
    }
}
