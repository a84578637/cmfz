<%@page pageEncoding="UTF-8" %>
<html>
<head>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/loginUser">
    用户名:<input type="text" name="userName"></br>
    密码:<input type="password" name="password"></br>
    <input type="submit" value="提交">
</form>
</body>
</html>
