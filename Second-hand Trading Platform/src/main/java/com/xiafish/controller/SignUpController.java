package com.xiafish.controller;

import com.xiafish.pojo.Result;
import com.xiafish.service.SignUpService;
import com.xiafish.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class SignUpController {
    @Autowired
    private SignUpService signUpService;
    @PostMapping("/signup")
    public Result signUp(@RequestBody Map<String, Object> loginBody)
    {
        String username=(String) loginBody.get("username");
        String password=(String) loginBody.get("password");
        String email =(String) loginBody.get("email");
        String phone =(String) loginBody.get("phone");
        // 使用BCryptPasswordEncoder进行密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(password);
        
        signUpService.addUser(username,encryptedPassword,phone,email);
        Map<String, Object> claims = new HashMap<>();

        Integer userId=signUpService.getId();
        claims.put("id", userId);
        claims.put("status",1);
        String jwt = JwtUtils.generateJwt(claims);
        return Result.success(jwt);
    }
}
