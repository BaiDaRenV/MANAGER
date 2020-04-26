package com.xinzhi.service;

import com.xinzhi.entity.Book;
import com.xinzhi.entity.Borrow;

import java.util.List;

public interface BookService {
    List<Book> findAll(int page);
    int getPages();
    int getBorrowPages(Integer userid);
    void addBorrow(Integer bookid,Integer userid);
    List<Borrow> findAllBorrowByUserId(Integer id,Integer page);
    List<Borrow> findAllBorrowBystate(Integer state,Integer page);
    int  getBorrowPagesBystate(Integer state);
    void handleBorrow(Integer Borrowid,Integer state,Integer adminid);
}
