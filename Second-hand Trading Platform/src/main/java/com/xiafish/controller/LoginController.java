package com.xiafish.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiafish.pojo.Result;
import com.xiafish.service.LoginService;
import com.xiafish.service.UserService;
import com.xiafish.util.EmailSendUtils;
import com.xiafish.util.JwtUtils;
import com.xiafish.util.MessageSendUtil;
import com.xiafish.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class LoginController implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> loginBody) throws BadCredentialsException {
        String username = (String) loginBody.get("username");
        String password = (String) loginBody.get("password");

        try{
            return Result.success(loginService.login(username,password));
        }catch (Exception ex){
            return Result.error(ex.getMessage());
        }
    }

    @PostMapping("/login/email")
    public Result emailLogin(@RequestBody Map<String, String> loginBody) {
        String email = loginBody.get("email");
        try {
            loginService.loginEmail(email);
        } catch (Exception ex) {
            return Result.error(ex.getMessage());
        }
        return Result.success();
    }

    @PostMapping("/login/phone")
    public Result phoneLogin(@RequestBody Map<String, String> loginBody) throws Exception {
        String phone = loginBody.get("phone");
        try {
            loginService.loginPhone(phone);
        } catch (Exception ex) {
            return Result.error(ex.getMessage());
        }
        return Result.success();
    }

    @PostMapping("/valid")
    public Result valid(@RequestBody Map<String, Object> validBody) {
        String email = (String) validBody.get("email");
        String phone = (String) validBody.get("phone");
        String valid = (String) validBody.get("valid");
        try {
            return Result.success(loginService.checkVerification(email, phone, valid));
        } catch (Exception ex) {
            return Result.error(ex.getMessage());
        }
    }
}
