<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border='1' style='border: 1px solid black;'>
	<thead>
		<tr>
			<th>일시</th>
			<th>단체</th>
			<th>후원 프로젝트</th>
			<th>후원기간</th>
			<th>전체 건수</th>
			<th>수수료(원)</th>
			<th>정산금액(원)</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${!empty sponsorMVOList }">
			<c:forEach items="${sponsorMVOList}" var="item">
			<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="srtDttm" value="${item.srtDttm }" />
			<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="endDttm" value="${item.endDttm }" />
				<tr>
						<td><c:out value="${item.creDttm }"/></td>
						<td><c:out value="${item.oztnNm }"/></td>
						<td><c:out value="${item.pjtTitl }" /></td>
						<td>
							<fmt:formatDate value="${srtDttm }" pattern="yyyy.MM.dd"/>
							~
							<fmt:formatDate value="${endDttm }" pattern="yyyy.MM.dd"/>
						</td>
						<td><c:out value="${item.count }"/></td>
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