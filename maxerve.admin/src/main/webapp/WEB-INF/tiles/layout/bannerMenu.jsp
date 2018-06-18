<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
jQuery(function($){
	$('.submenu_area .submenu li[data-mnu-cd=023002]').addClass('on');
});
</script>
<div id="sideMenu">
	<h1><a href="<c:url value="/"/>"><img src="../../img/logo.png" alt="서울혁신파크"/></a></h1>
	<h2>배너관리</h2>
	<!-- gnb -->
	<div id="menu_left" class="menu_left">
		<ul>
		<sec:authorize access="hasRole('023002001')">
			<li><a href='<c:url value="/banner/mainBannerList.do"/>' ><span>메인배너목록</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023002002')">
			<li><a href='<c:url value="/banner/innoBannerList.do"/>' ><span>혁신멤버배너목록</span></a></li>
		</sec:authorize>
		</ul>
	</div>
</div><!-- sideMenu -->