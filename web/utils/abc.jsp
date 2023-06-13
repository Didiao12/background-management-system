<%--
  Created by IntelliJ IDEA.
  User: 16673
  Date: 2023/5/31
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="leftM.jsp" %>
<%@include file="head.jsp" %>
<div id="S_body">

</div>


    <div class="container" id="chat" style="display: none">
        <div class="header"> ChatGPT3.5 <span onclick="javascript:document.getElementById('chat').style.display='none'">隐藏</span></div>
        <div class="chat-box">
            <div class="message-container" id="chatK">
                <div class="message received-message"><p>你好，有什么需要帮忙的吗？</p>
                    <time>2023-06-08 12:39:42</time>
                </div>

            </div>
        </div>

        <div class="input-box"><input id="information" type="text" placeholder="输入消息..."> <input onclick="send()"
                                                                                                     type="submit"
                                                                                                           value="发送"></div>
        <div id="myModal" >
            <div class="loader"></div>
        </div>
    </div>



<%--<c:if test="${sessionScope.get('flag')=='Home'}">--%>
<%--    <%@include file="Home.jsp"%>--%>
<%--</c:if>--%>

<%--<c:if test="${sessionScope.get('flag')=='updatePwd'}">--%>
<%--    <%@include file="updatePwd.jsp"%>--%>
<%--</c:if>--%>

<%--<c:if test="${sessionScope.get('flag')=='MyInfo'}">--%>
<%--    <%@include file="MyInfo.jsp"%>--%>
<%--</c:if>--%>
<script type='text/javascript' src="${ctx}/static/js/marked.js"></script>

<script>

    //柱状图专用JS
    function chartJs(e) {
        var $dashChartBarsCnt = jQuery('.js-chartjs-bars')[0].getContext('2d');
        $dashChartLinesCnt = jQuery('.js-chartjs-lines')[0].getContext('2d');

        var $dashChartBarsData = {
            labels: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
            datasets: [
                {
                    label: '注册用户',
                    borderWidth: 1,
                    borderColor: 'rgba(0,0,0,0)',
                    backgroundColor: 'rgba(51,202,185,0.5)',
                    hoverBackgroundColor: "rgba(51,202,185,0.7)",
                    hoverBorderColor: "rgba(0,0,0,0)",
                    data: [2500, 1500, 1200, 3200, 4800, 3500, 1500]
                }
            ]
        };
        var $dashChartLinesData = {
            labels: ['2003', '2004', '2005', '2006', '2007', '2008', '2009', '2010', '2011', '2012', '2013', '2014'],
            datasets: [
                {
                    label: '交易资金',
                    data: [20, 25, 40, 30, 45, 40, 55, 40, 48, 40, 42, 50],
                    borderColor: '#358ed7',
                    backgroundColor: 'rgba(53, 142, 215, 0.175)',
                    borderWidth: 1,
                    fill: false,
                    lineTension: 0.5
                }
            ]
        };

        new Chart($dashChartBarsCnt, {
            type: 'bar',
            data: $dashChartBarsData
        });

        var myLineChart = new Chart($dashChartLinesCnt, {
            type: 'line',
            data: $dashChartLinesData,
        });
    }

    //------柱状图结束

    //切换页面专用JS
    function handoff(value, keyword, pageIndex, rows) {
        if (typeof (keyword) == "undefined") {
            keyword = "";
        }
        if (typeof (pageIndex) == "undefined") {
            pageIndex = 1;
        }
        if (typeof (rows) == "undefined") {
            rows = 5;
        }
        $.ajax({
            url: "${ctx}/api/pages/tap?flag=" + value + "&keyword=" + keyword + "&pageIndex=" + pageIndex + "&rows=" + rows,
            type: "get",
            success(res) {
                let s_body = document.getElementById("S_body")
                s_body.innerText = "";
                s_body.innerHTML = res;
                let HeadTitle = document.getElementById("HeadTitle");
                if (value.indexOf("menuMG") != -1) {
                    OnAll();
                    menuLimit(1, 5);
                    HeadTitle.innerText="菜单管理";
                }else if(value.indexOf("Home")!=-1){
                    HeadTitle.innerText="后台首页";
                    chartJs();
                }else if(value.indexOf("businessGL")!=-1){
                    HeadTitle.innerText="商家管理";
                }else if(value.indexOf("roleMenu")!=-1){
                    HeadTitle.innerText="角色管理";
                }else if(value.indexOf("UserGL")!=-1){
                    HeadTitle.innerText="用户管理";
                }else if(value.indexOf("MyInfo")!=-1){
                    HeadTitle.innerText="个人信息";
                }else if(value.indexOf("updatePwd")!=-1){
                    HeadTitle.innerText="密码修改";
                }


            }
        })
    }

    //切换页面结束

    function menuLimit(pageIndex, rows, value) {

        if (!value) {
            value = ""
        }
        if (typeof (rows) == "undefined" || rows == null) {
            rows = 5;
        }
        if (typeof (pageIndex) == "undefined") {
            pageIndex = 1;
        }
        $.ajax({
            url: "${ctx}/api/menu/limit?pageIndex=" + pageIndex + "&rows=" + rows + "&keyword=" + value,
            type: "get",
            dataType: "json",
            success(res) {
                if (res.code == 200) {
                    var limit = document.getElementById("limit");
                    limit.innerText = "";
                    var list = res.data.list;
                    for (let i = 0; i < list.length - 1; i++) {
                        var tr = document.createElement("tr");
                        var td = document.createElement("td");
                        td.innerHTML = "<label class='lyear-checkbox checkbox-primary'><input type='checkbox' name='ids[]' value=" + list[i].id + "><span></span></label>"
                        tr.appendChild(td)
                        var td = document.createElement("td");
                        td.innerText = i + 1;
                        tr.appendChild(td)
                        var td = document.createElement("td");
                        td.innerText = list[i].title;
                        tr.appendChild(td)
                        var td = document.createElement("td");
                        td.innerText = list[i].href;
                        tr.appendChild(td)
                        var td = document.createElement("td");
                        td.innerText = list[i].icon;
                        tr.appendChild(td)
                        var td = document.createElement("td");
                        td.innerText = typeof (list[i].name) == "undefined" ? "" : list[i].name;
                        tr.appendChild(td)
                        var td = document.createElement("td");
                        if (list[i].status == 1) {
                            td.innerHTML = "<font class='text-success'>正常</font>";
                        } else {
                            td.innerHTML = "<font style='color:red;'>禁用</font>";
                        }
                        tr.appendChild(td)
                        var td = document.createElement("td");
                        td.innerText = list[i].createtime;
                        tr.appendChild(td)
                        var td = document.createElement("td");
                        td.innerHTML = " <td><div class='btn-group'><a  onclick='menuInfo(" + list[i].id + ")' data-target='#exampleModal' class='btn btn-xs btn-default' href='#!' title='编辑'data-toggle='modal'><i class='mdi mdi-pencil'></i></a><a  onclick='deleteMenu(" + list[i].id + ")' class='btn btn-xs btn-default' href='#!' title='删除' data-toggle='tooltip'><i class='mdi mdi-window-close'></i></a></div></td>"
                        tr.appendChild(td)
                        limit.appendChild(tr);
                    }
                    var btn = document.getElementById("btn");
                    btn.innerText = "";
                    btn.innerHTML = "<li class='disabled' onclick='menuLimit(1)'><span>«</span></li>";

                    for (let i = 1; i <= res.data.pageCount; i++) {
                        if (pageIndex == i) {
                            btn.innerHTML += "<li  class='active'><span>" + i + "</span></li>";
                        } else {
                            btn.innerHTML += "<li onclick='menuLimit(" + i + ",null,\"" + value + "\")'><span>" + i + "</span></li>";
                        }

                    }
                    btn.innerHTML += "<li class='disabled' onclick='menuLimit(" + res.data.pageCount + ")'><span>👉</span></li>";

                }


            }

        })
    }

    function enableAll() {
        let arr = getSelected();
        if (arr != null && arr != "") {
            enableMenu(arr);
        } else {
            alert("未选择任何菜单")
        }
    }

    function deleteAll() {
        let arr = getSelected();
        if (arr != null && arr != "") {
            deleteMenu(arr);
        } else {
            alert("未选择任何菜单")
        }

    }

    function getSelected() {
        let ids = document.getElementsByName("ids[]");
        let idArr = "";
        for (let i = 0; i < ids.length; i++) {
            if (ids[i].checked) {
                idArr += ids[i].value + ",";
            }

        }
        idArr = idArr.substring(0, idArr.length - 1);
        return idArr;
    }

    function enableMenu(id) {
        if (confirm("确认启用吗？")) {
            if (id != null && id != "") {
                updateMenu(id, "enable");
            }
        }

    }

    function deleteMenu(id) {
        if (confirm("确认删除吗？")) {
            if (id != null && id != "") {
                updateMenu(id, "delete");
            }
        }

    }

    function updateMenu(id, value) {
        $.ajax({
            url: "${ctx}/api/menu/" + value,
            type: "post",
            dataType: "json",
            data: {
                id: id
            },
            success(res) {
                if (res.code == 200) {
                    alert(res.msg)
                    menuLimit();
                } else {
                    alert(res.msg)
                }
            }

        })
    }


    let _id;
    let _title;
    let _href;
    let _ICON;
    let _pid;

    //编辑信息 重新获取一次当前菜单信息
    function menuInfo(id) {
        type = 2;
        document.getElementById("menuForm").reset();
        document.getElementById("exampleModalLabel").innerText = "编辑菜单";
        if (id != null && id != "") {
            $.ajax({
                url: "${ctx}/api/menu/getMenu",
                type: "post",
                dataType: "json",
                data: {
                    id: id
                }, success(res) {
                    if (res.code == 200) {
                        //获取信息成功时
                        _id = document.getElementById("id");
                        _id.value = typeof (res.data.id) == "undefined" ? "" : res.data.id;
                        _id = _id.value;
                        _title = document.getElementById("title");
                        _title.value = typeof (res.data.title) == "undefined" ? "" : res.data.title;
                        _title = _title.value;
                        _href = document.getElementById("href");
                        _href.value = typeof (res.data.href) == "undefined" ? "" : res.data.href
                        _href = _href.value;
                        _ICON = document.getElementById("ICON");
                        _ICON.value = typeof (res.data.icon) == "undefined" ? "" : res.data.icon
                        _ICON = _ICON.value;

                        var pids = document.getElementsByName("pids");
                        for
                        (let i = 0;
                         i < pids.length; i++) {
                            if (pids[i].value == res.data.pId) {
                                pids[i].selected = true;
                            }
                        }

                    } else {
                        alert(res.msg)
                    }
                }

            })


        }

    }

    let id;
    let title;
    let href;
    let ICON;

    function save() {
        _title = "";
        _href = "";
        _ICON = "";
        pid = document.getElementById("example-select").value;
        let url;
        if (type == 1) {
            id = "";
            url = "${ctx}/api/menu/addMenu"
        } else {
            url = "${ctx}/api/menu/edit"
        }
        if (checkMenu()) {
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: {
                    id: id,
                    title: title,
                    href: href,
                    icon: ICON,
                    p_id: pid
                },
                success(res) {
                    if (res.code == 200) {
                        alert("保存成功！");
                        document.getElementById("close").click();
                        menuLimit();

                    } else {
                        alert(res.msg);
                    }
                }
            })
        }
    }

    function checkMenu() {

        if (type != 1) {
            id = document.getElementById("id").value
            if (id == null || id == "") {
                alert("未知错误！！")
                return false;
            }
        }

        title = document.getElementById("title").value
        href = document.getElementById("href").value
        ICON = document.getElementById("ICON").value

        if (title == _title && href == _href && ICON == _ICON) {
            alert("数据未改变")
            return false;
        }


        if (title == null || title == "") {
            alert("标题不能为空")
            return false;
        }
        if (title == _title) {
            title = "";
        }
        if (href == _href) {
            href = "";
        }
        if (ICON == _ICON) {
            ICON = "";
        }
        return true;

    }

    //    update修改密码专用JS

    function updatePwd() {
        let oldPwd = $("#old-password").val();
        let newPwd = $("#new-password").val();
        let confirmpwd = $("#confirm-password").val();
        if (checkPwd(oldPwd, newPwd, confirmpwd)) {
            $.ajax({
                url: "${ctx}/api/user/updatePwd",
                type: "post",
                dataType: "json",
                data: {
                    oldPwd: oldPwd,
                    password: newPwd
                }, success(res) {
                    if (res.code == 200) {
                        //修改成功 YeeYeYee~~~~
                        alert(res.msg);
                        location.href = location.href
                    } else {
                        alert(res.msg)
                    }

                }


            })
        }


    }

    function checkPwd(oldPwd, newPwd, confirmpwd) {
        if (oldPwd == null || oldPwd == "" || newPwd == null || newPwd == "" || confirmpwd == null || confirmpwd == "") {
            alert("不能未空")
            return false;
        }
        if (newPwd != confirmpwd) {
            alert("两次密码输入不一致！")
            return false;
        }
        if (newPwd == oldPwd) {
            alert("新密码不能与旧密码相同！")
            return false;
        }
        if (confirm("确认要修改吗？")) {
            return true;
        } else {
            return false;
        }

    }

    //update密码结束

    var _nickname
    var _tel
    var _address
    //首次进入默认首页
    onload = function () {
        _nickname = "${user.nick_name}";
        _tel = "${user.phone}";
        _address = "${user.address}";
        $.ajax({
            url: "${ctx}/api/pages/tap",
            type: "get",
            success(res) {
                let s_body = document.getElementById("S_body")
                s_body.innerText = "";
                s_body.innerHTML = res;
                chartJs();
            }
        })
    }

    //进入首页结束-->

    //更新信息JS
    function update() {

        $.ajax({
            url: "${ctx}/api/user/update",
            type: "post",
            dataType: "json",
            data: {
                nick_name: nickname,
                phone: tel,
                address: address
            }, success(res) {
                if (res.code == 200) {
                    alert(res.msg);
                    location.href = location.href
                } else {
                    alert(res.msg)
                }

            }

        })
    }

    var nickname
    var tel
    var address

    function check() {
        nickname = document.getElementById("nickname").value;
        tel = document.getElementById("tel").value;
        address = document.getElementById("address").value;


        if (nickname == null || nickname == "" || tel == null || tel == "" || address == null || address == "") {
            alert("修改信息不能不空！");
            return false;
        }
        if (_nickname == nickname && _tel == tel && _address == address) {
            alert("信息未变动！");
            return false;
        }
        if (nickname == _nickname) {
            nickname = "";
        }
        if (tel == _tel) {
            tel = "";
        }
        if (address == _address) {
            address = "";
        }
        if (confirm("确认要改动吗？")) {
            update();
        } else {
            return false;
        }
    }

    //更新信息结束

    //动态选择菜单 设置样式

    function Onthis(e, href) {
        if (e.className.indexOf("open") != -1) {
            return;
        }
        var menu = document.getElementsByName("menu");
        for (let i = 0; i < menu.length; i++) {
            menu[i].className = menu[i].className.replace("active", "");
        }
        e.className = e.className + " active";
        if (href) {
            handoff(href);
        }


    }

    //设置样式结束

    //菜单管理JS
    function OnAll() {
        $('.search-bar .dropdown-menu a').click(function () {
            var field = $(this).data('field') || '';
            $('#search-field').val(field);
            $('#search-btn').html($(this).text() + ' <span class="caret"></span>');
        });
    }

    var type;

    function addM() {
        document.getElementById("exampleModalLabel").innerText = "新增菜单";
        type = 1;
        document.getElementById("menuForm").reset();

    }

    let keyW

    function inquire() {
        var keyword = document.getElementById("keyword").value;
        if (keyW == keyword) {
            return false
        }
        keyW = keyword;
        menuLimit(1, 5, keyword);
    }

    var roleNmae;

    function addR() {
        document.getElementById("roleForm").reset();
        document.getElementById("exampleModalLabel").value = "新增角色";
        type = 1;

    }

    function roleInfo(id) {
        document.getElementById("roleId").value = id;
        document.getElementById("roleForm").reset();
        document.getElementById("exampleModalLabel").value = "编辑角色";
        type = 2;
        $.ajax({
            url: "${ctx}/api/role/getRole",
            type: "post",
            dataType: "json",
            data: {
                id: id
            }, success(res) {
                if (res.code == 200) {
                    roleNmae = res.data.name
                    document.getElementById("roleName").value = res.data.name;
                } else {
                    alert(res.msg);
                }
            }

        })
    }


    function saveRole() {
        editRoleAndSave();
    }

    function editRoleAndSave() {
        let value = document.getElementById("roleName").value;
        let roleId = document.getElementById("roleId").value;
        let url;
        if (type == 1) {
            url = "${ctx}/api/role/save";
        } else {
            url = "${ctx}/api/role/edit";
        }

        if (value != roleNmae) {
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: {
                    id: roleId,
                    name: value
                }, success(res) {
                    if (res.code == 200) {
                        alert("保存成功！")
                        document.getElementById("close").click();
                        handoff('roleMenu');

                    } else {
                        alert(res.msg);
                    }
                }

            })
        } else {
            alert("内容未改变！");
        }
    }


    function deleteRole(id) {
        type = 1;
        deleteRl(id);
    }


    function deleteRoles() {
        type = 1;
        var ids = getSelected();
        deleteRl(ids);

    }

    function enableRoles() {
        type = 2;
        var ids = getSelected();
        deleteRl(ids);

    }

    function deleteRl(id) {
        let url;
        if (type == 1) {
            url = "${ctx}/api/role/delete";
        } else {
            url = "${ctx}/api/role/enable"
        }
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {
                id: id
            }, success(res) {
                if (res.code == 200) {
                    alert("修改成功");
                    document.getElementById("close").click();
                    handoff('roleMenu');
                } else {
                    alert(res.msg)
                }
            }
        })
    }

    function Roleinquire() {
        var keyword = document.getElementById("keyword").value;
        if (keyW == keyword) {
            return false
        }
        handoff('roleMenu', keyword);
    }

    function Userinquire() {
        var keyword = document.getElementById("keyword").value;
        if (keyW == keyword) {
            return false
        }
        handoff('UserGL', keyword);
    }

    function businessinquire() {
        var keyword = document.getElementById("keyword").value;
        if (keyW == keyword) {
            return false
        }
        handoff('businessGL', keyword);
    }

    function UserInfo(id) {
        type = 2;
        document.getElementById("id").value = id;
        document.getElementById("userForm").reset();
        document.getElementById("exampleModalLabel").innerText = "编辑用户";
        $.ajax({
            url: "${ctx}/api/user/getUser",
            type: "post",
            dataType: "json",
            data: {
                id: id
            }, success(res) {
                if (res.code == 200) {
                    roleNmae = res.data.name
                    document.getElementById("userName").value = res.data.userName;
                    document.getElementById("nickNmae").value =
                        res.data.nick_name;
                    document.getElementById(
                        "phone").value = res.data
                        .phone
                    ;
                    document.getElementById("address").value = res.data.address;
                    var rids = document.getElementsByName("rids");
                    for (let i = 0; i < rids.length; i++) {
                        if (rids[i].value == res.data.role_id) {
                            rids[i].selected = true;
                            break;
                        }
                    }
                    _nick_name = res.data.nick_name;
                    _phone = res.data.phone;
                    _address = res.data.address;
                    _username = res.data.userName;
                    _role_id = res.data.role_id;

                    document.getElementById("userName").setAttribute("disabled", "disabled");

                } else {
                    alert(res.msg);
                }
            }

        })
    }


    function saveUser() {
        editUser()
    }

    let nickNmae;
    let userName;
    let password;
    let phone;
    let addressU;
    let role_id;

    function editUser() {
        let id = document.getElementById("id").value;
        if (type == 1) {
            id = "";
        }
        nickNmae = document.getElementById("nickNmae").value;
        userName = document.getElementById("userName").value;
        password = document.getElementById("password").value;
        phone = document.getElementById("phone").value;
        addressU = document.getElementById("address").value;
        role_id = document.getElementById("example-select").value;
        let url;

        if (checkUser()) {
            $.ajax({
                url: "${ctx}/api/user/edit",
                type: "post",
                dataType: "json",
                data: {
                    id: id,
                    nick_name: nickNmae,
                    password: password,
                    phone: phone,
                    address: addressU,
                    user_name: userName,
                    role_id: role_id
                }, success(res) {
                    if (res.code == 200) {
                        alert("保存成功！")
                        document.getElementById("close").click();
                        handoff('UserGL');
                    } else {
                        alert(res.msg);
                    }
                }

            })
        } else {
            alert("内容未改变！");
        }
    }

    function addU() {
        type = 1;
        document.getElementById("userForm").reset();
        document.getElementById("exampleModalLabel").innerText = "新增用户";
        document.getElementById("userName").removeAttribute("disabled");
    }
function addbus(){
    type = 1;
    document.getElementById("businessForm").reset();
    document.getElementById("exampleModalLabel").innerText = "新增用户";
    document.getElementById("userName").removeAttribute("disabled");
}
    var _nick_name
    var _phone;
    var _address;
    var _password;
    var _username;
    var _role_id;

    function checkUser() {

        if (nickNmae == _nick_name && phone == _phone && addressU == _address && nickNmae == _nick_name && userName == _username && role_id == _role_id && _password == password) {
            return false;
        }

        if (nickNmae == _nick_name) {
            nickNmae = "";
        }
        if (phone == _phone) {
            phone = "";
        }
        if (addressU == _address) {
            addressU = "";
        }
        if (role_id == _role_id) {
            role_id = "";
        }


        if (password == "" || typeof (password) == "undefined") {
            password = "";
            if (userName == _username) {
                userName = "";
            }
            if (type==1) {
                alert("密码不能为空");
                return false;
            }
        }


        return true;

    }

    function enableUsers() {
        if (confirm("确认启用吗？")) {
            type = 2;//启用
            var ids = getSelected()
            updateUserStatus(ids);
        }

    }

    function deleteUsers() {
        if (confirm("确认删除吗？")) {
            type = 1;
            var ids = getSelected()
            updateUserStatus(ids);
        }


    }

    function deleteUser(id) {
        if (confirm("确认删除吗？")) {
            type = 1 //删除 禁用
            updateUserStatus(id);
        }
    }

    function updateUserStatus(id) {
        var url;
        var status;
        if (type == 1) {
            status = 0;
        } else {
            status = 1;
        }
        $.ajax({
            url: "${ctx}/api/user/edit",
            type: "post",
            dataType: "json",
            data: {
                id: id,
                status: status
            }, success(res) {
                if (res.code == 200) {
                    alert("修改成功")
                    handoff('UserGL');
                } else {
                    alert(res.msg)
                }
            }

        })

    }



    function businessInfo(id) {
        type = 2;
        document.getElementById("id").value = id;
        document.getElementById("businessForm").reset();
        document.getElementById("exampleModalLabel").innerText = "编辑商家";
        $.ajax({
            url: "${ctx}/api/business/getbusiness",
            type: "post",
            dataType: "json",
            data: {
                id: id
            }, success(res) {
                if (res.code == 200) {
                    roleNmae = res.data.name
                    document.getElementById("userName").value = res.data.businessname;
                    document.getElementById("nickNmae").value =res.data.nickname;
                    document.getElementById("phone").value = res.data.phone
                    document.getElementById("address").value = res.data.address;
                    _nick_name = res.data.nickname;
                    _username = res.data.businessname;
                    _phone = res.data.phone;
                    _address = res.data.address;

                    document.getElementById("userName").setAttribute("disabled", "disabled");

                } else {
                    alert(res.msg);
                }
            }

        })
    }



    function saveBus(){
        addOrEditBus()
    }


    function addOrEditBus(){
        let id = document.getElementById("id").value;
        if (type == 1) {
            id = "";
        }
        nickNmae = document.getElementById("nickNmae").value;
        userName = document.getElementById("userName").value;
        password = document.getElementById("password").value;
        phone = document.getElementById("phone").value;
        addressU = document.getElementById("address").value;
        let url;

        if (checkUser()) {
            $.ajax({
                url: "${ctx}/api/business/edit",
                type: "post",
                dataType: "json",
                data: {
                    id: id,
                    nickname: nickNmae,
                    password: password,
                    phone: phone,
                    address: addressU,
                    businessName: userName,
                }, success(res) {
                    if (res.code == 200) {
                        alert("保存成功！")
                        document.getElementById("close").click();
                        handoff('businessGL');
                    } else {
                        alert(res.msg);
                    }
                }

            })
        } else {
            alert("内容未改变！");
        }
    }


    function deleteBusiness(id){
        if (confirm("确认删除吗？")) {
            type = 1 //删除 禁用
            updateBusinessStatus(id);
        }
    }
    function enableBusiness() {
        if (confirm("确认启用吗？")) {
            type = 2;//启用
            var ids = getSelected()
            updateBusinessStatus(ids);
        }

    }
    function deleteBusinessS() {
        if (confirm("确认删除吗？")) {
            type = 1;
            var ids = getSelected()
            updateBusinessStatus(ids);
        }


    }
    function updateBusinessStatus(id){
        var status;
        if (type == 1) {
            status = 0;
        } else {
            status = 1;
        }
        $.ajax({
            url: "${ctx}/api/business/edit",
            type: "post",
            dataType: "json",
            data: {
                id: id,
                status: status
            }, success(res) {
                if (res.code == 200) {
                    alert("修改成功")
                    handoff('businessGL');
                } else {
                    alert(res.msg)
                }
            }

        })
    }

    function seleckedAll(){
        let checked = document.getElementById("check-all").checked;
        let ids = document.getElementsByName("ids[]");
        for (let i = 0; i < ids.length; i++) {
            ids[i].checked=checked;
        }
    }






    //TODO ChatGPT 专属JS
     function Yj(e){
         $.ajax({
            url:"https://api.binjie.fun/api/generateStream",
            type:"POST",
            dataType:"text",
            data:{
                network:true,
                prompt:e,
                stream:false,
                system:"",
                userId:"#/chat/1686117877553",
                withoutContext:false
            },success(res){
                document.getElementById("myModal").style.display="none";
                let div = document.createElement("div");
                div.className="message received-message";
                let p = document.createElement("p");
                let time = document.createElement("time");
                time.innerHTML=new Date();

                p.innerHTML=marked.parse(res);
                div.appendChild(p);
                div.appendChild(time);



                document.getElementById("chatK").appendChild(div);
                //document.getElementById("chatK").innerHTML+="<div class='message received-message'><p></p><time>"+new Date()+"</time></div>   "

            }

        })
    }

    function send() {
        let value=document.getElementById("information").value;
        document.getElementById("information").value="";
        if (value!=""){
            document.getElementById("chatK").innerHTML+=" <div class='message sent-message'><p>"+value+"</p><time>"+new Date()+"</time></div>"

            document.getElementById("myModal").style.display="block";
            Yj(value);
        }

    }
</script>



