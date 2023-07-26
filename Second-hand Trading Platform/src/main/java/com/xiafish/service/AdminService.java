package com.xiafish.service;

import com.xiafish.pojo.Order;
import com.xiafish.pojo.PageBean;
import com.xiafish.pojo.ReturnOrder;
import com.xiafish.pojo.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AdminService {
    /**
     * 条件分页查询
     * @param page 页码
     * @param pageSize 每页展示记录数
     * @param user 筛选条件
     * @return
     */
    PageBean findAllUser(Integer page, Integer pageSize, User user);

    void deleteUser(List<Integer> ids);

    void addUser(User user);

    void updateUser(User user);

    PageBean getOrder(Map<String,Object> orderBody);

}
