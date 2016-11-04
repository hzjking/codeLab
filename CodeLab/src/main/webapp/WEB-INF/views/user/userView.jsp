<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="michaelhe">
    <link href="<%=request.getContextPath()%>/static/css/style.default.css" rel="stylesheet">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/static/images/favicon.png" type="image/png">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bootstrap-timepicker.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/jquery.tagsinput.css" />
</head>

<body class="signin">

<!-- Preloader -->
<div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>
<section>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="panel-btns">
                <a href="#" class="panel-close">&times;</a>
                <a href="#" class="minimize">&minus;</a>
            </div><!-- panel-btns -->
            <h3 class="panel-title">用户列表</h3>
        </div>
        <form id="queryUser" name="queryUser" action="" method="post" onsubmit="return usersubmit()">
            <div class="form-group">

                <div class="col-sm-3">
                    <label class="control-label">用户名:</label>
                    <input id="userName" name="userName" type="text" placeholder="请输入用户名且长度不能小于3" class="form-control" onfocus="userNameFocus()"  onblur="userNameBlur()"/>
                </div>
                <div id="userNameDiv" class="col-sm-3" style="margin-top: 25px" ></div>

                <div class="col-sm-3">
                    <label class="control-label">手机号:</label>
                    <input id="userPhone" name="userPhone" type="text" placeholder="请输入手机号" class="form-control" onfocus=" userPhoneFocus()" onblur=" userPhoneBlur()"/>
                </div>
                <div id="userPhoneDiv" class="col-sm-3 " style="margin-top: 35px;color: #b20910"></div>
            </div>
            <div class="form-group">
                <div class="col-sm-3">
                    <label class="control-label">开始时间：</label>
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="mm/dd/yy" id="startTime" name="startTime" onfocus="startTimeFocus()" onblur="startTimeBlur()">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                    </div>
                </div>
                <div id="" class="col-sm-3" style="margin-top: 25px" ></div>
                <div class="col-sm-3">
                    <label class="control-label">结束时间：</label>
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="mm/dd/yy" id="endTime" name="endTime" onfocus="endTimeFocus()" onblur="endTimeBlur()">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <label class="control-label">状态</label>
                    <input type="hidden" id="selectValue"/>
                    <select id="userStatus" name="userStatus" class="form-control mb15">
                        <option value="normal">正常</option>
                        <option value="blocked">锁定</option>
                    </select>
                </div>
            </div>
            <div class="panel-footer">
                <div class="row pull-right"  style="padding-left: 10px">
                    <div class="col-sm-6 col-sm-offset-3" >
                        <a href="#"  id="search" class="btn btn-primary"  role="button">查询</a>
                    </div>
                </div>
                <div class="row pull-right"  style="padding-left: 10px">
                    <div class="col-sm-6 col-sm-offset-3">
                        <button type="button" id="modify" class="btn btn-primary"  onclick="updateUser();" > 修改</button>
                    </div>
                </div>
                <div class="row pull-right"   style="padding-left: 10px">
                <div class="col-sm-6 col-sm-offset-3">
                    <button type="button" id="insert" class="btn btn-primary" onclick="insertUser()"> 新增</button>
                </div>

            </div>
                <div class="row pull-right"   style="padding-left: 10px">
                    <div class="col-sm-6 col-sm-offset-3">
                        <a href="#" id="userExcel" class="btn btn-primary" role="button" onclick="excelExport()"> 导出</a>
                    </div>

                </div>
            </div>
            <div class="panel-body">
                <div id="c_lable">

                <div id="turn_pages_s.earch">
                    <input type="hidden" id="pageNo" name="pageNo">
                    <c:choose>
                        <c:when test="${page.pageNo==1}">
                            <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/static/images/previous.gif" border="0" /></a>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:void(0);prevPage(${page.pageNo});"><img src="<%=request.getContextPath()%>/static/images/previous.gif" border="0" /></a>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach items="${page.pageNums}" var="i">
                        <c:choose>
                            <c:when test="${page.pageNo==i}">
                                <a href="javascript:queryOrderListForPage(${i});void(0);">${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:queryOrderListForPage(${i});void(0);">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${page.pageNo==page.totalPage}">
                            <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/static/images/next.gif" border="0" /></a>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:void(0);nextPage(${page.pageNo});"><img src="<%=request.getContextPath()%>/static/images/next.gif" border="0" /></a>
                        </c:otherwise>
                    </c:choose>

                </div>
                    </div>


                <div class="table-responsive">
                    <table class="table  table-striped table-hover mb30" id="userTable">
                        <thead>
                        <tr>
                            <th></th>
                            <th>序号</th>
                            <th>用户名</th>
                            <th>邮箱</th>
                            <th>手机号</th>
                        </tr>
                        </thead>
                        <tbody  id="user_tb">
                        <c:if test="${!empty users}">
                            <c:forEach items="${users}" var="u">
                                <tr class="odd gradeX" onclick="do_check(this);">
                                    <td><input name="td_checkbox" id="${u.id}" onclick="do_checkbox(this);" type="checkbox" ></td>
                                    <td>${u.id}</td>
                                    <td>${u.username}</td>
                                    <td>${u.email}</td>
                                    <td>${u.mobilePhoneNumber}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty users}">
                            <tr class="odd gradeX">
                                <td colspan="4" align="center"><span style="color: #b20910">没有符合条件的结果，请确认查询条件！</span></td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>

                </div>
            </div><!-- panel-body -->
        </form>
    </div><!-- panel -->
</section>

<script src="<%=request.getContextPath()%>/static/js/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery-ui-1.10.3.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/modernizr.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery.sparkline.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/toggles.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/retina.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery.cookies.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery.autogrow-textarea.js"></script>
<script src="<%=request.getContextPath()%>/static/js/bootstrap-timepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery.maskedinput.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery.tagsinput.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jquery.mousewheel.js"></script>
<script src="<%=request.getContextPath()%>/static/js/chosen.jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/custom.js"></script>
<%-- <script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/static/js/userRule.js"></script>--%>
<script type="text/javascript" language="javascript">

    jQuery(document).ready(function(){
        // Date Picker
        jQuery('#startTime').datepicker();

        jQuery('#endTime').datepicker();

        $('#search').click(AjaxSubmit);
    });


    function queryOrderListForPage(pageNo){
        $("#pageNo").val(pageNo);
        AjaxSubmit();
    }
    function nextPage(pageNo){
        $("#pageNo").val(pageNo+1);
        AjaxSubmit();
    }
    function prevPage(pageNo){
        $("#pageNo").val(pageNo-1);
        AjaxSubmit();
    }


    function AjaxSubmit(){
        if(usersubmit()){
            $.ajax({
                type: "POST",
                cache:false,
                url:'<%=request.getContextPath()%>/user/user_queryList',
                data:$('#queryUser').serialize(),
                success: function (data){
                    data=eval('(' + data + ')');
                    var  arr= data.mngUserPageDataDTO.data;
                    var page=new Object();
                    page.pageNo=data.pageNo;
                    page.pageNums=data.pageNums;
                    page.totalPage=data.totalPage;
                    $('#user_tb').children('tr').remove();
                    $('#c_lable').children('div').remove();

                    var clable="" ;
                    clable+="<div id='turn_pages_s.earch' >"+
                    "<input type='hidden' id='pageNo' name='pageNo'>";
                    if(page.pageNo==1 || page.pageNo==null){
                        clable+="<a href='javascript:void(0);'><img src='/FactoringMngWeb/static/images/previous.gif' border='0'/>&nbsp;</a>";
                    }else{
                        clable+="<a href='javascript:void(0);prevPage("+page.pageNo+");'><img src='/FactoringMngWeb/static/images/previous.gif' border='0' />&nbsp;</a>";
                    }
                    var i=1;
                    for(i;i<=page.pageNums.length;i++){
                        if(page.pageNo==i){
                            clable+= "<a href='javascript:queryOrderListForPage("+i+");void(0);'>"+i+"&nbsp;</a>";
                        }else{
                            clable+="<a href='javascript:queryOrderListForPage("+i+");void(0);'>"+i+"&nbsp;</a>";
                        }
                    }
                    if(page.pageNo==page.totalPage){
                        clable+= "<a href='javascript:void(0);'><img src='/FactoringMngWeb/static/images/next.gif' border='0' />&nbsp;</a>";
                    }else{
                        clable+= "<a href='javascript:void(0);nextPage("+page.pageNo+");'><img src='/FactoringMngWeb/static/images/next.gif' border='0'/>&nbsp;</a>";
                    }
                    "</div>";
                    $('#c_lable').append(clable);

                    var tbody = "";
                    if(arr==null||arr==""){
                        tbody = "<tr class='odd gradeX'><td colspan='8' align='center'><span style='color: #b20910'>没有符合条件的结果，请确认查询条件！</span></td></tr>";
                    }else{
                        $.each(arr,function () {

                            tbody += "<tr class='odd gradeX 'onclick='do_check("+this+");'><td>"+
                            "<input name='td_checkbox'id="+this.id+" onclick='do_checkbox("+this+");' type='checkbox'>"+"</td><td>"+
                            this.id+"</td><td>"+ this.username+"</td><td>"+this.email+"</td><td>"
                            +this.mobilePhoneNumber+"</td></tr>";

                        });
                    }
                    $('#user_tb').append(tbody);
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert("加载失败");
                }
            });
        }
    }


    function excelExport(){
        if(confirm("确定要导出数据？")){
           location.href="<%=request.getContextPath()%>/user/user_excel";
        }
    }

    function userNameFocus(){
        document.getElementById("userName").style.borderColor="#D3EAF3 ";
    }


    function userNameBlur(){
        document.getElementById("userName").style.borderColor="d0cec2 ";
        var userName=document.getElementById("userName");
        var userNameDiv=document.getElementById("userNameDiv");
        var userNameReg=/^[a-zA-Z]\w{3,50}$/;
        userNameDiv.className="col-sm-3";
        userNameDiv.innerHTML="";
        if(!userNameReg.test(userName.value) && userName.value!=""){
            document.getElementById("userNameDiv").style.color="#b20910";
            userNameDiv.innerHTML="用户名只能以字母开头(由字母、数字、下划线组合)";
            return false;
        }else if(userNameReg.test(userName.value) && userName.value!=""){
            document.getElementById("userNameDiv").style.color="#24b92b";
            document.getElementById("userNameDiv").className='col-sm-3 fa fa-check';
            return true;
        }
    }
    function userPhoneFocus(){
        document.getElementById("userPhone").style.borderColor="#D3EAF3 ";
    }


    function userPhoneBlur(){
        document.getElementById("userPhone").style.borderColor="d0cec2 ";
        var userPhoneReg=/^1[3|4|5|7|8][0-9]\d{8}$/;
        var userPhoneDiv=document.getElementById("userPhoneDiv");
        userPhoneDiv.className="col-sm-3";
        userPhoneDiv.innerHTML="";
        var userPhone=document.getElementById("userPhone");
        if(!userPhoneReg.test(userPhone.value) && userPhone.value!=""){
            document.getElementById("userPhoneDiv").style.color="#b20910";
            userPhoneDiv.innerHTML="请检查手机号格式！";
            return false;
        }else if(userPhoneReg.test(userPhone.value) && userPhone.value!=""){
            document.getElementById("userPhoneDiv").style.color="#24b92b";
            document.getElementById("userPhoneDiv").className='col-sm-3 fa fa-check';
            return true;
        }



    }
    function startTimeFocus(){
        document.getElementById("startTime").style.borderColor="#D3EAF3 ";
    }


    function startTimeBlur(){
        document.getElementById("startTime").style.borderColor="d0cec2 ";
    }
    function endTimeFocus(){
        document.getElementById("endTime").style.borderColor="#D3EAF3 ";
    }



    function endTimeBlur(){
        document.getElementById("endTime").style.borderColor="d0cec2 ";
    }
    function userTime(){
        var startTime=document.getElementById("startTime");
        var endTime=document.getElementById("endTime");
        if(startTime.value==""&& endTime.value==""){
            return true;
        }
        var arr = startTime.value.split("/");
        var stime = new Date(arr[0], arr[1], arr[2]);
        var stimes = stime.getTime();
        var arrs = endTime.value.split("/");
        var dtime = new Date(arrs[0], arrs[1], arrs[2]);
        var dtimes = dtime.getTime();
        if(dtimes >= stimes){
            return true;
        }else{
            alert("请检查输入的日期范围！");
            return false;
        }
    }
    function usersubmit(){
            if( userTime()){
                return true;
        }
        return false;
    }
    //复选的单选实现
    function do_check(param){
//            $(param).siblings().children().children(":first").attr('checked',"false");
        var box=$(param).children().children(":first");
        if(box.is(":checked")){
            box.attr("checked",false);
        }else{
            $("input:checkbox[name=td_checkbox]").each(function(){
                $(this).attr("checked",false);
            });
            box.attr("checked",true);
        }
    }
    //防止冒泡
    function do_checkbox(){
        if(event.stopPropagation) { //W3C阻止冒泡方法
            event.stopPropagation();
        } else {
            event.cancelBubble = true; //IE阻止冒泡方法
        }
    }
    //修改
    function updateUser(){
        var obj=$("input:checkbox[name=td_checkbox]:checked").attr("id");

        if(typeof(obj)=="undefined"){
//                alert("选中一行修改！");
            confirm("请选中一行修改！");
            return ;
        }
        location.href="<%=request.getContextPath()%>/user/user_updateInit?id="+obj;

    }
    //新增
    function insertUser(){
        location.href="<%=request.getContextPath()%>/user/prepareInsert_user";
    }




    function refresh(url){
        window.location=url;
    }

</script>
</body>
</html>
