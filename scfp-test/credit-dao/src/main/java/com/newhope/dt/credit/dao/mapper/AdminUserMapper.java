package com.newhope.dt.crpt.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newhope.dt.crpt.dao.dataobject.AdminUserDO;
import org.springframework.stereotype.Repository;

/**
 * 用户信息表(AdminUser)表数据库访问层
 *
 * @author xiaoshichuan
 * @version 2023-03-09 10:45:21
 */
@Repository
public interface AdminUserMapper extends BaseMapper<AdminUserDO> {

}

