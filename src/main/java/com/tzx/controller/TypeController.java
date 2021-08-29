package com.tzx.controller;

import com.tzx.pojo.Category;
import com.tzx.service.TypeService;
import com.tzx.util.AjaxResult;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    // 跳转分类管理页面
    @RequestMapping("/bookType")
    public String bookType() {
        return "book/bookType";
    }

    // 查询类别数据
    @RequestMapping("/bookTypeList")
    @ResponseBody
    public String bookTypeList() {
        ArrayList<Category> listCategory = typeService.listCategory();

        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", listCategory.size());
        obj.put("data", listCategory);
        return obj.toString();
    }

    // 添加类别
    @RequestMapping("/addBookType")
    @ResponseBody
    public AjaxResult addBookType(String cname) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            typeService.addBookType(cname);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }

        return ajaxResult;
    }

    // 修改类别
    @RequestMapping("/editBookType")
    @ResponseBody
    public AjaxResult editBookType(Category category) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            typeService.updateBookType(category);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("更新失败");
        }
        return ajaxResult;
    }

    // 删除类别
    @RequestMapping("/delBookType")
    @ResponseBody
    public AjaxResult delBookType(String cid) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            typeService.delBookType(Integer.parseInt(cid));
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("删除失败");
        }
        return ajaxResult;
    }
}
