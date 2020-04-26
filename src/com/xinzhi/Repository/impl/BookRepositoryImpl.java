package com.xinzhi.Repository.impl;

import com.xinzhi.JdbcUtils.JdbcUtil;
import com.xinzhi.Repository.BookRepository;
import com.xinzhi.entity.Book;
import com.xinzhi.entity.Bookcase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> findAll(int index,int limit) {
        List<Book> list=new ArrayList<>();
        Connection conn= JdbcUtil.getconn();
        String sql="select * from  book b,bookcase bc where b.bookcase=bc.id order by b.id limit ?,?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,index);
            ps.setInt(2,limit);
            rs=ps.executeQuery();
            while (rs.next()){
                list.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),new Bookcase(rs.getInt(9),rs.getString(10))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return list;
    }

    @Override
    public int count() {

        Connection conn= JdbcUtil.getconn();
        String sql="select count(0) from  book b,bookcase bc where b.bookcase=bc.id";
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count=0;
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
               count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return count;
    }
}
