package com.rui.spzx.manager.service;


import com.rui.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.stereotype.Service;

public interface ValidateCodeService {
    ValidateCodeVo generateValidateCode();
}
