package com.rui.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.model.dto.system.SysRoleDto;
import com.rui.spzx.model.entity.system.SysRole;

import java.util.Map;

public interface SysRoleService{
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    //将系统中所有的角色数据都查询出来
    Map<String, Object> findAllRoles(Long userId);
}

