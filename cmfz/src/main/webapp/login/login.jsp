<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>后台管理中心</title>
			
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon" />

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>

	<script type="text/javascript">
	
		$(function(){
			//点击更换验证码：

			$("#captchaImage").live("click",function(){

                $("#captchaImage").attr("src","${pageContext.request.contextPath}/user/pic?time="+new Date());

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


    $.post("${pageContext.request.contextPath}/user/Login",
        {"userPhone":username,"password":password,"encode":encode}
        ,function(result){



        if(result=="success"){

          window.location.href="${pageContext.request.contextPath}/index.jsp";
        }else{
            alert(result);
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
								<img src="${pageContext.request.contextPath}/img/logo.png" />
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
							</td>

						</tr>					
					<tr>

						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
						<td align="center">
							<img id="captchaImage"  class="captchaImage" src="${pageContext.request.contextPath}/user/pic" title="点击更换验证码"/>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="submit" class="loginButton" value="登录">

						</td>

					</tr>
					<tr><td><a href="${pageContext.request.contextPath}/login/register_form.jsp">没有账号？</a></td></tr>
				</tbody></table>
				<div class="powered">COPYRIGHT © 2019-2019.</div>
				<div class="link">


				</div>
			</form>
		</div>
</body>
</html>