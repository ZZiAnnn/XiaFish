package com.xiafish.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SignUpMapper {

    @Insert("insert into xiafish.user(user_name,user_passwd,user_phone_num,user_email) values(#{username},#{password},#{phone},#{email})")
    void addUser(String username, String password,String phone,String email);

    @Select("SELECT LAST_INSERT_ID()")
    Integer selectId();
}
