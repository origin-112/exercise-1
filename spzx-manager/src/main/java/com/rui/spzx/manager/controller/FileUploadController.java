package com.rui.spzx.manager.controller;

import com.rui.spzx.manager.service.FileUploadService;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: FileUploadController
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/22 16:48
 * @version: 1.0
 */
@RestController
@RequestMapping("/admin/system")
@Tag(name = "上传接口-FileUploadController-minio")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file){
        //1 获取上传问价
        //2 调用service的方法上传，返回minio路径
        String url = fileUploadService.fileUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
