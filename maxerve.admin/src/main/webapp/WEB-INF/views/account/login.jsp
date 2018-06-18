<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>서울혁신파크</title>
<%@ include file="/WEB-INF/tiles/layout/head_script.jsp" %>
<link rel="stylesheet" href="../css/admin.css">

<script type="text/javascript">
jQuery(function($){
	$('#loginForm').validate({
		rules: {
			uid: "required",
			upw: "required"
		},
		messages: {
			uid: '아이디는 필수입니다.',
			upw: '비밀번호는 필수입니다.'
		},
		submitHandler: function(form) {
			var url = '/member/loginProc.json';
			var submitData = {
				uid: $('input[name=uid]').val(),
				upw: $('input[name=upw]').val()
//						${_csrf.parameterName}: '${_csrf.token }'
			};

			var callback = function(data) {
				location.href = '/main/main.do';
			}

			ajaxSubmit(url, submitData, callback, null);
		}
	});
});
</script>
</head>

<body class="bg_none">
	<div id="Wrapper" class="pt_250">
	<form:form method="post" id='loginForm'>
		<div id="Login">
			<!-- <img src="../img/login.png" alt="인트라넷" class="mb_40" /> -->
			<input type='text' name="uid" value='' class="input_login mb_10" placeholder="아이디">
			<input type='password' name="upw" value='' class="input_login mb_10" placeholder="비밀번호">
			<input type='submit' value='로그인' class="btnLogin"/>
		</div>
	</form:form>
	</div><!-- /Wrapper -->
</body>