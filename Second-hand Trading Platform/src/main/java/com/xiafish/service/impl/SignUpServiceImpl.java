package com.xiafish.service.impl;

import com.xiafish.mapper.SignUpMapper;
import com.xiafish.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private SignUpMapper signUpMapper;
    @Override
    public void addUser(String username, String password){
            signUpMapper.addUser(username, password);
    }

    @Override
    public Integer getId() {
        return signUpMapper.selectId();
    }

}
