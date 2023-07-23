package com.xiafish.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Integer goodsId;
    private String goodsName;
    private Float oriPrice;
    private Float curPrice;
    private String goodsCategoryName;
    private Integer sellerId;
    private LocalDateTime releaseTime;
    private Integer inventory;
    private String  goodsProfile;
    private String goodsPhotos;
    private String goodsCampus;
}
