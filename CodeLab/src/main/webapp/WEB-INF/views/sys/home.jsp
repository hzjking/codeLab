<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="michaelhe">
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/static/images/favicon.png" type="image/png">
  <script src="<%=request.getContextPath()%>/static/js/flot/flot.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/flot/flot.resize.min.js"></script>
  <%@include file="/WEB-INF/views/common/head.jsp" %>
  <title>主页</title>
</head>
<script type="text/javascript" language="javascript">
    function iFrameHeight() {
        var ifm= document.getElementById("iframe");
        var subWeb = document.frames ? document.frames["iframe"].document : ifm.contentDocument;
        if(ifm != null && subWeb != null) {
            ifm.height = subWeb.body.scrollHeight;
            ifm.width = subWeb.body.scrollWidth;
        }
    }
    function logout(url){
        if(confirm("确定退出？")){
            location.href=url;
        }

    }
</script>
<body>

<!-- Preloader -->
<div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>

<%--<section> 标签定义文档中的节（section、区段）。比如章节、页眉、页脚或文档中的其他部分。--%>
<section>
  
  <div class="leftpanel">
    
    <div class="logopanel">
        <h1><span>[</span> 保理管理平台 <span>]</span></h1>
    </div><!-- logopanel -->

    <div class="leftpanelinner">
      <br/>
      <ul class="nav nav-pills nav-stacked nav-bracket">
              <c:forEach items="${menus}" var="m">
                  <es:submenu menu="${m}"/>
                     <%-- <li class="nav-parent">
                          <a href="#"><i class="${m.icon}"></i><span>${m.name}</span></a>
                          <ul class="children">
                              <c:forEach items="${m.children}" var="c">
                                      <es:submenu menu="${c}"/>
                              </c:forEach>
                          </ul>
                      </li>--%>
              </c:forEach>
      </ul>
      
    </div><!-- leftpanelinner -->
  </div><!-- leftpanel -->
  
  <div class="mainpanel">

    <div class="headerbar">

      <a class="menutoggle"><i class="fa fa-bars"></i></a>
        <div class="header-right">
            <ul class="headermenu">
                <li>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <img src="<%=request.getContextPath()%>/static/images/loggeduser.png" alt="" />
                            ${username}
                        </button>
                    </div>
                </li>
                <li>
                    <div class="btn-group">
                            <button  class="btn btn-default dropdown-toggle tp-icon chat-icon" data-toggle="dropdown" onclick="logout('<%=request.getContextPath()%>/user/logout')">
                                <a href="#"role="button">退出</a>
                            </button>
                    </div>
                </li>

            </ul>
        </div>

      
    </div><!-- headerbar -->
      <div class="contentpanel">
          <div class="panel panel-default">
              <div class="row">
                    <iframe class="col-md-12" id="iframe"  marginheight="0" marginwidth="0" onload="iFrameHeight()"
                          frameborder="0" scrolling="no" src="<%=request.getContextPath()%>/welcome"></iframe>

              </div>
          </div>
      </div>
          <%-- <div class="contentpanel">


    </div>--%><!-- contentpanel -->
    
  </div><!-- mainpanel -->
  

  
  
</section>

</body>




</html>
