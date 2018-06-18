<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<h1><c:out value="${titl }" /> ��ȸ����Ȳ</h1>
<h5><c:out value="${titl }" /> ��ȸ��(<c:out value="${fn:length(memberList) }" />)</h5>

<table border="1">
	<thead>
		<tr>
			<th>�̿���</th>
			<th>����ȸ����</th>
			<c:if test="${titl eq '�����ũ' }">
				<th>��������</th>
			</c:if>
			<th>�����Ⱓ</th>
			<th>��ȸ����</th>
			<th>�������</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="memberList" items="${memberList }" >
			<tr>
			<td>
				<fmt:parseDate var="actMth" value="${memberList.actMth }" pattern="yyyy.MM" />
				<fmt:formatDate value="${actMth }" pattern="yyyy��MM��" />
			</td>
			<td style='mso-number-format:"\@";'><c:out value="${memberList.memberCnt }" /></td>
			<c:if test="${titl eq '�����ũ' }">
				<td style='mso-number-format:"\@";'><c:out value="${memberList.lmtMbCnt }" /></td>
			</c:if>
			<td style='mso-number-format:"\@";'><c:out value="${memberList.recruitmentPrd }" /></td>
			<td style='mso-number-format:"\@";'><c:out value="${memberList.prc }" /></td>
			<td><c:out value="${memberList.useYn==\'Y\'?\'������\':\'�����Ϸ�\' }" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>