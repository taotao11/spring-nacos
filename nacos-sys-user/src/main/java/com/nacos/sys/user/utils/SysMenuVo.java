package com.nacos.sys.user.utils;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.nacos.sys.user.entity.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2020-04-05
 */
public class SysMenuVo extends SysMenu {

    private static final long serialVersionUID = 1L;

    private List<SysMenuVo> chidrenMenus = new ArrayList<>();

    public List<SysMenuVo> getChidrenMenus() {
        return chidrenMenus;
    }

    public void setChidrenMenus(List<SysMenuVo> chidrenMenus) {
        this.chidrenMenus = chidrenMenus;
    }
}
