<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h5>�ڷ��(<c:out value="${fn:length(referenceList)}" />)</h5>
<table border="1">
	<tr>
		<th>����</th>
		<th>�з�</th>
		<th>�����</th>
		<th>�����</th>
		<th>����</th>
	</tr>
	<c:choose>
		<c:when test="${fn:length(referenceList) > 0 }">
			<c:forEach items="${referenceList}" var="referenceList" varStatus="varStatus">
				<tr>
						<td><c:out value="${referenceList.titl }" /></td>
						<td><c:out value="${referenceList.ctgrIdxNm }" /></td>
						<td><c:out value="${referenceList.ceoNm }" /><c:if test="${referenceList.oztnNm != ''}">/</c:if><c:out value="${referenceList.oztnNm }" /></td>
						<td><c:out value="${referenceList.creDttm }" /></td>
						<td style='mso-number-format:"\@";'><c:out value="${referenceList.useYn=='Y'?'ON':'OFF' }" /></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<td colspan="6" align="center">��ȸ����� �����ϴ�.</td>
		</c:otherwise>
	</c:choose>
</table>