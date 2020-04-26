package com.xinzhi.service.impl;

import com.xinzhi.Repository.BookRepository;
import com.xinzhi.Repository.BorrowRepository;
import com.xinzhi.Repository.impl.BookRepositoryImpl;
import com.xinzhi.Repository.impl.BorrowRepositoryImpl;
import com.xinzhi.entity.Book;
import com.xinzhi.entity.Borrow;
import com.xinzhi.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class BookServiceImpl implements BookService {
    private  BookRepository bookRepository=new BookRepositoryImpl();
    private BorrowRepository borrowRepository=new BorrowRepositoryImpl();
    private final int limit=5;
    @Override
    public List<Book> findAll(int page) {
        int index=(page-1)*limit;
        return bookRepository.findAll(index,limit);
    }

    @Override
    public int getPages() {
        int count=bookRepository.count();
        int page=0;
        if (count%limit==0){
            page=count/limit;
        }else {
            page=count/limit+1;
        }
        return page;
    }

    @Override
    public int getBorrowPages(Integer userid) {
        int count=borrowRepository.count(userid);
        int page=0;
        if (count%limit==0){
            page=count/limit;
        }else {
            page=count/limit+1;
        }
        return page;
    }

    @Override
    public void addBorrow(Integer bookid, Integer userid) {
//        借书时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime=simpleDateFormat.format(date1);
//        还书时间:借书时间加14天
        Calendar calendar=Calendar.getInstance();/*自动对时间进行计算*/
        int dates=calendar.get(Calendar.DAY_OF_YEAR)+14;
        calendar.set(Calendar.DAY_OF_YEAR,dates);
        Date date2=calendar.getTime();
        String returnTime=simpleDateFormat.format(date2);
        borrowRepository.insert(bookid,userid,borrowTime,returnTime,1,0);
    }

    @Override
    public List<Borrow> findAllBorrowByUserId(Integer id,Integer page) {
//      将page转化为  index 和 limit
        int index=(page-1)*limit;
        return borrowRepository.findAllByUserId(id,index,limit);
    }

    @Override
    public List<Borrow> findAllBorrowBystate(Integer state,Integer page) {
        int index=(page-1)*limit;
        return borrowRepository.findAllBorrowBystate(state,index,limit);
    }

    @Override
    public int getBorrowPagesBystate(Integer state) {
        int count=borrowRepository.Statecount(state);
        int page=0;
        if (count%limit==0){
            page=count/limit;
        }else {
            page=count/limit+1;
        }
        return page;
    }

    @Override
    public void handleBorrow(Integer Borrowid, Integer state,Integer adminid) {
        borrowRepository.handle(Borrowid,state,adminid);
    }
}
