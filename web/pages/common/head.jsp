<%--
  Created by IntelliJ IDEA.
  User: 94719
  Date: 2020/7/22
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 将ip地址localhost修改为真正的ip地址 --%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                      + request.getServerPort() +  request.getContextPath() + "/";

    pageContext.setAttribute("basePath",basePath);
%>

<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
