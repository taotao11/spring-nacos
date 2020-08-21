package com.nacos.sys.user.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author taotao
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu_rule")
public class SysMenuRule extends Model<SysMenuRule> {

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private String id;
    @TableField("m_id")
    private String mId;
    @TableField("r_id")
    private String rId;

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
        return this.id;
    }


}
