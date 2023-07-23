package com.xiafish.mapper;

import com.xiafish.pojo.Goods;
import com.xiafish.pojo.GoodsComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface GoodsMapper {
    

    @Select("select * from xiafish.goods where goods_id=#{id}")
    Goods getById(Integer id);

    @Select("select user_id from xiafish.user,xiafish.goods " +
            "where goods.seller_id=user.user_id and goods_id=#{goodsId}")
    Integer getSellerId(Integer goodsId);

    @Select("select cur_price from xiafish.goods where goods_id=#{goodsId}")
    Float getGoodsPrice(Integer goodsId);

    @Insert("insert into xiafish.`order`(buyer_id, seller_id, goods_id, order_num, " +
            "order_sum_price, order_status, order_date_time)" +
            " values(#{buyerId},#{sellerId},#{goodsId},#{orderNum},#{orderSumPrice},#{orderStatus},#{now})")
    void purchaseById(Integer buyerId, Integer sellerId, Integer goodsId, Integer orderNum,
                      float orderSumPrice, String orderStatus, LocalDateTime now);

    @Insert("insert into xiafish.goods_comment(buyer_id,goods_id,goods_comment_content) " +
            "values(#{buyerId},#{goodsId},#{goodsCommentContent})")
    void addComment(GoodsComment goodsComment);

    void insertImages(Integer goodsId, List<String> urls);

    List<Goods> searchGoods(String goodsName, String goodsCategoryName, LocalDateTime begin,
                            LocalDateTime end, Integer lowPrice,Integer highPrice,String campus);

    @Update("update xiafish.goods set goods.inventory = goods.inventory-#{orderNum} where goods.goods_id =#{goodsId}")
    void reduceInventory(Integer goodsId, Integer orderNum);
}
