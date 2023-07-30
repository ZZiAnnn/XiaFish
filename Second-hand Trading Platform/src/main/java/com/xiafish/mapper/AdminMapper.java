package com.xiafish.mapper;

import com.xiafish.DTO.OrderDTO;
import com.xiafish.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AdminMapper {
    List<User> list(User user);

    void deleteUser(List<Integer> ids);

    void addUser(User user);

    void updateUser(User user);

     List<OrderDTO> getOrder(String buyerName, String sellerName, LocalDateTime begin, LocalDateTime end);
     @Select("select user_id from xiafish.user where user_name=#{userName}")
     Integer getUserIdByUserName(String userName);

}
