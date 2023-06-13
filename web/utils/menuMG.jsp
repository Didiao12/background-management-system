<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 16673
  Date: 2023/6/3
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main class="lyear-layout-content">

    <div class="container-fluid">

        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-toolbar clearfix">
                        <form class="pull-right search-bar" method="get" action="javascript:inquire()" role="form">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <input type="hidden" name="search_field" id="search-field" value="title">
                                    <button class="btn btn-default dropdown-toggle" id="search-btn"
                                            data-toggle="dropdown" type="button" aria-haspopup="true"
                                            aria-expanded="false">
                                        标题 <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a tabindex="-1" href="javascript:void(0)" data-field="title">标题</a></li>
                                        <li><a tabindex="-1" href="javascript:void(0)" data-field="cat_name">栏目</a>
                                        </li>
                                    </ul>
                                </div>
                                <input type="text" class="form-control" value="" name="keyword" id="keyword"
                                       placeholder="请输入名称">
                            </div>
                        </form>
                        <div class="toolbar-btn-action">
                            <a class="btn btn-primary m-r-5" href="#!"  data-target='#exampleModal' data-toggle="modal" onclick="addM()"><i class="mdi mdi-plus"></i> 新增</a>
                            <a onclick="enableAll()" class="btn btn-success m-r-5" href="#!"><i class="mdi mdi-check"></i> 启用</a>
                            <a onclick="deleteAll()" class="btn btn-warning m-r-5" href="#!"><i class="mdi mdi-block-helper"></i> 禁用</a>

                        </div>
                    </div>
                    <div class="card-body">

                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>
                                        <label class="lyear-checkbox checkbox-primary">
                                            <input onclick="seleckedAll()" type="checkbox"  id="check-all"><span></span>
                                        </label>
                                    </th>
                                    <th>编号</th>
                                    <th>标题</th>
                                    <th>href</th>
                                    <th>图标</th>
                                    <th>上级菜单ID</th>
                                    <th>状态</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="limit">

<%--                                                                       ccccc--%>
                                </tbody>
                            </table>
                        </div>
                        <ul class="pagination" id="btn">
                            <li class="disabled"><span>«</span></li>

                            <c:if test="${pageCount<=8}">
                                <c:forEach begin="1" end="${pageCount}" var="i">

                                    <li class="active"><span>${i}</span></li>
                                </c:forEach>
                            </c:if>


                            <li><a href="#!">»</a></li>
                        </ul>


                    </div>



                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="exampleModalLabel">编辑菜单信息</h4>
                                </div>
                                <div class="modal-body">
                                    <input type="hidden" id="id">
                                    <form id="menuForm">
                                        <div class="form-group">
                                            <label for="title" class="control-label" >标题</label>
                                            <input type="text" class="form-control" id="title" style="color: red">
                                        </div>
                                        <div class="form-group">
                                            <label for="href" class="control-label">href</label>
                                            <input type="text" class="form-control" id="href"style="color: red">
                                        </div>
                                        <div class="form-group">
                                            <label for="ICON" class="control-label">ICON</label>
                                            <input type="text" class="form-control" id="ICON"style="color: red">
                                        </div>
                                        <select class="form-control" id="example-select" name="example-select" size="1">
                                            <option value="-1">请选择</option>
                                            <c:forEach items="${menuGen}" var="menu">
                                                <option name="pids" value="${menu.id}">${menu.title}</option>
                                            </c:forEach>
                                        </select>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" onclick="save()">保存</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </div>

    </div>

</main>