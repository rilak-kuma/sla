<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script>
jQuery(function($){

	//초기값
	$(document).ready(function(){

		var tbodyHtml = "";
		<c:forEach var="memberList" items="${memberList }" varStatus="varstatus" >
			tbodyHtml += '<tr>';
			tbodyHtml += '<td><c:out value="${memberList.mbrId }" /></td>';
			tbodyHtml += '<td><c:out value="${memberList.oztnNm }" /></td>';
			tbodyHtml += '<td style=\'mso-number-format:"\@";\'><c:out value="${memberList.ceoPhn }" /></td>';
			tbodyHtml += '<td><c:out value="${memberList.mbrTypCdNm }" /></td>';
			tbodyHtml += '<td><c:out value="${memberList.assOztnNm }" /></td>';
			tbodyHtml += '<td style=\'mso-number-format:"\@";\'><c:out value="${memberList.creDttm }" /></td>';
			tbodyHtml += '</tr>';
		</c:forEach>

		$("#tbody").html(tbodyHtml);

	});

});

</script>
<h1><c:out value="${titl }" /> 월회원현황</h1>
<h5>월회원 현황 - <c:out value="${searchVO.actMth }" /></h5>
<button id="btnExcelDown">엑셀저장</button>

<table border="1">
		<tr>
			<th>가입년월</th>
			<td><c:out value="${searchVO.actMth }" /></td>
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

<table border="1">
	<thead>
		<tr>
			<th>아이디(이메일)</th>
			<th>성명</th>
			<th>휴대전화</th>
			<th>멤버구분</th>
			<th>소속단체</th>
			<th>신청일</th>
		</tr>
	</thead>
	<tbody id="tbody">

	</tbody>
</table>