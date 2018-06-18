<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<h5>���� (<c:out value="${paginationInfo.totalRecordCount}" />)</h5>

<table border="1">
	<tr>
		<th>��ȣ</th>
		<th>����</th>
		<th>����Ͻ�</th>
		<th>�����</th>
		<th>��ȸ</th>
		<th>����</th>
	</tr>
	<c:choose>
		<c:when test="${fn:length(recruitList) > 0 }">
			<c:forEach items="${recruitList}" var="recruitList" varStatus="varStatus">
				<tr>
						<td><c:out value="${recruitList.rcrtRoomIdx}" /></td>
						<td><c:out value="${recruitList.titl }" /></td>
						<td><c:out value="${recruitList.creDttm }" /></td>
						<td><c:out value="${recruitList.ceoNm }" /><c:if test="${recruitList.oztnNm != ''}">/</c:if><c:out value="${recruitList.oztnNm }" /></td>
						<td><c:out value="${recruitList.hitCnt }" /></td>
						<td><c:out value="${recruitList.useYn=='Y'?'ON':'OFF' }" /></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<td colspan="6" align="center">��ȸ����� �����ϴ�.</td>
		</c:otherwise>
	</c:choose>
</table>