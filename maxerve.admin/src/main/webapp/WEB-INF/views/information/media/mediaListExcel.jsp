<%@ page language="java" contentType="text/plain; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border="1">
	<tr>
		<th>��ȣ</th>
		<th>����</th>
		<th>����ۼ���</th>
		<th>����Ͻ�</th>
		<th>�����</th>
		<th>��ȸ</th>
		<th>����</th>
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