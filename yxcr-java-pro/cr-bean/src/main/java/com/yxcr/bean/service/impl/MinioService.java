package com.yxcr.bean.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
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
    //筛选不需要的文件格式
    @Value("${minio.not-support-file-type}")
    private String notSupportFileType;

    public MinioService(MinioClient minioClient, @Value("${minio.bucket}") String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
    }

    public String uploadFile(MultipartFile multipartFile) throws Exception {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            // 上传文件的名称
            //String fileName = getDatePath() + multipartFile.getOriginalFilename();
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null){
                throw new RuntimeException("非法操作");
            }
            String fileName = generateFilePath(originalFilename);
            // PutObjectOptions，上传配置(文件大小，内存中文件分片大小)
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .stream(inputStream,multipartFile.getSize(),0L)
                    .object(fileName)
                    .contentType(multipartFile.getContentType())
                    .build());
            // 返回访问路径
            return UriUtils.encode(fileName, StandardCharsets.UTF_8);
        }
    }

    // 检查文件格式是否被禁止
    private boolean isForbiddenFileType(String fileType) {
        // 定义禁止上传的文件类型列表
        List<String> forbiddenFileTypes = Arrays.asList(notSupportFileType.split(","));
        return forbiddenFileTypes.contains(fileType);
    }

    public static String generateFilePath(String fileName){
        //生成日期
        String datePath = getDatePath();
        //使用uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //获取后缀
        int i = fileName.lastIndexOf(".");
        String fileType = fileName.substring(i);
        //拼接
        return new StringBuilder().append(datePath).append(uuid).append(fileType).toString();
    }

    /**
     * 获取当前日期字符串格式
     * @return 2021/12/5
     */
    public static String getDatePath() {
        LocalDateTime now = LocalDateTime.now();
        return String.format("/%s/%s/%s/", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
    }
}
