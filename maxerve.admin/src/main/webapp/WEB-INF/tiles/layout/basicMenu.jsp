<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
jQuery(function($){
	$('.submenu_area .submenu li[data-mnu-cd=023001]').addClass('on');
});
</script>
<div id="sideMenu">
	<h1><a href='<c:url value="/"/>'><img src="../../img/logo.png" alt="서울혁신파크"/></a></h1>
	<h2>기본관리</h2>
	<!-- gnb -->
	<div id="menu_left" class="menu_left">
		<ul>
			<li><a href='<c:url value="/basic/account/mypage.do"/>' ><span>정보수정</span></a></li>
		<sec:authorize access="hasRole('023001002')">
			<li><a href='<c:url value="/basic/account/list.do"/>' ><span>권한관리</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023001003')">
			<li><a href="#"><span>카테고리관리</span><span class="i"></span></a>
				<ul class='hidden'>
					<li><a href='<c:url value="/basic/category/list.do?ctgrTypCd=009001"/>'><span>혁신멤버</span></a></li>
					<li><a href='<c:url value="/basic/category/list.do?ctgrTypCd=009002"/>'><span>프로젝트</span></a></li>
					<li><a href='<c:url value="/basic/category/list.do?ctgrTypCd=009003"/>'><span>행사</span></a></li>
					<li><a href='<c:url value="/basic/category/list.do?ctgrTypCd=009004"/>'><span>자료실</span></a></li>
					<li><a href='<c:url value="/basic/category/list.do?ctgrTypCd=009005"/>'><span>활동이야기</span></a></li>
				</ul>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('023001004')">
			<li><a href='<c:url value="/basic/popular_search_word/list.do"/>' ><span>인기검색어</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023001005')">
			<li><a href='<c:url value="/basic/sms/list.do"/>' ><span>SMS관리</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('023001007')">
			<li><a href='<c:url value="/basic/family/list.do"/>' ><span>관련사이트</span></a></li>
		</sec:authorize>
		</ul>
	</div>
</div><!-- sideMenu -->