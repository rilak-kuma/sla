<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:parseDate pattern="yyyy.MM.dd" var="srtDttm" value="${facilitiesMasterDTO.useSrtDt}"/>
<fmt:parseDate pattern="yyyy.MM.dd" var="endDttm" value="${facilitiesMasterDTO.useEndDt}"/>
<table>
	<thead>
		<tr>
			<th>���̵�(�̸���)</th>
			<th>����</th>
			<th>�޴���ȭ</th>
			<th>�������</th>
			<th>�ҼӴ�ü</th>
			<th>��û��</th>
		</tr>
	</thead>
	<c:choose>
	<c:when test="${fn:length(facilitiesReserveMVOList) > 0 }">
	<tbody id="reserveTB">
		<c:forEach items="${facilitiesReserveMVOList}" var="item" varStatus="status" >
		<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="creDttm" value="item.creDttm" />
			<tr>
				<td><c:out value="${item.rsvtEmil }" /></td>
				<td><c:out value="${item.rsvtNm }" /></td>
				<td><c:out value="${item.rsvtPhnNmbr }" /></td>
				<td><c:out value="${item.mbrTypCdNm }" /></td>
				<td><c:out value="${item.assOztnNm }" /></td>
				<td><fmt:formatDate value="creDttm" pattern="yyyy.MM.dd"/></td>
			</tr>
		</c:forEach>
	</tbody>
	</c:when>
	<c:otherwise>
		<tbody>
			<tr>
				<td colspan="6" align="center">��ȸ����� �����ϴ�.</td>
			</tr>
		</tbody>
	</c:otherwise>
	</c:choose>
</table>