package com.nacos.sys.user.web;


import com.nacos.sys.user.entity.SysUser;
import com.nacos.sys.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/add")
    public SysUser addSysUser(@RequestBody SysUser user){
        if (user != null){
            boolean add = userService.insert(user);
        }

        return user;
    }

}
