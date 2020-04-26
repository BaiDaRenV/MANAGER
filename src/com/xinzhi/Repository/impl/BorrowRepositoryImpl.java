package com.xinzhi.Repository.impl;

import com.xinzhi.JdbcUtils.JdbcUtil;
import com.xinzhi.Repository.BorrowRepository;
import com.xinzhi.entity.Book;
import com.xinzhi.entity.Borrow;
import com.xinzhi.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {
    @Override
    public void insert(Integer bookid, Integer userid, String borrowtime, String returntime, Integer adminid, Integer state) {
        Connection conn = JdbcUtil.getconn();
        PreparedStatement ps = null;
        String sql = "insert into borrow(bookid,userid,borrowtime,returntime,state) values(?,?,?,?,0)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookid);
            ps.setInt(2, userid);
            ps.setString(3, borrowtime);
            ps.setString(4, returntime);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
    }

    @Override
    public List<Borrow> findAllByUserId(Integer id, Integer index, Integer limit) {
        Connection conn = JdbcUtil.getconn();
        String sql = "select bw.id, bk.`name`,bk.author,bk.publish,bw.borrowtime,bw.returntime,u.username,u.tel,u.cardid,bw.state from  book  bk,borrow bw,user1 u where  u.id=? and bk.id=bw.bookid and bw.userid=u.id order by  bw.id limit ?,? ";
        PreparedStatement ps = null;
        List<Borrow> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setInt(2, index);
            ps.setInt(3, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
//                int bwid=rs.getInt(1);
//                String bkname=rs.getString(2);
//                String bkauthor=rs.getString(3);
//                String bkpublish=rs.getString(4);
//                String borrowtime=rs.getString(5);
//                String returntime=rs.getString(6);
//                String username=rs.getString(7);
//                String user_tel=rs.getString(8);
//                String user_cardid=rs.getString(9);
////                封装
//                Book book=new Book(bkname,bkauthor,bkpublish);
//                User user=new User(username,user_tel,user_cardid);
//                Borrow borrow=new Borrow(bwid,book,user,borrowtime,returntime);
//                list.add(borrow);
                list.add(new Borrow(rs.getInt(1),
                        new Book(rs.getString(2), rs.getString(3), rs.getString(4)),
                        new User(rs.getString(7), rs.getString(8), rs.getString(9)), rs.getString(5), rs.getString(6), rs.getInt(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public int count(Integer userid) {
        Connection conn = JdbcUtil.getconn();
        String sql = "select count(0) from  book  bk,borrow bw,user1 u where  u.id=? and bk.id=bw.bookid and bw.userid=u.id ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count(0)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return count;
    }

    @Override
    public List<Borrow> findAllBorrowBystate(Integer state, Integer index, Integer limit) {
        Connection conn = JdbcUtil.getconn();
        String sql = "select bw.id, bk.`name`,bk.author,bk.publish,bw.borrowtime,bw.returntime,u.username,u.tel,u.cardid,bw.state from  book  bk,borrow bw,user1 u where  bw.state=? and bk.id=bw.bookid and bw.userid=u.id order by  bw.id limit ?,? ";
        PreparedStatement ps = null;
        List<Borrow> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, state);
            ps.setInt(2, index);
            ps.setInt(3, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Borrow(rs.getInt(1),
                        new Book(rs.getString(2), rs.getString(3), rs.getString(4)),
                        new User(rs.getString(7), rs.getString(8), rs.getString(9)), rs.getString(5), rs.getString(6), rs.getInt(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public int Statecount(Integer state) {
        Connection conn = JdbcUtil.getconn();
        String sql = "select count(0) from  book  bk,borrow bw,user1 u where  bw.state=? and bk.id=bw.bookid and bw.userid=u.id ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, state);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count(0)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return count;
    }

    @Override
    public void handle(Integer Borrowid, Integer state, Integer adminid) {
        Connection conn=JdbcUtil.getconn();
        String sql="update borrow set state=?, adminid=? where id=? ";
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,state);
            ps.setInt(2,adminid);
            ps.setInt(3, Borrowid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,null);
        }
    }

}
