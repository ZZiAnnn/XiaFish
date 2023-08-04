package com.xiafish.service.impl;

import com.xiafish.mapper.GoodsMapper;
import com.xiafish.mapper.OrderMapper;
import com.xiafish.mapper.ShoppingCartMapper;
import com.xiafish.pojo.*;
import com.xiafish.pojo.ShoppingCart;
import com.xiafish.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addToCart(Integer userId, Integer goodsId, Integer collectNum) throws Exception {
        Goods goods=goodsMapper.getById(goodsId);
        if(Objects.equals(goods.getSellerId(), userId))
        {
            throw new Exception("用户不能将自己发布的商品加入购物车");
        }
        try {
            shoppingCartMapper.insertToCart(userId, goodsId, collectNum, LocalDateTime.now());
        }catch (Exception e)
        {
            throw new Exception("该商品已加入购物车");
        }
    }

    @Override
    public List<ShoppingCart> getCart(Integer userId) {

        return shoppingCartMapper.getShoppingCartsList(userId);
    }

    @Override
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartMapper.updateShoppingCart(shoppingCart);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//事务管理（操作失败时回滚）
    public void buyFromShoppingCart(Integer userId, List<Integer> shoppingCartIds) throws Exception {
        for (Integer shoppingCartId : shoppingCartIds) {
            ShoppingCart shoppingCart = shoppingCartMapper.getShoppingCartById(shoppingCartId);
            shoppingCartMapper.deleteShoppingCart(shoppingCart);
            if (shoppingCart == null) {
                throw new Exception("对应的购物车Id不存在");
            }
            Goods goods = goodsMapper.getById(shoppingCart.getGoodsId());
            if (goods == null) {
                throw new Exception("对应的商品不存在");
            }
            if(Objects.equals(shoppingCart.getUserId(), goods.getSellerId()))
            {
                throw new Exception("用户不能购买自己发布的商品");
            }
            Order order = new Order(null, shoppingCart.getUserId(), goods.getSellerId(), shoppingCart.getGoodsId(), shoppingCart.getCollectNum(), goods.getCurPrice() * shoppingCart.getCollectNum(), "已下单", LocalDateTime.now());
            goodsMapper.reduceInventory(shoppingCart.getGoodsId(), shoppingCart.getCollectNum());
            orderMapper.addOrder(order);
        }
    }

    @Override
    public void deleteShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartMapper.deleteShoppingCart(shoppingCart);
    }

    @Override
    public Integer getCartCount(Integer userId) {
        return shoppingCartMapper.getCartCount(userId);
    }
}
