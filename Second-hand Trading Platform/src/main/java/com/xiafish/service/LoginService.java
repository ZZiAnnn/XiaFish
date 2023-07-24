package com.xiafish.service;
import java.util.Map;

public interface LoginService {

    String login(String username, String password) throws Exception;

    void loginPhone(String phone) throws Exception;

    void loginEmail(String email) throws Exception;

    String checkVerification(String email, String phone, String valid)throws Exception;
}
