<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
table th {width: 150px;}
ul {padding: 0; margin:0;}
ul, li {list-style: none;}
</style>
<script>

function fn_submit(pageIndex){
	$("#page").val(pageIndex);

	$('form[name=listFm]')
	.attr('src', '<c:url value="/reserve/facilities/scheduleMakerSpace.do"/>')
	.find('input[name=fctMstIdx]')
	.end()
	.val('<c:out value="${searchVO.fctMstIdx}" />')
	.end()
	.submit();
}

function fn_sort(sortType, num){
	$("form[name=form]")
		.attr('src', '<c:url value="/reserve/facilities/scheduleMakerSpace.do"/>')
		.find("input[name=sortType]").val(sortType+"_"+num)
		.end()
		.submit();
}

jQuery(function($){

	//시설선택
	$('span#fctSelect').on('click', function(){
		$("form[name=form]")
			.attr('src', '<c:url value="/reserve/facilities/scheduleMakerSpace.do"/>')
			.find("input[name=sumDay]")
			.val('')
			.end()
			.find("input[name=fctMstIdx]")
			.val($(this).attr('data-mst-idx'))
			.end()
			.submit();
	});

	//엑셀저장
	$('#btnExcelDown').on('click', function(){
		$("form[name=form]")
			.find("input[name=fctMstIdx]")
			.val('<c:out value="${searchVO.fctMstIdx}" />')
			.end()
			.attr('action','<c:out value="scheduleMakerSpaceListExcel.do" />')
			.attr('target','_blank')
			.submit();
	});

	//목록 상세
	$('#tbody').on('click','tr', function(){
		$("form[name=infoFm]")
			.find("input[name=mkspClsIdx]").val($(this).attr('data-mksp-cls-idx'))
			.end()
			.find("input[name=titl]").val($(this).attr('data-titl'))
			.end()
			.find("input[name=srtDt]").val($(this).attr('data-srtDt'))
			.end()
			.find("input[name=endDt]").val($(this).attr('data-endDt'))
			.end()
			.find("input[name=mkspClsRsvCnt]").val($(this).attr('data-rsv-cnt'))
			.end()
			.find("input[name=useYn]").val($(this).attr('data-useYn'))
			.end()
			.attr('action','<c:out value="scheduleMakerSpaceClassInfo.do" />')
			.submit();
	});

	// 초기설정
	$(document).ready(function(){

		$("form[name=form]")
			.find("input[name=fctMstIdx]").val('<c:out value="${searchVO.fctMstIdx}" />')
			.end()
			.find("input[name=year]").val('<c:out value="${searchVO.year}" />')
			.end()
			.find("input[name=month]").val('<c:out value="${searchVO.month}" />')
			.end()
			.find("input[name=srtDay]").val('<c:out value="${searchVO.srtDay}" />')
			.end()
			.find("input[name=sumDay]").val('<c:out value="${searchVO.sumDay}" />')
			.end()
			.find("input[name=sortType]").val('<c:out value="${searchVO.sortType}" />')
			.end()
			.find("input[name=page]").val('<c:out value="${searchVO.page}" />')
			.end();

		var subTitl = "";
		<c:forEach var="facilitiesList" items="${facilitiesList }">
			<c:if test="${facilitiesList.fctMstIdx eq searchVO.fctMstIdx}" >
				subTitl = '<c:out value="${facilitiesList.fctNm }" />(<c:out value="${fn:length(reserveList)}" />)';
			</c:if>
		</c:forEach>
		$("#subTitl").text(subTitl);

		if('<c:out value="${searchVO.sortType}" />' == 'sortOne_1'){
			$("#sortOne_1").hide();
		}else{
			$("#sortOne_2").hide();
		}
	});

});
</script>

<form:form commandName="reqMakerspaceClassVO" name="infoFm" method="GET">
	<form:hidden path="fctMstIdx"/>
	<form:hidden path="mkspClsIdx"/>
	<form:hidden path="titl"/>
	<form:hidden path="srtDt"/>
	<form:hidden path="endDt"/>
	<form:hidden path="mkspClsRsvCnt"/>
	<form:hidden path="useYn"/>
</form:form>

<form:form commandName="reqFacilitiesMasterVO" name="form" method="GET">
	<form:hidden path="fctMstIdx"/>
	<form:hidden path="year"/>
	<form:hidden path="month"/>
	<form:hidden path="srtDay"/>
	<form:hidden path="sumDay"/>
	<form:hidden path="page"/>
	<form:hidden path="sortType"/>
</form:form>
<div class="location_area"><h3><c:out value="${mainTitle }" /> 예약현황</h3></div>
<div class="subcon_area">
	<ul class="small_list">
		<c:forEach var="facilitiesList" items="${facilitiesList }">
			<c:if test="${facilitiesList.fctNm.indexOf('모임방') < 0 }" >
			<li>
				<span id="fctSelect" data-mst-idx='<c:out value="${facilitiesList.fctMstIdx }" />' <c:if test='${facilitiesList.fctMstIdx eq searchVO.fctMstIdx}' >style="color:red" </c:if> >
					<c:out value="${facilitiesList.fctNm }" />
				</span>
			</li>
			</c:if>
		</c:forEach>
	</ul>

	<div class="mt_20 mb_10">
		<strong id="subTitl"></strong>
		<button id="btnExcelDown" class="btnBasic_black fr">엑셀저장</button>
	</div>
	<div class="basicTbl">
		<table>
			<thead>
				<tr>
					<th>교육명</th>
					<th>모집기간</th>
					<th>신청인원수</th>
					<th>
						<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">진행상태△</a>
						<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">진행상태▽</a>
					</th>
				</tr>
			</thead>
			<tbody id="tbody">
		<c:choose>
			<c:when test="${empty reserveList }">
				<tr><td colspan="4" >조회 내용이 없습니다.</td></tr>
			</c:when>
			<c:otherwise>
			<c:forEach	items="${reserveList }" var="reserveList" >
				<tr data-mksp-cls-idx='<c:out value="${reserveList.mkspClsIdx}" />' data-titl='<c:out value="${reserveList.titl}" />' data-useYn='<c:out value="${reserveList.useYn}" />' data-srtDt='<c:out value="${reserveList.srtDt}" />' data-endDt='<c:out value="${reserveList.endDt}" />' data-rsv-cnt='<c:out value="${reserveList.mkspClsRsvCnt}" />'>
					<td><c:out value="${reserveList.titl}" /></td>
					<td><c:out value="${reserveList.srtDt}" />~<c:out value="${reserveList.endDt}" /></td>
					<td><c:out value="${reserveList.mkspClsRsvCnt}" /></td>
					<td><c:out value="${reserveList.useYn eq 'Y' ? '모집중' : '모집완료'}" /></td>
				</tr>
			</c:forEach>
			</c:otherwise>
		</c:choose>
			</tbody>
		</table>
	</div>
	<ul class="page" id="pagination">
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>
</div>