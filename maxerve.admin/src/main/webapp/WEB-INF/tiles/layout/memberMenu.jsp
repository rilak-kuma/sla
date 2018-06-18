<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
jQuery(function($){
	$('.submenu_area .submenu li[data-mnu-cd=023003]').addClass('on');
});
</script>
<div id="sideMenu">
	<h1><a href="<c:url value="/"/>"><img src="../../img/logo.png" alt="서울혁신파크"/></a></h1>
	<h2>회원관리</h2>
	<!-- gnb -->
	<div id="menu_left" class="menu_left">
		<ul>
		<sec:authorize access="hasRole('023003001')">
			<li><a href='<c:out value="/member/currentState.do"/>' ><span>입주단체현황</span></a></li>
			<li><a href='<c:out value="/member/moveInOztnList.do"/>' ><span>입주단체관리</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023003002')">
			<li><a href='<c:out value="/member/newMoveInAplyList.do"/>' ><span>신규입주신청</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023003003')">
			<li><a href='<c:out value="/member/onlineMemberState.do"/>' ><span>온라인멤버현황</span></a></li>
			<li><a href='<c:out value="/member/onlineMemberList.do"/>' ><span>온라인멤버관리</span></a></li>
		</sec:authorize>
		</ul>
	</div>
</div><!-- sideMenu -->