<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/21
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="michaelhe">
<link href="<%=request.getContextPath()%>/static/css/style.default.css" rel="stylesheet">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/static/images/favicon.png" type="image/png">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/jquery.tagsinput.css" />
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
<script src="<%=request.getContextPath()%>/static/js/jquery.validate.min.js"></script>
<html>
<head>
  <title>新增用户</title>
<script>
  $(function () {

    jQuery.validator.addMethod("isMobile", function(value, element) {
      var length = value.length;
      var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
      return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");

  $("#form_update").validate({
    errorPlacement : function(error, element) {
      if (element.is(":radio")){
        error.appendTo(element.parent());
      }else if (element.is(":checkbox")){
        error.appendTo(element.parent().parent());
      }else {
//        element.parent().next().html('').child().css("color","#b20910");
        element.parent().next().html('');
        error.appendTo(element.parent().next());
      }
    },
    rules: {
      userPwd:{
        rangelength:[6,12]

      },
      userRePwd:{
        rangelength:[6,12],
        equalTo:"#userPwd"
      },
      userEmail: {
        required: true,
        email: true,
        remote: {
          url: "<%=request.getContextPath()%>/user/checkUpdateEmail",
          type: "post",
          dataType: "json",
          data: {
            email: function () {
              return $("#userEmail").val();　　　　//这个是取要验证的密码
            },
            username:function(){
              return $("#disabledinput").val();
            }
          },
          dataFilter: function (data) {　　　　//判断控制器返回的内容
            if (data == 'true') {
              return false;
            }
            else {
              return true;
            }
          }
        }

      },
      userPhone:{
        required: true,
        minlength : 11,
        isMobile:true,
        remote: {
          url: "<%=request.getContextPath()%>/user/checkPhone",
          type: "post",
          dataType: "json",
          data: {
            phone: function () {
              return $("#userPhone").val();　　　　//这个是取要验证的密码
            },
            username:function(){
              return $("#disabledinput").val();
            }
          },
          dataFilter: function (data) {　　　　//判断控制器返回的内容
            if (data == 'true') {
              return false;
            }
            else {
              return true;
            }
          }
        }
      }

    },
    messages: {

      userEmail:{
        required: "请输入一个邮件地址",
        email:"请输入正确的邮箱！",
        remote:"此邮箱已被占用！"
      },
      userPwd: {
        rangelength: "密码长度6-12位(只能包含字符、数字和下划线)"
      },
      userRePwd: {
        rangelength: "密码长度6-12位(只能包含字符、数字和下划线)",
        equalTo:"两次密码输入不一致！"

      },
      userPhone:{
        required: "请输入手机号",
        minlength : "确认手机不能小于11个字符",
        isMobile:"请输入正确的手机号！",
        remote:"此手机号已被占用！"
      }
    },

    errorElement: "label", //可以用其他标签，记住把样式也对应修改
    success: function(label) {
      //label指向上面那个错误提示信息标签em
      label.text("√");
//      label.text("√").css("color","#24b92b");
    }

  });
  });

</script>





</head>
<body class="signin">

<!-- Preloader -->
<div id="preloader">
  <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>

<section>
  <div class="panel " style="border: #d0cec2 1px solid;overflow:visible;position:relative; top:50px ">
    <div style="border:1px solid #D3EAF3; border-radius: 10px; padding:5px 5px;position:absolute;top:-15px;left:45px ;z-index:100;text-align:center; background: #fff">
          修改用户
    </div>
    <div><br></div>
    <form id="form_update" name="form_update" action="<%=request.getContextPath()%>/user/user_update" method="post" >
      <!-- panel-body -->
      <div class="form-group">
        <input type="hidden" value="${user.id}" name="id">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">用户名:</span></label>
        <div class="col-sm-4">
          <input type="text" name="username" placeholder="Disabled Input" id="disabledinput" value="${user.username}" class="form-control" disabled="">
         </div>
        <div id="userNameDiv" class="col-sm-5" style="padding-top: 15px;color: #b20910" >*</div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">密码:</span></label>
        <div class="col-sm-4">
          <input id="userPwd" name="userPwd"  type="password" placeholder="非必填项" class="form-control" />
        </div>
        <div id="userPwdDiv" class="col-sm-5 " style="padding-top: 15px;color: #b20910"></div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">确认密码:</span></label>
        <div class="col-sm-4">
          <input id="userRePwd" name="userRePwd"  type="password" placeholder="非必填项" class="form-control" />
        </div>
        <div id="userRePwdDiv" class="col-sm-5 " style="padding-top: 15px;color: #b20910"></div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">手机号:</span></label>
        <div class="col-sm-4">
          <input id="userPhone" name="userPhone" value="${user.mobilePhoneNumber}" type="text" placeholder="请输入手机号" class="form-control" />
        </div>
        <div id="userPhoneDiv" class="col-sm-5 " style="padding-top: 15px;color: #b20910">*&nbsp;</div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">邮箱:</span></label>
        <div class="col-sm-4">
          <input id="userEmail" name="userEmail" value="${user.email}" type="text" placeholder="请输入邮箱" class="form-control" />
        </div>
        <div id="userEmailDiv" class="col-sm-5 " style="padding-top: 15px;color: #b20910">*&nbsp;</div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">状态:</span></label>
        <div class="col-sm-4">
          <label class="checkbox-inline control-label col-sm-3"  style="padding-top: 4px">
            <input type="radio" name="userStatus" value="normal" <c:if test="${user.status=='normal'}">checked</c:if>> 正常
          </label>
          <label class="checkbox-inline control-label col-sm-3"  style="padding-top: 4px">
            <input type="radio" name="userStatus" value="blocked" <c:if test="${user.status=='blocked'}">checked</c:if>> 锁定
          </label>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">管理员:</span></label>
        <div class="col-sm-4">
          <label class="checkbox-inline control-label col-sm-3"  style="padding-top: 4px">
            <input type="radio" name="userPremission" value="1" <c:if test="${user.admin==true}">checked</c:if>> 是
          </label>
          <label class="checkbox-inline control-label col-sm-3"  style="padding-top: 4px">
            <input type="radio" name="userPremission" value="0" <c:if test="${user.admin==false}">checked</c:if>> 不是
          </label>
        </div>


      </div>


      <div class="panel-footer">
        <div class="row pull-left" style="padding-left: 300px" >
          <div class="col-sm-6 col-sm-offset-3" >
            <button type="submit" id="insert" class="btn btn-success" >保存</button>
          </div>
        </div>
        <div class="row pull-left"  style="padding-left: 10px">
          <div class="col-sm-6 col-sm-offset-3">
            <button type="reset" class="btn btn-primary"> 重置</button>
          </div>
        </div>
        <div class="row pull-left" style="padding-left: 10px" >
          <div class="col-sm-6 col-sm-offset-3" >
            <button type="button" id="back" class="btn btn-success" onclick="window.history.back()">返回</button>
          </div>
        </div>
      </div>

    </form>
  </div><!-- panel -->
</section>


</body>
</html>
