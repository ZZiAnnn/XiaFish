package com.xiafish.service;


import com.xiafish.pojo.PageBean;
import com.xiafish.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void addToCart(Integer userId, Integer goodsId, Integer collectNum);

    List<ShoppingCart> getCart(Integer userId);

    void updateShoppingCart(ShoppingCart shoppingCart);

    void buyFromShoppingCart(ShoppingCart shoppingCart);

    void deleteShoppingCart(ShoppingCart shoppingCart);

    Integer getCartCount(Integer userId);

}
