<%--
  Created by IntelliJ IDEA.
  User: 16673
  Date: 2023/6/1
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main class="lyear-layout-content">

  <div class="container-fluid">

    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">

            <form method="post" class="site-form">
              <div class="form-group">
                <label for="old-password">旧密码</label>
                <input type="password" class="form-control" name="oldpwd" id="old-password" placeholder="输入账号的原登录密码">
              </div>
              <div class="form-group">
                <label for="new-password">新密码</label>
                <input type="password" class="form-control" name="newpwd" id="new-password" placeholder="输入新的密码">
              </div>
              <div class="form-group">
                <label for="confirm-password">确认新密码</label>
                <input type="password" class="form-control" name="confirmpwd" id="confirm-password" placeholder="请再次输入新密码">
              </div>
              <button type="button" class="btn btn-primary" onclick="updatePwd()">修改密码</button>
            </form>

          </div>
        </div>
      </div>

    </div>

  </div>

</main>
<!--End 页面主要内容-->
