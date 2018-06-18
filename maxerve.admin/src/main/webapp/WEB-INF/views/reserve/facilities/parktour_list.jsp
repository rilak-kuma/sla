<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
function goPage(pg) {

}

jQuery(function($){
	$('.inp_cal').datepicker();

	// 일정등록
	$('#btnInsert').on('click', function(){
		$('form[name=form]')
		.attr('action', '<c:out value="/reserve/facilities/parktour_insert.do"/>')
		.submit();
	});

	// 수정
	$('.basicTbl tbody').on('click', 'tr', function(){
		$('form[name=form]')
		.attr('action', '<c:url value="/reserve/facilities/parktour_insert.do"/>')
		.append('<input type="hidden" name="fctMstIdx" value="' + $(this).attr('data-fct-mst-idx') + '"/>')
		.submit();
	});

	$('.sortOne').on('click', function(){
		var sortVal = $('form[name=form] input[name=sortOne]').val() ? '':'1';

		$('form[name=form]')
		.attr('action', '<c:url value="/reserve/facilities/parktour_list.do"/>')
		.find('input[name=sortTwo]').val('')
		.end()
		.find('input[name=sortOne]').val(sortVal)
		.end()
		.submit();

		return false;
	});

	$('.sortTwo').on('click', function(){
		var sortVal = $('form[name=form] input[name=sortTwo]').val() ? '':'1';

		$('form[name=form]')
		.attr('action', '<c:url value="/reserve/facilities/parktour_list.do"/>')
		.find('input[name=sortOne]').val('')
		.end()
		.find('input[name=sortTwo]').val(sortVal)
		.end()
		.submit();

		return false;
	});
});
</script>
<form:form commandName="reqFacilitiesMasterVO" name="form" method="GET">
	<form:hidden path="page"/>
	<form:hidden path="rsvtPlcCd"/>
	<form:hidden path="fctNm"/>
	<form:hidden path="useYn"/>
	<form:hidden path="srtCreDt"/>
	<form:hidden path="endCreDt"/>
	<form:hidden path="useSrtDt"/>
	<form:hidden path="useEndDt"/>
	<form:hidden path="sortOne"/>
	<form:hidden path="sortTwo"/>
</form:form>

<div class="location_area"><h3>방문 일정관리</h3></div>

<div class="subcon_area">
	<div class="small_list" >
	<form:form commandName="reqFacilitiesMasterVO" name="searchForm" method="GET">
		<table>
			<tr>
				<th>제목</th>
				<td>
					<form:input path="fctNm" cssStyle="width:90%;" maxlength="50"/>
				</td>
				<th>진행상태</th>
				<td>
					<select name="useYn">
						<option value="">전체</option>
						<option value='Y'${reqFacilitiesMasterVO.useYn eq 'Y' ? ' SELECTED':'' }>ON</option>
						<option value='N'${reqFacilitiesMasterVO.useYn eq 'N' ? ' SELECTED':'' }>OFF</option>
					</select>
				</td>
				<td rowspan="2">
					<input type="submit" class="btnSearch" value="조회" />
				</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>
					<form:input path="srtCreDt" readonly="readonly" cssClass="inp_cal"/> ~
					<form:input path="endCreDt" readonly="readonly" cssClass="inp_cal"/>
				</td>
				<th>운영기간</th>
				<td>
					<form:input path="useSrtDt" readonly="readonly" cssClass="inp_cal"/> ~
					<form:input path="useEndDt" readonly="readonly" cssClass="inp_cal"/>
				</td>
			</tr>
		</table>
	</form:form>
	</div>

	<!-- 버튼 -->
	<sec:authorize access="hasRole('023005005Y')">
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
					<th class='sortOne'>
					<c:choose>
						<c:when test="${reqFacilitiesMasterVO.sortOne eq '1' }">등록일▽</c:when>
						<c:otherwise>등록일△</c:otherwise>
					</c:choose>
					</th>
					<th class='sortTwo'>
					<c:choose>
						<c:when test="${reqFacilitiesMasterVO.sortTwo eq '1' }">진행상태▽</c:when>
						<c:otherwise>진행상태△</c:otherwise>
					</c:choose>
					</th>
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
		<ui:pagination paginationInfo="${reqFacilitiesMasterVO.pageInfo }" jsFunction="goPage" type='image' />
	</ul>
</div>