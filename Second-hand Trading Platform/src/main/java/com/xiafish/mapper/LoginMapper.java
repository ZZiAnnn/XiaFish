package com.xiafish.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface LoginMapper {

    @Select("select user_id,user_passwd from xiafish.user where user_name=#{username}")
    Map<String, Object> getIdByName(String username);

    @Select("select user_status from xiafish.user where user_id=#{usrId}")
    Integer getStatusByUserId(Integer userId);

    @Select("select * from xiafish.user where user_email=#{email}")
    Integer findByEmail(String email);

    @Select("select user_id from xiafish.user " +
            "where user_email=#{email} or user_phone_num=#{phone}")
    Integer findByEmailOrPhone(String email, String phone);
}
