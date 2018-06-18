<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:parseDate pattern="yyyy.MM.dd" var="srtDttm" value="${facilitiesMasterDTO.useSrtDt}"/>
<fmt:parseDate pattern="yyyy.MM.dd" var="endDttm" value="${facilitiesMasterDTO.useEndDt}"/>
<table>
	<thead>
		<tr>
			<th>아이디(이메일)</th>
			<th>성명</th>
			<th>휴대전화</th>
			<th>멤버구분</th>
			<th>소속단체</th>
			<th>신청일</th>
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
				<td colspan="6" align="center">조회결과가 없습니다.</td>
			</tr>
		</tbody>
	</c:otherwise>
	</c:choose>
</table>