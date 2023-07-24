package com.xiafish.exception;

import java.net.ConnectException;
import com.xiafish.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception ex) {
        //输出错误信息
        ex.printStackTrace();
        //获取最底层的异常
        Throwable cause = ex;
        while(cause.getCause()!=null) {
            cause = cause.getCause();
        }

        if(cause instanceof SQLException) {
            return handleSQLException(cause);
        }
        if(cause instanceof ConnectException) {
            return handleConnectException(cause);
        }
        if(cause instanceof BadCredentialsException) {
            return handleBadCredentialsException(cause);
        }
        return Result.error("对不起操作失败，请联系管理员");
    }

    private Result handleBadCredentialsException(Throwable cause) {
        String message;
        message=cause.getMessage();
        log.info("异常：{}", message);
        if(message.contains("Incorrect password"))
        {
            return Result.error("用户名或密码错误");
        }
        return Result.error(cause.getMessage());
    }


    // 处理SQLException类异常
    public Result handleSQLException(Throwable cause){

        String message;
        message=cause.getMessage();
        log.info("异常：{}", message);

        if(message.contains("UNIQUE"))
        {
            return Result.error("插入的信息已存在");
        }
        if (message.contains("You have an error in your SQL syntax")) {
            return Result.error("数据库语法错误（后端马上改！！！）");
        }
        if (message.contains("Access denied for user")) {
            return Result.error("数据库登录失败(用户或密码错误)");
        }
        if (message.contains("a foreign key constraint fails")) {
            return Result.error("插入数据存在外键问题");
        }
        if (message.contains("goods_inventory_constraint")) {
            return Result.error("商品库存不足");
        }
        return Result.error(cause.getMessage());
    }
    // 处理ConnectException类异常
    public Result handleConnectException(Throwable cause) {
        String message;
        message = cause.getMessage();
        log.info("异常：{}", message);
        if (message.contains("Connection refused: connect")) {
            return Result.error("数据库连接失败");
        }

        return Result.error(cause.getMessage());
    }
}
