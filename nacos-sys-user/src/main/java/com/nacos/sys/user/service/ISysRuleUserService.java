package com.nacos.sys.user.service;

import com.nacos.sys.user.entity.SysRuleUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao
 * @since 2020-08-12
 */
public interface ISysRuleUserService extends IService<SysRuleUser> {
   public List<SysRuleUser> selectByUid(String uid);
    public void deleteByIds(List<String> ids);
    public void deleteById(String id);
}
