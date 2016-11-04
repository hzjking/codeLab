<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/21
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
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
<html>
<head>
    <title>新增用户</title>
  <script type="text/javascript" language="JavaScript">
    var xmlhttp=null;
    //创建异步请求对象
    function init(){
      try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");	//FireFox
      } catch (e) {
        try {
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	//IE
        } catch (e) {
          try {
            xmlhttp = new XMLHttpRequest();		//其他
            if (xmlhttp.overrideMimeType) {
              xmlhttp.overrideMimeType("text/xml");
            }
          } catch (e) {
            alert("异步请求对象创建失败");
          }
        }
      }
    }

    var result;
    function callbackusername(){
      document.getElementById("insert").disabled=true;
      if(xmlhttp.readyState==4&&xmlhttp.status==200){
        result=xmlhttp.responseText;
        if(result=="ok"){
          document.getElementById("insert").disabled=false;
          document.getElementById("userNameDiv").style.color="#24b92b";
          document.getElementById("userNameDiv").className='col-sm-5 fa fa-check';
          return true;
        }
        if(result=="no"){
          document.getElementById("insert").disabled=true;
          document.getElementById("userNameDiv").innerHTML="该用户名已被使用，请重新输入！";
          return false;
        }
      }
    }
    function callbackuserphone(){
      document.getElementById("insert").disabled=true;
      if(xmlhttp.readyState==4&&xmlhttp.status==200){
        result=xmlhttp.responseText;
        if(result=="ok"){
          document.getElementById("insert").disabled=false;
          document.getElementById("userPhoneDiv").style.color="#24b92b";
          document.getElementById("userPhoneDiv").className='col-sm-5 fa fa-check';
          return true;
        }
        if(result=="no"){
          document.getElementById("insert").disabled=true;
          document.getElementById("userPhoneDiv").innerHTML="该手机号已存在，请重新输入！";
          return false;
        }
      }
    }
    function callbackuseremail(){
      document.getElementById("insert").disabled=true;
      if(xmlhttp.readyState==4&&xmlhttp.status==200){
        result=xmlhttp.responseText;
        if(result=="ok"){
          document.getElementById("insert").disabled=false;
          document.getElementById("userEmailDiv").style.color="#24b92b";
          document.getElementById("userEmailDiv").className='col-sm-5 fa fa-check';
          return true;
        }
        if(result=="no"){
          document.getElementById("insert").disabled=true;
          document.getElementById("userEmailDiv").innerHTML="邮箱已被使用，请重新输入！";
          return false;
        }
      }
    }








    function userNameFocus(){
      document.getElementById("userName").style.borderColor="#D3EAF3 ";
      document.getElementById("userNameDiv").innerHTML="";
    }

    function userNameBlur(){
      document.getElementById("userName").style.borderColor="d0cec2 ";
      var userName=document.getElementById("userName");
      var userNameDiv=document.getElementById("userNameDiv");
      var userNameReg=/^[a-zA-Z0-9_.]{3,50}$/;
      userNameDiv.className="col-sm-5";
      userNameDiv.innerHTML="";
      document.getElementById("userNameDiv").style.color="#b20910";
      if(userName.value==""){
        userNameDiv.innerHTML="请输入您的用户名！";
      }
      else if(!userNameReg.test(userName.value) && userName.value!=""){
        userNameDiv.innerHTML="用户名格式错误(由字母、数字、下划线、点组合)";
        return false;
      }else if(userNameReg.test(userName.value) && userName.value!=""){
        init();
        if(xmlhttp==null){
          return false;
        }
        var url="<%=request.getContextPath()%>/user/check_username";
        xmlhttp.open("post",url,true);
        xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlhttp.send("userName="+userName.value);
        xmlhttp.onreadystatechange=callbackusername;
        return true;

      }
    }
    function userPwdFocus(){
      document.getElementById("userPwd").style.borderColor="#D3EAF3 ";
      document.getElementById("userPwdDiv").innerHTML="";
    }

    function  userPwdBlur(){
      document.getElementById("userPwd").style.borderColor="d0cec2 ";
      var  userPwd=document.getElementById("userPwd");
      var userPwdDiv=document.getElementById("userPwdDiv");
      var  userPwdReg=/^[a-zA-Z0-9]\w{5,12}$/;
      userPwdDiv.className="col-sm-5";
      userPwdDiv.innerHTML="";

      document.getElementById("userPwdDiv").style.color="#b20910";
      if(userPwd.value==""){
        userPwdDiv.innerHTML="请设置您的密码！";
      }
      else if(!userPwdReg.test(userPwd.value) &&  userPwd.value!="" && userPwd.value.length<6 ){
        userPwdDiv.innerHTML="密码长度6-12位(只能包含字符、数字和下划线)";
        return false;
      }else if(userPwdReg.test(userPwd.value) &&  userPwd.value!=""&& userPwd.value.length>=6 && userPwd.value.length<=12){
        document.getElementById("userPwdDiv").style.color="#24b92b";
        document.getElementById("userPwdDiv").className='col-sm-5 fa fa-check';
        return true;
      }
    }

    function userRePwdFocus(){
      document.getElementById("userRePwd").style.borderColor="#D3EAF3 ";
      document.getElementById("userRePwdDiv").innerHTML="";
    }

    function  userRePwdBlur(){
      document.getElementById("userRePwd").style.borderColor="d0cec2 ";
      var  userPwd=document.getElementById("userPwd");
      var  userRePwd=document.getElementById("userRePwd");
      var userRePwdDiv=document.getElementById("userRePwdDiv");
      userRePwdDiv.className="col-sm-5";
      userRePwdDiv.innerHTML="";

      document.getElementById("userRePwdDiv").style.color="#b20910";
      if(userRePwd.value==""){
        userRePwdDiv.innerHTML="请输入确认密码！";
        return false;
      }
     else if(userPwd.value!=userRePwd.value){
        userRePwdDiv.innerHTML="两次输入密码不一致！";
        return false;
      }
     else {
        document.getElementById("userRePwdDiv").style.color="#24b92b";
        document.getElementById("userRePwdDiv").className='col-sm-5 fa fa-check';
        return true;
      }
    }
    function userPhoneFocus(){
      document.getElementById("userPhone").style.borderColor="#D3EAF3 ";
      document.getElementById("userPhoneDiv").innerHTML="";
    }

    function userPhoneBlur(){
      document.getElementById("userPhone").style.borderColor="d0cec2 ";
      var userPhoneReg=/^1[3|4|5|7|8][0-9]\d{8}$/;
      var userPhoneDiv=document.getElementById("userPhoneDiv");
      userPhoneDiv.className="col-sm-5";
      userPhoneDiv.innerHTML="";
      var userPhone=document.getElementById("userPhone");
      document.getElementById("userPhoneDiv").style.color="#b20910";
      if( userPhone.value==""){
        userPhoneDiv.innerHTML="请输入您的手机号！";
        return false;
      }
     else if(!userPhoneReg.test(userPhone.value) && userPhone.value!=""){
        userPhoneDiv.innerHTML="请输入正确的手机号！";
        return false;
      }else if(userPhoneReg.test(userPhone.value) && userPhone.value!=""){
        init();
        if(xmlhttp==null){
          return false;
        }
        var url="<%=request.getContextPath()%>/user/check_userphone";
        xmlhttp.open("post",url,true);
        xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlhttp.send("userPhone="+userPhone.value);
        xmlhttp.onreadystatechange=callbackuserphone;
        return true;
      }


    }

    function userEmailFocus(){
      document.getElementById("userEmail").style.borderColor="#D3EAF3 ";
      document.getElementById("userEmailDiv").innerHTML="";
    }

    function  userEmailBlur(){
      document.getElementById("userEmail").style.borderColor="d0cec2 ";
      var userEmailReg= /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
      var userEmialDiv=document.getElementById("userEmailDiv");
      userEmialDiv.className="col-sm-5";
      userEmialDiv.innerHTML="";
      var userEmail=document.getElementById("userEmail");
      document.getElementById("userEmailDiv").style.color="#b20910";
      if( userEmail.value==""){
        userEmialDiv.innerHTML="请输入您的邮箱！";
        return false;
      }
      else if(!userEmailReg.test(userEmail.value) && userEmail.value!=""){
        userEmialDiv.innerHTML="请输入正确的邮箱！";
        return false;
      }else if(userEmailReg.test(userEmail.value) && userEmail.value!=""){
        init();
        if(xmlhttp==null){
          return false;
        }
        var url="<%=request.getContextPath()%>/user/check_useremail";
        xmlhttp.open("post",url,true);
        xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlhttp.send("userEmail="+userEmail.value);
        xmlhttp.onreadystatechange=callbackuseremail;
        return true;
      }


    }
    function usersubmit(){
      if(document.getElementById("userNameDiv").innerHTML==""&&document.getElementById("userPhoneDiv").innerHTML==""&&document.getElementById("userEmailDiv").innerHTML==""&&userNameBlur()&&userPwdBlur()&&userRePwdBlur()&&userPhoneBlur()&&userEmailBlur()){
        return true;
      }
      return false;
    }

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
     新增用户
    </div>
    <div><br></div>
    <form id="" name="" action="<%=request.getContextPath()%>/user/user_insert" method="post" onsubmit="return usersubmit()">
    <!-- panel-body -->
      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">用户名:</span></label>
        <div class="col-sm-4">
          <input id="userName" name="userName" type="text" placeholder="请输入用户名且长度不能小于3" class="form-control" onfocus="userNameFocus()"  onblur="userNameBlur()"/>
        </div>
        <div id="userNameDiv" class="col-sm-5" style="padding-top: 15px;color: #b20910" >*</div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">密码:</span></label>
        <div class="col-sm-4">
          <input id="userPwd" name="userPwd" type="password" placeholder="请设置密码" class="form-control" onfocus=" userPwdFocus()" onblur="userPwdBlur()"/>
        </div>
        <div id="userPwdDiv" class="col-sm-5 " style="padding-top: 15px;color: #b20910">*</div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">确认密码:</span></label>
        <div class="col-sm-4">
          <input id="userRePwd" name="userRePwd" type="password" placeholder="请确认密码" class="form-control" onfocus=" userRePwdFocus()" onblur=" userRePwdBlur()"/>
        </div>
        <div id="userRePwdDiv" class="col-sm-5 " style="padding-top: 15px;color: #b20910">*</div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">手机号:</span></label>
        <div class="col-sm-4">
          <input id="userPhone" name="userPhone" type="text" placeholder="请输入手机号" class="form-control" onfocus=" userPhoneFocus()" onblur=" userPhoneBlur()"/>
        </div>
        <div id="userPhoneDiv" class="col-sm-5 " style="padding-top: 15px;color: #b20910">*</div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">邮箱:</span></label>
        <div class="col-sm-4">
          <input id="userEmail" name="userEmail" type="text" placeholder="请输入邮箱" class="form-control" onfocus=" userEmailFocus()" onblur=" userEmailBlur()"/>
        </div>
        <div id="userEmailDiv" class="col-sm-5 " style="padding-top: 15px;color: #b20910">*</div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">状态:</span></label>
        <div class="col-sm-4">
          <label class="checkbox-inline control-label col-sm-3"  style="padding-top: 4px">
            <input type="radio" name="userStatus" value="normal" checked> 正常
          </label>
          <label class="checkbox-inline control-label col-sm-3"  style="padding-top: 4px">
            <input type="radio" name="userStatus" value="blocked"> 锁定
          </label>
        </div>
        </div>
        <div class="form-group">
        <label class="control-label col-sm-3 "><span class=" pull-right" style="padding-top: 7px">管理员:</span></label>
        <div class="col-sm-4">
          <label class="checkbox-inline control-label col-sm-3"  style="padding-top: 4px">
            <input type="radio" name="userPremission" value="1" checked> 是
          </label>
          <label class="checkbox-inline control-label col-sm-3"  style="padding-top: 4px">
            <input type="radio" name="userPremission" value="0"> 不是
          </label>
        </div>
      </div>


      <div class="panel-footer">
        <div class="row pull-left" style="padding-left: 300px" >
          <div class="col-sm-6 col-sm-offset-3" >
            <button type="submit" id="insert" class="btn btn-success" disabled="disabled" >保存</button>
          </div>
        </div>
        <div class="row pull-left"  style="padding-left: 10px">
          <div class="col-sm-6 col-sm-offset-3">
            <button type="reset" class="btn btn-primary"> 取消</button>
          </div>
        </div>
      </div>

    </form>
  </div><!-- panel -->
</section>


</body>
</html>
