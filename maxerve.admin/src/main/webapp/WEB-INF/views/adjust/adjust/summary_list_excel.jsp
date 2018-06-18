<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border='1' style='border: 1px solid black;'>
	<thead>
		<tr>
			<th>����</th>
			<th>��ü�Ǽ�</th>
			<th>�����Ϸ��</th>
			<th>������Ұ�</th>
			<th>����������</th>
			<th>�����ݾ�(��)</th>
			<th>������(��)</th>
			<th>����ݾ�(��)</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${!empty adjustSummaryMVOList }">
			<c:forEach items="${adjustSummaryMVOList}" var="item">
				<tr>
					<td><c:out value="${item.dt }"/></td>
					<td><c:out value="${item.total }" /></td>
					<td><c:out value="${item.appr }" /></td>
					<td><c:out value="${item.cancel }" /></td>
					<td><c:out value="${item.error }" /></td>
					<td><c:out value="${item.apprPrc }" /></td>
					<td><c:out value="${item.fees }" /></td>
					<td><c:out value="${item.apprPrc + item.fees }" /></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td>��ȸ����� �����ϴ�.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>