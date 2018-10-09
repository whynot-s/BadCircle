<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="ModifyUserInfo" action="ModifyUserInfo" method="post">
<table>
<tr><td>ID</td><td><input type="text" name="userId"></td></tr>
<tr><td>用户名</td><td><input type="text" name="userName"></td></tr>
<tr><td>电话</td><td><input type="text" name="phoneNumber"></td></tr>
<tr><td>性别</td><td><input type="text" name="gender"></td></tr>
<tr><td>生日</td><td><input type="text" name="birthday"></td></tr>
<tr><td>省份</td><td><input type="text" name="province"></td></tr>
<tr><td>城市</td><td><input type="text" name="city"></td></tr>
<tr><td>区域</td><td><input type="text" name="district"></td></tr>
<tr><td colspan="2" align="center"><input type="submit"  value="Submit"></td></tr>
</table>
</form>
</body>
</html>