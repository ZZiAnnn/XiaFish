package com.xiafish.pojo;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.SpringVersion;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer goodsId;
    private Integer orderNum;
    private Float orderSumPrice;
    private String orderStatus;//订单的状态
    private LocalDateTime orderDateTime;

}
