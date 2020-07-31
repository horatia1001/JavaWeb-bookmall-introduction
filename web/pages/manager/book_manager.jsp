<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.delClass").click(function () {
                /*
                * 1.confirm()函数返回值：返回true执行操作，返回false就不执行任何操作
                * 2.在function()函数中有当前正在响应时间的dom对象this
                * */
                return confirm("你确定要删除《" + $(this).parent().parent().find("td:first").text() + "》吗？")
            })



        })


    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>

        <c:forEach items="${requestScope.bookPage.pageItems}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=queryBook&id=${book.id}&pageNo=${requestScope.bookPage.pageNo}">修改</a></td>
                <td><a class="delClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.bookPage.pageNo}">删除</a></td>
                    <%-- 给del键加上class；href是超连接属性，这里是是我们请求发送的目标地址 --%>
            </tr>
        </c:forEach>


        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <%--这里没写?pageNo=${requestScope.bookPage.pageNo}导致添加图书后无法跳转--%>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.bookPage.pageNo}">添加图书</a></td>
        </tr>


        <%-- 这里一开始语法错误，对EL表达式的语法没有熟悉。要记得所有表达式都写在{}里，{}不是用来单纯取得一个对象的。 --%>
    </table>

    <%@include file="/pages/common/page_nav.jsp"%>

    <%--  原来script也可以写在这里 --%>
    <script type="text/javascript">
        $(function () {
            $("#search_btn").click(function () {
                var pageNo = $("#pn_input").val();
                location.href = "${pageScope.basePath}manager/bookServlet?action=page&pageNo="+pageNo;
            })

        })
    </script>


</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>