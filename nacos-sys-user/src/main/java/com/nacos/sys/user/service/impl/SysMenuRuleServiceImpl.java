package com.nacos.sys.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nacos.sys.user.entity.SysMenuRule;
import com.nacos.sys.user.mapper.SysMenuRuleMapper;
import com.nacos.sys.user.service.ISysMenuRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nacos.sys.user.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao
 * @since 2020-08-12
 */
@Service
public class SysMenuRuleServiceImpl extends ServiceImpl<SysMenuRuleMapper, SysMenuRule> implements ISysMenuRuleService {

    @Resource
    private SysMenuRuleMapper sysMenuRuleMapper;
    /**
     * 通过角色id查找
     * @param rId
     * @return
     */
    public List<SysMenuRule> selectByRid(String rId){
        if (StringUtils.isBlank(rId)){
            return null;
        }
        QueryWrapper<SysMenuRule> sysMenuRuleQueryWrapper = new QueryWrapper<>();
        sysMenuRuleQueryWrapper.eq("rId",rId);
        sysMenuRuleQueryWrapper.eq("deleteFlag",0);
        return sysMenuRuleMapper.selectList(sysMenuRuleQueryWrapper);
    }

    public void delById(String id){
        sysMenuRuleMapper.deleteById(id);
    }
    public void delByIds(List<String> ids){
        sysMenuRuleMapper.deleteBatchIds(ids);
    }
}
