package com.rui.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.rui.spzx.model.dto.system.AssginRoleDto;
import com.rui.spzx.model.dto.system.LoginDto;
import com.rui.spzx.model.dto.system.SysUserDto;
import com.rui.spzx.model.entity.system.SysUser;
import com.rui.spzx.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    //2 用户添加
    void saveSysUser(SysUser sysUser);

    //3 用户修改
    void updateSysUser(SysUser sysUser);

    //4 用户删除
    void deleteById(Long userId);

    //保存角色数据
    void doAssign(AssginRoleDto assginRoleDto);
}
