<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>���ִ�ü</div>
<table border="1" style='border: 1px solid black;'>
	<tr style='background-color: #EEEEEE;'>
		<th>��ü��</th>
		<th>��ǥ�ڸ�</th>
		<th>��ǥ�̸���</th>
		<th>��ǥ�޴���ȭ</th>
		<th>��ü����</th>
		<th>������</th>
		<th>���ֻ���</th>
		<th>�¶��θ��</th>
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