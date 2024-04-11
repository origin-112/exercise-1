package com.rui.spzx.manager.service.SysUserServiceImpl;

import com.rui.spzx.common.exception.RuiException;
import com.rui.spzx.manager.mapper.SysMenuMapper;
import com.rui.spzx.manager.mapper.SysRoleMenuMapper;
import com.rui.spzx.manager.service.SysMenuService;
import com.rui.spzx.manager.utils.MenuHelper;
import com.rui.spzx.model.entity.system.SysMenu;
import com.rui.spzx.model.entity.system.SysUser;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import com.rui.spzx.model.vo.system.SysMenuVo;
import com.rui.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: SysMenuServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2024/1/4 10:53
 * @version: 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {


    @Autowired
    private SysMenuMapper sysMenuMapper ;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    //菜单列表
    @Override
    public List<SysMenu> findNodes() {
        //1 查询所有菜单，返回list
        List<SysMenu> sysMenuList = sysMenuMapper.findAll();
        if (CollectionUtils.isEmpty(sysMenuList)){
            return null;
        }

        //2 调用工具类的方法，把返回List集合封装要求数据格式
        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList);
        return treeList;
    }
    //菜单添加
    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
        updateSysRoleMenuIsHalf(sysMenu);
    }
    private void updateSysRoleMenuIsHalf(SysMenu sysMenu) {

        // 查询是否存在父节点
        SysMenu parentMenu = sysMenuMapper.selectById(sysMenu.getParentId());

        if (parentMenu != null) {

            // 将该id的菜单设置为半开
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());

            // 递归调用
            updateSysRoleMenuIsHalf(parentMenu);

        }
    }
    //菜单修改
    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        int count = sysMenuMapper.countByParentId(id);
        if (count > 0){
            throw new RuiException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.deleteById(id);
    }

    //查询用户可以操作菜单
    @Override
    public List<SysMenuVo> findMenuByUserId() {
        //获取当前用户id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();
        //根据userId查询可以操作菜单
        List<SysMenu> sysMenuList = sysMenuMapper.findMenusByUserId(userId);
        //封装要求数据格式，返回
        List<SysMenu> sysMenuList1 = MenuHelper.buildTree(sysMenuList);
        List<SysMenuVo> sysMenuVos = this.buildMenus(sysMenuList1);
        return sysMenuVos;

    }
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
