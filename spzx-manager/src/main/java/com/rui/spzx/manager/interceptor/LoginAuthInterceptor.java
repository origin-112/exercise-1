package com.rui.spzx.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rui.spzx.model.entity.system.SysUser;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import com.rui.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.interceptor
 * @className: LoginAuthInterceptor
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/13 11:02
 * @version: 1.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String , String> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 获取请求方式
        //如果请求方式是options 预检请求，直接放行
        String method = request.getMethod();
        if ("OPTIONS".equals(method)){
            return true;
        }
        // 获取token
        String token = request.getHeader("token");
        // 如果token不为空，那么此时验证token的合法性(redis)
        if (StrUtil.isEmpty(token)){
            responseNoLoginInfo(response);
        }
        String userInfoString = redisTemplate.opsForValue().get("user:login" + token);

        if (StrUtil.isEmpty(userInfoString)){
            responseNoLoginInfo(response);
            return false;
        }
        // 将用户数据存储到ThreadLocal(线程共线信息)中
        SysUser sysUser = JSON.parseObject(userInfoString, SysUser.class);
        AuthContextUtil.set(sysUser);
        // 重置Redis中的用户数据的有效时间
        redisTemplate.expire("user:login" + token , 300 , TimeUnit.MINUTES);
        // 放行
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        //删除ThreadLocal
        AuthContextUtil.remove();
    }
    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
