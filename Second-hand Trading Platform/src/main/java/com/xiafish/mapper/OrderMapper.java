package com.xiafish.mapper;

import com.xiafish.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void addOrder(Order order);
}
