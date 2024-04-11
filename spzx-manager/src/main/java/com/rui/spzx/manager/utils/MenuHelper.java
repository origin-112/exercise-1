package com.rui.spzx.manager.utils;

import com.rui.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.utils
 * @className: MenuHelper
 * @author: liuzr
 * @description: TODO //递归封装树形菜单数据
 * @date: 2024/1/5 10:20
 * @version: 1.0
 */
public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList){
        //sysMenuList所有菜单集合
        //创建List集合，用于封装最终的数据
        List<SysMenu> trees = new ArrayList<>();
        //遍历所有菜单集合
        for (SysMenu sysMenu : sysMenuList){
            //找到递归操作入口，顶级菜单
            //条件：parent_id = 0
            if (sysMenu.getParentId().longValue() == 0){
                //根据第一层，递归找下层数据
                trees.add(findChildren(sysMenu , sysMenuList));
            }
        }
        return trees;
    }
    /*
     * @param sysMenu: 当前第一层菜单
     * @param sysMenuList:  所有菜单集合
     * @return SysMenu
     * @author rui
     * @description TODO 递归查找下层菜单
     * @date 2024/1/5 10:37
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        //SysMenu setChildren属性初始化以及封装数据
        sysMenu.setChildren(new ArrayList<>());
        //递归查询sysMenu的id == sysMenuList 的parentId
        for (SysMenu tmp : sysMenuList){
            if (sysMenu.getId().longValue() == tmp.getParentId().longValue()){
                sysMenu.getChildren().add(findChildren(tmp , sysMenuList));
            }
        }
        return sysMenu;
    }
}
