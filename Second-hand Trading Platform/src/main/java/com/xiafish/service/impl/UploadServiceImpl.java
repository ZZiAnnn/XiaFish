package com.xiafish.service.impl;

import com.xiafish.service.UploadService;
import com.xiafish.util.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private AliOSSUtils aliOSSUtils;
    public List<String> upload(MultipartFile[] photos) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile photo : photos) {
            log.info("文件上传{}", photo);
            String url = aliOSSUtils.upload(photo);
            log.info("文件上传成功，对应的url为:{}", url);
            urls.add(url);
        }
        return urls;
    }
}
