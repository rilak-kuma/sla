<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<div class="submenu_area">
	<ul class="submenu">
		<li data-mnu-cd='023001'><a href='<c:url value="/basic/proxy.do"/>'>기본관리</a></li>
	<sec:authorize access="hasRole('023002')">
		<li data-mnu-cd='023002'><a href='<c:url value="/banner/proxy.do"/>'>배너관리</a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('023003')">
		<li data-mnu-cd='023003'><a href='<c:url value="/member/proxy.do"/>'>회원관리</a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('023004')">
		<li data-mnu-cd='023004'><a href='<c:url value="/activity/proxy.do"/>'>혁신활동관리</a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('023005')">
		<li data-mnu-cd='023005'><a href='<c:url value="/reserve/proxy.do"/>' >예약일정관리</a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('023008')">
		<li data-mnu-cd='023008'><a href='<c:out value="/facilities/proxy.do"/>'>시설관리</a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('023006')">
		<li data-mnu-cd='023006'><a href='<c:out value="/adjust/proxy.do"/>'>정산관리</a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('023007')">
		<li data-mnu-cd='023007'><a href='<c:out value="/information/proxy.do"/>' >혁신파크관리</a></li>
	</sec:authorize>
		<li><a href='<c:out value="/member/logout.do"/>'>logout</a></li>
	</ul>
</div>