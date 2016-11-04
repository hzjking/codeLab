<%@tag pageEncoding="UTF-8" description="构建子菜单" %>
<%@ attribute name="menu" type="com.sand.pojo.Menu" required="true" description="当前菜单" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="es" tagdir="/WEB-INF/tags/es" %>
<link href="<%=request.getContextPath()%>/static/css/style.default.css" rel="stylesheet">
<script type="text/javascript">

    function refresh(url){
        var iframe = document.getElementById("iframe");
        iframe.src=url;
        iframe.location.reload;
    }

</script>
<c:choose>
    <c:when test="${!menu.hasChildren}">
        <li><a href="#" onclick="refresh('<%=menuUrl(request, menu.getUrl())%>')"><i class="${menu.icon}"></i><span>${menu.name}</span></a></li>
    </c:when>
    <c:otherwise>
        <li class="nav-parent">
            <a href="#"><i class="${menu.icon}"></i><span>${menu.name}</span></a>
            <ul class="children">
                <c:forEach items="${menu.children}" var="menu2">
                    <es:submenu menu="${menu2}"/>
                </c:forEach>
            </ul>
        </li>
        <%--<li class="nav-parent">
            <a href="#"><span>${menu.name}</span></a>
            <ul class="children">
                <c:forEach items="${menu.children}" var="menu2">
                    <es:submenu menu="${menu2}"/>
                </c:forEach>
            </ul>
        </li>--%>
    </c:otherwise>
    <%--<c:otherwise>
        <li>
            <a href="#"><i class="${m.icon}"></i><span>${menu.name}</span></a>
            <ul>
                <c:forEach items="${menu.children}" var="menu2">
                    <es:submenu menu="${menu2}"/>
                </c:forEach>
            </ul>
        </li>--%>
        <%--<li class="nav-parent">
            <a href="#"><span>${menu.name}</span></a>
            <ul class="children">
                <c:forEach items="${menu.children}" var="menu2">
                    <es:submenu menu="${menu2}"/>
                </c:forEach>
            </ul>
        </li>--%>
    <%--</c:otherwise>--%>
</c:choose>

<%!
    private static String menuUrl(HttpServletRequest request, String url) {
        if(url.startsWith("http")) {
            return url;
        }
        String ctx = request.getContextPath();

        if(url.startsWith(ctx) || url.startsWith("/" + ctx  )) {
            return url;
        }

        if(!url.startsWith("/")) {
            url = url + "/";
        }
        return ctx + url;

    }
%>

