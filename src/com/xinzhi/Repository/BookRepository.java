package com.xinzhi.Repository;

import com.xinzhi.entity.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll(int index,int limit);
    int  count();
}
