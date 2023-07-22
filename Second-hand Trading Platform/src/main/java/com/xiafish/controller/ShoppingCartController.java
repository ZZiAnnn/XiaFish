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
    public Result addToCart(@RequestParam("userId") Integer userId,
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
    public Result getCart(@RequestParam("userId") Integer userId,
                          @RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        log.info("分页查询用户{}的购物车信息，当前为第{}页，每页有{}条数据",userId,page,pageSize);

        PageBean list=shoppingCartService.getCart(userId,page,pageSize);
        return Result.success(list);
    }
    @PatchMapping("shoppingcart/update")
    public Result updateShoppingCart(@RequestBody ShoppingCart shoppingCart)
    {
        log.info("购物车修改数量：{}",shoppingCart.toString());
        shoppingCartService.updateShoppingCart(shoppingCart);
        return Result.success();
    }
    @PostMapping("shoppingcart/buy")
    public Result buyFromShoppingCart(@RequestBody ShoppingCart shoppingCart)
    {
        log.info("购物车下单：{}",shoppingCart.toString());
        shoppingCartService.buyFromShoppingCart(shoppingCart);
        return Result.success();
    }
    @DeleteMapping("shoppingcart/delete")
    public Result deleteShoppingCart(@RequestBody ShoppingCart shoppingCart)
    {
        log.info("购物车删除：{}",shoppingCart.toString());
        shoppingCartService.deleteShoppingCart(shoppingCart);
        return Result.success();
    }
}
