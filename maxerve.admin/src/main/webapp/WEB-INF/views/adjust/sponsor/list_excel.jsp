<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border='1' style='border: 1px solid black;'>
	<thead>
		<tr>
			<th>�Ŀ��Ͻ�</th>
			<th>�Ŀ���</th>
			<th>�޴���ȭ</th>
			<th>�Ŀ� ������Ʈ</th>
			<th>�������</th>
			<th>�Ŀ��ݾ�</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${!empty sponsorMVOList }">
			<c:forEach items="${sponsorMVOList}" var="item">
			<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="creDttm" value="${item.creDttm }" />
				<tr>
						<td><fmt:formatDate value="${creDttm }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
						<td><c:out value="${item.spoNm }"/></td>
						<td><c:out value="${item.spoPhn }" /></td>
						<td><c:out value="${item.pjtTitl }" /></td>
						<td>
					<c:choose>
						<c:when test="${item.payMthd eq 'CARD' }">�ſ�ī��</c:when>
						<c:when test="${item.payMthd eq 'BANK' }">������ü</c:when>
						<c:when test="${item.payMthd eq 'KAKAOPAY' }">īī������</c:when>
						<c:when test="${item.payMthd eq 'CELLPHONE' }">�޴���</c:when>
					</c:choose>
						</td>
						<td><c:out value="${item.spoAom }"/></td>
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