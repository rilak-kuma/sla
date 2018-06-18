<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table>
	<thead>
		<tr>
			<th>��ü��</th>
			<th>��ǥ�ڸ�</th>
			<th>�Ǽ�</th>
			<th>�����ݾ�(��)</th>
			<th>������(��)</th>
			<th>����ݾ�(��)</th>
			<th>������</th>
			<th>����</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${!empty adjustHistoryMVOList }">
			<c:forEach items="${adjustHistoryMVOList}" var="item">
				<tr>
						<td><c:out value="${item.oztnNm }"/></td>
						<td><c:out value="${item.ceoNm }"/></td>
						<td><c:out value="${item.count }"/></td>
						<td><c:out value="${item.prc }"/></td>
						<td><c:out value="${item.fees }" /></td>
						<td><c:out value="${item.prc + item.fees }" /></td>
						<td><c:out value="${item.ajmDt }"/></td>
						<td>
					<c:choose>
						<c:when test="${item.ajmYn eq 'Y' }">����Ϸ�</c:when>
						<c:otherwise>���꿹��</c:otherwise>
					</c:choose>
						</td>
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