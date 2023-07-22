package com.xiafish.mapper;

import com.xiafish.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from xiafish.user where user_id=#{userId}")
    User getUserById(Integer userId);

    void updateUser(User user);


    @Select("select * from xiafish.goods,xiafish.goods_category " +
            "where goods_category.category_id=goods.goods_category_id and seller_id=#{userId}")
    List<Goods> goodsList(Integer userId);

    void addGoods(Goods goods);
    void deleteGoods(Integer userId,List<Integer> goodsids);

    @Select("select * from xiafish.user_comment where seller_id = #{userid}")
    List<UserComment> selectcomment(Integer userid);
    @Select("select * from xiafish.shopping_cart where user_id=#{userid}")
    List<ShoppingCart> selectShoppingCart(Integer userid);
    @Select("select * from xiafish.`order` where buyer_id = #{userid}")
    List<Order> selectOrder(Integer userid);

    @Update("update xiafish.user set user_photo=#{url} where user_id=#{userId}")
    void updateHeadImg(Integer userId, String url);

    void updateGoods(Goods goods);

}
