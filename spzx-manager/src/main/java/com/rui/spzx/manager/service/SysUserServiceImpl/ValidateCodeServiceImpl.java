package com.rui.spzx.manager.service.SysUserServiceImpl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.rui.spzx.manager.service.ValidateCodeService;
import com.rui.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: ValidateCodeServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/6 17:02
 * @version: 1.0
 */

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String , String> redisTemplate;
    /*
     * @param :
     * @return ValidateCodeVo
     * @author rui
     * @description TODO    实现图片验证码
     * @date 2023/12/6 17:06
     */
    @Override
    public ValidateCodeVo generateValidateCode() {
        // 使用hutool工具包中的工具类生成图片验证码
        //参数：宽  高  验证码位数 干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 4);
        // 生成uuid作为图片验证码的key
        String codevalue = circleCaptcha.getCode();//验证码值
        String imageBase64 = circleCaptcha.getImageBase64();//图像验证码，base64编码
        // 将验证码存储到Redis中
        String key = UUID.randomUUID().toString().replace("-" , "");
        redisTemplate.opsForValue().set("user:validate" + key , codevalue ,
                                            5 ,
                                            TimeUnit.MINUTES);
        // 构建响应结果数据
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);//redis  存储数据key
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);
        // 返回数据
        return validateCodeVo;
    }
}
