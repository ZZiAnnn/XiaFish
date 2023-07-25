package com.xiafish.controller;

import com.xiafish.pojo.*;
import com.xiafish.service.UserService;
import com.xiafish.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //查询用户信息
    @GetMapping("/user")
    public Result getUserInfo(@RequestAttribute("userId") Integer userId) {
        log.info("查询的用户id：{}", userId);
        User user = userService.getUserById(userId);
        System.out.println(user);
        return Result.success(user);
    }

    //更新用户信息
    @PatchMapping("/user/update")
    public Result updateUser(@RequestBody User user) {
        try {
            log.info("更新的用户id：{}", user);
            userService.updateUser(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //查询用户商品
    @GetMapping("user/goods")
    public Result getGoodsByUserId(@RequestAttribute("userId") Integer userId) {
        List<Goods> goodsList = userService.getGoodsByUserId(userId);
        return Result.success(goodsList);
    }

    //用户发布商品
    @PutMapping("user/release")
    public Result releaseGoods(@RequestAttribute("userId") Integer userId,
                               @RequestBody Goods goods) {
        //设置商品发布时间
        goods.setReleaseTime(LocalDateTime.now());
        log.info("发布商品：{}", goods);
        userService.releaseGoods(goods, userId);
        return Result.success();
    }

    //用户删除已发布的商品
    @DeleteMapping("user/goods/{goodsIds}")
    public Result deleteGoods(@PathVariable List<Integer> goodsIds,
                              @RequestAttribute("userId") Integer userId) {
        log.info("用户 {} 删除商品：{}", userId, goodsIds.toString());
        userService.deleteGoods(userId, goodsIds);
        return Result.success();
    }

    //查询用户的订单
    @GetMapping("user/order")
    public Result findOrder(@RequestAttribute("userId") Integer userId) {
        List<ReturnOrder> userOrdersList = userService.findOrder(userId);
        return Result.success(userOrdersList);
    }

    @PatchMapping("user/order/update")
    public Result updateOrder(@RequestAttribute("userId")  Integer userId,@RequestBody Order order)
    {
        order.setBuyerId(userId);
        userService.updateOrder(order);
        return Result.success();
    }

    @GetMapping("user/comment")
    public Result findComment(@RequestAttribute("userId") Integer userId) {
        List<UserComment> userCommentsList = userService.findComment(userId);
        return Result.success(userCommentsList);
    }

    @GetMapping("user/shoppingcart")
    public Result viewShoppingCart(@RequestAttribute("userId") Integer userId) {
        List<ShoppingCart> shoppingCarts = userService.viewShoppingCart(userId);
        return Result.success(shoppingCarts);
    }

    @PatchMapping("user/goods/update")
    public Result updateUserGoods(@RequestBody Goods goods) {
        userService.updateUserGoods(goods);
        return Result.success();
    }

    @GetMapping("/other/{userId}")
    public Result otherInfo(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return Result.success(user);
    }

    @GetMapping("other/goods/{userId}")
    public Result getOtherGoods(@PathVariable Integer userId) {
        List<Goods> goodsList = userService.getGoodsByUserId(userId);
        return Result.success(goodsList);
    }

    @GetMapping("other/comment/{userId}")
    public Result getOtherComment(@PathVariable Integer userId) {
        List<UserComment> userCommentsList = userService.findComment(userId);
        return Result.success(userCommentsList);
    }
}
