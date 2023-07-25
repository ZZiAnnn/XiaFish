package com.xiafish.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiafish.mapper.AdminMapper;
import com.xiafish.pojo.Order;
import com.xiafish.pojo.PageBean;
import com.xiafish.pojo.ReturnOrder;
import com.xiafish.pojo.User;
import com.xiafish.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public PageBean findAllUser(Integer page, Integer pageSize, User user) {
        // 设置分页参数
        PageHelper.startPage(page,pageSize);
        //执行条件分页查询
        List<User> userList=adminMapper.list(user);
        //获取查询结果
        Page<User> p = (Page<User>) userList;
        //封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public void deleteUser(List<Integer> ids) {
        //ids数量大于0才执行
        if(ids.size()>0)adminMapper.deleteUser(ids);
    }

    @Override
    public void addUser(User user) {
        adminMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        adminMapper.updateUser(user);
    }

    @Override
    public PageBean getOrder(Integer page, Integer pageSize, String buyerName, String sellerName, LocalDateTime begin, LocalDateTime end) {


        // 设置分页参数
        PageHelper.startPage(page,pageSize);

        // 执行条件分页查询
        List<ReturnOrder> orderList = adminMapper.getOrder(buyerName,sellerName,begin,end);

        // PageHelper返回的orderList实际上是Page类型
        Page<ReturnOrder> p = (Page<ReturnOrder>) orderList;

        // 封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        // 返回查询结果
        return pageBean;
    }
}
