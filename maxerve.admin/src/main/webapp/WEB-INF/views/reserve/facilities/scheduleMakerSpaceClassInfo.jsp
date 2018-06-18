<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script>

jQuery(function($){

	//목록
	$('#btnCansle').on('click', function(){
		window.history.back();
	});

	//시설선택
	$('span#fctSelect').on('click', function(){
		location.href = '<c:url value="/reserve/facilities/scheduleMakerSpace.do"/>?fctMstIdx=' + $(this).attr('data-mst-idx');
		return false;
	});

	//목록 상세 : 오후예약 명단
	$('.tbody').on('click','tr', function(){
		var url = '<c:out value="scheduleMakerSpaceClassMbrList.do" />';
		url += '?mkspClsIdx=<c:out value="${searchVO.mkspClsIdx}" />&';
		url += 'srtTm=' + $(this).parents('tbody:first').attr('data-srt-tm') + '&';
		url += 'titl=<c:out value="${searchVO.titl }" />';

		window.open(url, 'newwin', 'width=800, height=600, scrollbars=yes');

		return false;
	});

	// 초기설정
	$(document).ready(function(){
		<c:forEach	items="${mkspReserveList }" var="mkspReserveList" >
			$('.thead[data-srt-tm="${mkspReserveList.srtTm}"] tr').append('<td><c:out value="${mkspReserveList.grpNm}" /></td>');
			$('.tbody[data-srt-tm="${mkspReserveList.srtTm}"] tr').append('<td><c:out value="${mkspReserveList.mkspClsRsvCnt}" />/<c:out value="${mkspReserveList.psct}" /></td>');
		</c:forEach>
	});

});
</script>
<form:form commandName="reqMakerspaceClassVO" name="infoFm" method="GET">
	<form:hidden path="mkspClsIdx"/>
	<form:hidden path="titl"/>
	<form:hidden path="srtTm"/>
</form:form>

<div class="location_area"><h3>서울이노베이션팹랩 예약현황</h3></div>
<div class="subcon_area">
	<ul class="small_list">
		<c:forEach var="facilitiesList" items="${facilitiesList }">
			<c:if test="${facilitiesList.fctNm.indexOf('모임방') < 0 }" >
			<li>
				<span id="fctSelect" data-mst-idx='<c:out value="${facilitiesList.fctMstIdx }" />' <c:if test='${facilitiesList.fctMstIdx eq searchVO.fctMstIdx}' >style="color:red" </c:if> >
					<c:out value="${facilitiesList.fctNm }" />
				</span>
			</li>
			</c:if>
		</c:forEach>
	</ul>

	<div class="basicTbl02">
		<table>
			<tr>
				<th>교육명</th>
				<td><c:out value="${searchVO.titl }" /></td>
				<th>신청인원수</th>
				<td><c:out value="${searchVO.mkspClsRsvCnt }" /></td>
			</tr>
			<tr>
				<th>모집기간</th>
				<td><c:out value="${searchVO.srtDt}" />~<c:out value="${searchVO.endDt}" /></td>
				<th>진행상태</th>
				<td><c:out value="${searchVO.useYn eq 'Y' ? '모집중' : '모집완료'}" /></td>
			</tr>
		</table>
	</div>

<c:forEach items="${groupTimeList }" var="item" varStatus="status">
	<div class="mt_20">
		<strong>${status.count }부(${item.srtTm } ~ ${item.endTm })</strong>
	</div>
	<div class="basicTbl">
		<table>
			<thead class='thead' data-srt-tm='${item.srtTm }'>
				<tr></tr>
			</thead>
			<tbody class='tbody' data-srt-tm='${item.srtTm }'>
				<tr></tr>
			</tbody>
		</table>
	</div>
</c:forEach>
</div>
<div class="tac">
	<button id="btnCansle" class="btnBasic_black">목록</button>
</div>
