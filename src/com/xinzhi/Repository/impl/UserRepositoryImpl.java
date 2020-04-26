package com.xinzhi.Repository.impl;

import com.xinzhi.JdbcUtils.JdbcUtil;
import com.xinzhi.Repository.UserRepository;
import com.xinzhi.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User login(String username, String password) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        User user=null;
        try {
            conn= JdbcUtil.getconn();
            String sql="select * from user1 where username=? and password=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while (rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setCardid(rs.getString("cardid"));
                user.setTel(rs.getString("tel"));
                user.setGender(rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return user;
    }
}
