<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<strong>��������</strong>
<table  border="1">
		<tr>
			<th>������ </th>
			<td style="mso-number-format:\@;"><c:out value="${memberVO.creDttm }" /></td>
			<th>�������</th>
			<td><c:out value="${memberVO.mbrTypCdNm }" /></td>
		</tr>
		<tr>
			<th>
			<c:choose>
				<c:when test="${memberVO.mbrTypCd eq '015003' }">�̸�</c:when>
				<c:otherwise>��ü��</c:otherwise>
			</c:choose>
			</th>
			<td><c:out value="${memberVO.oztnNm }" /></td>
			<th>
			<c:choose>
				<c:when test="${memberVO.mbrTypCd eq '015003' }"></c:when>
				<c:otherwise>�������</c:otherwise>
			</c:choose>
			</th>
			<td>
			<c:choose>
				<c:when test="${memberVO.mbrTypCd eq '015003' }"></c:when>
				<c:otherwise><c:out value="${memberVO.invrMbrCnt }" />��</c:otherwise>
			</c:choose>
			</td>
		</tr>
</table>

<strong>�������</strong>
<table  border="1">
		<tr>
			<th>���̵�</th>
			<td colspan="3" style="mso-number-format:\@;"><c:out value="${memberVO.mbrId }" /></td>
		</tr>
		<tr>
			<th>��ü��</th>
			<td>
			<c:choose>
				<c:when test="${memberVO.mbrTypCd eq '015003' and not empty memberVO.assMbrIdx }"><c:out value="${assMemberVO.oztnNm }" /></c:when>
				<c:otherwise><c:out value="${memberVO.oztnNm }" /></c:otherwise>
			</c:choose>
			</td>
			<th>���ֱ׷�</th>
			<td>
			<c:choose>
				<c:when test="${memberVO.mbrTypCd eq '015003' and not empty memberVO.assMbrIdx }"><c:out value="${assMemberVO.mvinGrpCdNm }" /></c:when>
				<c:otherwise><c:out value="${memberVO.mvinGrpCdNm }" /></c:otherwise>
			</c:choose>
			</td>
		</tr>
		<c:if test="${memberVO.mbrTypCd ne '015003' }">
		<tr>
			<th>����</th>
			<td colspan="3"><c:out value="${memberVO.mbrCtgrCdNm }" /></td>
		</tr>
		<tr>
			<th>��ǥ�ڼ���</th>
			<td colspan="3"><c:out value="${memberVO.ceoNm }" /></td>
		</tr>
		<tr>
			<th>��ǥ��ȭ</th>
			<td style="mso-number-format:\@;"><c:out value="${memberVO.ofcPhn }" /></td>
			<th>��ǥ�޴���ȭ</th>
			<td style="mso-number-format:\@;"><c:out value="${memberVO.ceoPhn }" /></td>
		</tr>
		<tr>
			<th>��ü�ּ�</th>
			<td colspan="3" style="mso-number-format:\@;"><c:out value="${memberVO.oztnAddr }" /></td>
		</tr>
		<tr>
			<th>��α�</th>
			<td colspan="3"><c:out value="${memberVO.blog }" /></td>
		</tr>
		</c:if>
</table>
<c:if test="${memberVO.mbrTypCd ne '015003' }">
<strong>�ΰ�����</strong>
<table  border="1">
		<tr>
			<th>��ü��ǥ�̹���</th>
			<td>
				<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
					<c:out value="${fileList.filePath}" />
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>��ü���ΰ�</th>
			<td><c:out value="${memberVO.oztnSlg }" /></td>
		</tr>
		<tr>
			<th>��ü�Ұ�</th>
			<td><c:out value="${memberVO.oztnItdc }" /></td>
		</tr>
		<tr>
			<th>�±�</th>
			<td>
				<c:forEach items="${tagList}" var="tagList" varStatus="varStatus">
					#<c:out value="${tagList.tagNm}" />&nbsp;
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>��ġ����</th>
			<td style="mso-number-format:\@;"><c:out value="${memberVO.locTypCdNm }" /></td>
		</tr>
		<tr>
			<th>��ġŸä����������</th>
			<td style="mso-number-format:\@;">
				<c:forEach items="${mbChannelList }" var="mbChannelList" varStatus="varStatus">
					<a href="${mbChannelList.cnlUrl }"><c:out value="${mbChannelList.cnlTypCdNm }"/></a>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>��������</th>
			<td><c:out value="${memberVO.newsLttrYn }" /></td>
		</tr>
</table>
</c:if>