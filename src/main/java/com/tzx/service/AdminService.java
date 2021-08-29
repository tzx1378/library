package com.tzx.service;

import com.tzx.pojo.Admin;

public interface AdminService {

    Admin select(Admin ad);

    void alterpwd(Admin ad);
}
