package com.rui.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.service.SysUserService;
import com.rui.spzx.model.dto.system.AssginRoleDto;
import com.rui.spzx.model.dto.system.SysUserDto;
import com.rui.spzx.model.entity.system.SysRole;
import com.rui.spzx.model.entity.system.SysUser;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: SysUserController
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/20 21:41
 * @version: 1.0
 */
@RestController
@RequestMapping(value = "/admin/system/sysUser")
@Tag(name = "用户接口-SysUserController")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    //1 用户条件分页查询接口  根据关键字、创建的开始时间和结束是时间 查询表单
    @GetMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result findByPage( @RequestBody SysUserDto sysUserDto,
                             @PathVariable(value = "pageNum") Integer pageNum,
                             @PathVariable(value = "pageSize") Integer pageSize){
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto , pageNum , pageSize);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS);
    }
    //2 用户添加
    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //3 用户修改
    @PutMapping("/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //4 用户删除
    @DeleteMapping("/deleteById/{userId}")
    public Result deleteById(@PathVariable("userId")Long userId){
        sysUserService.deleteById(userId);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }
    //保存角色数据
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto){
        sysUserService.doAssign(assginRoleDto);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }
}
