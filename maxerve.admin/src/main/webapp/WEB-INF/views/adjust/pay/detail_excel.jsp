<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="creDttm" value="${paymentMVO.creDttm }" />
<h1>����</h1>

<h5>��������</h5>
<table border="1" style='border: 1px solid black;'>
	<tr>
		<th>�����Ͻ�</th>
		<td><fmt:formatDate value="${creDttm }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
		<th>����</th>
		<td>
	<c:choose>
		<c:when test="${paymentMVO.apprYn ne 'Y' }">��������</c:when>
		<c:when test="${paymentMVO.cnclRstCd eq '2001' or paymentMVO.cnclRstCd eq '2002' }">�������</c:when>
		<c:otherwise>�����Ϸ�</c:otherwise>
	</c:choose>
		</td>
	</tr>
	<tr>
		<th>��������</th>
		<td>
	<c:choose>
		<c:when test="${paymentMVO.payMthd eq 'CARD' }">[�ſ�ī��]<c:out value="${paymentMVO.cardNm }"/></c:when>
		<c:when test="${paymentMVO.payMthd eq 'BANK' }">[������ü]<c:out value="${paymentMVO.bankNm }"/></c:when>
		<c:when test="${paymentMVO.payMthd eq 'CELLPHONE' }">[�޴���]<c:out value="${paymentMVO.dstAddr }"/></c:when>
		<c:when test="${paymentMVO.payMthd eq 'KAKAOPAY' }">[īī������]<c:out value="${paymentMVO.cardNm }"/></c:when>
	</c:choose>
		</td>
		<th>�����ݾ�</th>
		<td><c:out value="${paymentMVO.prc }"/>��</td>
	</tr>
<c:choose>
	<c:when test="${paymentMVO.payMthd eq 'CARD' or paymentMVO.payMthd eq 'KAKAOPAY' }">
		<tr>
			<th>�Һ�</th>
			<td>
		<c:choose>
			<c:when test="${paymentMVO.cardQuota eq '00' }">�Ͻú�</c:when>
			<c:otherwise><c:out value="paymentMVO.cardQuota"/>����</c:otherwise>
		</c:choose>
			</td>
			<th>ī������Ʈ����</th>
			<td>${paymentMVO.cardPoint eq '0' ? '�̻��':'���' }</td>
		</tr>
	</c:when>
	<c:when test="${paymentMVO.payMthd eq 'BANK' }">
		<tr>
			<th>���ݿ�����</th>
			<td colspan='3'>${paymentMVO.rcptTyp eq '0' ? '�̹߱�':'�߱�' }</td>
		</tr>
	</c:when>
</c:choose>
	<tr>
		<th>�����׸�</th>
		<td colspan='3'><c:out value="[${paymentMVO.locTitl }] ${paymentMVO.titl }" /></td>
	</tr>
<c:if test="${!empty stringList }">
	<tr>
		<th>�����󼼳���</th>
		<td colspan='3'>
		<c:forEach items="${stringList }" var="item" varStatus="status">
			${!status.first ? '<br/>':'' }
			<c:out value="${item }"/>
		</c:forEach>
		</td>
	</tr>
</c:if>
</table>

<h5>���������</h5>
<table border="1" style='border: 1px solid black;'>
	<tr>
		<th>�������</th>
		<td><c:out value="${memberMVO.mbrTypCdNm }"/></td>
		<th>���̵�(�̸���)</th>
		<td><c:out value="${memberMVO.mbrId }"/></td>
	</tr>
	<tr>
		<th>��ü��(����)</th>
		<td><c:out value="${memberMVO.oztnNm }"/></td>
		<th>�޴���ȭ</th>
		<td><c:out value="${memberMVO.ceoPhn }"/></td>
	</tr>
	<tr>
		<th>�ҼӴ�ü</th>
		<td><c:out value="${memberMVO.assOztnNm }"/></td>
		<th>���ֱ׷�</th>
		<td><c:out value="${memberMVO.mvinGrpCdNm }"/></td>
	</tr>
</table>