package com.xiafish.mapper;

import com.xiafish.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select user_id, user_name, user_phone_num, user_email, user_status, user_photo," +
            " user_campus, user_nickname, user_profile, user_gender from xiafish.user where user_id=#{userId}")
    User getUserById(Integer userId);

    void updateUser(User user);

    @Select("select * from xiafish.goods " +
            "where seller_id=#{userId} " +
            "order by release_time desc")
    List<Goods> goodsList(Integer userId);

    void addGoods(Goods goods);
    void deleteGoods(Integer userId,List<Integer> goodsids);

    @Select("select user_comment.*,user.user_name as buyerName,user.user_photo as buyerPhoto from xiafish.user_comment " +
            "left join xiafish.user on user.user_id=user_comment.buyer_id " +
            "where seller_id = #{userid}")
    List<UserComment> selectComment(Integer userid);
    @Select("select * from xiafish.shopping_cart where user_id=#{userid}")
    List<ShoppingCart> selectShoppingCart(Integer userid);


    List<ReturnOrder> selectOrder(Integer userid);

    @Update("update xiafish.user set user_photo=#{url} where user_id=#{userId}")
    void updateHeadImg(Integer userId, String url);

    void updateGoods(Goods goods);

}
