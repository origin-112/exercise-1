package com.rui.spzx.manager.mapper;


import com.rui.spzx.model.dto.system.SysRoleDto;
import com.rui.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    //角色列表
    List<SysRole> findByPage(SysRoleDto sysRoleDto);
    //角色添加
    void save(SysRole sysRole);
    //角色修改
    void updateSysRole(SysRole sysRole);
    //角色删除
    void deleteById(Long roleId);

    //将系统中所有的角色数据都查询出来
    List<SysRole> findAllRoles();
}
