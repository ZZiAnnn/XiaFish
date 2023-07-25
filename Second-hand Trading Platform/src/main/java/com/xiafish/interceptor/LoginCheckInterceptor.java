package com.xiafish.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xiafish.pojo.Result;
import com.xiafish.util.JwtUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //get url
        String url = request.getRequestURI();
        log.info("url:{}", url);

        String jwt = request.getHeader("token");
        //判断令牌是否存在
        if (!StringUtils.hasLength(jwt)) {
            log.info("token为空，返回未登录");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            //设置响应头（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(notLogin);
            return false;
        }

        //解析token
        Integer userId, userStatus;
        try {
            userId = JwtUtils.parseJwt(jwt).get("id", Integer.class);
            userStatus = JwtUtils.parseJwt(jwt).get("status", Integer.class);
        } catch (JwtException e) {
            //e.printStackTrace();
            log.info("令牌解析失败");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            //设置响应头（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(notLogin);
            return false;
        }
        request.setAttribute("userId", userId);
        request.setAttribute("userStatus", userStatus);

        return true;
    }

}
