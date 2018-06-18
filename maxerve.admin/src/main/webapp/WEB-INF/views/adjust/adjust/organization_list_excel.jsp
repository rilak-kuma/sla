<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table>
	<thead>
		<tr>
			<th>단체명</th>
			<th>대표자명</th>
			<th>건수</th>
			<th>결제금액(원)</th>
			<th>수수료(원)</th>
			<th>정산금액(원)</th>
			<th>정산일</th>
			<th>상태</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${!empty adjustHistoryMVOList }">
			<c:forEach items="${adjustHistoryMVOList}" var="item">
				<tr>
						<td><c:out value="${item.oztnNm }"/></td>
						<td><c:out value="${item.ceoNm }"/></td>
						<td><c:out value="${item.count }"/></td>
						<td><c:out value="${item.prc }"/></td>
						<td><c:out value="${item.fees }" /></td>
						<td><c:out value="${item.prc + item.fees }" /></td>
						<td><c:out value="${item.ajmDt }"/></td>
						<td>
					<c:choose>
						<c:when test="${item.ajmYn eq 'Y' }">정산완료</c:when>
						<c:otherwise>정산예정</c:otherwise>
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