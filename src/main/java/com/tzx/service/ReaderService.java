package com.tzx.service;

import com.tzx.pojo.Reader;
import com.tzx.util.PageBean;

import java.util.Map;


public interface ReaderService {
    int checkReader(Integer reader_id);

    void addReader(Reader reader);

    Reader select(Reader rd);


    PageBean<Reader> listReader(Map<String, Object> paramMap);

    Reader selectById(Integer id);

    void updateReader(Reader reader);

    void delReader(Integer id);

    void alterpwd(Reader reader);
}
