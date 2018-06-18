<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
jQuery(function($){
	$('.submenu_area .submenu li[data-mnu-cd=023004]').addClass('on');
});
</script>
<div id="sideMenu">
	<h1><a href='<c:url value="/"/>'><img src="../../img/logo.png" alt="서울혁신파크"/></a></h1>
	<h2>혁신활동관리</h2>
	<!-- gnb -->
	<div id="menu_left" class="menu_left">
		<ul>
		<sec:authorize access="hasRole('023004001')">
			<li><a href='<c:out value="/activity/propose/proposeList.do"/>' ><span>아이디어/협업</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023004002')">
			<li><a href='<c:out value="/activity/project/projectList.do"/>' ><span>프로젝트</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023004003')">
			<li><a href='<c:out value="/activity/event/eventList.do"/>' ><span>행사</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023004005')">
			<li><a href='<c:out value="/activity/propose/fswList.do"/>' ><span>아이디어/협업(탑)</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023004004')">
			<li><a href='<c:out value="/activity/referenceRoom/referenceList.do"/>' ><span>자료실</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023004006')">
			<li><a href='<c:out value="/activity/referenceRoom/popularTag.do"/>' ><span>자료실인기태그</span></a></li>
		</sec:authorize>
		</ul>
	</div>
</div><!-- sideMenu -->