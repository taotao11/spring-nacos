package com.nacos.sys.user.web;

import com.nacos.sys.user.entity.SysMenuRule;
import com.nacos.sys.user.service.ISysMenuRuleService;
import com.nacos.sys.user.utils.ReslutBean;
import com.nacos.sys.user.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 */
@RequestMapping("/user/SysMenuRule")
@RestController
public class SysMenuRuleController {

    @Autowired
    private ISysMenuRuleService iSysMenuRuleService;

    @RequestMapping("/add")
    public ReslutBean add(String mIds,String rId){
        if (StringUtils.isBlank(rId)){
            return ReslutBean.error("角色id不能为空");
        }
        if (StringUtils.isBlank(mIds)){
            return ReslutBean.error("菜单id不能为空");
        }
        List<SysMenuRule> sysMenuRules = iSysMenuRuleService.selectByRid(rId);
        List<String> strings = Arrays.asList(mIds.split(","));
        List<SysMenuRule> sysMenuRuleList = new ArrayList<>();
        SysMenuRule sysMenuRule = null;
        for (String str : strings){
            boolean isHave = false;
            for (SysMenuRule menuRule : sysMenuRules){
                if (str.equals(menuRule.getMId())){
                    isHave = true;
                    break;
                }
            }
            if (!isHave){
                sysMenuRule = new SysMenuRule();
                sysMenuRule.setId(StringUtils.getUuid());
                sysMenuRule.setMId(str);
                sysMenuRule.setRId(rId);
                sysMenuRule.setCreateTime(new Date());
                sysMenuRuleList.add(sysMenuRule);
            }
        }
        iSysMenuRuleService.saveBatch(sysMenuRuleList);
        return ReslutBean.success("新增成功");
    }
    @RequestMapping("/delByid")
    public ReslutBean delByid(String id){

        if (StringUtils.isBlank(id)){
            return ReslutBean.error("id不能为空");
        }
        iSysMenuRuleService.delById(id);
        return ReslutBean.success("删除成功");
    }
    @RequestMapping("/delByids")
    public ReslutBean delByids(String ids){

        if (StringUtils.isBlank(ids)){
            return ReslutBean.error("id不能为空");
        }
        iSysMenuRuleService.delByIds(Arrays.asList(ids.split(",")));
        return ReslutBean.success("删除成功");
    }

}
