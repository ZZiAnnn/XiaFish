package com.xiafish.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiafish.mapper.GoodsMapper;
import com.xiafish.pojo.Goods;
import com.xiafish.pojo.GoodsComment;
import com.xiafish.pojo.PageBean;
import com.xiafish.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public PageBean getGoods(Map<String,Object> goodsRequestBody) {

        String goodsName=(String)goodsRequestBody.get("goodsName");
        String goodsCategoryName=(String)goodsRequestBody.get("goodsCategoryName");
        String beginStr = (String) goodsRequestBody.get("begin");
        LocalDateTime begin = beginStr != null ? LocalDateTime.parse(beginStr) : null;
        String endStr = (String) goodsRequestBody.get("end");
        LocalDateTime end = endStr != null ? LocalDateTime.parse(endStr) : null;
        String campus = (String) goodsRequestBody.get("campus");
        Integer lowPrice=(Integer)goodsRequestBody.get("lowPrice");
        Integer highPrice=(Integer)goodsRequestBody.get("highPrice");
        Integer page=(Integer) goodsRequestBody.get("page");
        if(page == null) page=1;
        Integer pageSize=(Integer)goodsRequestBody.get("pageSize");
        if(pageSize==null)pageSize=10;

        // 设置分页参数
        PageHelper.startPage(page,pageSize);

        // 执行条件分页查询
        List<Goods> goodsList = goodsMapper.searchGoods(goodsName,goodsCategoryName,begin,end,lowPrice,highPrice,campus);

        // PageHelper返回的orderList实际上是Page类型
        Page<Goods> p = (Page<Goods>) goodsList;

        // 封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        // 返回查询结果
        return pageBean;

    }

    @Override
    public Goods getGoodsById(Integer id) {
        return goodsMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)//事务管理（操作失败时回滚）
    public void purchaseById(Integer userId, Integer goodsId, Integer orderNum) {
        Integer sellerId=goodsMapper.getSellerId(goodsId);
        Float goodsPrice=goodsMapper.getGoodsPrice(goodsId);
        String orderStatus="已下单";

        goodsMapper.reduceInventory(goodsId,orderNum);
        goodsMapper.purchaseById(userId,sellerId,goodsId,orderNum,
                orderNum*goodsPrice,orderStatus,LocalDateTime.now());
    }

    @Override
    public void releaseComment(GoodsComment goodsComment) {
        goodsMapper.addComment(goodsComment);
    }

    public void uploadImgs(Integer goodsId, List<String> urls) {
        goodsMapper.insertImages(goodsId,urls);

    }

}
