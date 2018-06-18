<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
/* CSS로 마우스 드래그 방지 풀기 */
.subcon_area{-ms-touch-action:text}
.subcon_area{-webkit-user-select:text;-khtml-user-select:text;-moz-user-select:text;-o-user-select:text;user-select:text}
</style>

<script type="text/javascript">
function goList() {
	var url = localStorage.getItem('backurl');

	if (url) {
		location.href = url;
	} else {
		$('form[name=form]')
		.attr('action', '<c:url value="/adjust/pay/list.do"/>')
		.submit();
	}
}

function goExcel() {
	if (!$('body').is(':has(#excelIframe)')) {
		$('body').append('<iframe class="hidden" id="excelIframe"></iframe>');
	}

	$('#excelIframe').attr('src', '<c:url value="/adjust/pay/detail_excel.do"/>?payIdx=<c:out value="${reqPaymentVO.payIdx}"/>');
}

jQuery(function($){
	$('.btns_02 .btnBasic_blue').off('click');

	$('.btns_02')
	// 확인
	.on('click', '.btnBasic_blue', function(){
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
<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="creDttm" value="${paymentMVO.creDttm }" />

<form:form commandName="reqPaymentVO" method="GET" name="form">
	<form:hidden path="page"/>
	<form:hidden path="oztnNm"/>
	<form:hidden path="srchLocTyp"/>
	<form:hidden path="srtDt"/>
	<form:hidden path="endDt"/>
	<form:hidden path="srchStatus"/>
</form:form>

<div class="location_area"><h3>결제</h3></div>

<div class="subcon_area">
	<div class='mt_20 mb_10'>
		<strong>결제정보</strong>
		<div class='fr'>
			<button id='btnExcel' class='btnBasic'>엑셀저장</button>
		</div>
	</div>

	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<table>
		<caption></caption>
		<colgroup>
			<col width="150px"/>
			<col width="40%"/>
			<col width="150px"/>
			<col width="*"/>
		</colgroup>
			<tr>
				<th>결제일시</th>
				<td><fmt:formatDate value="${creDttm }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
				<th>상태</th>
				<td>
			<c:choose>
				<c:when test="${paymentMVO.apprYn ne 'Y' }">결제오류</c:when>
				<c:when test="${paymentMVO.cnclRstCd eq '2001' or paymentMVO.cnclRstCd eq '2002' }">결제취소</c:when>
				<c:otherwise>결제완료</c:otherwise>
			</c:choose>
				</td>
			</tr>
			<tr>
				<th>결제수단</th>
				<td>
			<c:choose>
				<c:when test="${paymentMVO.payMthd eq 'CARD' }">[신용카드]<c:out value="${paymentMVO.cardNm }"/></c:when>
				<c:when test="${paymentMVO.payMthd eq 'BANK' }">[계좌이체]<c:out value="${paymentMVO.bankNm }"/></c:when>
				<c:when test="${paymentMVO.payMthd eq 'CELLPHONE' }">[휴대폰]<c:out value="${paymentMVO.dstAddr }"/></c:when>
				<c:when test="${paymentMVO.payMthd eq 'KAKAOPAY' }">[카카오페이]<c:out value="${paymentMVO.cardNm }"/></c:when>
			</c:choose>
				</td>
				<th>결제금액</th>
				<td><c:out value="${paymentMVO.prc }"/>원</td>
			</tr>
	<c:choose>
		<c:when test="${paymentMVO.payMthd eq 'CARD' or paymentMVO.payMthd eq 'KAKAOPAY' }">
			<tr>
				<th>할부</th>
				<td>
			<c:choose>
				<c:when test="${paymentMVO.cardQuota eq '00' }">일시불</c:when>
				<c:otherwise><c:out value="${paymentMVO.cardQuota}"/>개월</c:otherwise>
			</c:choose>
				</td>
				<th>카드포인트결제</th>
				<td>${paymentMVO.cardPoint eq '0' ? '미사용':'사용' }</td>
			</tr>
		</c:when>
		<c:when test="${paymentMVO.payMthd eq 'BANK' }">
			<tr>
				<th>현금영수증</th>
				<td colspan='3'>${paymentMVO.rcptTyp eq '0' ? '미발급':'발급' }</td>
			</tr>
		</c:when>
	</c:choose>
			<tr>
				<th>결제항목</th>
				<td colspan='3'><c:out value="[${paymentMVO.locTitl }] ${paymentMVO.titl }" /></td>
			</tr>
		<c:if test="${!empty stringList }">
			<tr>
				<th>결제상세내역</th>
				<td colspan='3'>
				<c:forEach items="${stringList }" var="item" varStatus="status">
					${!status.first ? '<br/>':'' }
					<c:out value="${item }"/>
				</c:forEach>
				</td>
			</tr>
		</c:if>
		</table>
	</div>

	<div class='mt_20 mb_10'>
		<strong>사용자정보</strong>
	</div>

	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<table>
		<caption></caption>
		<colgroup>
			<col width="150px"/>
			<col width="40%"/>
			<col width="150px"/>
			<col width="*"/>
		</colgroup>
			<tr>
				<th>멤버구분</th>
				<td><c:out value="${memberMVO.mbrTypCdNm }"/></td>
				<th>아이디(이메일)</th>
				<td><c:out value="${memberMVO.mbrId }"/></td>
			</tr>
			<tr>
				<th>단체명(성명)</th>
				<td><c:out value="${memberMVO.oztnNm }"/></td>
				<th>휴대전화</th>
				<td><c:out value="${memberMVO.ceoPhn }"/></td>
			</tr>
			<tr>
				<th>소속단체</th>
				<td><c:out value="${memberMVO.assOztnNm }"/></td>
				<th>입주그룹</th>
				<td><c:out value="${memberMVO.mvinGrpCdNm }"/></td>
			</tr>
		</table>
	</div>

	<!-- 버튼 -->
	<div class="btns_02 tar">
		<button type="button" class="btnBasic_blue">확인</button>
	</div>

</div><!--subcon_area-->
