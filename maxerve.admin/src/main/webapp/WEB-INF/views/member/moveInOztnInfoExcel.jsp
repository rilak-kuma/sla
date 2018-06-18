<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	th {
		background-color: #EEEEEE;
	}
</style>
<h1>���ִ�ü</h1>
<h3>��������</h3>
<table  border="1" style='border: 1px solid black;'>
	<tr>
		<th>���ֱⰣ</th>
		<td><c:out value="${moveInOztnInfo.mvinSrtDt }" /> ~ <c:out value="${moveInOztnInfo.mvinEndDt }" /></td>
	</tr>
	<tr>
		<th>�¶��θ��</th>
		<td><c:out value="${moveInOztnInfo.onlineMb }" /></td>
	</tr>
	<tr>
		<th>�������</th>
		<td><c:out value="${moveInOztnInfo.mvinPlc }" /></td>
	</tr>
	<tr>
		<th>���ֻ���</th>
		<td><c:out value="${moveInOztnInfo.aplyPgrCdNm }" /></td>
	</tr>
</table>

<h3>��ü����</h3>
<table  border="1" style='border: 1px solid black;'>
	<tr>
		<th>��ü��</th>
		<td><c:out value="${moveInOztnInfo.oztnNm }" /></td>
	</tr>
	<tr>
		<th>���ֱ׷�</th>
		<td>
		<c:forEach items="${mvinGrpCds }" var="mvinGrpCds" varStatus="varStatus">
					<c:if test="${mvinGrpCds.cmmnCd eq moveInOztnInfo.mvinGrpCd }"><c:out value="${mvinGrpCds.cmmnCdNm }" /></c:if>
		</c:forEach>
		</td>
	</tr>
	<tr>
		<th>��ü����</th>
		<td>
			<c:forEach items="${oztnTypCds }" var="oztnTypCds" varStatus="varStatus">
				<c:if test="${oztnTypCds.cmmnCd eq moveInOztnInfo.oztnTypCd }">
					<c:out value="${oztnTypCds.cmmnCdNm }" />
				</c:if>
			</c:forEach>
			<c:if test="${moveInOztnInfo.oztnTypEtc ne ''}">(<c:out value="${moveInOztnInfo.oztnTypEtc }" />)</c:if>
		</td>
	</tr>
	<tr>
		<th>����</th>
		<td>
			<c:forEach items="${ctgrIdxList }" var="ctgrIdxList" varStatus="varStatus">
						<c:forEach items="${moveInApplyCtgrList }" var="moveInApplyCtgrs" varStatus="varStatus">
							<c:if test="${ctgrIdxList.ctgrIdx eq moveInApplyCtgrs.ctgrIdx }">
							<c:out value="${ctgrIdxList.ctgrNm }"/>
							</c:if>
						</c:forEach>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<th>�ֿ�Ȱ��(����/��ǰ)</th>
		<td><c:out value="${moveInOztnInfo.svcAct }" /></td>
	</tr>
	<tr>
		<th>��ǥ�� ����</th>
		<td><c:out value="${moveInOztnInfo.ceoNm }" /></td>
	</tr>
	<tr>
		<th>�̸���</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.ceoEmil }" /></td>
	</tr>
	<tr>
		<th>�޴���ȭ</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.ceoPhn }" /></td>
	</tr>
	<tr>
		<th>����ο�</th>
		<td><c:out value="${moveInOztnInfo.ftePsct }" /></td>
	</tr>
	<tr>
		<th>����ڵ�Ϲ�ȣ</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.CRN }" /></td>
	</tr>
	<tr>
		<th>������</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.estDt }" /></td>
	</tr>
	<tr>
		<th>��ü�ּ�</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.oztnAddr }" /></td>
	</tr>
	<tr>
		<th>Ȩ������</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.hpg }" /></td>
	</tr>
	<tr>
		<th>����ó(�繫��) ��ȭ��ȣ</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.ofcPhn }" /></td>
	</tr>
	<tr>
		<th>�ѽ���ȣ</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.ofcFax }" /></td>
	</tr>
	<tr>
		<th>����� ����</th>
		<td><c:out value="${moveInOztnInfo.mngrNm }" /></td>
	</tr>
	<tr>
		<th>�޴���ȭ</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.mngrPhn }" /></td>
	</tr>
	<tr>
		<th>�̸���</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.mngrEmil }" /></td>
	</tr>
	<tr>
		<th>�ֿ�Ȱ��/����</th>
		<td><c:out value="${moveInOztnInfo.mainAct }" /></td>
	</tr>
	<tr>
		<th>��ü(��)�Ұ�</th>
		<td><c:out value="${moveInOztnInfo.oztnItdc }" /></td>
	</tr>
	<tr>
		<th>��ü(��)���� �����ϴ� ��ȸ����</th>
		<td><c:out value="${moveInOztnInfo.sclInv }" /></td>
	</tr>
	<tr>
		<th>�ذ��ϰ����ϴ� ��ȸ����</th>
		<td><c:out value="${moveInOztnInfo.sclIsu }" /></td>
	</tr>
	<tr>
		<th>�����ذ���̵��</th>
		<td><c:out value="${moveInOztnInfo.isuIdea }" /></td>
	</tr>
	<tr>
		<th>���ȿ��</th>
		<td><c:out value="${moveInOztnInfo.epctEft }" /></td>
	</tr>
	<tr>
		<th>��ü(��)�� �ٽɿ���</th>
		<td><c:out value="${moveInOztnInfo.oztnCcpc }" /></td>
	</tr>
	<tr>
		<th>�ֿ� ���(Ȱ��)��ȹ</th>
		<td><c:out value="${moveInOztnInfo.bssPlan }" /></td>
	</tr>
	<tr>
		<th>���� �� ������ȹ</th>
		<td><c:out value="${moveInOztnInfo.cprPlan }" /></td>
	</tr>
	<tr>
		<th>���������ڿ�, �ʿ��ڿ�</th>
		<td><c:out value="${moveInOztnInfo.cmmnRsc }" /></td>
	</tr>
</table>
