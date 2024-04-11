package com.rui.spzx.manager.service.SysUserServiceImpl;

import cn.hutool.core.date.DateUtil;
import com.rui.spzx.common.exception.RuiException;
import com.rui.spzx.manager.properties.MinioProperties;
import com.rui.spzx.manager.service.FileUploadService;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: FileUploadServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/22 16:52
 * @version: 1.0
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioProperties minioProperties;
    @Override
    //文件上传
    public String fileUpload(MultipartFile file) {
        try {
            //创建MinioClient对象
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioProperties.getEndpointUrl())
                            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {
                System.out.println("Bucket 'exercise-spzx-bucket' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            //获取上传文件名称 , 如果名字相同无法上传，需要保证名称不一致
            //1 uuid生成名称 01.jpg，2  根据当前日期分组 20231225

            //20231225/u22r2f232t3-3f22j01.jpg
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replace("-","");
            String filename = dateDir + "/" + uuid + file.getOriginalFilename();
            // 文件上传
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(minioProperties.getBucketName()).object(filename)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build());
            //获取上传文件在minio的文件路径
            //http://43.136.67.102/:9000/exercise-spzx-bucket/filename
            String url = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + "/" + filename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuiException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
