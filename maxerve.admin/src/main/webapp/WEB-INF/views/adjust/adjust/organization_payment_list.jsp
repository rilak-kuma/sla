<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
// 목록
function goList() {
	$('form[name=form]')
	.attr('action', '<c:url value="/adjust/adjust/organization_list.do"/>')
	.find('input')
	.not('[name=page]')
	.not('[name=oztnNm]')
	.not('[name=ajmYn]')
	.not('[name=dt]')
	.not('[name=srtDt]')
	.not('[name=endDt]')
	.not('[name=organizationPage]')
	.remove();

	$('form[name=form]').submit();
}

// 상세이동
function goDetail(payIdx) {
	localStorage.setItem('backurl', location.href);

	location.href = '<c:url value="/adjust/pay/detail.do"/>?payIdx=' + payIdx;
}

// 상태저장
function fnSave() {
	var url = '<c:url value="/adjust/adjust/history_insert.json"/>';
	var submitData = {
		ajmYm: $('form[name=form] input[name=dt]').val(),
		locTypCd: $('form[name=form] input[name=locTypCd]').val(),
		locIdx: $('form[name=form] input[name=locIdx]').val()
	};

	var callback = function() {
		alert(getMessage('msg.save.success'));
	}

	ajaxSubmit(url, submitData, callback);
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

	$('#btnSave')
	// 저장
	.on('click', function(){
		if ($('select#ajmYn').val() != 'Y') {
			alert('정산예정은 저장하실 수 없습니다.');
			return;
		}

		fnSave();

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
	<form:hidden path="locTypCd"/>
	<form:hidden path="locIdx"/>
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
			<col width="15%"/>
			<col width="20%"/>
			<col width="15%"/>
			<col width="20%"/>
			<col width="15%"/>
			<col width="*"/>
		</colgroup>
			<tr>
				<th>정산일</th>
				<td><c:out value="${reqOrganizationSummaryVO.dt }"/></td>
				<th>단체명(성명)</th>
				<td><c:out value="${adjustHistoryMVO.oztnNm }"/></td>
				<th>대표자명</th>
				<td><c:out value="${adjustHistoryMVO.ceoNm }"/></td>
			</tr>
			<tr>
				<th>건수</th>
				<td><c:out value="${adjustHistoryMVO.count }"/></td>
				<th>정산일</th>
				<td><c:out value="${adjustHistoryMVO.ajmDt }"/></td>
				<th>상태</th>
				<td>
					<select id='ajmYn'>
						<option value='N'>정산예정</option>
						<option value='Y'${adjustHistoryMVO.ajmYn eq 'Y' ? ' SELECTED':'' }>정산완료</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>결제금액</th>
				<td><c:out value="${adjustHistoryMVO.prc }"/></td>
				<th>수수료</th>
				<td><c:out value="${adjustHistoryMVO.fees }"/></td>
				<th>정산금액</th>
				<td><c:out value="${adjustHistoryMVO.prc + adjustHistoryMVO.fees }"/></td>
			</tr>
		</table>

		<!-- 버튼 -->
	<c:if test="${adjustHistoryMVO.ajmYn ne 'Y' }">
	<sec:authorize access="hasRole('023006002Y')">
		<div class="btns tar mb_40">
			<button type="button" class="btnBasic_blue" id='btnSave'>저장</button>
		</div>
	</sec:authorize>
	</c:if>
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
					<td colspan="6" align="center">조회결과가 없습니다.</td>
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
