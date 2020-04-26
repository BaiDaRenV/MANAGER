package com.xinzhi.Repository;

import com.xinzhi.entity.User;

public interface UserRepository {
    User login(String username, String password);
}
