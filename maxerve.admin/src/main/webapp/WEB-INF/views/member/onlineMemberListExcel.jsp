<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>�¶��θ��</div>
<table border="1">
	<tr style='background-color: #EEEEEE;'>
		<th>���̵�</th>
		<th>��ü��(����)</th>
		<th>��ǥ�ڸ�</th>
		<th>��ǥ�޴���ȭ</th>
		<th>�������</th>
		<th>���ֱ׷�</th>
		<th>������</th>
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