<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	//등록폼호출
	$("#btnRegFm").on('click', function(){
		$("#famIdx").val("");
		$('form[name=searchFm]')
			.attr("action","<c:url value='/basic/family/info.do' />")
			.submit();
	});
})

function fn_familyInfo(famIdx){
  $("#famIdx").val(famIdx);
	$('form[name=searchFm]')
		.attr("action","<c:url value='/basic/family/info.do' />")
		.submit();
}
</script>
<form name="searchFm" method="post">
<input type="hidden" name="famIdx" id="famIdx">
<div class="subcon_area">
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<tr>
				<th>제목</th>
				<td><input type="text" name="titl" id="titl" /></td>
				<td rowspan="4"><input type="submit" class="btnSearch" value="검색" /></td>
			</tr>
			<tr>
				<th>URL</th>
				<td><input type="text" name="url" id="url" /></td>
			</tr>
		</table>
	</div>
	<div class="stit mt_20 mb_10">
		<strong>전체</strong><span class="num">(<fmt:formatNumber value="${paginationInfo.totalRecordCount}" groupingUsed="true"/>)</span>
		<div class="fr">
			<button id="btnRegFm" class="btnBasic_blue">등록</button>
		</div>
	</div>
	<div class="basicTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width="10%"/>
				<col width="30%"/>
				<col width="30%"/>
				<col width="10%"/>
				<col width="20%"/>
			</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>URL</th>
				<th>노출순서</th>
				<th>생성일</th>
			</tr>
			<c:forEach var="fList" items="${familyList }" varStatus="status">
			<tr onclick="fn_familyInfo('${fList.famIdx}')">
				<td><c:out value="${ status.index+1}"/></td>
				<td><c:out value="${ fList.titl}"/></td>
				<td><c:out value="${ fList.url}"/></td>
				<td><c:out value="${ fList.ord}"/></td>
				<td><c:out value="${ fList.creDttm}"/></td>
			</tr>
			</c:forEach>
		</table>
	</div>

	<ul class='page'>
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>

	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
</div>
</form>