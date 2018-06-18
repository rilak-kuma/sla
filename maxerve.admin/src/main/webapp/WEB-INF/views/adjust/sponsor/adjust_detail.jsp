<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
// 상세이동
function goDetail(payIdx) {
	localStorage.setItem('backurl', location.href);

	location.href = '<c:url value="/adjust/pay/detail.do"/>?payIdx=' + payIdx;
}

//목록
function goList() {
	$('form[name=form]')
	.attr('action', '<c:url value="/adjust/sponsor/adjust_list.do"/>')
	.find('input')
	.not('[name=page]')
	.not('[name=srchDt]')
	.remove();

	$('form[name=form]').submit();
}

//엑셀저장
function goExcel() {
	if (!$('body').is(':has(#excelIframe)')) {
		$('body').append('<iframe class="hidden" id="excelIframe"></iframe>');
	}

	var param = $('form[name=form]').serialize();

	$('#excelIframe').attr('src', '<c:url value="/adjust/sponsor/adjust_detail_excel.do"/>?' + param);
}

jQuery(function($){
	$('.basicTbl')
	// 상세
	.on('click', '[data-pay-idx]', function(){
		goDetail($(this).attr('data-pay-idx'));

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
<form:form commandName="reqSponsorAdjustVO" method="GET" name="form">
	<form:hidden path="page"/>
	<form:hidden path="srchDt"/>
	<form:hidden path="dt"/>
	<form:hidden path="pjtIdx"/>
	<form:hidden path="detailPage"/>
</form:form>

<div class="location_area"><h3>업체별 후원금 정산</h3></div>

<div class="subcon_area">
	<!-- 조회 테이블 -->
	<div class="basicTbl02">
		<table>
			<caption></caption>
					<colgroup>
						<col width="120px"/>
						<col width=""/>
						<col width="100px"/>
					</colgroup>
			<tr>
				<th>단체명</th>
				<td><c:out value="${sponsorMVO.oztnNm }"/></td>
				<th>프로젝트명</th>
				<td><c:out value="${sponsorMVO.pjtTitl }"/></td>
			</tr>
			<tr>
				<th>후원기간</th>
				<td>
				<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="srtDttm" value="${sponsorMVO.srtDttm }" />
				<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="endDttm" value="${sponsorMVO.endDttm }" />

					<fmt:formatDate value="${srtDttm }" pattern="yyyy.MM.dd"/>
					~
					<fmt:formatDate value="${endDttm }" pattern="yyyy.MM.dd"/>
				</td>
			</tr>
			<tr>
				<th rowspan='2'>정산금액</th>
				<td><c:out value="${sponsorMVO.count }건 / ${sponsorMVO.spoAom + sponsorMVO.fees }원"/></td>
			</tr>
			<tr>
				<td>
					<c:out value="신용카드 (${sponsorMVO.cardAjmCount })건 / ${sponsorMVO.cardAjmPrc }원"/>,
					<c:out value="계좌이체 (${sponsorMVO.bankAjmCount })건 / ${sponsorMVO.bankAjmPrc }원"/>,
					<c:out value="카카오페이 (${sponsorMVO.kakaopayAjmCount })건 / ${sponsorMVO.kakaopayAjmPrc }원"/>,
					<c:out value="휴대폰 (${sponsorMVO.cellphoneAjmCount })건 / ${sponsorMVO.cellphoneAjmPrc }원"/>
				</td>
			</tr>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
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
				<th>후원자명</th>
				<th>결제수단</th>
				<th>결제금액</th>
				<th>수수료</th>
				<th>정산금액</th>
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
								<td>
							<c:choose>
								<c:when test="${item.payMthd eq 'CARD' }">신용카드</c:when>
								<c:when test="${item.payMthd eq 'BANK' }">계좌이체</c:when>
								<c:when test="${item.payMthd eq 'KAKAOPAY' }">카카오페이</c:when>
								<c:when test="${item.payMthd eq 'CELLPHONE' }">휴대폰</c:when>
							</c:choose>
								</td>
								<td><c:out value="${item.spoAom }" /></td>
								<td><c:out value="${item.fees }"/></td>
								<td><c:out value="${item.spoAom + item.fees }"/></td>
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
