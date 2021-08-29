package com.tzx.dao;

import com.tzx.pojo.Admin;

public interface AdminMapper {

    // 管理员登录验证
    Admin select(Admin ad);

    // 读者修改密码
    void alterpwd(Admin ad);
}
