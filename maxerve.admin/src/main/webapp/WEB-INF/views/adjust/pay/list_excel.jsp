<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border="1" style='border: 1px solid black;'>
	<thead>
		<tr>
			<th>결제일시</th>
			<th>결제항목</th>
			<th>결제금액(원)</th>
			<th>아이디</th>
			<th>단체명(성명)</th>
			<th>상태</th>
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
						<c:when test="${item.cnclRstCd eq '2001' or item.cnclRstCd eq '2002' or item.cnclRstCd eq '2211' }">결제취소</c:when>
						<c:when test="${item.apprYn eq 'Y' }">결제완료</c:when>
						<c:otherwise>결제오류</c:otherwise>
					</c:choose>
						</td>
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