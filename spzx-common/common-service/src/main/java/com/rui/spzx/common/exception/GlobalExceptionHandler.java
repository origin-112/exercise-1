package com.rui.spzx.common.exception;

import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.common.exception
 * @className: GlobalExceptionHandler
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/4 17:03
 * @version: 1.0
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(){
        return Result.build(null , ResultCodeEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(RuiException.class)
    @ResponseBody
    public Result error(RuiException e){
        return Result.build(null , e.getResultCodeEnum());
    }
}
