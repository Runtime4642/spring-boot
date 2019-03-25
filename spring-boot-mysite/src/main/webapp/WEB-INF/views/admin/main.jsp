<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/admin/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">

$(function(){
	var file = $('#input-file');
	file.change(function(event){
		if(file.val()!=""){
		var ext = file.val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
			 alert('gif,png,jpg,jpeg 파일만 업로드 할수 있습니다.');
			 file.val("");
			 return;
		      }
		}
	});
	
});
</script>


</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/admin/include/header.jsp" />
		<div id="wrapper">
			<div id="content"> 
				<div id="site-form">						
					<form method="post" action="${pageContext.request.contextPath }/admin/main/update" enctype="multipart/form-data">
						<label class="block-label" for="title">사이트 타이틀</label>
						<input id="title" name="title" type="text" value="${siteVo.title }">
						
						<label class="block-label" for="welcomeMessage">환영 메세지</label>
						<input id="welcomeMessage" name="welcome" type="text" value="${siteVo.welcome }">

						<label class="block-label">프로필 이미지</label>
						<img id="profile" onerror="this.src='${pageContext.request.contextPath }/assets/images/default_user.png'" src="${pageContext.request.contextPath }/${siteVo.profile }">
						<input id="input-file" type="file" name="img" multiple accept='image/*'>
							
						<label class="block-label">사이트 설명</label>
						<textarea name="description">${siteVo.description }</textarea>
						
						<input type="submit" value="변경" />
					</form>
									
				

				</div>
			</div>
			<c:import url="/WEB-INF/views/admin/include/navigation.jsp" />
		</div>
	</div>
	
	<c:if test="${result}">
		<script>alert('변경성공')</script>
	</c:if>
</body>
</html>