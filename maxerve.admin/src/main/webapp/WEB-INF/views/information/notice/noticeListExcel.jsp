<%@ page language="java" contentType="text/plain; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>등록일시</th>
		<th>등록자</th>
		<th>조회</th>
		<th>상태</th>
	</tr>
	<c:forEach items="${ntcList}" var="ntcList" varStatus="varStatus">
		<tr>
			<td><c:out value="${ntcList.topYn eq 'Y'?'공지':ntcList.ntcIdx }" /></td>
			<td><c:out value="${ntcList.titl }" /></td>
			<td><c:out value="${ntcList.creDttm }" /></td>
			<td><c:out value="${ntcList.mngrMbrIdx }" /></td>
			<td><c:out value="${ntcList.hitCnt }" /></td>
			<td><c:out value="${ntcList.useYn=='Y'?'ON':'OFF' }" /></td>
		</tr>
	</c:forEach>
</table>