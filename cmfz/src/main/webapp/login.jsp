<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>持名法州后台管理中心</title>
			
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

	<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="css/login.css" type="text/css"></link>
	<script type="text/javascript" src="script/jquery.js"></script>
	<script type="text/javascript" src="script/common.js"></script>
	<script type="text/javascript">
	
		$(function(){
			//点击更换验证码：

			$("#captchaImage").live("click",function(){
                $("#captchaImage").attr("src","${pageContext.request.contextPath}/admin/pic?time="+new Date());

			});
			//  form 表单提交
			$("#loginForm").bind("submit",function(){
				var username=$("#loginUserName").val();
				var password=$("#loginPassword").val();
				var encode=$("#enCode").val();
				if(username.length<4){
				    alert("用户名不能低于4位");
				    return false;
                }
				if(password.length<4){
                    alert("密码不能低于4位");
                    return false;
                }
				if(encode.length<4){
				    alert("验证码格式错误");
				    return false;
                }


    $.post("${pageContext.request.contextPath}/admin/Login",
        {"username":username,"password":password,"encode":encode}
        ,function(result){
        var Msg = eval(result);

      alert(Msg.message);
        if(Msg.flag=="true"){

          window.location.href="${pageContext.request.contextPath}/main/main.jsp";
        }

    },"JSON");



				return false;
			});
		});
	</script>
</head>
<body>
	
		<div class="login">
			<form id="loginForm" action="#" method="post" >
				
				<table>
					<tbody>
						<tr>
							<td width="190" rowspan="2" align="center" valign="bottom">
								<img src="img/header_logo.gif" />
							</td>
							<th>
								用户名:
							</th>
							<td>
								<input type="text" id="loginUserName" name="amdin.username" class="text"  maxlength="20"/>
							</td>
					  </tr>
					  <tr>
							<th>
								密&nbsp;&nbsp;&nbsp;码:
							</th>
							<td>
								<input type="password" id="loginPassword" name="admin.password" class="text"  maxlength="20" autocomplete="off"/>
							</td>
					  </tr>
					
						<tr>
							<td>&nbsp;</td>
							<th>验证码:</th>
							<td>
								<input type="text" id="enCode" name="enCode" class="text captcha" maxlength="4" autocomplete="off"/>
								<img id="captchaImage" class="captchaImage" src="${pageCOntext.request.contextPath}/cmfz/admin/pic" title="点击更换验证码"/>
							</td>
						</tr>					
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit" class="loginButton" value="登录">
						</td>
					</tr>
				</tbody></table>
				<div class="powered">COPYRIGHT © 2008-2017.</div>
				<div class="link">
					<a href="http://www.chimingfowang.com/">持名佛网首页</a> |
					<a href="http://www.chimingbbs.com/">交流论坛</a> |
					<a href="">关于我们</a> |
					<a href="">联系我们</a> |
					<a href="">授权查询</a>
				</div>
			</form>
		</div>
</body>
</html>