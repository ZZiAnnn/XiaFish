package com.xiafish.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiafish.mapper.GoodsMapper;
import com.xiafish.mapper.UserMapper;
import com.xiafish.pojo.*;
import com.xiafish.service.UserService;
import com.xiafish.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public void updateUser(User user) throws RuntimeException{
        String email = user.getUserEmail();
        String phoneNumber = user.getUserPhoneNum();

        // 验证邮箱和电话号码的格式
        if (email!=null && !ValidationUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (phoneNumber!=null && !ValidationUtils.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        userMapper.updateUser(user);
    }

    @Override
    public List<Goods> getGoodsByUserId(Integer userId) {
        return userMapper.goodsList(userId);
    }

    @Override
    public void releaseGoods(Goods goods,Integer userId) {
        goods.setSellerId(userId);
        userMapper.addGoods(goods);
    }

    @Override
    public void deleteGoods(Integer userId,List<Integer> goodsIds) {
        userMapper.deleteGoods(userId,goodsIds);
    }

    @Override
    public List<UserComment> findComment(Integer userid) {
        return userMapper.selectComment(userid);
    }

    @Override
    public List<ShoppingCart> viewShoppingCart(Integer userid) {
        return userMapper.selectShoppingCart(userid);
    }

    @Override
    public List<Order> findOrder(Integer userid) {
        return userMapper.selectOrder(userid);
    }

    @Override
    public void updateHeadImg(Integer userId, String url) {
        userMapper.updateHeadImg(userId,url);
    }

    @Override
    public void updateUserGoods(Goods goods) {
        userMapper.updateGoods(goods);
    }

}
