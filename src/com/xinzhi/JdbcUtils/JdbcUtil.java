package com.xinzhi.JdbcUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcUtil {
//    private  final  static  String DRIVER="com.mysql.jdbc.Driver";
//    private  final  static  String URL="jdbc:mysql://localhost:3306/xinzhishop3";
//    private  final  static  String USER="root";
//    private  final  static  String PASS="1234";
//    static {
//        try {
//            Class.forName(DRIVER);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    public static Connection getconn() throws SQLException {
//        return DriverManager.getConnection(URL,USER,PASS);
//    }
//    public static  void close(Connection conn, Statement st, ResultSet rs){
//        if (rs!=null){
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (st!=null){
//            try {
//                st.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (conn!=null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
        private static DataSource dataSource;
        static {
           dataSource=new ComboPooledDataSource("testc3p0");
        }
        public static  Connection getconn(){
            Connection conn=null;
            try {
                conn=dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }
        public static void close(Connection conn,Statement st,ResultSet rs){
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st!=null){
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

}
