<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script type="text/javascript">
// 상세이동
function goDetail(dt) {
	$('form[name=form]')
	.attr('action', '<c:url value="/adjust/adjust/organization_list.do"/>')
	.append('<input type="hidden" name="dt" value="' + dt + '"/>')
	.submit();
}

//엑셀저장
function goExcel() {
	if (!$('body').is(':has(#excelIframe)')) {
		$('body').append('<iframe class="hidden" id="excelIframe"></iframe>');
	}

	var param = $('form[name=form]').serialize();

	$('#excelIframe').attr('src', '<c:url value="/adjust/adjust/summary_organization_list_excel.do"/>?' + param);
}

jQuery(function($){
	// 달력
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
<form:form commandName="reqOrganizationSummaryVO" method="GET" name="form">
	<form:hidden path="page"/>
	<form:hidden path="srtDt"/>
	<form:hidden path="endDt"/>
</form:form>

<div class="location_area">
	<h3>단체별 정산</h3>
</div>

<div class="subcon_area">
	<!-- 조회 테이블 -->
	<div class="basicTbl02">
	<form:form commandName="reqOrganizationSummaryVO" method="GET" action="?" name="searchForm">
		<table>
			<caption></caption>
					<colgroup>
						<col width="120px"/>
						<col width=""/>
						<col width="100px"/>
					</colgroup>
			<tr>
				<th>정산월</th>
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
			<col width="100"/>
			<col width="100"/>
			<col width=""/>
		</colgroup>
		<thead>
			<tr>
				<th>월</th>
				<th>정산단체</th>
				<th>정산건수</th>
				<th>총 정산금액(원)</th>
			</tr>
		</thead>
			<tbody>
			<c:choose>
				<c:when test="${!empty adjustSummaryMVOList }">
					<c:forEach items="${adjustSummaryMVOList}" var="item">
						<tr data-dt='<c:out value="${item.dt }"/>'>
							<td><c:out value="${item.dt }" /></td>
							<td><c:out value="${item.total }"/></td>
							<td><c:out value="${item.appr }" /></td>
							<td><c:out value="${item.apprPrc }" /></td>
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
