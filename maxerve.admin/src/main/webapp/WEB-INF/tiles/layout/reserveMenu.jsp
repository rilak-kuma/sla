<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
jQuery(function($){
	$('.submenu_area .submenu li[data-mnu-cd=023005]').addClass('on');
});
</script>
<div id="sideMenu">
	<h1><a href='<c:url value="/"/>'><img src="../../img/logo.png" alt="서울혁신파크"/></a></h1>
	<h2>예약.일정 관리</h2>
	<!-- gnb -->
	<div id="menu_left" class="menu_left">
		<ul class="stit">
		<sec:authorize access="hasAnyRole('023005001', '023005002')">
			<li><a href="#"><span>미래청(회의/세미나)</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005002')">
					<li><a href='<c:url value="/reserve/facilities/insert.do?rsvtPlcCd=002001&fctMstIdx=1"/>' ><span>미래청(회의/세미나) 일정관리</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005001')">
					<%-- <li><a href='<c:url value="/reserve/facilities/scheduleCalendar.do?rsvtPlcCd=002001"/>' ><span>미래청(회의/세미나) 예약현황</span></a></li> --%>
					<li><a href='<c:url value="/reserve/facilities/reserve_list.do?rsvtPlcCd=002001&fctGubn=0"/>'><span>미래청(회의/세미나) 예약현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
			<li><a href="#"><span>모두모임방</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005002')">
					<li><a href='<c:url value="/reserve/facilities/insert.do?rsvtPlcCd=002001&fctMstIdx=9"/>' ><span>모두모임방 일정관리</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005001')">
					<li><a href='<c:url value="/reserve/facilities/reserve_list.do?rsvtPlcCd=002001&fctGubn=1"/>' ><span>모두모임방 예약현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
			<li><a href="#"><span>야외공간</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005002')">
					<li><a href='<c:url value="/reserve/facilities/insert.do?rsvtPlcCd=002002&fctMstIdx=11"/>' ><span>야외공간 일정관리</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005001')">
					<li><a href='<c:url value="/reserve/facilities/reserve_list.do?rsvtPlcCd=002002"/>' ><span>야외공간 예약현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
			<li><a href="#"><span>서울이노베이션팹랩</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005002')">
					<li><a href='<c:url value="/reserve/facilities/insert.do?rsvtPlcCd=002003&fctMstIdx=14"/>' ><span>팹랩 일정관리</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005001')">
					<li><a href='<c:url value="/reserve/facilities/scheduleMakerSpace.do"/>' ><span>예약일정관리 - 팹랩 예약현황</span></a></li>
					<%-- <li><a href='<c:url value="/reserve/fctMember/facilitiesMemberState.do?locTypCd=002003"/>' ><span>팹랩 - 월회원현황</span></a></li> --%>
					<li><a href='<c:url value="/reserve/facilities/reserve_list2.do?locTypCd=002003"/>' ><span>팹랩 - 멤버십현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
			<li><a href="#"><span>우드파크</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005002')">
					<li><a href='<c:url value="/reserve/facilities/woodpark_reserve_calendar.do"/>' ><span>우드파크 예약현황</span></a></li>
					<li><a href='<c:url value="/reserve/facilities/woodpark_list.do"/>' ><span>우드파크 일정관리</span></a></li>
					<li><a href='<c:url value="/reserve/facilities/woodpark_class.do"/>' ><span>우드파크 클래스관리</span></a></li>
					<li><a href='<c:url value="/reserve/facilities/woodpark_class_schedule.do?locTypCd=002004&fctMstIdx=21"/>' ><span>우드파크 클래스일정</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005001')">
					<li><a href='<c:url value="/reserve/fctMember/facilitiesMemberState.do?locTypCd=002004"/>' ><span>우드파크 - 월회원현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('023005004', '023005005')">
			<li><a href="#"><span>방문</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005005')">
					<li><a href='<c:url value="/reserve/facilities/parktour_list.do"/>' ><span>방문 일정관리</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005004')">
					<li><a href='<c:url value="/reserve/tour/parktourReserve_list.do"/>' ><span>방문 예약현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('023005001', '023005002')">
			<li><a href="#"><span>미래청(녹음/운동)</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005002')">
					<li><a href='<c:url value="/reserve/facilities/insert.do?rsvtPlcCd=002006&fctMstIdx=22"/>' ><span>미래청(녹음/운동) 일정관리</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005001')">
					<%-- <li><a href='<c:url value="/reserve/facilities/scheduleCalendar.do?rsvtPlcCd=002006"/>'><span>미래청(녹음/운동) 예약현황</span></a></li> --%>
					<li><a href='<c:url value="/reserve/facilities/reserve_list.do?rsvtPlcCd=002006"/>'><span>미래청(녹음/운동) 예약현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
			<li><a href="#"><span>참여동</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005002')">
					<li><a href='<c:url value="/reserve/facilities/insert.do?rsvtPlcCd=002010&fctMstIdx=8"/>' ><span>참여동 일정관리</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005001')">
					<%-- <li><a href='<c:url value="/reserve/facilities/scheduleCalendar.do?rsvtPlcCd=002010"/>' ><span>참여동 예약현황</span></a></li> --%>
				    <li><a href='<c:url value="/reserve/facilities/reserve_list.do?rsvtPlcCd=002010"/>' ><span>참여동 예약현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
			<li><a href="#"><span>극장동</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005002')">
					<li><a href='<c:url value="/reserve/facilities/insert.do?rsvtPlcCd=002008&fctMstIdx=34"/>' ><span>극장동 일정관리</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005001')">
					<li><a href='<c:url value="/reserve/facilities/reserve_list.do?rsvtPlcCd=002008"/>' ><span>극장동 예약현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
			<li><a href="#"><span>맛동</span><span class="i"></span></a>
				<ul class="hidden">
				<sec:authorize access="hasRole('023005002')">
					<li><a href='<c:url value="/reserve/facilities/insert.do?rsvtPlcCd=002009&fctMstIdx=36"/>' ><span>맛동 일정관리</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('023005001')">
					<li><a href='<c:url value="/reserve/facilities/reserve_list.do?rsvtPlcCd=002009"/>' ><span>맛동 예약현황</span></a></li>
				</sec:authorize>
				</ul>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('023005002')">
			<%-- <li><a href='<c:url value="/reserve/rent_area/list.do?mbrId=ALL"/>' ><span>대관일정(대관제한)</span></a></li> --%>
			<li><a href='<c:url value="/reserve/rent_area/list.do?mbrId="/>' ><span>대관일정(대관제한)</span></a></li>
			<li><a href='<c:url value="/reserve/facilities/equip.do"/>' ><span>대여장비관리</span></a></li>
		</sec:authorize>
		</ul>
	</div>
</div><!-- sideMenu -->