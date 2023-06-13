<%--
  Created by IntelliJ IDEA.
  User: 16673
  Date: 2023/5/30
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="utils/tools.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
  <title>登录页面 - 光年(Light Year Admin)后台管理系统模板</title>
  <link rel="icon" href="static/imgs/favicon.ico" type="image/ico">
  <meta name="keywords" content="LightYear,光年,后台模板,后台管理系统,光年HTML模板">
  <meta name="description" content="LightYear是一个基于Bootstrap v3.3.7的后台管理系统的HTML模板。">
  <meta name="author" content="yinqi">
  <link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-3.3.4.css">
  <link href="static/bootstrap/css/materialdesignicons.min.css" rel="stylesheet">
  <link href="static/bootstrap/css/style.min.css" rel="stylesheet">
  <style>
    .lyear-wrapper {
      position: relative;
    }
    .lyear-login {
      display: flex !important;
      min-height: 100vh;
      align-items: center !important;
      justify-content: center !important;
    }
    .login-center {
      background: #fff;
      min-width: 38.25rem;
      padding: 2.14286em 3.57143em;
      border-radius: 5px;
      margin: 2.85714em 0;
    }
    .login-header {
      margin-bottom: 1.5rem !important;
    }
    .login-center .has-feedback.feedback-left .form-control {
      padding-left: 38px;
      padding-right: 12px;
    }
    .login-center .has-feedback.feedback-left .form-control-feedback {
      left: 0;
      right: auto;
      width: 38px;
      height: 38px;
      line-height: 38px;
      z-index: 4;
      color: #dcdcdc;
    }
    .login-center .has-feedback.feedback-left.row .form-control-feedback {
      left: 15px;
    }
  </style>
</head>

<body>

<div class="row lyear-wrapper">
  <div class="lyear-login">
    <div class="login-center">
      <div class="login-header text-center">
        <a href="#"> <img alt="light year admin" src="static/bootstrap/images/logo-sidebar.png"> </a>
      </div>
      <form action="#!" method="post">
        <div class="form-group has-feedback feedback-left">
          <input type="text" placeholder="请输入您的用户名" class="form-control" name="username" id="username" />
          <span class="mdi mdi-account form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="form-group has-feedback feedback-left">
          <input type="password" placeholder="请输入密码" class="form-control" id="password" name="password" />
          <span class="mdi mdi-lock form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="form-group has-feedback feedback-left row">
          <div class="col-xs-7">
            <input type="text" id="code" name="captcha" class="form-control" placeholder="验证码">
            <span class="mdi mdi-check-all form-control-feedback" aria-hidden="true"></span>
          </div>
          <div class="col-xs-5">
            <img src="captcha" class="pull-right" id="captcha" style="cursor: pointer;" onclick="this.src=this.src+'?d='+Math.random();" title="点击刷新" alt="captcha">
          </div>
        </div>
        <div class="form-group">
          <button class="btn btn-block btn-primary" type="button" onclick="onlogin()">立即登录</button>
        </div>
      </form>
      <hr>
    </div>
  </div>
</div>

<img src="https://i.tuiimg.net/006/2874/1.jpg">
<script src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="https://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
<script type="text/javascript">;</script>
</body>

<script type="text/javascript" src="static/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

  function onlogin(){
    $.ajax({
      url:"${ctx}/api/user/login",
      type:"post",
      dataType:"json",
      data:{
        username: document.getElementById("username").value,
        password:document.getElementById("password").value,
        code:document.getElementById("code").value
      },
      success(res){

        if(res.code==200){
            location.href="${ctx}/api/menu/showMenu";
        }else if (res.code==202){
          alert(res.msg);
          document.getElementById("captcha").onclick();
        }else {
          alert(res.msg);
        }
      }
    })
  }

</script>

</html>


