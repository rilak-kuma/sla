<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script>
//페이지 이동
function fn_submit(pageIndex){
	$('form[name=searchFm]')
		.find('input[name=locTypCd]').val('<c:out value="${searchVO.locTypCd}" />')
		.end()
		.find("#pageIndex").val(pageIndex)
		.end()
		.submit();
}

//정렬
function fn_sort(sortType, num){
	$('form[name=searchFm]')
		.find("input[name=sortType]").val(sortType+"_"+num)
		.end()
		.submit();
}

//월회원 상세 목록
function fn_goInfo(index){

	var formHtml = "";
	<c:forEach var="memberList" items="${memberList }" varStatus="varstatus" >
		if('<c:out value="${varstatus.index}"/>' == index){
			formHtml += '<input type="hidden" name="actMth" value=<c:out value="${memberList.actMth }" /> />';
			formHtml += '<input type="hidden" name="memberCnt" value=<c:out value="${memberList.memberCnt }" /> />';
			formHtml += '<input type="hidden" name="recruitmentPrd" value=<c:out value="${memberList.recruitmentPrd }" /> />';
			formHtml += '<input type="hidden" name="prc" value=<c:out value="${memberList.prc }" /> />';
			formHtml += '<input type="hidden" name="useYn" value=<c:out value="${memberList.useYn==\'Y\'?\'모집중\':\'모집완료\' }" /> />';
			formHtml += '<input type="hidden" name="locTypCd" value=<c:out value="${searchVO.locTypCd}" /> />';
			<c:if test="${titl eq '우드파크' }">
				formHtml += '<td><input type="hidden" name="lmtMbCnt" value=<c:out value="${memberList.lmtMbCnt }" /> /></td>';
			</c:if>
		}
	</c:forEach>

	$("form[name=Infofm]").html(formHtml)
	.submit();
}

jQuery(function($){

	//엑셀다운로드
	$("#btnExcelDown").on("click",function(){
		$('form[name=searchFm]')
		 .attr({
			 target: '_blank',
			 action: '<c:out value="/reserve/fctMember/facilitiesMemberStateExcel.do" />'
		 })
		 .submit();

		 $('form[name=searchFm]').removeAttr("target");
		 $('form[name=searchFm]').attr("action",'<c:out value="/reserve/fctMember/facilitiesMemberState.do" />');
	});

	//초기값
	$(document).ready(function(){

		var tbodyHtml = "";
		<c:forEach var="memberList" items="${memberList }" varStatus="varstatus" >
			tbodyHtml += '<tr onclick="fn_goInfo(${varstatus.index})">';
			tbodyHtml += '<td><fmt:parseDate var="actMth" value="${memberList.actMth }" pattern="yyyy.MM" />'
			tbodyHtml += '<fmt:formatDate value="${actMth }" pattern="yyyy년MM월" /></td>';
			tbodyHtml += '<td><c:out value="${memberList.memberCnt }" /></td>';
			<c:if test="${titl eq '우드파크' }">
				tbodyHtml += '<td><c:out value="${memberList.lmtMbCnt }" /></td>';
			</c:if>
			tbodyHtml += '<td><c:out value="${memberList.recruitmentPrd }" /></td>';
			tbodyHtml += '<td><c:out value="${memberList.prc }" /></td>';
			tbodyHtml += '<td><c:out value="${memberList.useYn==\'Y\'?\'모집중\':\'모집완료\' }" /></td>';
			tbodyHtml += '</tr>';
		</c:forEach>

		$("#tbody").html(tbodyHtml);
		$("input").attr("readonly","readonly");

		var sortType = '<c:out value="${searchVO.sortType}" />';
		if(sortType == 'sortOne_1'){
			$("#sortOne_1").hide();
		}else {
			$("#sortOne_2").hide();
		}

		$('form[name=searchFm]')
			.find("input[name=sortType]").val(sortType)
			.end()
			.attr("action",'<c:out value="/reserve/fctMember/facilitiesMemberState.do" />');
	});

});

</script>
<div class="location_area"><h3><c:out value="${titl }" /> 월회원현황</h3></div>

<div class="subcon_area">
	<div class="stit mt_20 mb_10">
		<strong><c:out value="${titl }" /> 월회원(<c:out value="${fn:length(memberList) }" />)</strong>
		<button id="btnExcelDown" class="btnBasic_black fr">엑셀저장</button>
	</div>

	<div class="basicTbl">
		<table>
			<thead>
				<tr>
					<th>이용년월</th>
					<th>가입회원수</th>
					<c:if test="${titl eq '우드파크' }">
						<th>모집정원</th>
					</c:if>
					<th>모집기간</th>
					<th>월회원비</th>
					<th>
						<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">진행상태△</a>
						<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">진행상태▽</a>
					</th>
				</tr>
			</thead>
			<tbody id="tbody">

			</tbody>
		</table>
	</div>
	<form:form commandName="reqFacilitiesMemberVO" name="searchFm" method="post" >
		<form:hidden path="locTypCd"/>
		<form:hidden path="sortType"/>

		<ul class="page" id="pagination">
			<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
		</ul>

		<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
	</form:form>
	<form method="POST" name="Infofm" action=<c:out value="/reserve/fctMember/facilitiesMemberInfo.do" /> ></form>
</div>
