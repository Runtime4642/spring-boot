<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value='/' />assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./jquery/jquery-1.9.0.js"></script>

</head>
<body>


				<form  method="post" action="${pageContext.servletContext.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<input  name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input name="email" type="text" value="">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>

</body>
</html>