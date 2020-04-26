package com.xinzhi.controller;

import com.xinzhi.Repository.BookRepository;
import com.xinzhi.entity.Admin;
import com.xinzhi.entity.Borrow;
import com.xinzhi.service.BookService;
import com.xinzhi.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private BookService bookService=new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String method=req.getParameter("method");
       if (method==null){
           method="findAllBorrow";
       }
       switch (method){
           case "findAllBorrow":
               String pagestr=req.getParameter("page");
              Integer page=Integer.parseInt(pagestr);
               List<Borrow> list=bookService.findAllBorrowBystate(0,page);
               req.setAttribute("list",list);
               req.setAttribute("datePrePage",5);
               req.setAttribute("currentPage",page);
               req.setAttribute("pages",bookService.getBorrowPagesBystate(0));
               req.getRequestDispatcher("admin.jsp").forward(req,resp);
               break;
           case "handle":
               String idstr=req.getParameter("id");
               String statestr=req.getParameter("state");
               Integer id=Integer.parseInt(idstr);
               Integer state=Integer.parseInt(statestr);
               HttpSession session=req.getSession();
               Admin admin= (Admin) session.getAttribute("admin");
               bookService.handleBorrow(id,state,admin.getId());
               if (state==1 || state==2){
                   resp.sendRedirect("/admin?page=1");
               }
              else if (state==3){
                   resp.sendRedirect("/admin?method=getBorrow&page=1");
               }
               break;
           case "getBorrow":
               pagestr=req.getParameter("page");
               page=Integer.parseInt(pagestr);
               list=bookService.findAllBorrowBystate(1,page);
               req.setAttribute("list",list);
               req.setAttribute("datePrePage",5);
               req.setAttribute("currentPage",page);
               req.setAttribute("pages",bookService.getBorrowPagesBystate(1));
               req.getRequestDispatcher("return.jsp").forward(req,resp);
               break;
       }
    }
}
