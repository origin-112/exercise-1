package com.rui.spzx.manager.mapper;

import com.rui.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    //1 查询所有菜单，返回list
    List<SysMenu> findAll();
    //菜单添加
    void insert(SysMenu sysMenu);

    //菜单修改
    void update(SysMenu sysMenu);

    int countByParentId(Long id);

    void deleteById(Long id);

    List<SysMenu> findMenusByUserId(Long userId);

    public abstract SysMenu selectById(Long Id);
}
