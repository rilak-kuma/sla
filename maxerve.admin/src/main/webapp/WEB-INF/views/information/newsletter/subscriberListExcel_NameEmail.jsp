<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border="1">
	<tr>
		<th>성명</th>
		<th>이메일</th>
	</tr>
	<c:forEach items="${subscriberList}" var="subscriberList" varStatus="varStatus">
		<tr>
			<td><c:out value="${subscriberList.scbNm }" /></td>
			<td><c:out value="${subscriberList.emil }" /></td>
		</tr>
	</c:forEach>
</table>