package com.rui.spzx.manager.service.SysUserServiceImpl;

import com.rui.spzx.manager.mapper.SysRoleMenuMapper;
import com.rui.spzx.manager.service.SysMenuService;
import com.rui.spzx.manager.service.SysRoleMenuService;
import com.rui.spzx.model.dto.system.AssginMenuDto;
import com.rui.spzx.model.entity.system.SysMenu;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: SysRoleMenuServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2024/1/5 14:05
 * @version: 1.0
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {

        // 查询所有的菜单数据
        List<SysMenu> sysMenuList = sysMenuService.findNodes() ;

        // 查询当前角色的菜单数据
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId) ;

        // 将数据存储到Map中进行返回
        Map<String , Object> result = new HashMap<>() ;
        result.put("sysMenuList" , sysMenuList) ;
        result.put("roleMenuIds" , roleMenuIds) ;

        // 返回
        return result;
    }


    //2 保存角色分配菜单数据
    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        //删除角色分配菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());

        //保存分配数据
        List<Map<String, Number>> menuIdInfo = assginMenuDto.getMenuIdList();
        if (menuIdInfo != null && menuIdInfo.size() > 0){//角色分配了菜单
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
