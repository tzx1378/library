package com.tzx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 图书类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    private Integer book_id;// 书本编号
    private String name;
    private String author;
    private String publish;// 出版社
    private String ISBN;// 条形码
    private String introduction;// 介绍
    private String language;// 语言
    private double price;// 价格
    private String pubdate;// 出版时间

    private Integer cid;// 类别编号
    private Category category;// 类别

    private Integer stock;// 库存

}
