<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>입주단체현황</h1>

<h5>입주중 단체</h5>
<table border="1" style='border: 1px solid black;'>
	<tr style='background-color: #EEEEEE;'>
		<th>입주그룹</th>
		<th>단체수</th>
		<th>상근인원(명)</th>
	</tr>
	<c:set var="totGrpCnt" value="0" />
	<c:set var="totFtePsct" value="0" />
	<c:forEach items="${moveinList }" var="moveinList" varStatus="varStatus">
	<tr>
		<td><c:out value="${moveinList.mvinGrpCdNm }"/></td>
		<td style="mso-number-format:\#\,\#\#0\;"><c:out value="${moveinList.grpCnt }"/></td>
		<td style="mso-number-format:\#\,\#\#0\;"><c:out value="${moveinList.ftePsct }"/></td>
	</tr>
		<c:set var="totGrpCnt" value="${totGrpCnt+moveinList.grpCnt}" />
		<c:set var="totFtePsct" value="${totFtePsct+moveinList.ftePsct}" />
	</c:forEach>
	<tr style='background-color: #99CCFF;'>
		<td>전체</td>
		<td style="mso-number-format:\#\,\#\#0\;"><c:out value="${totGrpCnt}" /></td>
		<td style="mso-number-format:\#\,\#\#0\;"><c:out value="${totFtePsct}" /></td>
	</tr>
</table>

<h5>입주 기간만료 단체</h5>
<table border="1">
	<tr style='background-color: #EEEEEE;'>
		<th>입주그룹</th>
		<th>단체수</th>
		<th>상근인원(명)</th>
	</tr>
	<c:set var="totGrpCnt" value="0" />
	<c:set var="totFtePsct" value="0" />
	<c:forEach items="${expirationList }" var="expirationList" varStatus="varStatus">
	<tr>
		<td><c:out value="${expirationList.mvinGrpCdNm }"/></td>
		<td><c:out value="${expirationList.grpCnt }"/></td>
		<td><c:out value="${expirationList.ftePsct }"/></td>
	</tr>
		<c:set var="totGrpCnt" value="${totGrpCnt+expirationList.grpCnt}" />
		<c:set var="totFtePsct" value="${totFtePsct+expirationList.ftePsct}" />
	</c:forEach>
	<tr style='background-color: #99CCFF;'>
		<td>전체</td>
		<td><c:out value="${totGrpCnt}" /></td>
		<td><c:out value="${totFtePsct}" /></td>
	</tr>
</table>