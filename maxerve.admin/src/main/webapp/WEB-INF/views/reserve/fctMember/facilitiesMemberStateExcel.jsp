<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<h1><c:out value="${titl }" /> 월회원현황</h1>
<h5><c:out value="${titl }" /> 월회원(<c:out value="${fn:length(memberList) }" />)</h5>

<table border="1">
	<thead>
		<tr>
			<th>이용년월</th>
			<th>가입회원수</th>
			<c:if test="${titl eq '우드파크' }">
				<th>모집정원</th>
			</c:if>
			<th>모집기간</th>
			<th>월회원비</th>
			<th>진행상태</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="memberList" items="${memberList }" >
			<tr>
			<td>
				<fmt:parseDate var="actMth" value="${memberList.actMth }" pattern="yyyy.MM" />
				<fmt:formatDate value="${actMth }" pattern="yyyy년MM월" />
			</td>
			<td style='mso-number-format:"\@";'><c:out value="${memberList.memberCnt }" /></td>
			<c:if test="${titl eq '우드파크' }">
				<td style='mso-number-format:"\@";'><c:out value="${memberList.lmtMbCnt }" /></td>
			</c:if>
			<td style='mso-number-format:"\@";'><c:out value="${memberList.recruitmentPrd }" /></td>
			<td style='mso-number-format:"\@";'><c:out value="${memberList.prc }" /></td>
			<td><c:out value="${memberList.useYn==\'Y\'?\'모집중\':\'모집완료\' }" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>