package com.tzx.controller;

import com.tzx.pojo.Admin;
import com.tzx.pojo.LendInfo;
import com.tzx.pojo.Reader;
import com.tzx.service.AdminService;
import com.tzx.service.LendInfoSerivce;
import com.tzx.service.ReaderService;
import com.tzx.util.AjaxResult;
import com.tzx.util.Const;
import com.tzx.util.PageBean;
import com.tzx.util.StringUtil;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class commonController {

    @Autowired
    private ReaderService readerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private LendInfoSerivce lendInfoSerivce;

    AjaxResult ajaxResult = new AjaxResult();

    //跳转登录界面
    @RequestMapping("/toLogin")
    public String login() {
        return "login";
    }

    // 跳转注册页面
    @RequestMapping("/toRegister")
    public String register() {
        return "register";
    }

    // 注册查询是否存在该用户
    @RequestMapping("/checkReader")
    @ResponseBody
    public AjaxResult checkReader(Integer reader_id) {
        int count = readerService.checkReader(reader_id);
        if (count == 0) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("当前学号已被占用");
        }

        return ajaxResult;
    }

    // 注册读者信息
    @RequestMapping("/submitAddReader")
    @ResponseBody
    public AjaxResult submitAddReader(Reader reader) {
        try {
            readerService.addReader(reader);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("注册成功");
        } catch (Exception e) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("注册失败");
        }
        return ajaxResult;
    }

    //登录
    @RequestMapping("/doLogin")
    @ResponseBody
    public AjaxResult list(String name, String password, String access, HttpSession session) {
        try {
            if (access.equals("0")) {
                // 管理员
                Admin ad = new Admin();
                ad.setName(name);
                ad.setPassword(Integer.parseInt(password));
                Admin admin = adminService.select(ad);
                System.err.println(admin);
                if (admin != null) {
                    //数据库有此用户，存在session中
                    session.setAttribute(Const.ADMIN, admin);
                    ajaxResult.setStatus("0");
                } else {
                    // 数据库没有此用户，给出提示
                    ajaxResult.setStatus("2");
                    ajaxResult.setMessage("用户名或密码错误");
                }
            } else {
                //读者
                Reader rd = new Reader();
                rd.setReader_id(Integer.parseInt(name));
                rd.setPassword(Integer.parseInt(password));
                Reader reader = readerService.select(rd);
                if (reader != null) {
                    session.setAttribute(Const.READER, reader);
                    ajaxResult.setStatus("1");
                } else {
                    ajaxResult.setStatus("2");
                    ajaxResult.setMessage("用户名或密码错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setStatus("3");
            ajaxResult.setMessage("服务器异常,请改天登录");
        }
        return ajaxResult;
    }

    //退出
    @RequestMapping("/loginout")
    public String loginout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    //跳转借阅管理页面
    @RequestMapping("/listDisBackAdmin")
    public String listDisBackAdmin() {
        return "listDisBackAdmin";
    }

    //跳转读者借阅记录页面
    @RequestMapping("/listDisBack")
    public String listDisBack() {
        return "listDisBack";
    }

    // 管理员 借阅管理数据查询
    @RequestMapping("/listDisBackBook")
    @ResponseBody
    public String listDisBackBook(
            @RequestParam(value = "page", defaultValue = "1") Integer pageno,
            @RequestParam(value = "limit", defaultValue = "5") Integer pagesize,
            @RequestParam(value = "power", defaultValue = "0") Integer power,
            String rname, String bname, String state
    ) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageno", pageno);
        paramMap.put("pagesize", pagesize);

        // 判断是否为空
        if (StringUtil.isNotEmpty("rname")) {
            paramMap.put("rname", rname);
        }
        if (StringUtil.isNotEmpty("bname")) {
            paramMap.put("bname", bname);
        }
        if (StringUtil.isNotEmpty("state")) {
            paramMap.put("state", state);
        }
        PageBean<LendInfo> pageBean = lendInfoSerivce.queryLeadInfoPage(paramMap);

        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", pageBean.getTotalsize());
        obj.put("data", pageBean.getDatas());
        return obj.toString();
    }

    // 管理员归还图书
    @RequestMapping("/backBook")
    @ResponseBody
    public AjaxResult backBook(@RequestParam(value = "reader_id", defaultValue = "1") Integer reader_id,
                               @RequestParam(value = "book_id", defaultValue = "1") Integer book_id) {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("reader_id", reader_id);
        ret.put("book_id", book_id);
        try {
            lendInfoSerivce.backBook(ret);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("已归还");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("归还失败");
        }
        return ajaxResult;
    }

    // 跳转修改密码页面
    @RequestMapping("/toAlterpwdPage")
    public String toAlterpwdPage(String state, Model model) {
        model.addAttribute("state", state);
        return "alterPwd";
    }

    // 确认密码
    @RequestMapping("/checkPwd")
    @ResponseBody
    public AjaxResult checkPwd(Integer password, String state, HttpSession session) {
        if (state.equals("0")) {
            Admin admin = (Admin) session.getAttribute(Const.ADMIN);
            if (admin.getPassword().equals(password)) {
                ajaxResult.setSuccess(true);
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
            }
        } else {
            Reader reader = (Reader) session.getAttribute(Const.READER);
            if (reader.getPassword().equals(password)) {
                ajaxResult.setSuccess(true);
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
            }
        }

        return ajaxResult;
    }

    // 修改密码
    @RequestMapping("/alterpwd")
    @ResponseBody
    public AjaxResult alterpwd(Integer password, String state, HttpSession session) {
        try {
            if (state.equals("0")) {
                Admin admin = (Admin) session.getAttribute(Const.ADMIN);
                admin.setPassword(password);
                adminService.alterpwd(admin);
            } else {
                Reader reader = (Reader) session.getAttribute(Const.READER);
                reader.setPassword(password);
                readerService.alterpwd(reader);
            }
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("更改密码成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("更改密码失败");

        }
        return ajaxResult;
    }

}
