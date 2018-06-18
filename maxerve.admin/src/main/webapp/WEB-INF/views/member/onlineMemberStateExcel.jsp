<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>�¶��� ��� ��Ȳ</h1>
<table  border='1' style='border: 1px solid black;'>
		<c:set var="length" value="${fn:length(onlineMemberState) }" />
		<c:forEach items="${onlineMemberState }" var="onlineMemberState" varStatus="varStatus">
			<c:choose>
				<c:when test="${varStatus.index < 1 }">
					<tr>
						<td rowspan="${length}" style='background-color: #EEEEEE;'>��ü �¶��θ��</td>
						<td rowspan="${length}"><c:out value="${onlineMemberState.olineAllMbrCnt }" /> </td>
						<td style='background-color: #EEEEEE;'><c:out value="${onlineMemberState.mbrTypCdNm }" /></td>
						<td><c:out value="${onlineMemberState.mbrTypeCnt }" /></td>
						<td style='background-color: #EEEEEE;'>���ָ������</td>
						<td><c:out value="${onlineMemberState.invrMbrCnt }" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td style='background-color: #EEEEEE;'><c:out value="${onlineMemberState.mbrTypCdNm }" /></td>
						<td colspan="3"><c:out value="${onlineMemberState.mbrTypeCnt }" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
</table>

<h5>�¶��� ���ָ��</h5>
<table  border='1' style='border: 1px solid black;'>
	<thead>
		<tr style='background-color: #EEEEEE;'>
			<th>���ֱ׷�</th>
			<th>��ü��</th>
			<th>���ָ������(��)</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="totGrpCnt" target="${totGrpCnt+grpCnt}" />
		<c:forEach items="${onlineMvinMbState }" var="onlineMvinMbState" varStatus="varStatus">
			<tr>
				<td><c:out value="${onlineMvinMbState.mvinGrpCdNm }" /></td>
				<td style="mso-number-format:\#\,\#\#0\;"><c:out value="${onlineMvinMbState.grpCnt }" /></td>
				<td style="mso-number-format:\#\,\#\#0\;"><c:out value="${onlineMvinMbState.ftePsct }" /></td>
			</tr>
			<c:set var="totGrpCnt" value="${totGrpCnt+onlineMvinMbState.grpCnt}" />
			<c:set var="totFtePsct" value="${totFtePsct+onlineMvinMbState.ftePsct}" />
		</c:forEach>
		<tr style='background-color: #99CCFF;'>
			<td>��ü</td>
			<td style="mso-number-format:\#\,\#\#0\;"><c:out value="${totGrpCnt}" /></td>
			<td style="mso-number-format:\#\,\#\#0\;"><c:out value="${totFtePsct}" /></td>
		</tr>
	</tbody>
</table>