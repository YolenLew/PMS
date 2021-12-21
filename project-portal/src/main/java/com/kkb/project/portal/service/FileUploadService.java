
package com.kkb.project.portal.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author lemon
 * @Date 2021/4/17
 * @Description minio文件上传
 * @Version 1.0
 **/
public interface FileUploadService {

    /**
     * 上传文件
     *
     * @param multipartFile 上传的文件
     * @return 文件访问地址
     */
    String uploadFile(MultipartFile multipartFile);
}

