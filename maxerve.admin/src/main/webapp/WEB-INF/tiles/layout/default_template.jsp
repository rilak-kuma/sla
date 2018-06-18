<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>서울혁신파크 관리</title>
<tiles:insertAttribute name="headScript" />
<script type="text/javascript">
jQuery(function(){
	// 좌메뉴펼침
	$('#menu_left')
	.on('click', 'a:has(.i)', function(){
		if ($(this).next('ul').is('.hidden')) {
			$(this).next('ul').hide().removeClass('hidden');
		}

		$(this).next('ul').slideToggle(100);
		$(this).parent().toggleClass('active');

		return false;
	});
});
</script>
</head>
<body>
	<img src="/img/loading.png" class="loading"/>
<script type="text/javascript">
pageLoad();
</script>
<% out.flush(); %>
<div id="Wrapper">
	<tiles:insertAttribute name="sideMenu" defaultValue=""/>
	<div id="mainVisual">
		<tiles:insertAttribute name="topMenu" />
		<tiles:insertAttribute name="body" defaultValue="" />
	</div>
</div>
 </body>
</html>