package com.xinzhi.Repository;

import com.xinzhi.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    void insert(Integer bookid,Integer userid,String borrowtime,String returntime,Integer adminid,Integer state);
    List<Borrow> findAllByUserId(Integer id,Integer index,Integer limit);
    int count(Integer userid);
    List<Borrow> findAllBorrowBystate(Integer state, Integer index, Integer limit);
    int Statecount(Integer state);
    void handle(Integer Borrowid,Integer state,Integer adminid);
}
