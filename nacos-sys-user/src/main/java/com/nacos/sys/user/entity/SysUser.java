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
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId("u_id")
    private String uId;

    /**
     * 用户名称
     */
    @TableField("u_name")
    private String uName;

    /**
     * 用户密码
     */
    @TableField("u_pwd")
    private String uPwd;

    /**
     * 用户邮箱
     */
    @TableField("u_emil")
    private String uEmil;

    /**
     * 用户手机号码
     */
    @TableField("u_phone_number")
    private String uPhoneNumber;

    /**
     * 用户性别
     */
    @TableField("u_sex_cd")
    private Integer uSexCd;

    /**
     * 用户状态
     */
    @TableField("u_state_cd")
    private Integer uStateCd;

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
        return this.uId;
    }

}
