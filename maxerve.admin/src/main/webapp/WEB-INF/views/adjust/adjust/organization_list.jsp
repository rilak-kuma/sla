<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
// 목록
function goList() {
	$('form[name=form]')
	.attr('action', '<c:url value="/adjust/adjust/summary_organization_list.do"/>')
	.find('input')
	.not('[name=page]')
	.not('[name=srtDt]')
	.not('[name=endDt]')
	.remove();

	$('form[name=form]').submit();
}

// 상세이동
function goDetail(locTypCd, locIdx) {
	$('form[name=form]')
	.attr('action', '<c:url value="/adjust/adjust/organization_payment_list.do"/>')
	.append('<input type="hidden" name="locTypCd" value="' + locTypCd + '"/>')
	.append('<input type="hidden" name="locIdx" value="' + locIdx + '"/>')
	.submit();
}

//엑셀저장
function goExcel() {
	if (!$('body').is(':has(#excelIframe)')) {
		$('body').append('<iframe class="hidden" id="excelIframe"></iframe>');
	}

	var param = $('form[name=form]').serialize();

	$('#excelIframe').attr('src', '<c:url value="/adjust/adjust/organization_list_excel.do"/>?' + param);
}

jQuery(function($){
	$('.basicTbl')
	// 상세
	.on('click', '[data-loc-typ-cd]', function(){
		goDetail($(this).attr('data-loc-typ-cd'), $(this).attr('data-loc-idx'));

		return false;
	});

	$('.btnBasic_black')
	.off('click')
	// 이전
	.on('click', function(){
		goList();

		return false;
	});

	$('#btnExcel')
	// 엑셀저장
	.on('click', function(){
		goExcel();

		return false;
	});
});
</script>
<form:form commandName="reqOrganizationSummaryVO" method="GET" name="form">
	<form:hidden path="page"/>
	<form:hidden path="oztnNm"/>
	<form:hidden path="ajmYn"/>
	<form:hidden path="dt"/>
	<form:hidden path="srtDt"/>
	<form:hidden path="endDt"/>
	<form:hidden path="organizationPage"/>
</form:form>

<div class="location_area">
	<h3>단체별 정산</h3>
</div>

<div class="subcon_area">
	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<table>
		<caption></caption>
		<colgroup>
			<col width="10%"/>
			<col width="20%"/>
			<col width="10%"/>
			<col width="20%"/>
			<col width="10%"/>
			<col width="*"/>
		</colgroup>
			<tr>
				<th>정산월</th>
				<td><c:out value="${reqOrganizationSummaryVO.dt }"/></td>
				<th>정산건수</th>
				<td><c:out value="${adjustSummaryMVO.appr }"/></td>
				<th>총 정산금액</th>
				<td><c:out value="${adjustSummaryMVO.apprPrc }"/></td>
			</tr>
		</table>
	</div>

	<!-- 조회 테이블 -->
	<div class="basicTbl02">
	<form:form commandName="reqOrganizationSummaryVO" method="GET" action="?" name="searchForm">
	<form:hidden path="page"/>
	<form:hidden path="dt"/>
	<form:hidden path="srtDt"/>
	<form:hidden path="endDt"/>
		<table>
			<caption></caption>
					<colgroup>
						<col width="120px"/>
						<col width=""/>
						<col width="100px"/>
					</colgroup>
			<tr>
				<th>단체명(성명)</th>
				<td>
					<form:input path="oztnNm" style="width:120px;"/>
				</td>
				<th>상태</th>
				<td>
					<select style='width:120px;' name='ajmYn'>
						<option value=''>전체</option>
						<option value='Y'${reqOrganizationSummaryVO.ajmYn eq 'Y' ? ' SELECTED':'' }>정산완료</option>
						<option value='N'${reqOrganizationSummaryVO.ajmYn eq 'N' ? ' SELECTED':'' }>정산예정</option>
					</select>
				</td>
				<td>
					<input type='submit' class='btnSearch' value='조회' />
				</td>
			</tr>
		</table>
	</form:form>
	</div>

	<div class="stit mt_20 mb_10">
		총  <strong><fmt:formatNumber value="${paginationInfo.totalRecordCount}" groupingUsed="true"/></strong>건
		<div class='fr'>
			<button id='btnExcel' class='btnBasic'>엑셀저장</button>
		</div>
	</div>

	<!-- 리스트형 테이블 -->
	<div class="basicTbl">
		<table>
		<caption></caption>
		<colgroup>
			<col width="150"/>
			<col width=""/>
			<col width="100"/>
			<col width="100"/>
			<col width="100"/>
			<col width="100"/>
		</colgroup>
		<thead>
			<tr>
				<th>단체명</th>
				<th>대표자명</th>
				<th>건수</th>
				<th>결제금액(원)</th>
				<th>수수료(원)</th>
				<th>정산금액(원)</th>
				<th>정산일</th>
				<th>상태</th>
			</tr>
		</thead>
			<tbody>
			<c:choose>
				<c:when test="${!empty adjustHistoryMVOList }">
					<c:forEach items="${adjustHistoryMVOList}" var="item">
						<tr data-loc-typ-cd='<c:out value="${item.locTypCd }"/>' data-loc-idx='<c:out value="${item.locIdx }"/>'>
								<td><c:out value="${item.oztnNm }"/></td>
								<td><c:out value="${item.ceoNm }"/></td>
								<td><c:out value="${item.count }"/></td>
								<td><c:out value="${item.prc }"/></td>
								<td><c:out value="${item.fees }" /></td>
								<td><c:out value="${item.prc + item.fees }" /></td>
								<td><c:out value="${item.ajmDt }"/></td>
								<td>
							<c:choose>
								<c:when test="${item.ajmYn eq 'Y' }">정산완료</c:when>
								<c:otherwise>정산예정</c:otherwise>
							</c:choose>
								</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6" align="center">조회결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
	<!--페이지번호-->
<c:if test="${paginationInfo.totalRecordCount gt 0 }">
	<ul class='page'>
		<ui:pagination paginationInfo="${paginationInfo}" jsFunction="goPage" type='image' />
	</ul>
</c:if>

<!-- 버튼 -->
<div class="btns tar mb_40">
	<button type="button" class="btnBasic_black">이전</button>
</div>
</div><!--subcon_area-->
