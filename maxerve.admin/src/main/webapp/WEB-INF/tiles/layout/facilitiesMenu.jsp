<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<div id="sideMenu">
	<h1><a href='<c:url value="/"/>'><img src="../../img/logo.png" alt="서울혁신파크"/></a></h1>
	<h2>시설관리</h2>
	<!-- gnb -->
	<div id="menu_left" class="menu_left">
	<sec:authorize access="hasRole('023008001')">
		<ul>
			<li><a href='<c:out value="/facilities/future/floorRoomList.do"/>' ><span>미래청 층별관리</span></a></li>
		</ul>
	</sec:authorize>
	</div>
</div><!-- sideMenu -->