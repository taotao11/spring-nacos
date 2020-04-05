package com.nacos.sys.user.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nacos.sys.user.entity.SysUser;
import com.nacos.sys.user.mapper.SysUserMapper;
import com.nacos.sys.user.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-04-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
