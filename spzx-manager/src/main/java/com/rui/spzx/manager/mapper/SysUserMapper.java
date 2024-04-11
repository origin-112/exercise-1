package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.dto.system.SysUserDto;
import com.rui.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    List<SysUser> findByPage(SysUserDto sysUserDto);

    //2 如果用户名查询数据库表 sys_user表
    SysUser selectUserInfoByUserName(String userName);

    //2 用户添加
    void saveSysUser(SysUser sysUser);
    //用户修改
    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);
}
