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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private EmailSendUtils emailSendUtils;

    @Autowired
    private MessageSendUtil messageSendUtil;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> loginBody)throws BadCredentialsException
    {
        String username=(String) loginBody.get("username");
        String password=(String) loginBody.get("password");

        // 使用BCryptPasswordEncoder进行密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Integer userStatus;
        Map<String, Object> claims = new HashMap<>();
        Integer userId;
        String userPasswd;

        Map<String,Object>  userIdAndPasswd= loginService.getIdByUserName(username);
        userId=(Integer) userIdAndPasswd.get("user_id");
        userPasswd = (String) userIdAndPasswd.get("user_passwd");
        if(!encoder.matches(password,userPasswd))throw new BadCredentialsException("Incorrect password");

        userStatus=loginService.getStatusByUserId(userId);

        claims.put("id", userId);
        claims.put("status",userStatus);
        String jwt = JwtUtils.generateJwt(claims);
        return Result.success(jwt);
    }

    @PostMapping("/login/email")
    public Result emailLogin(@RequestBody Map<String, String> loginBody){
        String email = loginBody.get("email");
        // 检查邮箱是否为空
        if (email == null || email.isEmpty()) {
            return Result.error("Email cannot be empty.");
        }

        if(loginService.checkEmail(email)){
            String verificationCode = ValidationUtils.generateVerificationCode();
            // 存储验证码到 Redis，并设置有效期
            String redisKey = email;

            // 发送验证码到用户邮箱，调用相应的邮件发送服务
            log.info("正在向邮箱{}发送验证码",email);
            emailSendUtils.sendMessageToEmail(verificationCode,email);
            log.info("成功向邮箱{}发送验证码",email);

            redisTemplate.opsForValue().set(redisKey, verificationCode);
            redisTemplate.expire(redisKey, 5, TimeUnit.MINUTES);
            return Result.success();
        }else {
            return Result.error("The email is not registered.");
        }
    }

    @PostMapping("/login/phone")
    public Result phoneLogin(@RequestBody Map<String, String> loginBody) throws Exception {
        String phone = loginBody.get("phone");
        // 检查号码是否为空
        if (phone == null || phone.isEmpty()) {
            return Result.error("Email cannot be empty.");
        }

        if(loginService.checkPhone(phone)){
            String verificationCode = ValidationUtils.generateVerificationCode();
            // 存储验证码到 Redis，并设置有效期
            String redisKey = phone;

            // 发送验证码到用户邮箱，调用相应的邮件发送服务
            log.info("正在向手机号{}发送验证码",phone);
            messageSendUtil.send(verificationCode,phone);
            log.info("成功向手机号{}发送验证码",phone);

            redisTemplate.opsForValue().set(redisKey, verificationCode);
            redisTemplate.expire(redisKey, 5, TimeUnit.MINUTES);
            return Result.success();
        }else {
            return Result.error("The phone is not registered.");
        }
    }

    @PostMapping("/valid")
    public Result valid(@RequestBody Map<String, Object> validBody){
        String email=(String) validBody.get("email");
        String phone=(String) validBody.get("phone");
        String valid=(String) validBody.get("valid");
        if (email == null && phone == null) {
            return Result.error("邮箱和电话号码不能同时为空");
        }

        if (email != null && !ValidationUtils.isValidEmail(email)) {
            return Result.error("邮箱格式不正确");
        }

        if (phone != null && !ValidationUtils.isValidPhoneNumber(phone)) {
            return Result.error("电话号码格式不正确");
        }

        String storedCode=null;
        if(phone!=null) storedCode=(String) redisTemplate.opsForValue().get(phone);
        else storedCode=(String) redisTemplate.opsForValue().get(email);

        if (storedCode != null && storedCode.toString().equals(valid)) {
            Integer userId=loginService.valid(email,phone);
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", userId);
            claims.put("status",1);
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        else return Result.error("验证码错误");
    }
}
