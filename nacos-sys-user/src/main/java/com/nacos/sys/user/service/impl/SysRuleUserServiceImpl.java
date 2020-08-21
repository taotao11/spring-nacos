package com.nacos.sys.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nacos.sys.user.entity.SysRuleUser;
import com.nacos.sys.user.mapper.SysRuleUserMapper;
import com.nacos.sys.user.service.ISysRuleUserService;
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
public class SysRuleUserServiceImpl extends ServiceImpl<SysRuleUserMapper, SysRuleUser> implements ISysRuleUserService {
    @Resource
    private SysRuleUserMapper sysRuleUserMapper;

    /**
     * 通过uid查询角色
     * @param uid
     * @return
     */
    public List<SysRuleUser> selectByUid(String uid){
        if (StringUtils.isBlank(uid)){
            return null;
        }
        QueryWrapper<SysRuleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("deleteFlag",0);
        List<SysRuleUser> sysRuleUsers = sysRuleUserMapper.selectList(queryWrapper);
        return sysRuleUsers;
    }

    /**
     * 删除角色
     *
     * @param ids
     */
    public void deleteByIds(List<String> ids){
        sysRuleUserMapper.deleteBatchIds(ids);
    }

    /**
     * 单个删除
     * @param id
     */
    public void deleteById(String id){
        sysRuleUserMapper.deleteById(id);
    }
}
