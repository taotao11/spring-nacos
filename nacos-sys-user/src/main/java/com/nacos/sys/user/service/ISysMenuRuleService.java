package com.nacos.sys.user.service;

import com.nacos.sys.user.entity.SysMenuRule;
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
public interface ISysMenuRuleService extends IService<SysMenuRule> {
    /**
     * 通过角色id查找
     * @param rId
     * @return
     */
    public List<SysMenuRule> selectByRid(String rId);

    public void delById(String id);
    public void delByIds(List<String> ids);

}
