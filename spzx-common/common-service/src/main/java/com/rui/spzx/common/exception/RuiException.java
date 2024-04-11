package com.rui.spzx.common.exception;

import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.common.exception
 * @className: RuiException
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/4 17:10
 * @version: 1.0
 */
@Data
public class RuiException extends RuntimeException{
    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public RuiException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
    public RuiException(Integer code , String message){
        this.code = code;
        this.message = message;
    }
}
