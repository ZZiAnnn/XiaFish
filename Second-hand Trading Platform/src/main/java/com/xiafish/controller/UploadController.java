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

    @PostMapping("/upload/Imgs")
    public Result uploadPhotos(@RequestParam("photos") MultipartFile[] photos) throws IOException {
        // 检查文件数量
        List<String> urls = new ArrayList<>();
        for(MultipartFile photo:photos){
            log.info("文件上传{}",photo);
            String url= aliOSSUtils.upload(photo);
            log.info("文件上传成功，对应的url为:{}",url);
            urls.add(url);
        }
        return Result.success(urls);
    }
}
