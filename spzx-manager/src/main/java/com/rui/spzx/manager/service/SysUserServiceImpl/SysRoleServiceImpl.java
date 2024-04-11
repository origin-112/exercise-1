package com.rui.spzx.manager.service.SysUserServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rui.spzx.manager.mapper.SysRoleMapper;
import com.rui.spzx.manager.mapper.SysRoleUserMapper;
import com.rui.spzx.manager.service.SysRoleService;
import com.rui.spzx.model.dto.system.SysRoleDto;
import com.rui.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: SysRoleServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/18 16:00
 * @version: 1.0
 */

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //1、角色列表方法
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        PageHelper.startPage(current , limit);
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        //封装pageinfo
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //2、角色添加方法
    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.save(sysRole);
    }
    //3、角色修改
    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    //将系统中所有的角色数据都查询出来
    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        //1 查所有角色
        List<SysRole> sysRoleList = sysRoleMapper.findAllRoles() ;
        //2 分配过的角色列表
        //根据userID查询用户分配过的角色id列表
        List<Long>roleIds = sysRoleUserMapper.selectRoleIdsByUserId(userId);
        Map<String , Object> resultMap = new HashMap<>() ;
        resultMap.put("allRolesList" , sysRoleList) ;
        resultMap.put("sysUserRoles" , roleIds);
        return resultMap;
    }
}
