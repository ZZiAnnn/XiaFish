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

import java.time.LocalDateTime;
import java.util.List;

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
    public void addToCart(Integer userId, Integer goodsId, Integer collectNum) {
        shoppingCartMapper.insertToCart(userId, goodsId, collectNum, LocalDateTime.now());
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
            if (shoppingCart == null) {
                throw new Exception("对应的购物车Id不存在");
            }
            Goods goods = goodsMapper.getById(shoppingCart.getGoodsId());
            if (goods == null) {
                throw new Exception("对应的商品不存在");
            }

            Order order = new Order(null, shoppingCart.getUserId(), goods.getSellerId(),
                    shoppingCart.getGoodsId(), shoppingCart.getCollectNum(),
                    goods.getCurPrice() * shoppingCart.getCollectNum(), "1",
                    LocalDateTime.now());

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
