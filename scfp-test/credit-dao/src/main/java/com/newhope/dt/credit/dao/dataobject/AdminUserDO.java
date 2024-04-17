package com.newhope.dt.crpt.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;
/**
 * 用户信息表(AdminUser)表实体类
 *
 * @author xiaoshichuan
 * @version 2023-03-09 09:56:10
 */
@Data
@Accessors(chain = true)
@TableName("admin_user")
public class AdminUserDO {

    /** 员工id */
    @TableId(type = IdType.ASSIGN_ID)
    private String userId;

    /** 飞书头像连接 */
    private String larkPicture;

    /** 飞书账号名 */
    private String larkAccount;

    /** 飞书openid */
    private String larkOpenId;

    /** 部门ID */
    private String deptId;

    /** 员工账号（登陆账号） */
    private String userAccount;

    /** 员工姓名 */
    private String userName;
    /** 密码 */
    private String password;

    /** 原业务归属在后序业务修改为应用授权的作用:0-默认都不属于 1-信用 2-担保 3-信用+担保*/
    private String businessAttr;

    /** 后序业务扩展中了该业务归属方字段为， 0-担保、1-信用、2-产业方、3-开发、4-运营、5-金服*/
    private String businessOwnership;

    /** 该用户是否展示飞书小程序二维码*/
    private String displayTheFeiShuQrCode;

    /** 数据权限类型 1:本人,2-本人+指定部门 3-全部 */
    private String dataScopeType;

    /** 员工邮箱 */
    private String email;

    /** 身份证号 */
    private String certNo;

    /** 生日 */
    private LocalDateTime birthday;

    /** 员工EBS编号 */
    private String userEbsNo;

    /** 员工OA账号 */
    private String oaAccount;

    /** 职位 */
    private String job;

    /** 员工手机号 */
    private String phoneNumber;

    /** 办公电话 */
    private String tel;

    /** 员工性别（0男 1女 2未知） */
    private String sex;

    /** 头像地址(头像文件Id) */
    private String avatarId;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** token */
    private String token;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登陆IP */
    private String loginIp;

    /** 最后登陆时间 */
    private LocalDateTime loginDate;

    /** 上次修改密码时间 */
    private LocalDateTime lastModTime;

    /** 登录密码输入错误次数大于5次锁定 */
    private String failCount;

    /** 数据迁移备注 */
    private String migrateRemark;

    /** 备注 */
    private String remark;

    /** 创建者user_id */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新者user_id */
    private String updateBy;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 邀请码 */
    private String invitationCode;
}

