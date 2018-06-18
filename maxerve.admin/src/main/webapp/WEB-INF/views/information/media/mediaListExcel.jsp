<%@ page language="java" contentType="text/plain; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>기사작성자</th>
		<th>등록일시</th>
		<th>등록자</th>
		<th>조회</th>
		<th>상태</th>
	</tr>
	<c:forEach items="${mdaList}" var="mList" varStatus="varStatus">
		<tr>
			<td><c:out value="${mList.mdaIdx }" /></td>
			<td><c:out value="${mList.titl }" /></td>
			<td><c:out value="${mList.writer }" /></td>
			<td><c:out value="${mList.creDttm }" /></td>
			<td><c:out value="${mList.mngrMbrNm }" />/<c:out value="${mList.mngrMbrDpt }" /></td>
			<td><c:out value="${mList.hitCnt }" /></td>
			<td><c:out value="${mList.useYn=='Y'?'ON':'OFF' }" /></td>
		</tr>
	</c:forEach>
</table>