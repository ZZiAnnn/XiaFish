package com.xiafish.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiafish.pojo.Result;
import com.xiafish.service.GoodsService;
import com.xiafish.service.UploadService;
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
    private UploadService uploadService;
    @PostMapping("/upload/Imgs")
    public Result uploadPhotos(@RequestParam("photos") MultipartFile[] photos) throws IOException {
        List<String>urls=uploadService.upload(photos);
        return Result.success(urls);
    }
}
