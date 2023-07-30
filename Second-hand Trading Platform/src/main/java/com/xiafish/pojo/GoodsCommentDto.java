package com.xiafish.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCommentDto {
    private Integer goodsCommentId;
    private Integer goodsId;
    private Integer buyerId;
    private Integer userId;
    private String goodsCommentContent;
    private String userName;
    private String userPhoto;
    private String userNickname;
}
