<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript" >
		// 页面加载完成之后
		$(function () {

			// 给按钮绑定单击事件
			$("#sub_btn").click(function () {
				// 1、验证用户名：
				// 获取用户输入框的内容
				var username = $("#username").val();
				// 创建正则表达式对象
				var usernamePatt = /^\w{5,12}$/;     // 5-12位的数字，字母，下划线
				// 是否匹配正则表达式：使用test方法验证
				if( !usernamePatt.test( username )){
					// 提示用户验证结果
					$("span.errorMsg").text("用户名不合法！");            // 为什么这里要写span.errorMsg?
					return false;
				}

				// 2、验证密码
				// 获取用户输入框内容
				var password = $("#password").val();
				// 创建正则表达式对象
				var passwordPatt = /^\w{5,12}$/;
				// 验证并提示用户验证结果
				if( !passwordPatt.test(password) ){
					$("span.errorMsg").text("密码不合法！");
					return false;
				}

				$("span.errorMsg").text("");


			})
		});

	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									<%--<%=request.getAttribute("loginMsg")==null? "请输入用户名和密码":request.getAttribute("loginMsg") %>--%>
									${empty requestScope.loginMsg? "请输入用户名和密码":requestScope.loginMsg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="login">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
										   name="username"  />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>