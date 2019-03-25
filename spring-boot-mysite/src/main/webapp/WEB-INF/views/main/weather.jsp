<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

$(function(){
	
	var targeturl = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?ServiceKey=QpPIMCGW4LSOWcqDWO0pb1%2BxmbD5jfTlijVznu1S%2Bbm2j4OcTyuqqRALg0zZJkFloRzqwwwA%2BQ6aIgIXGfaTMA%3D%3D&base_date=20190222&base_time=1430&nx=96&ny=74&pageNo=1&numOfRows=7&_type=json";
	
	
	$.ajax({
		url : targeturl,
		type : "get",
		dataType : "json",
		data : "",
		success : function(response) {
			console.log(response)
			
		},
		error : function(xhr, status, e) {
			console.error(statuse + ":" + e);
		}
	})
	
});

</script>

</head>
<body>

</body>
</html>
