<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<h5>�����̳뺣�̼��շ� Ŭ���� ��û��� - <c:out value="${searchVO.titl }" /></h5>

<table border="1">
	<thead>
		<tr>
			<th>Ŭ������</th>
			<th>����</th>
			<th>���̵�(�̸���)</th>
			<th>�޴���ȭ</th>
			<th>��û��</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach	items="${classList }" var="classList" varStatus="varstatus">
			<c:set var="i" value="${0}" />
			<c:forEach	items="${mkspClsMbrList }" var="mkspClsMbrList" >
				<c:if test="${classList.mkspClsGrpIdx eq mkspClsMbrList.mkspClsGrpIdx}" >
					<tr>
					<td><c:out value="${classList.grpNm}" /></td>
					<td><c:out value="${mkspClsMbrList.ceoNm }" /></td>
					<td><c:out value="${mkspClsMbrList.mbrId }" /></td>
					<td style='mso-number-format:"@";'><c:out value="${mkspClsMbrList.ceoPhn }" /></td>
					<td style='mso-number-format:"@";'><c:out value="${mkspClsMbrList.creDttm }" /></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>