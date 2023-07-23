package com.xiafish.mapper;

import com.xiafish.pojo.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    @Insert("insert into xiafish.shopping_cart (user_id, goods_id, collect_num, collect_time) " +
            "values (#{userId},#{goodsId},#{collectNum},#{now})")
    void insertToCart(Integer userId, Integer goodsId, Integer collectNum, LocalDateTime now);

    @Select("select xiafish.shopping_cart.*,xiafish.goods.* " +
            "from xiafish.shopping_cart LEFT JOIN xiafish.goods ON (shopping_cart.goods_id=goods.goods_id)" +
            "where user_id = #{userId} ")
    List<ShoppingCart> getShoppingCartsList(Integer userId);

    @Update("update xiafish.shopping_cart set collect_num=#{collectNum} " +
            "where shopping_cart_id=#{shoppingCartId} and user_id = #{userId}")
    void updateShoppingCart(ShoppingCart shoppingCart);
    @Select("select xiafish.shopping_cart.* " +
            "from xiafish.shopping_cart " +
            "where shopping_cart_id = #{shoppingCartId} ")
    ShoppingCart getShoppingCartById(Integer shoppingCartId);

    @Delete("delete from xiafish.shopping_cart where shopping_cart_id=#{shoppingCartId} and user_id =#{userId}")
    void deleteShoppingCart(ShoppingCart shoppingCart);
}
