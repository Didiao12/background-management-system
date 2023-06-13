<%--
  Created by IntelliJ IDEA.
  User: 16673
  Date: 2023/6/1
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="tools.jsp"%>
<main class="lyear-layout-content">

  <div class="container-fluid">

    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">

            <div class="edit-avatar">
              <img src="${ctx}/static/bootstrap/images/users/avatar.jpg" alt="..." class="img-avatar">
              <div class="avatar-divider"></div>
              <div class="edit-avatar-content">
                <button class="btn btn-default">修改头像</button>
                <p class="m-0">选择一张你喜欢的图片，裁剪后会自动生成264x264大小，上传图片大小不能超过2M。</p>
              </div>
            </div>
            <hr>
            <form method="post"  class="site-form">
              <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" class="form-control" name="username" id="username" value="${user.userName}" disabled="disabled" />
              </div>
              <div class="form-group">
                <label for="nickname">昵称</label>
                <input type="text" class="form-control" name="nickname" id="nickname" placeholder="输入您的昵称" value="${user.nick_name}">
              </div>
              <div class="form-group">
                <label for="tel">手机号</label>
                <input type="tel" class="form-control" name="tel" id="tel" aria-describedby="emailHelp" placeholder="请输入正确的手机号" value="${user.phone}">
                <small id="emailHelp" class="form-text text-muted">请保证您填写的手机号是正确的。</small>
              </div>
              <div class="form-group">
                <label for="address">地址</label>
                <textarea class="form-control" name="address" id="address" rows="3" >${user.address}</textarea>
              </div>
              <button type="button" class="btn btn-primary" onclick="check()">保存</button>
            </form>

          </div>
        </div>
      </div>

    </div>

  </div>

</main>
<!--End 页面主要内容-->

