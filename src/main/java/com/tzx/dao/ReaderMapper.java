package com.tzx.dao;

import com.tzx.pojo.Reader;

import java.util.List;
import java.util.Map;

public interface ReaderMapper {
    // 查询用户存在该用户
    int checkReader(Integer reader_id);

    // 添加读者信息
    void addReader(Reader reader);

    Reader select(Reader rd);

    List<Reader> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    Reader selectById(Integer id);

    void updateReader(Reader reader);

    void delReader(Integer id);

    void alterpwd(Reader reader);
}
