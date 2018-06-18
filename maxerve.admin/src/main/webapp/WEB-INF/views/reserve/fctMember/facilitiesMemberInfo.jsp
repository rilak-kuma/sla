<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script>
//정렬
function fn_sort(sortType, num){
	$('form[name=searchFm]')
		.find("input[name=sortType]").val(sortType+"_"+num)
		.end()
		.submit();
}

//월회원 상세 목록
function fn_goInfo(index){
	$("form[name=form"+index+"]")
	.append('<input type="hidden" name="locTypCd" valeu=<c:out value="${searchVO.locTypCd}" /> />')
	.submit();
}

jQuery(function($){

	//엑셀다운로드
	$("#btnExcelDown").on("click",function(){
		$('form[name=searchFm]')
		 .attr({
			 target: '_blank',
			 action: '<c:out value="/reserve/fctMember/facilitiesMemberInfoExcel.do" />'
		 })
		 .submit();
	});

	//목록
	$("#btnCansle").on("click",function(){
		window.history.back();
	});

	$("#tbody").on("click","tr",function(){
		$("form[name=infoFm]")
			.html('<input type="hidden" name="mbrIdx" value="'+$(this).attr("data-mbr-idx")+'" />')
			.attr('action','<c:out value="/member/onlineMemberInfo.do" />')
			.submit();
	});

	//초기값
	$(document).ready(function(){


		var tbodyHtml = "";
		<c:if test="${fn:length(memberList) > 0}" >
			<c:forEach var="memberList" items="${memberList }" varStatus="varstatus" >
				tbodyHtml += '<form method="POST" name="form${varstatus.index}" action=<c:out value="/member/memberInfo.do" /> >';
				tbodyHtml += '<tr data-mbr-idx=<c:out value="${memberList.mbrIdx }" /> >';
				tbodyHtml += '<td><c:out value="${memberList.mbrId }" /></td>';
				tbodyHtml += '<td><c:out value="${memberList.oztnNm }" /></td>';
				tbodyHtml += '<td><c:out value="${memberList.ceoPhn }" /></td>';
				tbodyHtml += '<td><c:out value="${memberList.mbrTypCdNm }" /></td>';
				tbodyHtml += '<td><c:out value="${memberList.assOztnNm }" /></td>';
				tbodyHtml += '<td><c:out value="${memberList.creDttm }" /></td>';
				tbodyHtml += '</tr>';
				tbodyHtml += '</form>';
			</c:forEach>
		</c:if>

		$("#tbody").html(tbodyHtml);
		$("input").attr("readonly","readonly");

		$("form[name=searchFm]").find($("input[name=locTypCd]").val('<c:out value="${searchVO.locTypCd}" />') );
		$("form[name=searchFm]").find($("input[name=actMth]").val('<c:out value="${searchVO.actMth}" />') );

		var sortType = '<c:out value="${searchVO.sortType}" />';
		if(sortType == 'sortOne_1'){
			$("#sortOne_1").hide();
			$("#sortTwo_2").hide();
		}else if(sortType == 'sortTwo_1'){
			$("#sortOne_2").hide();
			$("#sortTwo_1").hide();
		}else {
			$("#sortOne_2").hide();
			$("#sortTwo_2").hide();
		}
		$("form[name=searchFm]").find("input[name=sortType]").val(sortType);

	});

});

</script>
<div class="location_area"><h3><c:out value="${titl }" /> 월회원현황</h3></div>
<div class="subcon_area">
	<div class="stit mt_20 mb_10">
		<strong>월회원 현황 - <c:out value="${searchVO.actMth }" /></strong>
		<button id="btnExcelDown" class="btnBasic_black fr">엑셀저장</button>
	</div>

	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>가입년월</th>
				<td>
					<fmt:parseDate var="actMth" value="${searchVO.actMth }" pattern="yyyy.MM" />
					<fmt:formatDate value="${actMth }" pattern="yyyy년MM월" />
				</td>
				<th>모집기간</th>
				<td><c:out value="${searchVO.recruitmentPrd }" /></td>
				<th>월회원비</th>
				<td><c:out value="${searchVO.prc }" /></td>
			</tr>
			<tr>
				<th>가입회원수</th>
				<td><c:out value="${searchVO.memberCnt }" /></td>
				<th><c:out value="${titl eq '우드파크' ? '모집정원' : '' }" /></th>
				<td><c:out value="${titl eq '우드파크' ? searchVO.lmtMbCnt : '' }" /></td>
				<th>진행상태</th>
				<td><c:out value="${searchVO.useYn }" /></td>
			</tr>
		</table>
	</div>
	<form:form commandName="reqFacilitiesMemberInfoVO" name="searchFm" method="post" >
	<div class="basicTbl  mt_20">
		<table>
			<caption></caption>
			<colgroup>
				<col width="25%"/>
				<col width=""/>
				<col width="15%"/>
				<col width=""/>
				<col width=""/>
				<col width="18%"/>
			</colgroup>
			<thead>
				<tr>
					<th>아이디(이메일)</th>
					<th>성명</th>
					<th>휴대전화</th>
					<th>
						<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">멤버구분△</a>
						<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">멤버구분▽</a>
					</th>
					<th>소속단체</th>
					<th>
						<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">신청일△</a>
						<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">신청일▽</a>
					</th>
				</tr>
			</thead>
			<tbody id="tbody">

			</tbody>
		</table>
	</div>
	<form:hidden path="locTypCd"/>
	<form:hidden path="actMth"/>
	<form:hidden path="sortType"/>
	</form:form>
	<form name="infoFm" method="post" ></form>
</div>
<div class="tac"><button id="btnCansle" class="btnBasic_black">목록</button></div>
