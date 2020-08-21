package com.nacos.sys.user.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nacos.sys.user.entity.SysMenu;
import com.nacos.sys.user.service.ISysMenuService;
import com.nacos.sys.user.utils.PageQuery;
import com.nacos.sys.user.utils.ReslutBean;
import com.nacos.sys.user.utils.StringUtils;
import com.nacos.sys.user.utils.SysMenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.*;
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
@RequestMapping("/user/sys-menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService iSysMenuService;

    @RequestMapping("add")
    public ReslutBean add(@RequestBody SysMenu sysMenu){
        if (sysMenu  == null){
            return ReslutBean.error("参数不能为空");
        }
        sysMenu.setMId(StringUtils.getUuid());
        sysMenu.setCreateTime(new Date());
        sysMenu.setDeleteFlag(0);
        iSysMenuService.save(sysMenu);
        return ReslutBean.success("新增成功");
    }

    @RequestMapping("/update")
    public ReslutBean update(@RequestBody SysMenu sysMenu){
        if (sysMenu  == null || "".equals(sysMenu.getMId())){
            return ReslutBean.error("id不能为空");
        }
        sysMenu.setUpdateTime(new Date());
        iSysMenuService.updateById(sysMenu);
        return ReslutBean.success("修改成功");
    }

    @RequestMapping("/delById")
    public ReslutBean delById(String id){
        if (StringUtils.isBlank(id)){
            return ReslutBean.error("id不能为空");
        }
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDeleteFlag(1);
        sysMenu.setUpdateTime(new Date());
        sysMenu.setMId(id);
        iSysMenuService.updateById(sysMenu);
        return ReslutBean.success("删除成功");
    }

    @RequestMapping("/page")
    public ReslutBean page(@RequestBody PageQuery<SysMenu> query){
        if (query == null){
            return ReslutBean.error("参数不能为空");
        }
        SysMenu queryEntity = query.getQueryEntity();
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        if (queryEntity != null){

        }
        queryWrapper.eq("delete_flag",0);
        List<SysMenu> list = iSysMenuService.list(queryWrapper);
        //000000 为顶级菜单
        List<SysMenuVo> sysMenuVos = getSysMenuVos(list,"000000");
        return ReslutBean.success(sysMenuVos);
    }
    @RequestMapping("/getMenus")
    public ReslutBean getMenus(){
//        if (query == null){
//            return ReslutBean.error("参数不能为空");
//        }
//        SysMenu queryEntity = query.getQueryEntity();
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
//        if (queryEntity != null){
//
//        }
        queryWrapper.eq("delete_flag",0);
        List<SysMenu> list = iSysMenuService.list(queryWrapper);
        //000000 为顶级菜单
        List<SysMenuVo> sysMenuVos = getSysMenuVos(list,"000000");
        List<Map<String,Object>> menus = getRoutMenus(sysMenuVos);
        return ReslutBean.success(menus);
    }

    /**
     * 组装菜单
     * @param sysMenuVos
     * @return
     */
    public List<Map<String, Object>> getRoutMenus(List<SysMenuVo> sysMenuVos) {
        List<Map<String,Object>> menus = new ArrayList<>();

        if (CollectionUtils.isEmpty(sysMenuVos)){
            return null;
        }
        Map<String,Object> meta = null;
        Map<String,Object> menu = null;
        for(SysMenuVo sysMenuVo : sysMenuVos){
            menu = new HashMap<String, Object>();
            meta = new HashMap<>();
            menu.put("path",sysMenuVo.getMPath());
            menu.put("component",sysMenuVo.getMComponent());
            menu.put("name",sysMenuVo.getMName());
            //
            meta.put("title",sysMenuVo.getMTitle());
            meta.put("icon",sysMenuVo.getMIcon());
            menu.put("meta",meta);
            //递归调用
            menu.put("children",getRoutMenus(sysMenuVo.getChidrenMenus()));
            menus.add(menu);
        }
        return menus;
    }

    /**
     * 递归得到层级菜单
     * @param list
     * @return
     */
    public List<SysMenuVo> getSysMenuVos(List<SysMenu> list,String parentId) {
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        List<SysMenuVo> sysMenuVos = new ArrayList<>();
        Iterator<SysMenu> iterator = list.iterator();
        while (iterator.hasNext()) {
              SysMenu sysMenu = iterator.next();
              //判断是否与父id一样
              if (parentId.equals(sysMenu.getMParentId())) {
                  //复制属性
                  SysMenuVo sysMenuVo = new SysMenuVo();
                  BeanUtils.copyProperties(sysMenu,sysMenuVo);
//                  iterator.remove();
                  List<SysMenuVo> chridSysMenuVos = getSysMenuVos(list, sysMenuVo.getMId());
                  sysMenuVo.setChidrenMenus(chridSysMenuVos);
                  sysMenuVos.add(sysMenuVo);
              }
        }
        return sysMenuVos;
    }
}
