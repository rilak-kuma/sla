<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
// 상세이동
function goDetail(dt, pjtIdx) {
	$('form[name=form]')
	.attr('action', '<c:url value="/adjust/sponsor/adjust_detail.do"/>')
	.append('<input type="hidden" name="dt" value="' + dt + '" />')
	.append('<input type="hidden" name="pjtIdx" value="' + pjtIdx + '" />')
	.submit();
}

//엑셀저장
function goExcel() {
	if (!$('body').is(':has(#excelIframe)')) {
		$('body').append('<iframe class="hidden" id="excelIframe"></iframe>');
	}

	var param = $('form[name=form]').serialize();

	$('#excelIframe').attr('src', '<c:url value="/adjust/sponsor/adjust_list_excel.do"/>?' + param);
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
	.on('click', '[data-dt][data-pjt-idx]', function(){
		goDetail($(this).attr('data-dt'), $(this).attr('data-pjt-idx'));

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
</form:form>

<div class="location_area"><h3>후원금 정산</h3></div>

<div class="subcon_area">
	<!-- 조회 테이블 -->
	<div class="basicTbl02">
	<form:form commandName="reqSponsorAdjustVO" method="GET" action="?" name="searchForm">
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
					<form:input path="srchDt" cssStyle="width:100px" cssClass='inp_cal' id='searchSrtDt'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
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
			<col width="100"/>
			<col width="100"/>
			<col width="*"/>
			<col width="200"/>
			<col width="100"/>
			<col width="100"/>
			<col width="100"/>
		</colgroup>
		<thead>
			<tr>
				<th>일시</th>
				<th>단체</th>
				<th>후원 프로젝트</th>
				<th>후원기간</th>
				<th>전체 건수</th>
				<th>수수료(원)</th>
				<th>정산금액(원)</th>
			</tr>
		</thead>
			<tbody>
			<c:choose>
				<c:when test="${!empty sponsorMVOList }">
					<c:forEach items="${sponsorMVOList}" var="item">
					<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="srtDttm" value="${item.srtDttm }" />
					<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="endDttm" value="${item.endDttm }" />
						<tr data-dt='<c:out value="${item.creDttm }"/>' data-pjt-idx='${item.locIdx }'>
								<td><c:out value="${item.creDttm }"/></td>
								<td><c:out value="${item.oztnNm }"/></td>
								<td><c:out value="${item.pjtTitl }" /></td>
								<td>
									<fmt:formatDate value="${srtDttm }" pattern="yyyy.MM.dd"/>
									~
									<fmt:formatDate value="${endDttm }" pattern="yyyy.MM.dd"/>
								</td>
								<td><c:out value="${item.count }"/></td>
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
</div><!--subcon_area-->
