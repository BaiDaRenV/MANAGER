package com.xinzhi.service.impl;

import com.xinzhi.JdbcUtils.JdbcUtil;
import com.xinzhi.Repository.AdminRepository;
import com.xinzhi.Repository.UserRepository;
import com.xinzhi.Repository.impl.AdminRepositoryImpl;
import com.xinzhi.Repository.impl.UserRepositoryImpl;
import com.xinzhi.entity.Admin;
import com.xinzhi.entity.User;
import com.xinzhi.service.LoginService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {
    private UserRepository userRepository=new UserRepositoryImpl();
    private AdminRepository adminRepository=new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password,String type) {
        Object o=null;
        switch (type){
            case "user":
                o=userRepository.login(username,password);
                break;
            case "admin":
                o= adminRepository.login(username,password);
                break;
        }
      return o;
    }
}
