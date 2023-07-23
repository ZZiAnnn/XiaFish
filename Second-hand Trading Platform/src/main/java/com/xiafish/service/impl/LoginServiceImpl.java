package com.xiafish.service.impl;

import com.xiafish.mapper.LoginMapper;
import com.xiafish.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public Map<String, Object> getIdByUserName(String username)throws RuntimeException{
        Map<String,Object> userIdAndPasswd=loginMapper.getIdByName(username);
        Integer userId= (Integer) userIdAndPasswd.get("user_id");
        if(userId == null)throw new RuntimeException("用户不存在");
        return userIdAndPasswd;
    }

    @Override
    public Integer getStatusByUserId(Integer userId)
    {
        return loginMapper.getStatusByUserId(userId);
    }

    @Override
    public Integer valid(String email, String phone) {
        return loginMapper.findByEmailOrPhone(email,phone);
    }

    @Override
    public boolean checkEmail(String email) {
        return !(loginMapper.findByEmail(email)==0);
    }
}
