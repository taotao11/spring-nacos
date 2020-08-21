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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
@RequestMapping("/user/sys-user")
public class SysUserController {

    @Autowired
    public ISysUserService userService;

    @Autowired
    public ISysRuleUserService iSysRuleUserService;

    @PostMapping("/add")
    public ReslutBean addSysUser(@RequestBody SysUser user){
        user.setUPwd(StringUtils.strTobCrypt(user.getUPwd()));
        user.setDeleteFlag(0);
        if (user != null){
            boolean add = userService.save(user);
        }
        return ReslutBean.success("新增成功");
    }
    @RequestMapping("userInfo")
    public ReslutBean userInfo(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ReslutBean.success(user);
    }
    @RequestMapping("/info")
    public ReslutBean info(String id){
        if (StringUtils.isBlank(id)){
            return ReslutBean.error("id不能为空");
        }
        SysUser byId = userService.getById(id);
        return ReslutBean.success(byId);
    }
    @RequestMapping("/list")
    public ReslutBean list(@RequestBody PageQuery<SysUser> query){
        Page<SysUser> sysUserPage = userService.selectUserList(query);
        return ReslutBean.success(sysUserPage);
    }

    /**
     *
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ReslutBean update(@RequestBody SysUser user){
        if (user == null || StringUtils.isBlank(user.getUId())){
            return ReslutBean.error("id不能为空");
        }
        boolean b = userService.updateById(user);
        return ReslutBean.success("修改成功");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("deleteByIds")
    public ReslutBean deleteByIds(String ids){
        if (StringUtils.isBlank(ids)){
            return ReslutBean.error("id不能为空");
        }
        String[] split = ids.split(",");
        List<SysUser> sysUsers = new ArrayList<>();
        SysUser sysUser = null;
        for (String id : split){
            sysUser = new SysUser();
            sysUser.setUId(id);
            sysUser.setDeleteFlag(1);
            sysUser.setUpdateTime(new Date());
            sysUsers.add(sysUser);
        }
        boolean b = userService.updateBatchById(sysUsers);
        return ReslutBean.success("删除成功");
    }

    /**
     * 单个删除
     * @param id
     * @return
     */
    @RequestMapping("deleteById")
    public ReslutBean deleteById(String id){
        if (StringUtils.isBlank(id)){
            return ReslutBean.error("id不能为空");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUId(id);
        sysUser.setDeleteFlag(1);
        sysUser.setUpdateTime(new Date());
        boolean b = userService.updateById(sysUser);
        return ReslutBean.success("删除成功");
    }
}
