<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 94719
  Date: 2020/7/26
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 分页代码 --%>
<div id="page_nav">

    <c:if test="${requestScope.bookPage.pageNo > 1}">
        <a href="${requestScope.bookPage.url}&pageNo=${requestScope.bookPage.pageNo-1}">上一页</a>
        <a href="${requestScope.bookPage.url}&pageNo=1">首页</a>
        <a>...</a>
        <a href="${requestScope.bookPage.url}&pageNo=${requestScope.bookPage.pageNo-1}">${requestScope.bookPage.pageNo-1}</a>

    </c:if>

    【${requestScope.bookPage.pageNo}】

    <c:if test="${requestScope.bookPage.pageNo < requestScope.bookPage.pageTotalCount}">
        <a href="${requestScope.bookPage.url}&pageNo=${requestScope.bookPage.pageNo+1}">${requestScope.bookPage.pageNo+1}</a>
        <a>...</a>
        <a href="${requestScope.bookPage.url}&pageNo=${requestScope.bookPage.pageTotalCount}">末页</a>
        <a href="${requestScope.bookPage.url}&pageNo=${requestScope.bookPage.pageNo+1}">下一页</a>
    </c:if>

    共${requestScope.bookPage.pageTotalCount}页，${requestScope.bookPage.pageItemTotalCount}条记录。

    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="search_btn" >


</div>
