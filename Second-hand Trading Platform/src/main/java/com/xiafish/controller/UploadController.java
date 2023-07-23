package com.xiafish.controller;

import com.xiafish.pojo.Result;
import com.xiafish.service.GoodsService;
import com.xiafish.service.UserService;
import com.xiafish.util.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/upload/headImg")
    public Result uploadHeadImg(@RequestAttribute("userId")Integer userId, MultipartFile image) throws IOException {
        log.info("文件上传:{}",image);
        String url= aliOSSUtils.upload(image);
        log.info("文件上传成功，对应的url为:{}",url);
        userService.updateHeadImg(userId,url);
        return Result.success(url);
    }

    @PostMapping("/upload/goodsImgs")
    public Result uploadPhotos(@RequestParam("photos") MultipartFile[] photos,
                               @RequestParam("goodsId") Integer goodsId) throws IOException {
        // 检查文件数量
        if (photos.length > 5) Result.error("最多只能上传五张图片");
        List<String> urls = new ArrayList<>();
        for(MultipartFile photo:photos){
            log.info("文件上传{}",photo);
            String url= aliOSSUtils.upload(photo);
            log.info("文件上传成功，对应的url为:{}",url);
            urls.add(url);
        }
        goodsService.uploadImgs(goodsId,urls);
        return Result.success(urls);
    }
}
