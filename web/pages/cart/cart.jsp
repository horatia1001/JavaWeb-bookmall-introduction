<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(".updateCount").live("change",function () {       // 这里由于change事件不起作用，故换成live
			var name = $(this).parent().parent().find("td:first").text();
			var count = this.value;

			var id = $(this).attr("id");

			if(confirm("您确定将《"+ name +"》的数量修改为" + count +"吗？")){
				location.href = "${pageScope.basePath}cartServlet?action=update&id=" + id + "&count=" + count;
			}else {
				this.value = this.defaultValue;
			}

		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<c:if test="${empty sessionScope.cart.itemMap}">
				<tr>
					<td colspan="5">
						当前购物车为空~
					</td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.cart.itemMap}">
			<c:forEach var="cartItem" items="${sessionScope.cart.itemMap}">
				<tr>
					<td>${cartItem.value.name}</td>
					<td>
						<input class="updateCount"  id="${cartItem.value.id}" type="text" style="width: 50px;" value="${cartItem.value.count}" >
					</td>
					<td>${cartItem.value.singlePrice}</td>
					<td>${cartItem.value.totalPrice}</td>
					<td><a class="deleteConfirm" href="cartServlet?action=delete&id=${cartItem.value.id}">删除</a></td>
					<script type="text/javascript">
						$(".deleteConfirm").click(function () {
							return confirm("您确定要删除《" + $(this).parent().parent().find("td:first").text()  +"》吗？");
						})
					</script>
				</tr>
			</c:forEach>
			</c:if>

			
		</table>
		<c:if test="${not empty sessionScope.cart.itemMap}" >
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.itemTotalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.itemTotalPrice}</span>元</span>
			<span class="cart_span"><a class="clearConfirm" href="cartServlet?action=clear">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>

			<script type="text/javascript">
				$(".clearConfirm").click(function () {
					return confirm("您确定要清空购物车吗？");
				})
			</script>

		</div>
		</c:if>

	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>