<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
#facilitiesBox li.on {
	color: red;
}
</style>
<script>
function goPage(pg) {

}

jQuery(function($){
	// 일정등록
	$('#btnInsert').on('click', function(){
		$('form[name=form]')
		.attr('action', '<c:out value="/reserve/facilities/makerspace_class_insert.do"/>')
		.submit();
	});

	// 시설 클릭
	$('#facilitiesBox').on('click', 'li', function(){
		var url = '?';

		if ($('form[name=form] input[name=rsvtPlcCd]').val() == '002003' && $(this).attr('data-fct-mst-idx') == '20') {
			url = '<c:url value="/reserve/facilities/makerspace_class_list.do"/>';
		} else {
			url = '<c:url value="/reserve/facilities/insert.do"/>';
		}

		$('form[name=form]')
		.attr('action', url)
		.find('input[name=fctMstIdx]')
		.val($(this).attr('data-fct-mst-idx'));

		$('form[name=form]').submit();
	});

	// 수정
	$('tbody').on('click', 'tr', function(){
		$('form[name=form]')
		.attr('action', '<c:url value="/reserve/facilities/makerspace_class_insert.do"/>')
		.append('<input type="hidden" name="mkspClsIdx" value="' + $(this).attr('data-mksp-cls-idx') + '"/>')
		.submit();
	});
});
</script>
<form:form commandName="reqMakerspaceClassVO" name="form" method="GET">
	<form:hidden path="rsvtPlcCd"/>
	<form:hidden path="fctMstIdx"/>
	<form:hidden path="page"/>
</form:form>

<div class="location_area"><h3>서울이노베이션팹랩 일정관리</h3></div>

<div class="subcon_area">

	<ul id='facilitiesBox' class='small_list'>
	<c:forEach items="${facilitiesMasterDTOList }" var="item">
	<c:set var="c">
		<c:if test="${item.fctMstIdx eq reqMakerspaceClassVO.fctMstIdx }"> class='on'</c:if>
	</c:set>
		<li data-fct-mst-idx='<c:out value="${item.fctMstIdx }"/>'${c }><c:out value="${item.fctNm }"/></li>
	</c:forEach>
	</ul>

	<sec:authorize access="hasRole('023005002Y')">
	<!-- 버튼 -->
	<div class="btns tar">
		<button type="button" class="btnBasic_blue mt_20 mb_10" id='btnInsert'>일정등록</button>
	</div>
	</sec:authorize>

	<!-- 리스트형 테이블 -->
	<div class="basicTbl">
		<table>
			<thead>
				<tr>
					<th>제목</th>
					<th>모집기간</th>
					<th>등록일</th>
					<th>진행상태</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${makerspaceClassDTOList }" var="item">
			<fmt:parseDate var="creDt" value="${item.creDttm }" pattern="yyyy-MM-dd HH:mm:ss" />
				<tr data-mksp-cls-idx='<c:out value="${item.mkspClsIdx }"/>'>
					<td><c:out value="${item.titl }"/></td>
					<td>
						<c:out value="${item.srtDt }"/>
						~
						<c:out value="${item.endDt }"/>
					</td>
					<td><fmt:formatDate value="${creDt }" pattern="yyyy.MM.dd" /></td>
					<td>
					<c:if test="${item.useYn eq 'Y' }">ON</c:if>
					<c:if test="${item.useYn ne 'Y' }">OFF</c:if>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<ul class="page">
		<ui:pagination paginationInfo="${reqMakerspaceClassVO.pageInfo }" jsFunction="goPage" type="image"/>
	</ul>
</div>