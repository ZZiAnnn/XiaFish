package com.xiafish.service;

import com.xiafish.pojo.Goods;
import com.xiafish.pojo.GoodsComment;
import com.xiafish.pojo.PageBean;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    PageBean getGoods(Map<String,Object> goodsRequestBody);

    Goods getGoodsById(Integer id);

    void purchaseById(Integer userId, Integer goodsId, Integer orderNum);

    void releaseComment(GoodsComment goodsComment);


    void uploadImgs(Integer goodsId, List<String> urls);

}
