<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>尚硅谷会员注册页面</title>
		<%@include file="/pages/common/head.jsp"%>
		<script type="text/javascript" >
			// 页面加载完成之后
			$(function () {

				$("#username").live("change",function () {
					var username = this.value;
					$.getJSON("${pageScope.basePath}userServlet", "action=ajaxExistUsername&username="+username, function (data) {
						if(data.existUsername){
							$("span.errorMsg").text("用户名已存在！")
						}else {
							$("span.errorMsg").text("用户名可用")
						}
					})

				})


				// 给验证码图片添加单击事件
				$("#checkCode").click(function () {
					this.src = "${basePath}kaptcha.jpg?d=" + new Date();
				})



				// 给提交按钮绑定单击事件
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

					// 3、验证确认密码：和密码相同
					var repPassword = $("#repwd").val();
					if( repPassword != password){
						$("span.errorMsg").text("确认密码与密码不一致!");
						return false;
					}

					// 4、验证邮箱：
					var email = $("#email").val();
					var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

					if( !emailPatt.test(email) ){
						$("span.errorMsg").text("邮箱格式不一致!");
						return false;
					}

					// 5、验证码
					var code = $("#code").val();
					// 去掉字符串前后的字符
					code = $.trim(code);

					if( code == null || code == "" ){
						$("span.errorMsg").text("验证码错误!");
						return false;
					}


					$("span.errorMsg").text("");


				})
			});

		</script>


	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${requestScope.RegisterUsernameMsg}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="register">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
											value="${requestScope.RegisterCodeMsg}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
											value="${requestScope.RegisterCodeMsg}"   />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 110px;" name="code" id="code"/>
									<img id="checkCode" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 30px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
	</body>
</html>