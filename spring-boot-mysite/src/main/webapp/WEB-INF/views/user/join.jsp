<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value='/' />assets/css/user.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"
	src="<c:url value='/' />assets/js/jquery/jquery-1.9.0.js"></script>

<script>


$(function(){
	
     $("#join-form").submit(function(){
    	 /*
		//1. 이름 체크
		if($('#name').val() == "")
		{
			alert("이름은 필수 입력 사항입니다.")
			$('#name').focus();
			return false;
		}
		//2. 이메일 비어 있는지 확인
		if($('#email').val()=="")
		{

			alert("이메일은 필수 입력 사항입니다.")
			$('#email').focus();
			return false;
		}
		*/
		
		//2-2. 이메일 중복체크 유무
		if($("#img-checkemail").is(":visible")==false)
		{
			alert("이메일 중복체크를 해주세요");
			return false;
		}
		/*
		//3 비밀번호 비어 있는지 확인
		if($('input[type="password"]').val()=="")
		{

			alert("비밀번호는 필수 입력 사항입니다.");
			$('input[type="password"]').focus();
			return false;
		}
		*/
		
		//4.약관 동의
		if($("#agree-prov").is(":checked")==false)
			{
			alert("약관에 동의해주세요");
			return false;
			}
		
		return true;
		
	}); 
	

	
	var emailChecked = function(){
		var email = $("#email").val();
		if(email ==""){
			return;
		};
		
		$.ajax({
			url:"${pageContext.request.contextPath}/user/api/checkemail",
			type:"post",
			dataType:"json",
			data:"email="+email,
			success:function(response){
				if(response.result=="fail")
				{
					console.error(response.message)	
				}
				if(response.data){
					alert("이미존재하는 이메일입니다.");
					$('email').val("").focus();
					$('#img-checkemail').hide();
					$('#btn-checkemail').show();
					return;
				}
				else{
					$('#img-checkemail').show();
					$('#btn-checkemail').hide();
					return;
				}
			},
			error: function(xhr,status,e){
				console.error(status+":"+e);
			}
		});
		
	};
	$("#btn-checkemail").click(emailChecked);
	$('#email').change(function(){
		if($("#img-checkemail").is(":visible")==true)
		{
			$('#img-checkemail').hide();
			$('#btn-checkemail').show();
			//alert("체크후 아이디변경");
			//emailChecked();
			
		}
	});
});




</script>



</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form id="join-form" name="joinForm" method="post" action="${pageContext.servletContext.contextPath }/user/join" 
							modelAttribute="userVo">				
					<label class="block-label" for="name">이름</label> 
					<input id="name" name="name" type="text" value="${userVo.name}">
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p style="padding: 5px 0 0 0; text-align: left; color: red">
								<strong> <spring:message
										code="${errors.getFieldError( 'name' ).codes[0] }"
										text="${errors.getFieldError( 'name' ).defaultMessage }" />
								</strong>
							</p>
						</c:if>
					</spring:hasBindErrors>
					<label class="block-label" for="email">이메일</label> 
					     <input id="email" name="email" type="text" value="">
						<input type="button" value="id 중복체크" id="btn-checkemail">
						<img id="img-checkemail" src="<c:url value='/' />assets/images/check.png" style="display: none; width: 20px; height: 20px;">
								<strong><p style="padding:5px 0 0 0;color:red;text-align: left;"><form:errors path="email" /></p></strong>
						 <label class="block-label">패스워드</label> 
						 		<strong><p style="padding:5px 0 0 0;color:red;text-align: left;"><form:errors path="password" /></p></strong>
						 <input name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>