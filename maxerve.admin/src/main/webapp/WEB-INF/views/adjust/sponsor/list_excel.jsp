<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border='1' style='border: 1px solid black;'>
	<thead>
		<tr>
			<th>후원일시</th>
			<th>후원자</th>
			<th>휴대전화</th>
			<th>후원 프로젝트</th>
			<th>결제방식</th>
			<th>후원금액</th>
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
						<c:when test="${item.payMthd eq 'CARD' }">신용카드</c:when>
						<c:when test="${item.payMthd eq 'BANK' }">계좌이체</c:when>
						<c:when test="${item.payMthd eq 'KAKAOPAY' }">카카오페이</c:when>
						<c:when test="${item.payMthd eq 'CELLPHONE' }">휴대폰</c:when>
					</c:choose>
						</td>
						<td><c:out value="${item.spoAom }"/></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td>조회결과가 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>