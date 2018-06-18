<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
function goPage(pg) {

}

jQuery(function($){
	// 일정등록
	$('#btnInsert').on('click', function(){
		$('form[name=form]')
		.attr('action', '<c:out value="/reserve/facilities/insert.do"/>')
		.submit();
	});

	// 수정
	$('tbody').on('click', 'tr', function(){
		$('form[name=form]')
		.attr('action', '<c:url value="/reserve/facilities/insert.do"/>')
		.append('<input type="hidden" name="fctMstIdx" value="' + $(this).attr('data-fct-mst-idx') + '"/>')
		.submit();
	});
});
</script>
<form:form commandName="reqFacilitiesMasterVO" name="form" method="GET">
	<form:hidden path="page"/>
	<form:hidden path="rsvtPlcCd"/>
</form:form>

<div class="location_area"><h3>우드파크 일정관리</h3></div>

<div class="subcon_area">

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
					<th>모집정원</th>
					<th>운영기간</th>
					<th>등록일</th>
					<th>진행상태</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${facilitiesMasterDTOList }" var="item">
			<fmt:parseDate var="creDt" value="${item.creDttm }" pattern="yyyy-MM-dd HH:mm:ss" />
				<tr data-fct-mst-idx='<c:out value="${item.fctMstIdx }"/>'>
					<td><c:out value="${item.fctNm }"/></td>
					<td><c:out value="${item.psblMaxPsct }"/> / 1회</td>
					<td>
						<c:out value="${item.useSrtDt }"/>
						~
						<c:out value="${item.useEndDt }"/>
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

	<ul class='page'>
		<ui:pagination paginationInfo="${reqFacilitiesMasterVO.pageInfo }" jsFunction="goPage" type='image'/>
	</ul>
</div>