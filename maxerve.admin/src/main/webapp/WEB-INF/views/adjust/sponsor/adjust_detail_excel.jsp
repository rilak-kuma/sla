<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table>
	<thead>
		<tr>
			<th>후원일시</th>
			<th>후원자명</th>
			<th>결제수단</th>
			<th>결제금액</th>
			<th>수수료</th>
			<th>정산금액</th>
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
						<c:when test="${item.payMthd eq 'CARD' }">신용카드</c:when>
						<c:when test="${item.payMthd eq 'BANK' }">계좌이체</c:when>
						<c:when test="${item.payMthd eq 'KAKAOPAY' }">카카오페이</c:when>
						<c:when test="${item.payMthd eq 'CELLPHONE' }">휴대폰</c:when>
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
				<td>조회결과가 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>