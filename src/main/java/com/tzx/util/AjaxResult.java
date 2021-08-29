package com.tzx.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AjaxResult {

    private boolean success;// 成功
    private String message;// 消息提示
    private String status;

}
