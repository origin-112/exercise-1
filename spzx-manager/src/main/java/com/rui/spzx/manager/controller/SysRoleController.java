package com.rui.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.service.SysRoleService;
import com.rui.spzx.model.dto.system.SysRoleDto;
import com.rui.spzx.model.entity.system.SysRole;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: SysRoleController
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/18 15:57
 * @version: 1.0
 */

@RestController
@RequestMapping(value = "/admin/system/sysRole")
@Tag(name = "角色接口-SysRoleController")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    //2、角色添加方法
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole){
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }
    //1、角色列表方法    current:当前页 limit：每页显示限制
    //SysRoleDto    条件角色名称对象
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit ,
                             @RequestBody SysRoleDto sysRoleDto){
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto , current ,limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS);
    }

    //3、角色修改方法
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }
    //4、角色删除方法
    @DeleteMapping(value = "/deleteById/{roleId}")
    public Result deleteById(@PathVariable(value = "roleId") Long roleId) {
        sysRoleService.deleteById(roleId) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    //将系统中所有的角色数据都查询出来
    @GetMapping("/findAllRoles/{userId}")
    public Result<Map<String , Object>> findAllRoles(@PathVariable("userId") Long userId){
        Map<String , Object> resultMap = sysRoleService.findAllRoles(userId);
        return Result.build(resultMap , ResultCodeEnum.SUCCESS);
    }
}
