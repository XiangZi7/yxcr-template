package com.yxcr.bean.service.impl;

import cn.hutool.core.io.FileUtil;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final String bucketName;

    // 从配置文件中读取不允许上传的文件类型列表
    @Value("${minio.not-support-file-type}")
    private String notSupportFileType;

    public MinioService(MinioClient minioClient, @Value("${minio.bucket}") String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
    }

    // 上传文件到MinIO存储桶
    public String uploadFile(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename();
        // 检查文件名是否合法
        if (originalFilename == null || originalFilename.trim().isEmpty()) {
            throw new IllegalArgumentException("文件名称不能为空");
        }

        // 从文件名获取文件类型
        String fileType = FileUtil.extName(originalFilename);

        // 检查是否是不允许上传的文件类型
        if (isForbiddenFileType(fileType)) {
            throw new IllegalArgumentException("不允许上传" + fileType + "文件类型");
        }

        // 生成文件的路径
        String fileName = generateFilePath(originalFilename);
        // 上传文件到MinIO
        try (InputStream inputStream = multipartFile.getInputStream()) {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(inputStream, multipartFile.getSize(), -1)
                    .contentType(multipartFile.getContentType())
                    .build());
        } catch (MinioException e) {
            throw new Exception("上传文件时发生错误: " + e.getMessage(), e);
        }

        // 返回编码后的文件访问路径
        return UriUtils.encodePath(fileName, StandardCharsets.UTF_8);
    }

    // 检查是否为禁止上传的文件类型
    private boolean isForbiddenFileType(String fileType) {
        List<String> forbiddenFileTypes = Arrays.asList(notSupportFileType.split(","));
        return forbiddenFileTypes.contains(fileType);
    }

    // 从MinIO存储桶中删除文件
    public void deleteFile(String fileName) throws Exception {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build());
        } catch (MinioException e) {
            throw new Exception("删除文件失败: " + e.getMessage(), e);
        }
    }

    // 根据原始文件名生成文件的路径
    private static String generateFilePath(String originalFilename) {
        // 使用UUID来保证文件名的唯一性
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 获取文件类型的扩展名
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 生成基于日期的路径，如“2023/04/01”
        String datePath = getDatePath();

        return String.format("%s%s%s", datePath, uuid, fileExtension);
    }

    // 获取当前日期的字符串格式，用于生成文件路径
    private static String getDatePath() {
        LocalDateTime now = LocalDateTime.now();
        return String.format("%d/%02d/%02d/", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
    }
}
