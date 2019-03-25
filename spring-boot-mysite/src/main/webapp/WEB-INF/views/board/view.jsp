<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%pageContext.setAttribute("newline", "\n");%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value='/' />assets/css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function showList(){
	target = document.getElementById("default");
	if( target.style.display =="none")
    target.style.display = "block";
	else
		target.style.display = "none";
}

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="3">글보기</th>
					</tr>
					<c:if test="${not empty boardVo.fileName}">
					<tr>
						<td class="label"></td>
						<td id="attach-label"><a id='attach' onclick="showList()" >첨부파일</a><br>
						<ul id="default" style="display:none;">
							<li id="attach-down"><a href="${pageContext.request.contextPath }${boardVo.fileName}" download>파일다운로드</a></li>
							
						</ul>
						</td>
						
					</tr>
					</c:if>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(boardVo.contents, newline, "<br>") }
							</div>
						</td>
					</tr>
				
				</table>
				<div class="bottom">
					<a href="<c:url value='/' />board">글목록</a>
				<c:choose>
					<c:when test="${not empty authuser}">
					<a href="<c:url value='/' />board/reply?no=${boardVo.no}">답글달기</a>
					</c:when>
				</c:choose>	
				<c:if test="${boardVo.userNo == authuser.no}">
					<a href="<c:url value='/' />board/modify?no=${boardVo.no}">글수정</a>
					</c:if>	
				</div>
				
				
				<!-- 댓글 보기창 -->
				
					<table class="tbl-ex2">
					<tr>
						<th colspan="3">댓글</th>
					</tr>
					<c:forEach items="${list}" var="vo" varStatus="status">		
					<tr>
						<td class="label" id="comment-Writer">${vo.userName}</td>
						<td id="comment-content">${vo.contents}</td>
						<c:choose>
							<c:when test="${authuser.no==vo.userNo}">
						<td id="comment-delete"><a href="<c:url value='/' />board/commentdelete?no=${vo.no}&boardNo=${boardVo.no }" class="del"></a></td>
							</c:when>
							<c:otherwise>
						<td id="comment-delete"> </td>
							</c:otherwise>
						</c:choose>
					</tr>
					</c:forEach>
				</table>
				
				<!--  기존 버그 해결전 코드
				
				<div class="pager">
					<ul>
						<c:set var="loop" value="true"/>
						<c:forEach  begin="${bStart}" end="${bEnd}" step="1" var="i" >
						<c:if test="${loop}">
						<c:if test="${bStart != 1}">
							<li id="pre"><a href="<c:url value='/' />board?a=view&page=${i-4}&no=${boardVo.no }">◀ </a></li>
						</c:if>
							<li id="${i}" class=""><a href="<c:url value='/' />board?a=view&page=${i}&no=${boardVo.no }">${i} </a></li>
						<c:if test="${i==5 && bEnd-bStart >=5}">
							<li id="next"><a href="<c:url value='/' />board?a=view&page=${i+1}&no=${boardVo.no }">▶</a></li>
							<c:set var="loop" value="false"/>	
						</c:if>
						</c:if>
						</c:forEach>
					</ul>
				</div>	
			
				-->
				<!--  페이지 버그 해결후 코드 -->
				<div class="pager">
					<ul>
						<c:set var="loop" value="true"/>
						<c:set var="left" value="true"/>
						<c:set var="j" value="1"/>
						<c:forEach  begin="${bStart}" end="${bEnd}" step="1" var="i" >
						<c:if test="${loop}">
							<c:set var="j" value="${j+1}"/>
						<c:if test="${bStart != 1 && left}">
							<li id="pre"><a href="<c:url value='/' />board/view?page=${bStart-1}&start=${i-5}&no=${boardVo.no }">◀ </a></li>
							<c:set var="left" value="false"/>
						</c:if>
							<li id="${i}" class=""><a href="<c:url value='/' />board/view?page=${i}&start=${bStart}&no=${boardVo.no }">${i} </a></li>
						<c:if test="${j==6&&j!=bEnd+1}">
							<li id="next"><a href="<c:url value='/' />board/view?page=${i+1}&start=${i+1}&no=${boardVo.no }">▶</a></li>
							<c:set var="loop" value="false"/>	
						</c:if>
						</c:if>
						</c:forEach>
					</ul>
				</div>	
				 
				
				<br/>
					<script>
				document.getElementById('${page}').className="selected"
				</script>
				
				<br/>
				
				<!--  댓글 입력창 -->
				<c:if test="${not empty authuser}">
				<form action="<c:url value='/' />board/comment" method="post">
				<input type="hidden" name="boardNo" value="${boardVo.no}">
				<div class="comment">
				<input type="textbox" id="comment-text" name="contents" value="">
				<input type="submit" value="댓글입력" id="comment-input">
				</div>
				</form>
				</c:if>
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="board" value="guestbook"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>