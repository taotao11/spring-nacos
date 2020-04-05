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
@TableName("sys_rule")
public class SysRule extends Model<SysRule> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("r_id")
    private String rId;

    /**
     * 角色名称
     */
    @TableField("r_name")
    private String rName;

    /**
     * 角色描述
     */
    @TableField("r_description")
    private String rDescription;

    /**
     * 角色状态
     */
    @TableField("r_state_cd")
    private Integer rStateCd;

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
        return this.rId;
    }

}
