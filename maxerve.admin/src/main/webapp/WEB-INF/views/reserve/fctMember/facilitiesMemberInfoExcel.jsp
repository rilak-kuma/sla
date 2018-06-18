<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script>
jQuery(function($){

	//�ʱⰪ
	$(document).ready(function(){

		var tbodyHtml = "";
		<c:forEach var="memberList" items="${memberList }" varStatus="varstatus" >
			tbodyHtml += '<tr>';
			tbodyHtml += '<td><c:out value="${memberList.mbrId }" /></td>';
			tbodyHtml += '<td><c:out value="${memberList.oztnNm }" /></td>';
			tbodyHtml += '<td style=\'mso-number-format:"\@";\'><c:out value="${memberList.ceoPhn }" /></td>';
			tbodyHtml += '<td><c:out value="${memberList.mbrTypCdNm }" /></td>';
			tbodyHtml += '<td><c:out value="${memberList.assOztnNm }" /></td>';
			tbodyHtml += '<td style=\'mso-number-format:"\@";\'><c:out value="${memberList.creDttm }" /></td>';
			tbodyHtml += '</tr>';
		</c:forEach>

		$("#tbody").html(tbodyHtml);

	});

});

</script>
<h1><c:out value="${titl }" /> ��ȸ����Ȳ</h1>
<h5>��ȸ�� ��Ȳ - <c:out value="${searchVO.actMth }" /></h5>
<button id="btnExcelDown">��������</button>

<table border="1">
		<tr>
			<th>���Գ��</th>
			<td><c:out value="${searchVO.actMth }" /></td>
			<th>�����Ⱓ</th>
			<td><c:out value="${searchVO.recruitmentPrd }" /></td>
			<th>��ȸ����</th>
			<td><c:out value="${searchVO.prc }" /></td>
		</tr>
		<tr>
			<th>����ȸ����</th>
			<td><c:out value="${searchVO.memberCnt }" /></td>
			<th><c:out value="${titl eq '�����ũ' ? '��������' : '' }" /></th>
			<td><c:out value="${titl eq '�����ũ' ? searchVO.lmtMbCnt : '' }" /></td>
			<th>�������</th>
			<td><c:out value="${searchVO.useYn }" /></td>
		</tr>
</table>

<table border="1">
	<thead>
		<tr>
			<th>���̵�(�̸���)</th>
			<th>����</th>
			<th>�޴���ȭ</th>
			<th>�������</th>
			<th>�ҼӴ�ü</th>
			<th>��û��</th>
		</tr>
	</thead>
	<tbody id="tbody">

	</tbody>
</table>