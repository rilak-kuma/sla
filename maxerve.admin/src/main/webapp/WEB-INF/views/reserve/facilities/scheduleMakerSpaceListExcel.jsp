<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
table th {width: 150px;}
ul {padding: 0; margin:0;}
ul, li {list-style: none;}
</style>

<h1><c:out value="${mainTitle }" /> 예약현황</h1>
<h5>
		<c:forEach var="facilitiesList" items="${facilitiesList }">
			<c:if test="${facilitiesList.fctMstIdx eq searchVO.fctMstIdx}" >
				<c:out value="${facilitiesList.fctNm }" />(<c:out value="${fn:length(reserveList)}" />)
			</c:if>
		</c:forEach>
</h5>
<table border="1">
	<thead>
		<tr>
			<th>교육명</th>
			<th>모집기간</th>
			<th>신청인원수</th>
			<th>진행상태</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach	items="${reserveList }" var="reserveList" >
			<tr>
			<td><c:out value="${reserveList.titl}" /></td>
			<td><c:out value="${reserveList.srtDt}" />~<c:out value="${reserveList.endDt}" /></td>
			<td style='mso-number-format:"@";'><c:out value="${reserveList.mkspClsRsvCnt}" /></td>
			<td><c:out value="${reserveList.useYn eq 'Y' ? '모집중' : '모집완료'}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>