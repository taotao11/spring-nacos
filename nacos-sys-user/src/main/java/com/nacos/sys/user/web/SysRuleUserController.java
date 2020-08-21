package com.nacos.sys.user.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nacos.sys.user.entity.SysRuleUser;
import com.nacos.sys.user.entity.SysUser;
import com.nacos.sys.user.service.ISysRuleUserService;
import com.nacos.sys.user.service.ISysUserService;
import com.nacos.sys.user.utils.PageQuery;
import com.nacos.sys.user.utils.ReslutBean;
import com.nacos.sys.user.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/user/sysRuleUser")
public class SysRuleUserController {

    @Autowired
    public ISysRuleUserService iSysRuleUserService;

    /**
     * 加角色
     * @param rIds
     * @return
     */
    @RequestMapping("addRule")
    public ReslutBean addRule(String rIds,String uid){
        if (StringUtils.isBlank(rIds)){
            return ReslutBean.error("角色id不能为空");
        }
        if (StringUtils.isBlank(uid)){
            return ReslutBean.error("用户id不能为空");
        }
        //查询该用户角色
        List<SysRuleUser> querys = iSysRuleUserService.selectByUid(uid);
        String[] split = rIds.split(",");
        List<SysRuleUser> sysRuleUsers = new ArrayList<>();
        SysRuleUser ruleUser = null;

        for (String rid : split){
            //判断是否有角色 初始化没有
            boolean isHave = false;
            for (SysRuleUser q : querys){
                if (rid.equals(q.getRId())){
                    isHave = true;
                    continue;
                }
            }
            if (!isHave){
                ruleUser = new SysRuleUser();
                ruleUser.setId(StringUtils.getUuid());
                ruleUser.setRId(rid);
                ruleUser.setUId(uid);
                ruleUser.setDeleteFlag(0);
                ruleUser.setCreateId(uid);
                ruleUser.setCreateTime(new Date());
                sysRuleUsers.add(ruleUser);
            }
        }
        boolean b = iSysRuleUserService.saveBatch(sysRuleUsers);
        return ReslutBean.success("添加成功");
    }

    /**
     * 批量删除
     * @param ids
     * @param uid
     * @return
     */
    @RequestMapping("/delBatchIdsRule")
    public ReslutBean delBatchIdsRule(String ids,String uid){
        if (StringUtils.isBlank(ids)){
            return ReslutBean.error("角色id不能为空");
        }
        if (StringUtils.isBlank(uid)){
            return ReslutBean.error("用户id不能为空");
        }
        iSysRuleUserService.deleteByIds(Arrays.asList(ids.split(",")));
        return ReslutBean.success("删除成功");
    }

    /**
     * 单个删除
     * @param id
     * @return
     */
    @RequestMapping("delRule")
    public ReslutBean delRule(String id){
        if (StringUtils.isBlank(id)){
            return ReslutBean.error("角色id不能为空");
        }

        iSysRuleUserService.deleteById(id);
        return ReslutBean.success("删除成功");
    }
}
