<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@page import="java.io.PrintWriter"%>
<%@page import="com.douzone.emaillist.vo.EmailListVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.douzone.emaillist.dao.EmailListDao"%>    
    

<%

		request.setCharacterEncoding("utf-8");
		
		String firstName = request.getParameter("fn");
		String lastName = request.getParameter("ln");
		String email = request.getParameter("email");		
		EmailListVo vo = new EmailListVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		new EmailListDao().insert(vo);
		response.sendRedirect("/emailList1");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>등록성공</h1>


</body>
</html>