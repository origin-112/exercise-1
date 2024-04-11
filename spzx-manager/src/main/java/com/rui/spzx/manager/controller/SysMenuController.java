package com.rui.spzx.manager.controller;

import com.rui.spzx.manager.service.SysMenuService;
import com.rui.spzx.model.entity.system.SysMenu;
import com.rui.spzx.model.vo.common.Result;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.controller
 * @className: SysMenuController
 * @author: liuzr
 * @description: TODO
 * @date: 2024/1/4 10:50
 * @version: 1.0
 */
@RestController
@Tag(name = "菜单接口-SysMenuController")
@RequestMapping(value = "/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    //菜单列表
    @GetMapping("/findNodes")
    public Result<List<SysMenu>> findNodes(){
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list , ResultCodeEnum.SUCCESS);
    }
    //菜单添加
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    //菜单修改
    @PutMapping("/updateById")
    public Result updateById(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    //菜单删除
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id) {
        sysMenuService.removeById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

}
