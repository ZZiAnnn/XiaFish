package com.xiafish.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnOrder {
    private Integer orderId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer goodsId;
    private Integer orderNum;
    private float orderSumPrice;
    private String orderStatus;
    private LocalDateTime orderDateTime;
    private String buyerName;
    private String sellerName;
    private String goodsName;
    private String goodsPhotos;
}
