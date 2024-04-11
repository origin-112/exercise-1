package com.rui.spzx.manager.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.properties
 * @className: MinioProperties
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/25 14:53
 * @version: 1.0
 */
@Component
@Data
public class MinioProperties {
    @Value("${spzx.minio.endpointUrl}")
    private String endpointUrl;
    @Value("${spzx.minio.accessKey}")
    private String accessKey;
    @Value("${spzx.minio.secretKey}")
    private String secretKey;
    @Value("${spzx.minio.bucketName}")
    private String bucketName;
}
