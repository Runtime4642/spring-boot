<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%pageContext.setAttribute("newline", "\n");%>
<!DOCTYPE html>
<html>
<head>
<title>${siteVo.title}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value='/' />assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" onerror="this.src='${pageContext.request.contextPath }/assets/images/default_user.png'" src="${pageContext.request.contextPath }${siteVo.profile }" 
					style="width:120px">
					<h2>${fn:replace(siteVo.welcome, newline, "<br>") }</h2>
					<p>
						${fn:replace(siteVo.description, newline, "<br>") }
						<br>
						<br>
						<a href="<c:url value='/' />gb">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>	
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>