package com.xiafish.controller;

import com.xiafish.pojo.Goods;
import com.xiafish.pojo.GoodsComment;
import com.xiafish.pojo.PageBean;
import com.xiafish.pojo.Result;
import com.xiafish.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/goods/all")
    public Result getGoods(@RequestBody Map<String, Object> goodsRequestBody) {
        PageBean goodsList = goodsService.getGoods(goodsRequestBody);
        return Result.success(goodsList);
    }

    @GetMapping("/goods")
    public Result getGoodsById(@RequestParam("goodsId") Integer Id) {
        log.info("根据商品id查找商品{}", Id);
        Goods goods = goodsService.getGoodsById(Id);
        return Result.success(goods);
    }

    @PostMapping("/goods/purchase")
    @Transactional(rollbackFor = Exception.class)//事务管理（操作失败时回滚）
    public Result purchaseById(@RequestAttribute("userId") Integer userId,
                               @RequestParam("goodsId") Integer goodsId,
                               @RequestParam(value = "orderNum", defaultValue = "1") Integer orderNum) {
        log.info("用户 {} 直接购买商品 {}，数量为 {}", userId, goodsId, orderNum);
        goodsService.purchaseById(userId, goodsId, orderNum);
        return Result.success();
    }

    @PutMapping("/goods/comment")
    public Result releaseComments(@RequestAttribute("userId") Integer userId, @RequestBody GoodsComment goodsComment) {
        goodsComment.setBuyerId(userId);
        log.info("发布商品评价 {}", goodsComment.toString());
        goodsService.releaseComment(goodsComment);
        return Result.success();
    }
}
