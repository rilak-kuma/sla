<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
jQuery(function(){
	$('.submenu_area .submenu li[data-mnu-cd]:first a').get(0).click();

});
</script>
<%-- <a href='<c:out value="/member/logout.do"/>'>logout</a> --%>
<ul>
<%-- <li><a href='<c:url value="/basic/account/mypage.do"/>' target='_blank'>기본관리 - 정보수정</a></li>
<li><a href='<c:url value="/basic/account/list.do"/>' target='_blank'>기본관리 - 권한관리</a></li>
<li><a href='<c:url value="/basic/category/list.do"/>' target='_blank'>기본관리 - 카테고리관리</a></li>
<li><a href='<c:url value="/basic/popular_search_word/list.do"/>' target='_blank'>기본관리 - 인기검색어</a></li>
<li><a href='<c:url value="/basic/sms/list.do"/>' target='_blank'>기본관리 - SMS관리</a></li> --%>

<%-- <li><a href='<c:out value="/member/currentState.do"/>' target='_blank'>입주단체현황</a></li>
<li><a href='<c:out value="/member/moveInOztnList.do"/>' target='_blank'>입주단체관리</a></li>
<li><a href='<c:out value="/member/newMoveInAplyList.do"/>' target='_blank'>신규입주신청</a></li>
<li><a href='<c:out value="/member/onlineMemberState.do"/>' target='_blank'>온라인멤버현황</a></li>
<li><a href='<c:out value="/member/onlineMemberList.do"/>' target='_blank'>온라인멤버</a></li> --%>

<%-- <li><a href='<c:out value="/information/notice/noticeList.do"/>' target='_blank'>센터공지</a></li>
<li><a href='<c:out value="/information/recruit/recruitList.do"/>' target='_blank'>구인</a></li>
<li><a href='<c:url value="/information/newsletter/subscriberList.do"/>' target='_blank'>뉴스레터 구독자 목록</a></li>
<li><a href='<c:url value="/information/newsletter/newsletterList.do"/>' target='_blank'>뉴스레터 목록</a></li> --%>

<%-- <li><a href='<c:out value="/activity/referenceRoom/popularTag.do"/>' target='_blank'>자료실인기태그</a></li>
<li><a href='<c:out value="/activity/propose/proposeList.do"/>' target='_blank'>제안</a></li>
<li><a href='<c:out value="/activity/project/projectList.do"/>' target='_blank'>프로젝트</a></li>
<li><a href='<c:out value="/activity/event/eventList.do"/>' target='_blank'>이벤트/행사</a></li>
<li><a href='<c:out value="/activity/propose/fswList.do"/>' target='_blank'>아이디어/협업</a></li>
<li><a href='<c:out value="/activity/referenceRoom/referenceList.do"/>' target='_blank'>자료실</a></li> --%>

<%-- <li><a href='<c:out value="/facilities/future/floor.do"/>' target='_blank'>시설관리 : 층별호실정보</a></li> --%>
<%-- <li><a href='<c:out value="/facilities/future/floorRoomList.do"/>' target='_blank'>시설관리 : 미래청 층별관리 </a></li> --%>

<%-- li><a href='<c:url value="/reserve/facilities/list.do?rsvtPlcCd=002001"/>' target='_blank'>예약일정관리 - 회의/세미나예약현황</a></li>
<li><a href='<c:url value="/reserve/facilities/reserve_list.do?rsvtPlcCd=002001"/>' target='_blank'>예약일정관리 - 모두모임방 예약현황</a></li>
<li><a href='<c:url value="/reserve/facilities/reserve_list.do?rsvtPlcCd=002002"/>' target='_blank'>예약일정관리 - 야외공간 예약현황</a></li>
<li><a href='<c:url value="/reserve/tour/parktourReserve_list.do"/>' target='_blank'>예약일정관리 - 파크투어 예약현황</a></li> --%>

<%-- <li><a href='<c:url value="/reserve/facilities/list.do?rsvtPlcCd=002005"/>' target='_blank'>예약일정관리 - 모두모임방</a></li>
<li><a href='<c:url value="/reserve/facilities/list.do?rsvtPlcCd=002002"/>' target='_blank'>예약일정관리 - 야외공간</a></li>
<li><a href='<c:url value="/reserve/facilities/list.do?rsvtPlcCd=002003"/>' target='_blank'>예약일정관리 - 서울이노베이션팹랩</a></li>
<li><a href='<c:url value="/reserve/facilities/woodpark_list.do"/>' target='_blank'>예약일정관리 - 우드파크</a></li>
<li><a href='<c:url value="/reserve/facilities/woodpark_class.do"/>' target='_blank'>예약일정관리 - 우드파크 클래스관리</a></li>
<li><a href='<c:url value="/reserve/facilities/woodpark_class_schedule.do"/>' target='_blank'>예약일정관리 - 우드파크 클래스일정</a></li>
<li><a href='<c:url value="/reserve/facilities/list.do?rsvtPlcCd=002006"/>' target='_blank'>예약일정관리 - 기타시설</a></li>
<li><a href='<c:url value="/reserve/rent_area/list.do"/>' target='_blank'>대관목록</a></li>
<li><a href='<c:url value="/reserve/facilities/equip.do"/>' target='_blank'>대여장비관리</a></li>
<li><a href='<c:url value="/reserve/facilities/parktour_list.do"/>' target='_blank'>파크투어 일정관리</a></li>
<li><a href='<c:url value="/reserve/fctMember/facilitiesMemberState.do?locTypCd=002003"/>' target='_blank'>서울이노베이션팹랩 - 월회원현황</a></li>
<li><a href='<c:url value="/reserve/fctMember/facilitiesMemberState.do?locTypCd=002004"/>' target='_blank'>우드파크 - 월회원현황</a></li> --%>

<%-- <li><a href='<c:url value="/reserve/facilities/scheduleCalendar.do?rsvtPlcCd=002006"/>' target='_blank'>예약일정관리 - 기타시설 예약현황(달력)</a></li> --%>
<%-- <li><a href='<c:url value="/reserve/facilities/scheduleCalendar.do?rsvtPlcCd=002001"/>' target='_blank'>예약일정관리 - 회의세미나 예약현황(달력)</a></li> --%>
<%-- <li><a href='<c:url value="/reserve/facilities/scheduleMakerSpace.do"/>' target='_blank'>예약일정관리 - 서울이노베이션팹랩 예약현황(달력)</a></li> --%>
</ul>
