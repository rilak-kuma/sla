<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
function goPage(pg) {
	$('form[name=form]')
	.attr('action', '/adjust/pay/list.do')
	.find('input[name=page]')
	.val(pg)
	.end()
	.submit();
}

// 상세이동
function goDetail(payIdx) {
	localStorage.setItem('backurl', location.href);

	location.href = '<c:url value="/adjust/pay/detail.do"/>?payIdx=' + payIdx;
}

// 엑셀저장
function goExcel() {
	if (!$('body').is(':has(#excelIframe)')) {
		$('body').append('<iframe class="hidden" id="excelIframe"></iframe>');
	}

	var param = $('form[name=form]').serialize();

	$('#excelIframe').attr('src', '<c:url value="/adjust/pay/list_excel.do"/>?' + param);
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
	<!-- 조회 테이블 -->
	<div class="basicTbl02">
	<form:form commandName="reqPaymentVO" method="GET" action="?" name="paySearchForm">
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
					<form:input path="oztnNm" maxlength="50" cssStyle="width:70%"/>
				</td>
				<th>결제항목</th>
				<td>
					<select style='width:120px;' name='srchLocTyp'>
						<option value=''>전체</option>
						<option value='1'${reqPaymentVO.srchLocTyp eq '1' ? ' SELECTED':'' }>미래청(회의/세미나)</option>
						<option value='2'${reqPaymentVO.srchLocTyp eq '2' ? ' SELECTED':'' }>야외공간</option>
						<option value='3'${reqPaymentVO.srchLocTyp eq '3' ? ' SELECTED':'' }>우드파크</option>
						<option value='4'${reqPaymentVO.srchLocTyp eq '4' ? ' SELECTED':'' }>서울이노베이션팹랩</option>
						<option value='5'${reqPaymentVO.srchLocTyp eq '5' ? ' SELECTED':'' }>행사</option>
						<option value='6'${reqPaymentVO.srchLocTyp eq '6' ? ' SELECTED':'' }>미래청(녹음/운동)</option>
						<option value='7'${reqPaymentVO.srchLocTyp eq '7' ? ' SELECTED':'' }>극장동</option>
						<option value='8'${reqPaymentVO.srchLocTyp eq '8' ? ' SELECTED':'' }>맛동</option>
					</select>
				</td>
				<td rowspan='2'>
					<input type='submit' class='btnSearch' value='조회' />
				</td>
			</tr>
			<tr>
				<th>결제일</th>
				<td>
					<form:input path="srtDt" cssStyle="width:100px" cssClass='inp_cal' id='searchSrtDt'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
					<span class="mr_20"> ~ </span>
					<form:input path="endDt" cssStyle="width:100px" cssClass='inp_cal' id='searchEndDt'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
				</td>
				<th>상태</th>
				<td>
					<select style='width:120px;' name='srchStatus'>
						<option value=''>전체</option>
						<option value='1'${reqPaymentVO.srchStatus eq '1' ? ' SELECTED':'' }>결제취소</option>
						<option value='2'${reqPaymentVO.srchStatus eq '2' ? ' SELECTED':'' }>결제오류</option>
						<option value='3'${reqPaymentVO.srchStatus eq '3' ? ' SELECTED':'' }>결제완료</option>
					</select>
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
			<col width="200"/>
			<col width="150"/>
			<col width="100"/>
		</colgroup>
		<thead>
			<tr>
				<th>결제일시</th>
				<th>결제항목</th>
				<th>결제금액(원)</th>
				<th>아이디</th>
				<th>단체명(성명)</th>
				<th>상태</th>
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
								<td><c:out value="${item.prc }" /></td>
								<td><c:out value="${item.mbrId }" /></td>
								<td><c:out value="${item.oztnNm }" /></td>
								<td>
							<c:choose>
								<c:when test="${item.cnclRstCd eq '2001' or item.cnclRstCd eq '2002' or item.cnclRstCd eq '2211' }">결제취소</c:when>
								<c:when test="${item.apprYn eq 'Y' }">결제완료</c:when>
								<c:otherwise>결제오류</c:otherwise>
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
</div><!--subcon_area-->
