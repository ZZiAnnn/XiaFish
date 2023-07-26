package com.xiafish.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiafish.mapper.AdminMapper;
import com.xiafish.pojo.Order;
import com.xiafish.pojo.PageBean;
import com.xiafish.pojo.ReturnOrder;
import com.xiafish.pojo.User;
import com.xiafish.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PageBean findAllUser(Integer page, Integer pageSize, User user) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        //执行条件分页查询
        List<User> userList = adminMapper.list(user);
        //获取查询结果
        Page<User> p = (Page<User>) userList;
        //封装PageBean

        return new PageBean(p.getTotal(), p.getResult());
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
    public PageBean getOrder(Map<String,Object> orderBody) {

        Integer page = (Integer) orderBody.get("page");
        if (page == null) page = 1;
        Integer pageSize = (Integer) orderBody.get("pageSize");
        if (pageSize == null) pageSize = 10;
        String buyerName = (String) orderBody.get("buyerName");
        String sellerName = (String) orderBody.get("sellerName");
        String orderStatus =(String) orderBody.get("orderStatus");
        String goodsName = (String) orderBody.get("goodsName");
        String beginStr = (String) orderBody.get("begin");
        LocalDateTime begin = beginStr != null ? LocalDateTime.parse(beginStr) : null;
        String endStr = (String) orderBody.get("end");
        LocalDateTime end = endStr != null ? LocalDateTime.parse(endStr) : null;
        log.info("管理员查看历史订单，卖家{}，买家{}，开始时间{}，结束时间{},商品名称{}，订单状态{}", sellerName, buyerName, begin, end,goodsName,orderStatus);

        // 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 执行条件分页查询
        List<ReturnOrder> orderList = adminMapper.getOrder(buyerName,sellerName,begin,end,goodsName,orderStatus);

        // PageHelper返回的orderList实际上是Page类型
        Page<ReturnOrder> p = (Page<ReturnOrder>) orderList;

        // 封装PageBean

        return new PageBean(p.getTotal(), p.getResult());
    }
}
