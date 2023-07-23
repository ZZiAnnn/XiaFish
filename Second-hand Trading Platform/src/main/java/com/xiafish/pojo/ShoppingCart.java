package com.xiafish.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private Integer shoppingCartId;
    private Integer userId;
    private Integer goodsId;
    private String goodsName;
    private Float curPrice;
    private Integer inventory;
    private Integer collectNum;
    private LocalDateTime collectTime;
    private Float oriPrice;
    private String goodsCategoryName;
    private Integer sellerId;
    private LocalDateTime releaseTime;

    private String  goodsProfile;
    private List<String> goodsPhoto;
}
