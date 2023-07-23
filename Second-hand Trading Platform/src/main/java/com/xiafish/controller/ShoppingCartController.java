package com.xiafish.controller;

import com.xiafish.pojo.PageBean;
import com.xiafish.pojo.Result;
import com.xiafish.pojo.ShoppingCart;
import com.xiafish.service.ShoppingCartService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PutMapping("goods/cart")
    public Result addToCart(@RequestAttribute("userId")  Integer userId,
                            @RequestParam("goodsId") Integer goodsId,
                            @RequestParam(value = "collectNum", defaultValue = "1") Integer collectNum){
        log.info("用户 {} 将商品 {}加入购物车，数量为 {}", userId, goodsId, collectNum);
        try {
            shoppingCartService.addToCart(userId,goodsId,collectNum);
            return Result.success();
        }catch (Exception e){
            return Result.error("该商品已加入购物车");
        }
    }
    @GetMapping("/shoppingcart")
    public Result getCart(@RequestAttribute("userId") Integer userId){
        log.info("分页查询用户{}的购物车信息",userId);

        Long total= Long.valueOf(shoppingCartService.getCartCount(userId));
        List<ShoppingCart> list=shoppingCartService.getCart(userId);
        PageBean pageBean=new PageBean(total,list);
        return Result.success(pageBean);
    }
    @PatchMapping("shoppingcart/update")
    public Result updateShoppingCart(@RequestAttribute("userId") Integer userId,@RequestBody ShoppingCart shoppingCart)
    {
        shoppingCart.setUserId(userId);
        log.info("购物车修改数量：{}",shoppingCart.toString());
        shoppingCartService.updateShoppingCart(shoppingCart);
        return Result.success();
    }
    @PostMapping("shoppingcart/buy")
    public Result buyFromShoppingCart(@RequestAttribute("userId") Integer userId,@RequestParam List<Integer> shoppingCartIds)
    {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);
        for (Integer shoppingCartId : shoppingCartIds) {
            shoppingCart.setShoppingCartId(shoppingCartId);
            log.info("购物车下单：{}",shoppingCart.toString());
            shoppingCartService.buyFromShoppingCart(shoppingCart);
    }
        return Result.success();

    }
    @DeleteMapping("shoppingcart/delete")
    public Result deleteShoppingCart(@RequestAttribute("userId") Integer userId,@RequestBody List<Integer> shoppingCartIds) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);
        for (Integer shoppingCartId : shoppingCartIds) {
            log.info("购物车删除：{}", shoppingCart);
            shoppingCartService.deleteShoppingCart(shoppingCart);
        }
        return Result.success();
    }
}

