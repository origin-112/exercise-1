package com.rui.spzx.manager.controller;

import com.rui.spzx.manager.service.SysMenuService;
import com.rui.spzx.manager.service.SysUserService;
import com.rui.spzx.manager.service.ValidateCodeService;
import com.rui.spzx.model.dto.system.LoginDto;
import com.rui.spzx.model.entity.system.SysUser;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import com.rui.spzx.model.vo.system.LoginVo;
import com.rui.spzx.model.vo.system.SysMenuVo;
import com.rui.spzx.model.vo.system.ValidateCodeVo;
import com.rui.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: IndexController
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/2 15:57
 * @version: 1.0
 */

@Tag(name = "登录接口-IndexController")
@RestController
@RequestMapping(value = "/admin/system/index")
@CrossOrigin
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;
    //用户登录
    @Operation(summary = "登录方法")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo , ResultCodeEnum.SUCCESS);
    }
    //用户退出
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token){
        sysUserService.logout(token);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }
    //验证码接口
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo , ResultCodeEnum.SUCCESS);
    }
    //获取登录用户信息
//    @GetMapping("/getUserInfo")
//    public Result<SysUser> getUserInfo(@RequestHeader(name = "token")String token){
//        SysUser sysUser = sysUserService.getUserInfo(token);
//        return Result.build(sysUser , ResultCodeEnum.SUCCESS);
//    }

    //获取登录用户信息
    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo(){

        return Result.build(AuthContextUtil.get() , ResultCodeEnum.SUCCESS);
    }
    //查询用户可以操作菜单
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> sysMenuVoList =  sysMenuService.findMenuByUserId() ;
        return Result.build(sysMenuVoList , ResultCodeEnum.SUCCESS) ;
    }
}
