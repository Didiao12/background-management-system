<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 16673
  Date: 2023/6/4
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main class="lyear-layout-content">

    <div class="container-fluid">

        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">

                        <form method="post" action="#!" class="site-form">
                            <div class="form-group">
                                <label for="username">用户名</label>

                                <c:set value="${index}" var="id1"></c:set>

                                <input type="text" class="form-control" name="username" id="username" value="${menuInfo[id1].id}"
                                       disabled="disabled" />
                            </div>

                            <div class="form-group">
                                <label for="title">标题</label>
                                <input type="text" class="form-control" name="nickname" id="title" placeholder="设置标题"
                                       value="${menuInfo[id1].title}">
                            </div>
                            <div class="form-group">
                                <label for="href">href</label>
                                <input type="text" class="form-control" name="nickname" id="href" placeholder='设置href'
                                       value="${menuInfo[id1].href}">
                            </div>
                            <div class="form-group">
                                <label for="icon">图标</label>
                                <input type="text" class="form-control" name="nickname" id="icon" placeholder="设置Icon"
                                       value="${menuInfo[id1].icon}">
                            </div>
                            <div class="form-group">
                                <label for="PId">上级菜单ID</label>
                                <input type="text" class="form-control" name="nickname" id="PId" placeholder="设置上级PID"
                                       value="${menuInfo[id1].PId}">
                            </div>
                            <div class="form-group row">
                                <div class="col-xs-1">启用/禁用</div>
                                <div class="col-xs-0">
                                    <label class="lyear-switch switch-yellow">
                                        <input type="checkbox" checked>
                                        <span></span>
                                    </label>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary">保存</button>
                        </form>

                    </div>
                </div>
            </div>

        </div>

    </div>

</main>