<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script type="text/javascript">
// 상세이동
function goDetail(dt) {
	$('form[name=form]')
	.attr('action', '<c:url value="/adjust/adjust/payment_list.do"/>')
	.append('<input type="hidden" name="dt" value="' + dt + '"/>')
	.submit();
}

//엑셀저장
function goExcel() {
	if (!$('body').is(':has(#excelIframe)')) {
		$('body').append('<iframe class="hidden" id="excelIframe"></iframe>');
	}

	var param = $('form[name=form]').serialize();

	$('#excelIframe').attr('src', '<c:url value="/adjust/adjust/summary_list_excel.do"/>?' + param);
}

jQuery(function($){
	// 달력
	if ($('form[name=form] input[name=adjustType]').val() == '1') { // yyyy.mm.dd
		$('.inp_cal').datepicker();
	} else if ($('form[name=form] input[name=adjustType]').val() == '2') { // yyyy.mm
		$('body').append('<style>.ui-datepicker-calendar {display:none;}</style>');

		$('.inp_cal').datepicker({
			changeMonth: true,
			changeYear: true,
			closeText : "선택",
			dateFormat: 'yy.mm',
			showButtonPanel: true
		}).on('focus', function(){
			var thisCalendar = $(this);
			$('.ui-datepicker-calendar').detach();
			$('.ui-datepicker-calendar').hide();
			$('.ui-datepicker-close').click(function() {
				var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
				var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
				thisCalendar.datepicker('setDate', new Date(year, month, 1));
			});
		});
	} else if ($('form[name=form] input[name=adjustType]').val() == '3') { // yyyy
		$('body').append('<style>.ui-datepicker-calendar,.ui-datepicker-month {display:none;}</style>');

		$('.inp_cal').datepicker({
			stepMonths: 12,
			changeYear: true,
			closeText : "선택",
			dateFormat: 'yy',
			showButtonPanel: true
		}).on('focus', function(){
			var thisCalendar = $(this);
			$('.ui-datepicker-calendar').detach();
			$('.ui-datepicker-calendar').hide();
			$('.ui-datepicker-close').click(function() {
				var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
				thisCalendar.datepicker('setDate', new Date(year, 0, 1));
			});
		});
	}

	$('.btn_cal')
	.on('click', function(){
		$(this).prev('.inp_cal').datepicker('show');
	});

	$('.basicTbl')
	// 상세
	.on('click', '[data-dt]', function(){
		goDetail($(this).attr('data-dt'));

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
	<!-- 조회 테이블 -->
	<div class="basicTbl02">
	<form:form commandName="reqAdjustVO" method="GET" action="?" name="searchForm">
	<form:hidden path="adjustType"/>
		<table>
			<caption></caption>
					<colgroup>
						<col width="120px"/>
						<col width=""/>
						<col width="100px"/>
					</colgroup>
			<tr>
				<th>정산일</th>
				<td>
					<form:input path="srtDt" cssStyle="width:100px" cssClass='inp_cal' id='searchSrtDt'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
					<span class="mr_20"> ~ </span>
					<form:input path="endDt" cssStyle="width:100px" cssClass='inp_cal' id='searchEndDt'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
				</td>
				<td>
					<input type='submit' class='btnSearch' value='조회' />
				</td>
			</tr>
		</table>
	</form:form>
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
			<col width=""/>
			<col width="100"/>
			<col width="100"/>
			<col width="100"/>
			<col width="100"/>
			<col width="100"/>
			<col width="100"/>
		</colgroup>
		<thead>
			<tr>
				<th>일자</th>
				<th>전체건수</th>
				<th>결제완료건</th>
				<th>결제취소건</th>
				<th>결제오류건</th>
				<th>결제금액(원)</th>
				<th>수수료(원)</th>
				<th>정산금액(원)</th>
			</tr>
		</thead>
			<tbody>
			<c:choose>
				<c:when test="${!empty adjustSummaryMVOList }">
					<c:forEach items="${adjustSummaryMVOList}" var="item">
						<tr data-dt='<c:out value="${item.dt }"/>'>
							<td><c:out value="${item.dt }"/></td>
							<td><c:out value="${item.total }" /></td>
							<td><c:out value="${item.appr }" /></td>
							<td><c:out value="${item.cancel }" /></td>
							<td><c:out value="${item.error }" /></td>
							<td><c:out value="${item.apprPrc }" /></td>
							<td><c:out value="${item.fees }" /></td>
							<td><c:out value="${item.apprPrc + item.fees }" /></td>
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
