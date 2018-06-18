<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
// 상세이동
function goDetail(payIdx) {
	localStorage.setItem('backurl', location.href);

	location.href = '<c:url value="/adjust/pay/detail.do"/>?payIdx=' + payIdx;
}

//엑셀저장
function goExcel() {
	if (!$('body').is(':has(#excelIframe)')) {
		$('body').append('<iframe class="hidden" id="excelIframe"></iframe>');
	}

	var param = $('form[name=form]').serialize();

	$('#excelIframe').attr('src', '<c:url value="/adjust/sponsor/list_excel.do"/>?' + param);
}

jQuery(function($){
	// 달력
	$('.inp_cal').datepicker();

	$('.btn_cal')
	.on('click', function(){
		$(this).prev('.inp_cal').datepicker('show');
	});

	$('.basicTbl')
	// 상세
	.on('click', '[data-pay-idx]', function(){
		goDetail($(this).attr('data-pay-idx'));

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
<form:form commandName="reqSponsorVO" method="GET" name="form">
	<form:hidden path="page"/>
	<form:hidden path="srtDt"/>
	<form:hidden path="endDt"/>
	<form:hidden path="payMthd"/>
	<form:hidden path="srchTyp"/>
	<form:hidden path="srchWord"/>
</form:form>

<div class="location_area"><h3>후원 현황</h3></div>

<div class="subcon_area">
	<!-- 조회 테이블 -->
	<div class="basicTbl02">
	<form:form commandName="reqSponsorVO" method="GET" action="?" name="searchForm">
		<table>
			<caption></caption>
					<colgroup>
						<col width="120px"/>
						<col width=""/>
						<col width="100px"/>
					</colgroup>
			<tr>
				<th>후원일시</th>
				<td>
					<form:input path="srtDt" cssStyle="width:100px" cssClass='inp_cal' id='searchSrtDt'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
					<span class="mr_20"> ~ </span>
					<form:input path="endDt" cssStyle="width:100px" cssClass='inp_cal' id='searchEndDt'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
				</td>
				<td rowspan='3'>
					<input type='submit' class='btnSearch' value='조회' />
				</td>
			</tr>
			<tr>
				<th>결제방식</th>
				<td>
					<select style='width:120px;' name='payMthd'>
						<option value=''>전체</option>
						<option value='CARD'${reqSponsorVO.payMthd eq 'CARD' ? ' SELECTED':'' }>신용카드</option>
						<option value='BANK'${reqSponsorVO.payMthd eq 'BANK' ? ' SELECTED':'' }>계좌이체</option>
						<option value='KAKAOPAY'${reqSponsorVO.payMthd eq 'KAKAOPAY' ? ' SELECTED':'' }>카카오페이</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>검색어</th>
				<td>
					<select style='width:120px;' name='srchTyp'>
						<option value='1'${reqSponsorVO.srchTyp eq '1' ? ' SELECTED':'' }>후원자</option>
						<option value='2'${reqSponsorVO.srchTyp eq '2' ? ' SELECTED':'' }>후원프로젝트</option>
					</select>
					<form:input path="srchWord" cssStyle="width:120px;" />
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
			<col width="100"/>
			<col width="100"/>
			<col width="*"/>
			<col width="100"/>
			<col width="100"/>
		</colgroup>
		<thead>
			<tr>
				<th>후원일시</th>
				<th>후원자</th>
				<th>휴대전화</th>
				<th>후원 프로젝트</th>
				<th>결제방식</th>
				<th>후원금액</th>
			</tr>
		</thead>
			<tbody>
			<c:choose>
				<c:when test="${!empty sponsorMVOList }">
					<c:forEach items="${sponsorMVOList}" var="item">
					<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="creDttm" value="${item.creDttm }" />
						<tr data-pay-idx='<c:out value="${item.payIdx }"/>'>
								<td><fmt:formatDate value="${creDttm }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
								<td><c:out value="${item.spoNm }"/></td>
								<td><c:out value="${item.spoPhn }" /></td>
								<td><c:out value="${item.pjtTitl }" /></td>
								<td>
							<c:choose>
								<c:when test="${item.payMthd eq 'CARD' }">신용카드</c:when>
								<c:when test="${item.payMthd eq 'BANK' }">계좌이체</c:when>
								<c:when test="${item.payMthd eq 'KAKAOPAY' }">카카오페이</c:when>
								<c:when test="${item.payMthd eq 'CELLPHONE' }">휴대폰</c:when>
							</c:choose>
								</td>
								<td><c:out value="${item.spoAom }"/></td>
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
</div><!--subcon_area-->
