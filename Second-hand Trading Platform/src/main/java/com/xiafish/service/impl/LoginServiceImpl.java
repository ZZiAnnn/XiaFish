package com.xiafish.service.impl;

import com.xiafish.mapper.LoginMapper;
import com.xiafish.service.LoginService;
import com.xiafish.util.EmailSendUtils;
import com.xiafish.util.JwtUtils;
import com.xiafish.util.MessageSendUtil;
import com.xiafish.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private EmailSendUtils emailSendUtils;
    @Autowired
    private MessageSendUtil messageSendUtil;


    @Override
    public String login(String username, String password) throws Exception {

        // 使用BCryptPasswordEncoder进行密码加密
        Map<String, Object> claims = new HashMap<>();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        Map<String, Object> userIdAndPasswd = loginMapper.getIdByName(username);
        if(userIdAndPasswd==null) {
            throw new Exception("用户不存在");
        }
        Integer userId = (Integer) userIdAndPasswd.get("user_id");
        String userPasswd = (String) userIdAndPasswd.get("user_passwd");

        if (!encoder.matches(password, userPasswd)) {
            throw new BadCredentialsException("Incorrect password");
        }else {
            Integer userStatus = loginMapper.getStatusByUserId(userId);
            claims.put("id", userId);
            claims.put("status", userStatus);
            return JwtUtils.generateJwt(claims);
        }
    }

    @Override
    public void loginPhone(String phone) throws Exception{
        if (phone == null || phone.isEmpty()) {
            throw new Exception("手机号码不能为空");
        } else if(!ValidationUtils.isValidPhoneNumber(phone)){
            throw new Exception("邮箱格式不正确");
        }

        if(loginMapper.findByPhone(phone)!=null){
            String verificationCode = ValidationUtils.generateVerificationCode();
            // 存储验证码到 Redis，并设置有效期
            redisTemplate.opsForValue().set(phone, verificationCode);
            redisTemplate.expire(phone, 5, TimeUnit.MINUTES);
            // 发送验证码到用户邮箱，调用相应的邮件发送服务
            log.info("正在向手机号{}发送验证码",phone);
            messageSendUtil.send(verificationCode,phone);
            log.info("成功向手机号{}发送验证码",phone);
        }else {
            throw new Exception("当前手机号尚未注册");
        }
    }

    @Override
    public void loginEmail(String email) throws Exception{
        if (email == null || email.isEmpty()) {
            throw new Exception("邮箱不能为空");
        } else if(!ValidationUtils.isValidEmail(email)){
            throw new Exception("邮箱格式不正确");
        }

        if(loginMapper.findByEmail(email)!=null){
            String verificationCode = ValidationUtils.generateVerificationCode();
            // 存储验证码到 Redis，并设置有效期
            redisTemplate.opsForValue().set(email, verificationCode);
            redisTemplate.expire(email, 5, TimeUnit.MINUTES);
            // 发送验证码到用户邮箱，调用相应的邮件发送服务
            log.info("正在向邮箱{}发送验证码",email);
            emailSendUtils.sendMessageToEmail(verificationCode,email);
            log.info("成功向邮箱{}发送验证码",email);
        }else {
            throw new Exception("当前邮箱尚未注册");
        }
    }
    @Override
    public String checkVerification(String email, String phone, String valid)throws Exception{
        if (email == null && phone == null) {
            throw new Exception("邮箱和电话号码不能同时为空");
        } else if (email != null && !ValidationUtils.isValidEmail(email)) {
            throw new Exception("邮箱格式不正确");
        }else if (phone != null && !ValidationUtils.isValidPhoneNumber(phone)) {
            throw new Exception("电话号码格式不正确");
        }

        String storedCode=null;
        if(phone!=null) storedCode=(String) redisTemplate.opsForValue().get(phone);
        else storedCode=(String) redisTemplate.opsForValue().get(email);

        if (storedCode != null && storedCode.toString().equals(valid)) {
            Integer userId=loginMapper.findByEmailOrPhone(email,phone);
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", userId);
            claims.put("status",1);
            return JwtUtils.generateJwt(claims);
        }
        throw new Exception("验证码错误");
    }

}
