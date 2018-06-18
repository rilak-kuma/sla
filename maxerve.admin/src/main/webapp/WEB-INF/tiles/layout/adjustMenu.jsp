<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
jQuery(function($){
	$('.submenu_area .submenu li[data-mnu-cd=023006]').addClass('on');
});
</script>
<div id="sideMenu">
	<h1><a href='<c:url value="/"/>'><img src="../../img/logo.png" alt="서울혁신파크"/></a></h1>
	<h2>정산관리</h2>
	<!-- gnb -->
	<div id="menu_left" class="menu_left">
		<ul>
		<sec:authorize access="hasRole('023006001')">
			<li><a href='<c:url value="/adjust/pay/list.do"/>' ><span>결제</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023006002')">
			<li>
				<a href="#"><span>정산관리</span><span class="i"></span></a>
				<ul class='hidden'>
					<li><a href='<c:url value="/adjust/adjust/summary_list.do?adjustType=1"/>'><span>일별정산</span></a></li>
					<li><a href='<c:url value="/adjust/adjust/summary_list.do?adjustType=2"/>'><span>월별정산</span></a></li>
					<li><a href='<c:url value="/adjust/adjust/summary_list.do?adjustType=3"/>'><span>연도별정산</span></a></li>
					<li><a href='<c:url value="/adjust/adjust/summary_organization_list.do"/>'><span>단체별정산</span></a></li>
					<li><a href='<c:url value="/adjust/adjust/organization_write.do"/>'><span>정산단체관리</span></a></li>
					<li><a href='<c:url value="/adjust/adjust/fees_write.do"/>'><span>수수료관리</span></a></li>
				</ul>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('023006003')">
			<li>
				<a href="#"><span>후원금</span><span class="i"></span></a>
				<ul class='hidden'>
					<li><a href='<c:url value="/adjust/sponsor/list.do"/>'><span>후원현황</span></a></li>
					<li><a href='<c:url value="/adjust/sponsor/adjust_list.do"/>'><span>후원금정산</span></a></li>
				</ul>
			</li>
		</sec:authorize>
		</ul>
	</div>
</div><!-- sideMenu -->