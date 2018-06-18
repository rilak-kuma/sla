<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table>
	<thead>
		<tr>
			<th>�Ŀ��Ͻ�</th>
			<th>�Ŀ��ڸ�</th>
			<th>��������</th>
			<th>�����ݾ�</th>
			<th>������</th>
			<th>����ݾ�</th>
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
						<td>
					<c:choose>
						<c:when test="${item.payMthd eq 'CARD' }">�ſ�ī��</c:when>
						<c:when test="${item.payMthd eq 'BANK' }">������ü</c:when>
						<c:when test="${item.payMthd eq 'KAKAOPAY' }">īī������</c:when>
						<c:when test="${item.payMthd eq 'CELLPHONE' }">�޴���</c:when>
					</c:choose>
						</td>
						<td><c:out value="${item.spoAom }" /></td>
						<td><c:out value="${item.fees }"/></td>
						<td><c:out value="${item.spoAom + item.fees }"/></td>
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