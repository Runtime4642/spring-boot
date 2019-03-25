<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value='/' />assets/css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<c:url value='/' />/assets/js/jquery/jquery-1.9.0.js"></script>
	

<script type="text/javascript">
$(function(){
	
	$('.board-form').submit(function() {
		//1. 이름 체크
		if($('#title').val() == "")
		{
			alert("제목은 필수 입력 사항입니다.")
			$('#title').focus();
			return false;
		}
		
		return true;
	});
	
});
</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board"> 				
				<form class="board-form" method="post" action="<c:url value='/' />board/write" enctype="multipart/form-data" >
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" id="title" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="contents"></textarea>
							</td>
						</tr>
						<tr>
							<td class="label">파일첨부</td>
							<td>
								<input type="file" name="file">
							</td>
						</tr>
					
					</table>
					<div class="bottom">
						<a href="<c:url value='/' />board">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="board" value="guestbook"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>