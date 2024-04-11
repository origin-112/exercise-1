package com.rui.spzx.utils;

import com.rui.spzx.model.entity.system.SysUser;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.utils
 * @className: AuthContextUtil
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/13 10:57
 * @version: 1.0
 */
public class AuthContextUtil {

    //创建threadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    //添加数据
    public static void set(SysUser sysUser){
        threadLocal.set(sysUser);
    }
    //获取数据
    public static SysUser get(){
        return threadLocal.get();
    }
    //删除数据
    public static void remove(){
        threadLocal.remove();
    }

}
