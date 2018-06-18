<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
// 목록
function goList() {
	$('form[name=form]')
	.attr('action', '<c:url value="/adjust/adjust/summary_list.do"/>')
	.find('input')
	.not('[name=page]')
	.not('[name=adjustType]')
	.not('[name=srtDt]')
	.not('[name=endDt]')
	.remove();

	$('form[name=form]').submit();
}

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

	$('#excelIframe').attr('src', '<c:url value="/adjust/adjust/payment_list_excel.do"/>?' + param);
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
<form:form commandName="reqAdjustVO" method="GET" name="form">
	<form:hidden path="page"/>
	<form:hidden path="adjustType"/>
	<form:hidden path="srtDt"/>
	<form:hidden path="endDt"/>
	<form:hidden path="dt"/>
	<form:hidden path="srchLocTyp"/>
	<form:hidden path="payMthd"/>
	<form:hidden path="srchStatus"/>
</form:form>

<div class="location_area">
	<h3>
	<c:choose>
		<c:when test="${reqAdjustVO.adjustType eq '1' }">일별</c:when>
		<c:when test="${reqAdjustVO.adjustType eq '2' }">월별</c:when>
		<c:when test="${reqAdjustVO.adjustType eq '3' }">연도별</c:when>
	</c:choose>
		정산
	</h3>
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
				<th>정산일</th>
				<td><c:out value="${reqAdjustVO.dt }"/></td>
				<th>전체건수</th>
				<td><c:out value="${adjustSummaryMVO.total }"/></td>
				<th>정산금액</th>
				<td><c:out value="${adjustSummaryMVO.apprPrc + adjustSummaryMVO.fees }"/></td>
			</tr>
		</table>
	</div>

	<!-- 조회 테이블 -->
	<div class="basicTbl03">
		<table style="width:calc((100% - 20px;)/3); margin-right:10px;">
			<caption>결제완료</caption>
			<colgroup>
				<col style="width:90px;">
				<col style="">
			</colgroup>
			<tr>
				<th colspan="2">결제완료</th>
			</tr>
			<tr>
				<td colspan="2" class="tac emtxt"><strong><c:out value="${adjustSummaryMVO.appr }"/>건 / <c:out value="${adjustSummaryMVO.apprPrc }"/>원</strong></td>
			</tr>
			<tr>
				<td>신용카드</td>
				<td class="tar"><c:out value="${paymentSummaryMVO.apprCardCount }"/>건 / <c:out value="${paymentSummaryMVO.apprCardPrc }"/>원</td>
			</tr>
			<tr>
				<td>계좌이체</td>
				<td class="tar"><c:out value="${paymentSummaryMVO.apprBankCount }"/>건 / <c:out value="${paymentSummaryMVO.apprBankPrc }"/>원</td>
			</tr>
			<tr>
				<td>페이</td>
				<td class="tar"><c:out value="${paymentSummaryMVO.apprKakaopayCount }"/>건 / <c:out value="${paymentSummaryMVO.apprKakaopayPrc }"/>원</td>
			</tr>
		</table>

		<table style="width:calc((100% - 20px;)/3); margin-right:10px;">
			<caption>결제취소</caption>
			<colgroup>
				<col style="width:90px;">
				<col style="">
			</colgroup>
			<tr>
				<th colspan="2">결제취소</th>
			</tr>
			<tr>
				<td colspan="2" class="tac emtxt"><strong><c:out value="${adjustSummaryMVO.cancel }"/>건 / <c:out value="${adjustSummaryMVO.cancelPrc }"/>원</strong></td>
			</tr>
			<tr>
				<td>신용카드</td>
				<td class="tar"><c:out value="${paymentSummaryMVO.cancelCardCount }"/>건 / <c:out value="${paymentSummaryMVO.cancelCardPrc }"/>원</td>
			</tr>
			<tr>
				<td>계좌이체</td>
				<td class="tar"><c:out value="${paymentSummaryMVO.cancelBankCount }"/>건 / <c:out value="${paymentSummaryMVO.cancelBankPrc }"/>원</td>
			</tr>
			<tr>
				<td>페이</td>
				<td class="tar"><c:out value="${paymentSummaryMVO.cancelKakaopayCount }"/>건 / <c:out value="${paymentSummaryMVO.cancelKakaopayPrc }"/>원</td>
			</tr>
		</table>

		<table style="width:calc((100% - 20px;)/3); margin-right:0;">
			<caption>결제오류</caption>
			<colgroup>
				<col style="width:90px;">
				<col style="">
			</colgroup>
			<tr>
				<th colspan="2">결제오류</th>
			</tr>
			<tr>
				<td colspan="2" class="tac emtxt"><strong><c:out value="${adjustSummaryMVO.error }"/>건 / <c:out value="${adjustSummaryMVO.errorPrc }"/>원</strong></td>
			</tr>
			<tr>
				<td>신용카드</td>
				<td class="tar"><c:out value="${paymentSummaryMVO.errorCardCount }"/>건 / <c:out value="${paymentSummaryMVO.errorCardPrc }"/>원</td>
			</tr>
			<tr>
				<td>계좌이체</td>
				<td class="tar"><c:out value="${paymentSummaryMVO.errorBankCount }"/>건 / <c:out value="${paymentSummaryMVO.errorBankPrc }"/>원</td>
			</tr>
			<tr>
				<td>페이</td>
				<td class="tar"><c:out value="${paymentSummaryMVO.errorKakaopayCount }"/>건 / <c:out value="${paymentSummaryMVO.errorKakaopayPrc }"/>원</td>
			</tr>
		</table>
	</div>

	<!-- 조회 테이블 -->
	<div class="basicTbl02">
	<form:form commandName="reqAdjustVO" method="GET" action="?" name="searchForm">
	<form:hidden path="page"/>
	<form:hidden path="adjustType"/>
	<form:hidden path="srtDt"/>
	<form:hidden path="endDt"/>
	<form:hidden path="dt"/>
		<table>
			<caption></caption>
					<colgroup>
						<col width="120px"/>
						<col width=""/>
						<col width="100px"/>
					</colgroup>
			<tr>
				<th>결제항목</th>
				<td>
					<select style='width:120px;' name='srchLocTyp'>
						<option value=''>전체</option>
						<option value='1'${reqAdjustVO.srchLocTyp eq '1' ? ' SELECTED':'' }>미래청(회의/세미나)</option>
						<option value='2'${reqAdjustVO.srchLocTyp eq '2' ? ' SELECTED':'' }>야외공간</option>
						<option value='3'${reqAdjustVO.srchLocTyp eq '3' ? ' SELECTED':'' }>우드파크</option>
						<option value='4'${reqAdjustVO.srchLocTyp eq '4' ? ' SELECTED':'' }>서울이노베이션팹랩</option>
						<option value='5'${reqAdjustVO.srchLocTyp eq '5' ? ' SELECTED':'' }>행사</option>
						<option value='6'${reqAdjustVO.srchLocTyp eq '6' ? ' SELECTED':'' }>미래청(녹음/운동)</option>
						<option value='7'${reqAdjustVO.srchLocTyp eq '7' ? ' SELECTED':'' }>극장동</option>
						<option value='8'${reqAdjustVO.srchLocTyp eq '8' ? ' SELECTED':'' }>맛동</option>
					</select>
				</td>
				<th>결제수단</th>
				<td>
					<select style='width:120px;' name='payMthd'>
						<option value=''>전체</option>
						<option value='CARD'${reqAdjustVO.payMthd eq 'CARD' ? ' SELECTED':'' }>신용카드</option>
						<option value='BANK'${reqAdjustVO.payMthd eq 'BANK' ? ' SELECTED':'' }>계좌이체</option>
						<option value='KAKAOPAY'${reqAdjustVO.payMthd eq 'KAKAOPAY' ? ' SELECTED':'' }>카카오페이</option>
					</select>
				</td>
				<th>상태</th>
				<td>
					<select style='width:120px;' name='srchStatus'>
						<option value=''>전체</option>
						<option value='1'${reqAdjustVO.srchStatus eq '1' ? ' SELECTED':'' }>결제취소</option>
						<option value='2'${reqAdjustVO.srchStatus eq '2' ? ' SELECTED':'' }>결제오류</option>
						<option value='3'${reqAdjustVO.srchStatus eq '3' ? ' SELECTED':'' }>결제완료</option>
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
				<th>결제일시</th>
				<th>결제항목</th>
				<th>결제수단</th>
				<th>상태</th>
				<th>결제금액(원)</th>
				<th>수수료(원)</th>
				<th>정산금액</th>
			</tr>
		</thead>
			<tbody>
			<c:choose>
				<c:when test="${!empty paymentMVOList }">
					<c:forEach items="${paymentMVOList}" var="item">
					<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="creDttm" value="${item.creDttm }" />
						<tr data-pay-idx='<c:out value="${item.payIdx }"/>'>
								<td><fmt:formatDate value="${creDttm }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
								<td><c:out value="[${item.locTitl }] ${item.titl }" /></td>
								<td>
							<c:choose>
								<c:when test="${item.payMthd eq 'CARD' }">신용카드</c:when>
								<c:when test="${item.payMthd eq 'BANK' }">계좌이체</c:when>
								<c:when test="${item.payMthd eq 'VBANK' }">가상계좌</c:when>
								<c:when test="${item.payMthd eq 'CELLPHONE' }">휴대폰</c:when>
								<c:when test="${item.payMthd eq 'KAKAOPAY' }">카카오페이</c:when>
							</c:choose>
								</td>
								<td>
							<c:choose>
								<c:when test="${item.cnclRstCd eq '2001' or item.cnclRstCd eq '2002' or item.cnclRstCd eq '2211' }">결제취소</c:when>
								<c:when test="${item.apprYn eq 'Y' }">결제완료</c:when>
								<c:otherwise>결제오류</c:otherwise>
							</c:choose>
								</td>
								<td><c:out value="${item.prc }" /></td>
								<td><c:out value="${item.fees }" /></td>
								<td><c:out value="${item.prc + item.fees }" /></td>
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
