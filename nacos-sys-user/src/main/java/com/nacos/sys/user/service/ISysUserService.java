package com.nacos.sys.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nacos.sys.user.entity.SysUser;
import com.nacos.sys.user.utils.PageQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-04-05
 */
public interface ISysUserService extends IService<SysUser> {

    public Page<SysUser> selectUserList(PageQuery<SysUser> pageQuery);

    public SysUser selectUserByName(String name);

}
