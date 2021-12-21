package com.kkb.project.portal.service.impl;

import com.kkb.project.portal.domain.Minio;
import com.kkb.project.portal.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author lemon
 * @Date 2021/4/17
 * @Description minio文件上传实现类
 * @Version 1.0
 **/
@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private Minio minio;

    /**
     * minio文件上传
     *
     * @param file 上传的文件
     * @return 文件访问地址
     */
    @Override
    public String uploadFile(MultipartFile file) {
        String bucketName = minio.getBucketName();
        String objectName;
        String objectUrl = null;
        try {
            boolean bucketExists = minioClient.bucketExists(bucketName);
            if (!bucketExists) {
                minioClient.makeBucket(bucketName);
            }
            // 设置上传文件名
            String filename = file.getOriginalFilename();
            objectName = System.currentTimeMillis() + "_" + filename;
            // 上传配置
            PutObjectOptions options = new PutObjectOptions(file.getSize(), PutObjectOptions.MIN_MULTIPART_SIZE);
            options.setContentType(file.getContentType());
            minioClient.putObject(bucketName, objectName, file.getInputStream(), options);
            objectUrl = minioClient.getObjectUrl(minio.getBucketName(), objectName);
        } catch (Exception e) {
            log.error("上传文件失败", e.getMessage());
        }
        return objectUrl;
    }
}
