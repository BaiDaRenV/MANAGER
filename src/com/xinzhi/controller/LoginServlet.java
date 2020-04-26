package com.xinzhi.controller;

import com.xinzhi.entity.Admin;
import com.xinzhi.entity.Book;
import com.xinzhi.entity.Borrow;
import com.xinzhi.entity.User;
import com.xinzhi.service.BookService;
import com.xinzhi.service.LoginService;
import com.xinzhi.service.impl.BookServiceImpl;
import com.xinzhi.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService=new LoginServiceImpl();
    /**
     * 登录业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String type=req.getParameter("type");
        Object login = loginService.login(username, password,type);
        if (login!=null){
            HttpSession session=req.getSession();
            switch (type){
                case "user":
                    User user=(User) login;
                    session.setAttribute("user",user);
//                    跳转用户首页
                    resp.sendRedirect("/book?page=1");
                    break;
                case "admin":
                    Admin admin=(Admin) login;
                    session.setAttribute("admin",admin);
                    //                    跳转管理员首页
                    resp.sendRedirect("/admin?method=findAllBorrow&page=1");
                    break;
            }

        }else {
            resp.sendRedirect("login.jsp");
        }
    }
}
