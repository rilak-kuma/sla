<%@ page language="java" contentType="text/plain; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border="1">
	<tr>
		<th>��ȣ</th>
		<th>����</th>
		<th>����Ͻ�</th>
		<th>�����</th>
		<th>��ȸ</th>
		<th>����</th>
	</tr>
	<c:forEach items="${ntcList}" var="ntcList" varStatus="varStatus">
		<tr>
			<td><c:out value="${ntcList.topYn eq 'Y'?'����':ntcList.ntcIdx }" /></td>
			<td><c:out value="${ntcList.titl }" /></td>
			<td><c:out value="${ntcList.creDttm }" /></td>
			<td><c:out value="${ntcList.mngrMbrIdx }" /></td>
			<td><c:out value="${ntcList.hitCnt }" /></td>
			<td><c:out value="${ntcList.useYn=='Y'?'ON':'OFF' }" /></td>
		</tr>
	</c:forEach>
</table>