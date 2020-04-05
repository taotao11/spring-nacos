package com.nacos.sys.user.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableId("m_id")
    private String mId;

    @TableField("m_parent_id")
    private String mParentId;

    @TableField("m_name")
    private String mName;

    @TableField("m_path")
    private String mPath;

    @TableField("m_redirect")
    private String mRedirect;

    @TableField("m_component")
    private String mComponent;

    @TableField("m_title")
    private String mTitle;

    @TableField("m_keepAlive_cd")
    private Integer mKeepaliveCd;

    @TableField("m_icon")
    private String mIcon;

    /**
     * 删除状态
     */
    @TableField("delete_flag")
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 创建人id
     */
    @TableField("create_id")
    private String createId;

    /**
     * 更新人id
     */
    @TableField("update_id")
    private String updateId;


    @Override
    protected Serializable pkVal() {
        return this.mId;
    }

}
