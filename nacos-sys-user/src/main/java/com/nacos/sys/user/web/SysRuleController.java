package com.nacos.sys.user.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nacos.sys.user.entity.SysRule;
import com.nacos.sys.user.service.ISysRuleService;
import com.nacos.sys.user.utils.PageQuery;
import com.nacos.sys.user.utils.ReslutBean;
import com.nacos.sys.user.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/user/sys-rule")
public class SysRuleController {
    @Autowired
    private ISysRuleService iSysRuleService;

    /**
     * 新增
     * @param sysRule
     * @return
     */
    @RequestMapping("add")
    public ReslutBean add(@RequestBody SysRule sysRule){
        if (sysRule == null){
            return ReslutBean.error("参数不存在");
        }
        sysRule.setRId(StringUtils.getUuid());
        sysRule.setDeleteFlag(0);
        sysRule.setCreateTime(new Date());
        iSysRuleService.save(sysRule);
        return ReslutBean.success("新增成功");
    }

    @RequestMapping("update")
    public ReslutBean update(@RequestBody SysRule sysRule){
        if (sysRule == null || "".equals(sysRule.getRId())){
            return ReslutBean.error("id不存在");
        }
        iSysRuleService.updateById(sysRule);
        return ReslutBean.success("修改成功");
    }

    @RequestMapping("delById/{id}")
    public ReslutBean delById(@PathVariable("id")String id){
        if (StringUtils.isBlank(id)){
            return ReslutBean.error("id不存在");
        }
        SysRule sysRule = new SysRule();
        sysRule.setRId(id);
        sysRule.setDeleteFlag(1);
        sysRule.setUpdateTime(new Date());
        iSysRuleService.updateById(sysRule);
        return ReslutBean.success("删除成功");
    }

    /**
     * 单个查询
     * @param id
     * @return
     */
    @RequestMapping("info")
    public ReslutBean info(String id){
        SysRule byId = iSysRuleService.getById(id);
        return ReslutBean.success(byId);
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @RequestMapping("selectByPage")
    public ReslutBean selectByPage(PageQuery<SysRule> query){
        SysRule queryEntity = query.getQueryEntity();
        QueryWrapper<SysRule> queryWrapper = new QueryWrapper<>();
        if (queryEntity != null){
            queryWrapper.likeRight("rName",queryEntity.getRName());
        }
        Page<SysRule> page = iSysRuleService.page(new Page<SysRule>(query.getCurrentPage(), query.getPageSize()));
        return ReslutBean.success(page);
    }
}
