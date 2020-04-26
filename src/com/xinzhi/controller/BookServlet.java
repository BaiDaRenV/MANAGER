package com.xinzhi.controller;

import com.xinzhi.Repository.BorrowRepository;
import com.xinzhi.Repository.impl.BorrowRepositoryImpl;
import com.xinzhi.entity.Book;
import com.xinzhi.entity.Borrow;
import com.xinzhi.entity.User;
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
@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService=new BookServiceImpl();
    private BorrowRepository borrowRepository=new BorrowRepositoryImpl();
    /**
     * 加载图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String method=req.getParameter("method");
       if (method==null){
           method="findAll";
       }
        HttpSession session=req.getSession();
        User user= (User) session.getAttribute("user");
//       流程控制
        switch (method){
            case "findAll":
                String pagestr=req.getParameter("page");
                Integer page=Integer.parseInt(pagestr);
                List<Book> list=bookService.findAll(page);
                req.setAttribute("list",list);
                req.setAttribute("datePrePage",5);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getPages());
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                break;
            case "addBorrow":
                String bookidStr=req.getParameter("bookid");
                Integer bookid=Integer.parseInt(bookidStr);
//                添加借书请求
                bookService.addBorrow(bookid,user.getId());
                resp.sendRedirect("/book?method=findAllBorrow&page=1");
//                展示当前用户的所有借书记录
                break;
            case "findAllBorrow":
                pagestr=req.getParameter("page");
                page=Integer.parseInt(pagestr);
                List<Borrow> list1=bookService.findAllBorrowByUserId(user.getId(),page);
                req.setAttribute("list1",list1);
                req.setAttribute("datePrePage",5);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPages(user.getId()));
                req.getRequestDispatcher("borrow.jsp").forward(req,resp);
                break;
        }
    }
}
