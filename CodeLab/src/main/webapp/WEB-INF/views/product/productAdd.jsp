<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="michaelhe">
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/png">
  <link href="<%=request.getContextPath()%>/static/css/style.default.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/static/css/jquery.datatables.css" rel="stylesheet">
  <script src="<%=request.getContextPath()%>/static/js/flot/flot.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/flot/flot.resize.min.js"></script>
  <%@include file="/WEB-INF/views/common/head.jsp" %>
  <title>产品管理</title>
  
</head>

<body>

<!-- Preloader -->
<div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>

<section>
  
  <div class="leftpanel">
    
    <div class="logopanel">
        <h1><span>[</span> bracket <span>]</span></h1>
    </div><!-- logopanel -->

    <div class="leftpanelinner">
      <br>
      <ul class="nav nav-pills nav-stacked nav-bracket">
        <li><a href="<%=request.getContextPath()%>/"><i class="fa fa-home"></i> <span>首页</span></a></li>
        <li class="active"><a href="#"><i class="fa fa-briefcase"></i> <span>产品管理</span></a>
          <ul class="children">
            <li class="active"><a href="#"><i class="fa fa-caret-right"></i> 产品新增</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> 产品作废</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> 产品发布管理</a></li>
          </ul>
        </li>
        <li class="nav-parent"><a href="#"><i class="fa fa-suitcase"></i> <span>贷款管理</span></a>
          <ul class="children">
            <li><a href="#"><i class="fa fa-caret-right"></i> 合同管理</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> 放款审核</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> 额度管理</a></li>
          </ul>
        </li>
        <li class="nav-parent"><a href="#"><i class="fa fa-file-text"></i> <span>系统管理</span></a>
          <ul class="children">
            <li><a href="#"><i class="fa fa-caret-right"></i> 用户管理</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> 角色管理</a></li>
            <li><a href="#"><i class="fa fa-caret-right"></i> 日志管理</a></li>

          </ul>
        </li>
      </ul>



    </div><!-- leftpanelinner -->
  </div><!-- leftpanel -->

  <div class="mainpanel">
    <div class="headerbar">
      <a class="menutoggle"><i class="fa fa-bars"></i></a>
    </div><!-- headerbar -->
    <div class="contentpanel">
    </div><!-- contentpanel -->
  </div><!-- mainpanel -->
  

  
</section>

</body>
</html>
