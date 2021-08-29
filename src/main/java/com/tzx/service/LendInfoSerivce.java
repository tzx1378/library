package com.tzx.service;

import com.tzx.pojo.LendInfo;
import com.tzx.util.PageBean;

import java.util.Map;

public interface LendInfoSerivce {
    // 分页查询 人员借书
    PageBean<LendInfo> queryLeadInfoPage(Map<String, Object> paramMap);

    // 归还图书
    void backBook(Map<String, Object> ret);

    // 判断该读者是否已经借阅过该图书
    boolean isLended(LendInfo lendInfo);

    // 判断是否达到借书上限
    Integer cardState(Integer reader_id);

    // 借书成功
    void lendBook(LendInfo lendInfo);
}
