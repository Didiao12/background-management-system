1<%--
  Created by IntelliJ IDEA.
  User: 16673
  Date: 2023/6/6
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                        <form class="pull-right search-bar" method="get" action="javascript:businessinquire()" role="form">
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
                                <input type="text" class="form-control" value="${keyword}" name="keyword" id="keyword"
                                       placeholder="请输入名称" >
                            </div>
                        </form>
                        <div class="toolbar-btn-action">
                            <a onclick="addbus()"  class="btn btn-primary m-r-5" href="#!" data-target='#exampleModal' data-toggle="modal"><i
                                    class="mdi mdi-plus" ></i> 新增</a>
                            <a onclick="enableBusiness()" class="btn btn-success m-r-5" href="#!"><i
                                    class="mdi mdi-check"></i> 启用</a>
                            <a onclick="deleteBusinessS()" class="btn btn-warning m-r-5" href="#!"><i
                                    class="mdi mdi-block-helper"></i> 禁用</a>

                        </div>
                    </div>
                    <div class="card-body">

                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>
                                        <label class="lyear-checkbox checkbox-primary">
                                            <input onclick="seleckedAll()" type="checkbox" id="check-all"><span></span>
                                        </label>
                                    </th>
                                    <th>编号</th>
                                    <th>名称</th>
                                    <th>密码</th>
                                    <th>昵称</th>
                                    <th>联系方式</th>
                                    <th>地址</th>
                                    <th>创建时间</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="limit">
                                <c:forEach items="${businessList.list}" var="business" varStatus="i">
                                    <tr>
                                     <td><label class='lyear-checkbox checkbox-primary'><input type='checkbox' name='ids[]'value=${business.id}><span></span></label></td>
                                    <td>${i.index+1}</td>
                                    <td>${business.businessName}</td>
                                    <td>${business.password}</td>
                                    <td>${business.nickname}</td>
                                    <td>${business.phone}</td>
                                    <td>${business.address}</td>
                                    <td>${business.createTime}</td>
                                        <c:if test="${business.status==1}">
                                            <td class="text-success">正常</td>
                                        </c:if>
                                        <c:if test="${business.status!=1}">
                                            <td>不正常</td>
                                        </c:if>
                                    <td><div class='btn-group'><a  onclick='businessInfo(${business.id})' data-target='#exampleModal' class='btn btn-xs btn-default' href='#!' title='编辑'data-toggle='modal'><i class='mdi mdi-pencil'></i></a>
                                        <a  onclick='deleteBusiness(${business.id})' class='btn btn-xs btn-default' href='#!' title='删除' data-toggle='tooltip'><i class='mdi mdi-window-close'></i></a></div></td>
                                    </tr>
                                </c:forEach>

                                <%--                                                                       ccccc--%>
                                </tbody>
                            </table>
                        </div>
                        <ul class="pagination" id="btn">
                            <li class="disabled"><span>«</span></li>

                            <c:if test="${businessList.pageCount<=8}">
                                <c:forEach begin="1" end="${businessList.pageCount}" var="i">

                                    <li class="
                                    <c:if test="${page==i}">active</c:if>" onclick="handoff('businessGL','${keyword}',${i},5)"> <span>${i}</span></li>
                                </c:forEach>
                            </c:if>


                            <li><a href="#!">»</a></li>
                        </ul>


                    </div>


                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="exampleModalLabel">编辑角色信息</h4>
                                </div>
                                <div class="modal-body">
                                    <input type="hidden" id="id">
                                    <form id="businessForm">
                                        <input type="hidden" id="roleId">
                                        <div class="form-group">
                                            <label for="userName" class="control-label">账号</label>
                                            <input type="text" class="form-control" id="userName" style="color: red">
                                        </div>
                                        <div class="form-group">
                                            <label for="password" class="control-label">密码</label>
                                            <input type="text" class="form-control" id="password" style="color: red">
                                        </div>
                                        <div class="form-group">
                                            <label for="nickNmae" class="control-label">昵称</label>
                                            <input type="text" class="form-control" id="nickNmae" style="color: red">
                                        </div>
                                        <div class="form-group">
                                            <label for="phone" class="control-label">联系方式</label>
                                            <input type="text" class="form-control" id="phone" style="color: red">
                                        </div>
                                        <div class="form-group">
                                            <label for="address" class="control-label">地址</label>
                                            <input type="text" class="form-control" id="address" style="color: red">
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" id="close" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" onclick="saveBus()">保存</button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <

                </div>
            </div>

        </div>

    </div>

</main>