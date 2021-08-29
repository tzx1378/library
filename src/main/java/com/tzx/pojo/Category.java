package com.tzx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 类别类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    private Integer cid;//类别id
    private String cname;//类别名字
}
