<%--
  Created by IntelliJ IDEA.
  User: hzj_king
  Date: 2017/1/17
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="michaelhe">
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/static/images/favicon.png" type="image/png">
        <link href="<%=request.getContextPath()%>/static/css/style.default.css" rel="stylesheet">
        <%@include file="/WEB-INF/views/common/head.jsp" %>
    </head>
</head>
<body>
Dear <strong>${user}</strong>, Welcome to Admin Page.
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>
