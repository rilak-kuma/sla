<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border="1" style='border: 1px solid black;'>
	<thead>
		<tr>
			<th>�����Ͻ�</th>
			<th>�����׸�</th>
			<th>�����ݾ�(��)</th>
			<th>���̵�</th>
			<th>��ü��(����)</th>
			<th>����</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${!empty paymentMVOList }">
			<c:forEach items="${paymentMVOList}" var="item">
			<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="creDttm" value="${item.creDttm }" />
				<tr>
						<td><fmt:formatDate value="${creDttm }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
						<td><c:out value="[${item.locTitl }] ${item.titl }" /></td>
						<td><c:out value="${item.prc }" /></td>
						<td><c:out value="${item.mbrId }" /></td>
						<td><c:out value="${item.oztnNm }" /></td>
						<td>
					<c:choose>
						<c:when test="${item.cnclRstCd eq '2001' or item.cnclRstCd eq '2002' or item.cnclRstCd eq '2211' }">�������</c:when>
						<c:when test="${item.apprYn eq 'Y' }">�����Ϸ�</c:when>
						<c:otherwise>��������</c:otherwise>
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