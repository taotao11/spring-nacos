package com.nacos.sys.user.service.impl;

import com.nacos.sys.user.entity.SysRule;
import com.nacos.sys.user.entity.SysUser;
import com.nacos.sys.user.mapper.SysRuleMapper;
import com.nacos.sys.user.service.ISysRuleService;
import com.nacos.sys.user.service.ISysUserService;
import com.nacos.sys.user.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 验证用户账号密码权限类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ISysUserService iSysUserService;

    @Resource
    private SysRuleMapper sysRuleMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (StringUtils.isBlank(s)){
            throw new UsernameNotFoundException("用户不为空");
        }
        //查询用户
        SysUser sysUser = iSysUserService.selectUserByName(s);
        if (sysUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //角色必须是ROLE_开头，可以在数据库中设置
        List<SysRule> sysRules = sysRuleMapper.selectRuleByUid(sysUser.getUId());
        //权限容器
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (SysRule rule : sysRules){
            grantedAuthorities.add(new SimpleGrantedAuthority(rule.getRName()));
        }
        boolean enabled = true; // 可用性 :true:可用 false:不可用
        boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
        boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
        boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定

        //角色必须是ROLE_开头，可以在数据库中设置
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
//        grantedAuthorities.add(grantedAuthority);

        User user = new User(s, sysUser.getUPwd(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
