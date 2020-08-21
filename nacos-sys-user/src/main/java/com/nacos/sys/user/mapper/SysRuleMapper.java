package com.nacos.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nacos.sys.user.entity.SysRule;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-04-05
 */
public interface SysRuleMapper extends BaseMapper<SysRule> {

    public List<SysRule> selectRuleByUid(String uid);

}
