package com.yxcr.admin.controller;

import cn.hutool.core.io.FileUtil;
import com.yxcr.bean.service.impl.MinioService;
import com.yxcr.common.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Resource
    private MinioService minioService;


    @Value("${minio.size}")
    private int imgSize;

    //文件上传接口
    @PostMapping("/upload")
    @Operation(summary = "上传图片文件")
    public ApiResult uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResult.ok();
        }
        try {
//            AttachFile attachFile = new AttachFile();
//            long maxFileSize = imgSize * 1024 * 1024; // 文件大小限制
//            if (file.getSize() > maxFileSize) {
//                return ApiResult.error("文件大小超过" + imgSize + "MB");
//            }

            String file1 = minioService.uploadFile(file);
            return ApiResult.ok(file1);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    //文件上传接口
    @PostMapping("/FileDelete")
    @Operation(summary = "文件删除")
    public ApiResult FileDelete(String FileName) {
        try {
            minioService.deleteFile(FileName);
            return ApiResult.ok("成功");
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }
}
