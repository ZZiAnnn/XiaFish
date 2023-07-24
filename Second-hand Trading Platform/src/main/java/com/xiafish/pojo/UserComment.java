package com.xiafish.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserComment {
    private Integer userCommentId;
    private Integer buyerId;
    private Integer sellerId;
    private String userCommentContent;

    private String buyerName;
    private String buyerPhoto;
}
