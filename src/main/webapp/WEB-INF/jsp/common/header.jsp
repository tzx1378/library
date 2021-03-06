<%--
  Created by IntelliJ IDEA.
  User: WDD
  Date: 2019/6/15
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/code.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/laydate/default/laydate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/layer/default/layer.css">
    <title></title>
    <style type="text/css">
        .layui-table-cell {
            height: 36px;
            line-height: 36px;
        }
    </style>
</head>
<body>
<div class="layui-header">
    <div class="layui-logo">图书管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">

        <c:if test="${admin!=null}">
            <li class="layui-nav-item">
                <a href="javascript:;">图书管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/library/index">图书列表</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/type/bookType">分类管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/reader/readerIndex">读者列表</a></li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/listDisBackAdmin">借阅管理</a></li>
            <li class="layui-nav-item"><a onclick="alterPwd('0');">修改密码</a></li>
        </c:if>
        <c:if test="${reader!=null}">
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/library/frontIndex">图书列表</a></li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/listDisBack">借阅记录</a></li>
            <li class="layui-nav-item"><a onclick="alterPwd('1');">修改密码</a></li>
        </c:if>
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="${pageContext.request.contextPath}/image/微信图片_20200814092758.jpg" class="layui-nav-img">
                ${admin.name}
                ${reader.name}
            </a>
        </li>
        <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/loginout">退出</a></li>
    </ul>
</div>


<div style="padding: 15px;">
</body>
<script src="${pageContext.request.contextPath}/js/layui.js"></script>
<script>

    function alterPwd(state) {//添加
        layer.open({
            type: 2,
            title: '修改密码',
            skin: 'layui-layer-demo', //加上边框
            area: ['500px', '300px'], //宽高
            content: '${pageContext.request.contextPath}/toAlterpwdPage?state=' + state
        });
    }

</script>
</html>
