package com.rui.spzx.manager.service.SysUserServiceImpl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rui.spzx.common.exception.RuiException;
import com.rui.spzx.manager.mapper.SysRoleUserMapper;
import com.rui.spzx.manager.mapper.SysUserMapper;
import com.rui.spzx.manager.service.SysUserService;
import com.rui.spzx.model.dto.system.AssginRoleDto;
import com.rui.spzx.model.dto.system.LoginDto;
import com.rui.spzx.model.dto.system.SysUserDto;
import com.rui.spzx.model.entity.system.SysUser;
import com.rui.spzx.model.vo.common.ResultCodeEnum;
import com.rui.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: exercise-spzx-parent
 * @package: com.rui.spzx.manager.service.SysUserServiceImpl
 * @className: SysUserServiceImpl
 * @author: liuzr
 * @description: TODO
 * @date: 2023/12/2 15:59
 * @version: 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper SysUserMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper ;
    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    @Value("${spzx.captchaOnOff}")
    private boolean captchaOnOff;
    @Override
    public LoginVo login(LoginDto loginDto) {

        // 校验验证码是否正确
        String captcha = loginDto.getCaptcha();        // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();        // redis中验证码的数据key

        if (captchaOnOff) {
            // 从Redis中获取验证码
            String redisCode = redisTemplate.opsForValue().get("user:validate" + codeKey);
            if (StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode, captcha)) {
                throw new RuiException(ResultCodeEnum.VALIDATECODE_ERROR);
            }
            // 验证通过删除redis中的验证码
            redisTemplate.delete("user:validate" + codeKey);
        }

        //1 获取提交用户名，loginDto 获取到
        String userName = loginDto.getUserName();
        //2 如果用户名查询数据库表 sys_user表

        SysUser sysUser = SysUserMapper.selectUserInfoByUserName(userName);
        //3 如果根据用户名查不到对应信息，用户不存在，返回错误信息
        if (sysUser == null) {
            //throw new RuntimeException("用户名不存在");
            throw new RuiException(ResultCodeEnum.LOGIN_ERROR);
        }

        //4 如果根据用户名查询到用户信息，用户存在
        String database_password = sysUser.getPassword();
        //5 获取输入的密码，比较输入的密码和数据库密码是否一致
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        //6 密码一致，登录成功，不一致失败
        if (!input_password.equals(database_password)) {
            //throw new RuntimeException("密码不正确");
            throw new RuiException(ResultCodeEnum.LOGIN_ERROR);
        }
        //7 登录成功，生成toekn

        String token = UUID.randomUUID().toString()
                .replaceAll("-" , "");
        //8 把登录成功用户信息放到redis里面

        redisTemplate.opsForValue().set("user:login"+token ,
                JSON.toJSONString(sysUser) ,
                7 ,
                TimeUnit.DAYS);
        //9 返回loginvo对象

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    /*
     * @param token:
     * @return SysUser
     * @author rui
     * @description TODO 获取成功登录用户信息
     * @date 2023/12/7 10:52
     */
    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login" + token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }
    
    //用户退出
    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login" + token);
    }

    //1 用户条件分页查询接口  根据关键字、创建的开始时间和结束是时间 查询表单
    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> list = SysUserMapper.findByPage(sysUserDto);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    //2 用户添加
    @Override
    public void saveSysUser(SysUser sysUser) {
        //1 判断用户名不能重复
        String userName = sysUser.getUserName();
        SysUser dbSysUser = SysUserMapper.selectUserInfoByUserName(userName);
        if (dbSysUser != null){
            throw new RuiException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //2 输入密码进行加密
        String password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(password);
        SysUserMapper.saveSysUser(sysUser);
    }
    //用户修改
    @Override
    public void updateSysUser(SysUser sysUser) {
        SysUserMapper.updateSysUser(sysUser);
    }

    //4 用户删除
    @Override
    public void deleteById(Long userId) {
        SysUserMapper.deleteById(userId);
    }

    //保存角色数据
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        // 删除之前的所有的用户所对应的角色数据
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId());

        // 分配新的角色数据
        List<Long> roleIdList = assginRoleDto.getRoleIdList();

        for (Long roleId : roleIdList){
            sysRoleUserMapper.doAssign(assginRoleDto.getUserId(),roleId);
        }
    }
    //用户删除

}
