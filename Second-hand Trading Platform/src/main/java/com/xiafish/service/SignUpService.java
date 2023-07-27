package com.xiafish.service;

public interface SignUpService {
    void addUser(String username, String password,String phone,String email);

    Integer getId();
}
