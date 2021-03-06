package com.tzx.service;

import com.tzx.dao.BookMapper;
import com.tzx.pojo.Book;
import com.tzx.pojo.Category;
import com.tzx.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public PageBean<Book> queryBookPage(Map<String, Object> paramMap) {
        PageBean<Book> pageBean = new PageBean<Book>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));
        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Book> datas = bookMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = bookMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public List<Category> listCategory() {
        return bookMapper.listCategory();
    }

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public Book selectById(int book_id) {
        return bookMapper.selectById(book_id);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public void delBook(int book_id) {
        bookMapper.delBook(book_id);
    }
}
