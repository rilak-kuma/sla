<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>온라인멤버</div>
<table border="1">
	<tr style='background-color: #EEEEEE;'>
		<th>아이디</th>
		<th>단체명(성명)</th>
		<th>대표자명</th>
		<th>대표휴대전화</th>
		<th>멤버구분</th>
		<th>입주그룹</th>
		<th>가입일</th>
	</tr>
	<c:forEach items="${onlineMemberList }" var="onlineMemberList" >
	<tr>
		<td style="mso-number-format:\@;" >${onlineMemberList.mbrId }</td>
		<td><c:out value="${onlineMemberList.oztnNm }"/></td>
		<td><c:out value="${onlineMemberList.ceoNm }"/></td>
		<td style="mso-number-format:\@;" ><c:out value="${onlineMemberList.ceoPhn }"/></td>
		<td><c:out value="${onlineMemberList.mbrTypCdNm }"/></td>
		<td><c:out value="${onlineMemberList.mvinGrpCdNm }" /></td>
		<td style="mso-number-format:\@;" ><c:out value="${onlineMemberList.creDttm }"/></td>
	</tr>
	</c:forEach>
</table>