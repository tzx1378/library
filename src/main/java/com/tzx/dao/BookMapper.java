package com.tzx.dao;

import com.tzx.pojo.Book;
import com.tzx.pojo.Category;

import java.util.List;
import java.util.Map;

public interface BookMapper {

    List<Book> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    //查询图书类别
    List<Category> listCategory();

    void addBook(Book book);

    Book selectById(int book_id);

    void updateBook(Book book);

    void delBook(int book_id);

    void reduceStock(Integer book_id);
}
