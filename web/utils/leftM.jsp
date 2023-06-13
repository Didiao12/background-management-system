<%--
  Created by IntelliJ IDEA.
  User: 16673
  Date: 2023/5/31
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="tools.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>首页 - 低调(Di Diao Admin)后台管理系统模板</title>
    <link rel="icon" href="${ctx}/static/imgs/favicon.ico" type="image/ico">
    <meta name="keywords" content="LightYear,光年,后台模板,后台管理系统,光年HTML模板">
    <meta name="description" content="LightYear是一个基于Bootstrap v3.3.7的后台管理系统的HTML模板。">
    <meta name="author" content="yinqi">
    <link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link href="${ctx}/static/bootstrap/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="${ctx}/static/bootstrap/css/style.min.css" rel="stylesheet">
    <style type="text/css">        body {
        background-color: #ffffff;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 14px;
        line-height: 1.5;
        margin: 0;
        padding: 0;
    }

    code {
        color: #ce9b9b;
        background-color: black;
        border-radius: 4px;
        font-size: 90%;
        padding: 2px 4px;
    }

    .container {
        position: fixed;
        bottom: 0px;
        right: 0px;
        width: 33%;
        margin: 50px auto;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        border-radius: 10px;
        overflow: hidden;
        background-color: #ffffff;
    }

    .header {
        width: 105%;
        background-color: #288bd4;
        color: #ffffff;
        padding: 10px;
        font-size: 18px;
        font-weight: bold;
        position: relative;
        right: 15px;
    }

    .header span {

        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        right: 10px;
    }

    .chat-box {
        max-height: 300px;
        height: 82%;
        overflow-y: scroll;
        scrollbar-width: none;
        padding: 20px;
    }

    .chat-box::-webkit-scrollbar {
        display: none;
    }

    .message-container {
        display: flex;
        flex-direction: column;
        margin-bottom: 10px;
    }

    .message {
        display: inline-block;
        padding: 10px;
        border-radius: 10px;
        max-width: 80%;
        margin-bottom: 10px;
    }

    .sent-message {
        align-self: flex-end;
        background-color: #288bd4;
        color: #ffffff;
    }

    .received-message {
        align-self: flex-start;
        background-color: #f1f0f0;
        color: #000000;
    }

    .message time {
        font-size: 12px;
        font-style: italic;
        margin-top: 5px;
        text-align: right;
    }

    .input-box {
        margin-left: -16px;
        width: 105%;
        background-color: #f1f0f0;
        padding: 10px;
        display: flex;
        align-items: center;
    }

    .input-box input[type="text"] {
        flex-grow: 1;
        border-radius: 20px;
        border: none;
        margin-right: 10px;
        padding: 10px;
        font-size: 16px;
    }

    .input-box input[type="submit"] {
        background-color: #288bd4;
        color: #ffffff;
        border: none;
        border-radius: 20px;
        padding: 10px 20px;
        font-size: 16px;
        cursor: pointer;
    }

    .Tan {
        width: 100%;
        height: calc(100vh - 42px);
        background-color: rgba(0, 0, 0, .3);
        position: absolute;
        top: 0;
        left: 0;
    }

    .t1 {
        white-space: pre-wrap;
    }

    /* 弹窗样式 */
    #myModal {
        display: none;
        position: absolute;
        z-index: 1;
        left: 0;
        bottom: 0;
        width: 100%;
        height: 89%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.5);
    }

    /* 转圈等待动画 */
    #myModal .loader {
        border: 3px solid #f3f3f3;
        border-top: 3px solid #3498db;
        border-radius: 50%;
        width: 33px;
        height: 33px;
        animation: spin 2s linear infinite;
        position: absolute;
        left: 45%;
        top: 41%;
        transform: translate(-50%, -50%);
    }

    /* 动画效果*/
    @keyframes spin {
        0% {
            transform: rotate(0deg);
        }

        100% {
            transform: rotate(360deg);
        }
    }
    </style>
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <aside class="lyear-layout-sidebar">

            <!-- logo -->
            <div id="logo" class="sidebar-header">
                <a href="${ctx}/api/menu/showMenu"><img src="${ctx}/static/bootstrap/images/logo-sidebar.png"
                                                              title="LightYear" alt="LightYear"/></a>
            </div>
            <div class="lyear-layout-sidebar-scroll">
                <nav class="sidebar-main">
                    <ul class="nav nav-drawer">
                        <%-- 遍历一级菜单      --%>
                        <c:forEach var="i" items="${MenuList}">
                        <li class="nav-item
                                <%-- 如果当前菜单有下级菜单的话 那么将其增加class样式    --%>
                           <c:if test="${not empty i.list}">
                           nav-item-has-subnav
                            </c:if>
                              <c:if test="${i.id==1}"><%-- 默认选中首页    --%>
                              active
                        </c:if>

                            "  onclick="Onthis(this,'${i.href}') " name="menu">
                            <a href="javascript:void(0)"><i class="${i.icon}"></i> ${i.title}</a>
                            <c:if test="${not empty i.list}">
                                <%-- 如果当前菜单有下级菜单的话 遍历其下级菜单    --%>
                            <ul class="nav nav-subnav">
                            <c:forEach var="j" items="${i.list}">

                                <li onclick="Onthis(this,'${j.href}')" name="menu"><a href="#!">${j.title}</a></li>
                                <li class="nav-item nav-item-has-subnav">
                                </li>

                            </c:forEach>
                            </ul>
                            </c:if>
                            </c:forEach>

                            <li class="nav-item"><a onclick="javascript:document.getElementById('chat').style.display='block'">ChatGPT3.5</a> </li>

                    </ul>
                </nav>

                <div class="sidebar-footer">
                    <p class="copyright">Copyright &copy; 2019. All rights reserved. More Templates </p>
                </div>
            </div>

        </aside>


    </div>
</div>
</body>
<!--End 页面主要内容-->
<script src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="https://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/main.min.js"></script>

<!--图表插件-->
<script type="text/javascript" src="https://s3.pstatp.com/cdn/expire-1-M/Chart.js/2.7.0/Chart.min.js"></script>
</html>
