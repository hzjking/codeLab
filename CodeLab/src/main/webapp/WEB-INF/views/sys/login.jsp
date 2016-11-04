<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="michaelhe">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/static/images/favicon.png" type="image/png">
    <link href="<%=request.getContextPath()%>/static/css/style.default.css" rel="stylesheet">
    <%@include file="/WEB-INF/views/common/head.jsp" %>
  <title>用户登录</title>
</head>

<body class="signin">

<!-- Preloader -->
<div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>

<section>
  
    <div class="signinpanel">
        
        <div class="row">
            
            <div class="col-md-7">
                
                <div class="signin-info">
                    <div class="logopanel">
                        <h1><span>[</span> 管理平台 <span>]</span></h1>
                    </div><!-- logopanel -->
                
                    <div class="mb20"></div>
                
                    <h5><strong>欢迎访问管理平台!</strong></h5>
                    <ul>
                        <li><i class="fa fa-arrow-circle-o-right mr5"></i> 合同管理</li>
                        <li><i class="fa fa-arrow-circle-o-right mr5"></i> 产品管理</li>
                        <li><i class="fa fa-arrow-circle-o-right mr5"></i> 系统管理</li>
                        <li><i class="fa fa-arrow-circle-o-right mr5"></i> and much more...</li>
                    </ul>
                    <div class="mb20"></div>
                    <strong>还不是会员? <a href="signup.html">注册</a></strong>
                </div><!-- signin0-info -->
            
            </div><!-- col-sm-7 -->
            
            <div class="col-md-5">
                
                <form method="post" action="<%=request.getContextPath()%>/user/login">
                    <h4 class="nomargin">登录</h4>
                    <p class="mt5 mb20">${error}</p>

                    <input type="text" class="form-control uname" placeholder="用户名"
                             name="username"/>
                    <input type="password" class="form-control pword" placeholder="密码" name="password"/>
                    <a href="#"><small>忘记密码?</small></a>
                    <button class="btn btn-success btn-block">登录</button>
                    
                </form>
            </div><!-- col-sm-5 -->
            
        </div><!-- row -->
        
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2016. All Rights Reserved.
            </div>
        </div>
        
    </div><!-- signin -->
  
</section>


</body>
</html>
