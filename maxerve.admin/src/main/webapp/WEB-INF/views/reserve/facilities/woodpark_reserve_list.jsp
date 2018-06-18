<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<script>
function goList() {
	location.href = '<c:url value="/reserve/facilities/woodpark_reserve_calendar.do"/>';
}

jQuery(function($){
	$('#btnExcelDown').on('click', function(){
		$('form[name=form1]')
		.attr("target","_blank")
		.attr("action",'<c:url value="/reserve/facilities/woodpark_reserve_list_excel.do" />')
		.submit();

		return false;
	});

	$('.sortOne').on('click', function(){
		var sortVal = $('form[name=form1] input[name=sortOne]').val() ? '':'1';

		$('form[name=form1]')
		.attr('action', '<c:url value="/reserve/facilities/woodpark_reserve_list.do"/>')
		.find('input[name=sortOne]').val(sortVal)
		.end()
		.submit();

		return false;
	});

	$('.sortTwo').on('click', function(){
		var sortVal = $('form[name=form1] input[name=sortTwo]').val() ? '':'1';

		$('form[name=form1]')
		.attr('action', '<c:url value="/reserve/facilities/woodpark_reserve_list.do"/>')
		.find('input[name=sortTwo]').val(sortVal)
		.end()
		.submit();

		return false;
	});
});
</script>
<fmt:parseDate pattern="yyyy.MM.dd" var="srtDttm" value="${facilitiesMasterDTO.useSrtDt}"/>
<fmt:parseDate pattern="yyyy.MM.dd" var="endDttm" value="${facilitiesMasterDTO.useEndDt}"/>

<form:form commandName="reqFacilitiesMasterVO" name="form1" method="GET">
	<form:hidden path="fctMstIdx"/>
	<form:hidden path="clndDt"/>
	<form:hidden path="srtTm"/>
	<form:hidden path="endTm"/>
	<form:hidden path="sortOne"/>
	<form:hidden path="sortTwo"/>
</form:form>
<div class="location_area"><h3>우드파크 예약현황</h3></div>
<div class="subcon_area">
	<div class="small_list" >
		<table>
			<tr>
				<th>클래스명</th>
				<td><c:out value="${woodparkClassMVO.clsNm }"/></td>
				<th>이용일시</th>
				<td><c:out value="${reqFacilitiesMasterVO.clndDt } ${reqFacilitiesMasterVO.srtTm }~${reqFacilitiesMasterVO.endTm }"/></td>
				<th>이용료</th>
				<td><c:out value="${woodparkClassMVO.usePrcTypCdNm }"/></td>
			</tr>
			<tr>
				<th>신청회원수</th>
				<td><c:out value="${woodparkClassMVO.rsvtCnt }"/></td>
				<th>모집정원</th>
				<td><c:out value="${facilitiesMasterDTO.psblMaxPsct }"/></td>
				<th>진행상태</th>
				<td>
				<c:choose>
					<c:when test="${facilitiesMasterDTO.useYn ne 'Y' }">OFF</c:when>
					<c:when test="${now lt srtDttm }">모집준비중</c:when>
					<c:when test="${now gt endDttm }">모집완료</c:when>
					<c:otherwise>모집중</c:otherwise>
				</c:choose>
				</td>
			</tr>
		</table>
	</div>
	<div class="stit mt_20 mb_10">
		<div class="fr">
			<button id="btnExcelDown" class="btnBasic_blue">엑셀저장</button>
		</div>
	</div>

	<div class="basicTbl">
		<table>
			<thead>
				<tr>
					<th>아이디(이메일)</th>
					<th>성명</th>
					<th>휴대전화</th>
					<th class='sortOne'>
					<c:choose>
						<c:when test="${reqFacilitiesMasterVO.sortOne eq '1' }">멤버구분▽</c:when>
						<c:otherwise>멤버구분△</c:otherwise>
					</c:choose>
					</th>
					<th>소속단체</th>
					<th class='sortTwo'>
					<c:choose>
						<c:when test="${reqFacilitiesMasterVO.sortTwo eq '1' }">신청일▽</c:when>
						<c:otherwise>신청일△</c:otherwise>
					</c:choose>
					</th>
				</tr>
			</thead>
			<c:choose>
			<c:when test="${fn:length(facilitiesReserveMVOList) > 0 }">
			<tbody id="reserveTB">
				<c:forEach items="${facilitiesReserveMVOList}" var="item" varStatus="status" >
				<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="creDttm" value="item.creDttm" />
					<tr>
						<td><c:out value="${item.rsvtEmil }" /></td>
						<td><c:out value="${item.rsvtNm }" /></td>
						<td><c:out value="${item.rsvtPhnNmbr }" /></td>
						<td><c:out value="${item.mbrTypCdNm }" /></td>
						<td><c:out value="${item.assOztnNm }" /></td>
						<td><fmt:formatDate value="creDttm" pattern="yyyy.MM.dd"/></td>
					</tr>
				</c:forEach>
			</tbody>
			</c:when>
			<c:otherwise>
				<tbody>
					<tr>
						<td colspan="6" align="center">조회결과가 없습니다.</td>
					</tr>
				</tbody>
			</c:otherwise>
			</c:choose>
		</table>
	</div>

	<div class="btns tar">
		<button type="button" class="btnBasic_black mb_10" onClick="goList();">목록</button>
	</div>
</div>