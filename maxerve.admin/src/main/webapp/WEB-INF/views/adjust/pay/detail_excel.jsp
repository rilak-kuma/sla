<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" var="creDttm" value="${paymentMVO.creDttm }" />
<h1>결제</h1>

<h5>결제정보</h5>
<table border="1" style='border: 1px solid black;'>
	<tr>
		<th>결제일시</th>
		<td><fmt:formatDate value="${creDttm }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
		<th>상태</th>
		<td>
	<c:choose>
		<c:when test="${paymentMVO.apprYn ne 'Y' }">결제오류</c:when>
		<c:when test="${paymentMVO.cnclRstCd eq '2001' or paymentMVO.cnclRstCd eq '2002' }">결제취소</c:when>
		<c:otherwise>결제완료</c:otherwise>
	</c:choose>
		</td>
	</tr>
	<tr>
		<th>결제수단</th>
		<td>
	<c:choose>
		<c:when test="${paymentMVO.payMthd eq 'CARD' }">[신용카드]<c:out value="${paymentMVO.cardNm }"/></c:when>
		<c:when test="${paymentMVO.payMthd eq 'BANK' }">[계좌이체]<c:out value="${paymentMVO.bankNm }"/></c:when>
		<c:when test="${paymentMVO.payMthd eq 'CELLPHONE' }">[휴대폰]<c:out value="${paymentMVO.dstAddr }"/></c:when>
		<c:when test="${paymentMVO.payMthd eq 'KAKAOPAY' }">[카카오페이]<c:out value="${paymentMVO.cardNm }"/></c:when>
	</c:choose>
		</td>
		<th>결제금액</th>
		<td><c:out value="${paymentMVO.prc }"/>원</td>
	</tr>
<c:choose>
	<c:when test="${paymentMVO.payMthd eq 'CARD' or paymentMVO.payMthd eq 'KAKAOPAY' }">
		<tr>
			<th>할부</th>
			<td>
		<c:choose>
			<c:when test="${paymentMVO.cardQuota eq '00' }">일시불</c:when>
			<c:otherwise><c:out value="paymentMVO.cardQuota"/>개월</c:otherwise>
		</c:choose>
			</td>
			<th>카드포인트결제</th>
			<td>${paymentMVO.cardPoint eq '0' ? '미사용':'사용' }</td>
		</tr>
	</c:when>
	<c:when test="${paymentMVO.payMthd eq 'BANK' }">
		<tr>
			<th>현금영수증</th>
			<td colspan='3'>${paymentMVO.rcptTyp eq '0' ? '미발급':'발급' }</td>
		</tr>
	</c:when>
</c:choose>
	<tr>
		<th>결제항목</th>
		<td colspan='3'><c:out value="[${paymentMVO.locTitl }] ${paymentMVO.titl }" /></td>
	</tr>
<c:if test="${!empty stringList }">
	<tr>
		<th>결제상세내역</th>
		<td colspan='3'>
		<c:forEach items="${stringList }" var="item" varStatus="status">
			${!status.first ? '<br/>':'' }
			<c:out value="${item }"/>
		</c:forEach>
		</td>
	</tr>
</c:if>
</table>

<h5>사용자정보</h5>
<table border="1" style='border: 1px solid black;'>
	<tr>
		<th>멤버구분</th>
		<td><c:out value="${memberMVO.mbrTypCdNm }"/></td>
		<th>아이디(이메일)</th>
		<td><c:out value="${memberMVO.mbrId }"/></td>
	</tr>
	<tr>
		<th>단체명(성명)</th>
		<td><c:out value="${memberMVO.oztnNm }"/></td>
		<th>휴대전화</th>
		<td><c:out value="${memberMVO.ceoPhn }"/></td>
	</tr>
	<tr>
		<th>소속단체</th>
		<td><c:out value="${memberMVO.assOztnNm }"/></td>
		<th>입주그룹</th>
		<td><c:out value="${memberMVO.mvinGrpCdNm }"/></td>
	</tr>
</table>