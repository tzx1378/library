package com.tzx.service;

import com.tzx.pojo.Category;

import java.util.ArrayList;

public interface TypeService {
    ArrayList<Category> listCategory();

    void updateBookType(Category category);

    void delBookType(Integer cid);

    void addBookType(String cname);
}
