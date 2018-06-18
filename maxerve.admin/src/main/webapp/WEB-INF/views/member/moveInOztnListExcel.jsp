<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>입주단체</div>
<table border="1" style='border: 1px solid black;'>
	<tr style='background-color: #EEEEEE;'>
		<th>단체명</th>
		<th>대표자명</th>
		<th>대표이메일</th>
		<th>대표휴대전화</th>
		<th>단체형태</th>
		<th>입주일</th>
		<th>입주상태</th>
		<th>온라인멤버</th>
	</tr>
		<c:forEach items="${moveinList }" var="item" varStatus="varStatus">
	<tr>
		<td><c:out value="${item.oztnNm }"/></td>
		<td><c:out value="${item.ceoNm }"/></td>
		<td><c:out value="${item.ceoEmil }"/></td>
		<td style="mso-number-format:\@;" ><c:out value="${item.ceoPhn }"/></td>
		<td><c:out value="${item.oztnTypCdNm }"/></td>
		<td style="mso-number-format:\@;"><c:out value="${item.mvinSrtDt }"/></td>
		<td><c:out value="${item.aplyPgrCdNm }"/></td>
		<td style="mso-number-format:\@;"><c:out value="${item.onlineMb }"/></td>
	</tr>
	</c:forEach>
</table>