package com.xiafish.service;
import java.util.Map;

public interface LoginService {

    Map<String, Object> getIdByUserName(String username);

    Integer getStatusByUserId(Integer userId);

    Integer valid(String email, String phone);

    boolean checkEmail(String email);
}
