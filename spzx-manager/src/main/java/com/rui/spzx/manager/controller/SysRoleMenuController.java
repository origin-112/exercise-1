package com.rui.spzx.manager.controller;

import com.rui.spzx.manager.service.SysRoleMenuService;
import com.rui.spzx.model.dto.system.AssginMenuDto;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: SysRoleMenuController
 * @author: liuzr
 * @description: TODO
 * @date: 2024/1/5 14:03
 * @version: 1.0
 */
@RestController
@Tag(name = "角色-菜单接口-SysRoleMenuController")
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    //1 查询所有菜单 和 查询角色分配过菜单id列表
    @GetMapping(value = "/findSysRoleMenuByRoleId/{roleId}")
    public Result<Map<String , Object>> findSysRoleMenuByRoleId(@PathVariable(value = "roleId") Long roleId) {
        Map<String , Object> sysRoleMenuList = sysRoleMenuService.findSysRoleMenuByRoleId(roleId) ;
        return Result.build(sysRoleMenuList , ResultCodeEnum.SUCCESS) ;
    }

    //2 保存角色分配菜单数据
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto) {
        sysRoleMenuService.doAssign(assginMenuDto);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
