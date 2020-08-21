package com.nacos.sys.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nacos.sys.user.entity.SysUser;
import com.nacos.sys.user.mapper.SysUserMapper;
import com.nacos.sys.user.service.ISysUserService;
import com.nacos.sys.user.utils.PageQuery;
import com.nacos.sys.user.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    @Resource
    private SysUserMapper sysUserMapper;

    public Page<SysUser> selectUserList(PageQuery<SysUser> pageQuery){
        SysUser queryEntity = pageQuery.getQueryEntity();

        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(queryEntity.getUName())){
            sysUserQueryWrapper.likeRight("uName",queryEntity.getUName());
        }

        if (StringUtils.isNotBlank(queryEntity.getUPhoneNumber())){
            sysUserQueryWrapper.eq("uPhoneNumber",queryEntity.getUPhoneNumber());
        }

        if (queryEntity.getUSexCd() != null){
            sysUserQueryWrapper.eq("uSexCd",queryEntity.getUSexCd());
        }

        if (queryEntity.getUStateCd() != null){
            sysUserQueryWrapper.eq("uStateCd",queryEntity.getUStateCd());
        }

        if (queryEntity.getDeleteFlag() != null){
            sysUserQueryWrapper.eq("deleteFlag",queryEntity.getDeleteFlag());
        }

        return sysUserMapper.selectPage(new Page<SysUser>(1,10),sysUserQueryWrapper);
    }

    public SysUser selectUserByName(String name){
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("u_name",name);

        return sysUserMapper.selectOne(sysUserQueryWrapper);
    }
}
