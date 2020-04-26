package com.xinzhi.Repository.impl;

import com.xinzhi.JdbcUtils.JdbcUtil;
import com.xinzhi.Repository.AdminRepository;
import com.xinzhi.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public Admin login(String username, String password) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Admin admin=null;
        try {
            conn= JdbcUtil.getconn();
            String sql="select * from admin1 where username=? and password=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while (rs.next()){
                admin=new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return admin;
        }
    }

