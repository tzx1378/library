package com.tzx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 管理员类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {

    private Integer id;
    private String name;
    private Integer password;

}
