<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
jQuery(function($){
	$('.submenu_area .submenu li[data-mnu-cd=023007]').addClass('on');
});
</script>
<div id="sideMenu">
	<h1><a href='<c:url value="/"/>'><img src="../../img/logo.png" alt="서울혁신파크"/></a></h1>
	<h2>혁신파크 관리</h2>
	<!-- gnb -->
	<div id="menu_left" class="menu_left">
		<ul>
		<sec:authorize access="hasRole('023007001')">
			<li><a href='<c:url value="/information/notice/noticeList.do"/>' ><span>센터공지</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023007003')">
			<li><a href='<c:url value="/information/recruit/recruitList.do"/>' ><span>구인관리</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023007002')">
			<li><a href='<c:url value="/information/newsletter/newsletterList.do"/>' ><span>뉴스레터 관리</span></a></li>
			<li><a href='<c:url value="/information/newsletter/subscriberList.do"/>' ><span>뉴스레터 구독자 관리</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023007004')">
			<li><a href='<c:url value="/information/story/storyList.do"/>' ><span>활동이야기</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023007005')">
			<li><a href='<c:url value="/information/media/mediaList.do"/>' ><span>언론보도</span></a></li>
		</sec:authorize>
		</ul>
	</div>
</div><!-- sideMenu -->