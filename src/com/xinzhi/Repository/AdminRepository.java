package com.xinzhi.Repository;

import com.xinzhi.entity.Admin;

public interface AdminRepository {
    Admin login(String username,String password);
}
