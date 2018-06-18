<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<h2><c:out value="${mainTitle }" /></h2>

<h5><c:out value="${subTitle }" />(<c:out value="${paginationInfo.totalRecordCount}" />)</h5>
<table border="1">
	<caption></caption>
	<colgroup>
			    <col width="8%"/>
				<col width="12%"/>
				<col width="12%"/>
				<col width="11%"/>
				<col width="12%"/>
				<col width="12%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
	</colgroup>
	<thead>
		<tr>
		    <th>멤버십회원 번호</th>
		    <th>단체명(성명)</th>
		    <th>팀명</th>
		    <th>가입유형</th>
		    <th>휴대전화</th>
			<th>아이디(이메일)</th>
			<th>신청일</th>
			<th>결제유형</th>
			<th>진행상태</th>
		</tr>
	</thead>
	<tbody id="reserveTB">
		<c:choose>
			<c:when test="${fn:length(reserveList) > 0 }">
				<c:forEach items="${reserveList}" var="reserveList" varStatus="varStatus">
					<tr data-fct-rsvt-idx='<c:out value="${reserveList.fctMbrIdx }" />' >
					    <td><c:out value="${reserveList.fctMbrIdx }" /></td>
					    <td><c:out value="${reserveList.oztnNm }" /></td>
					    <td><c:out value="${reserveList.timNm }" /></td>
						<td><c:out value="${reserveList.joinTypeNm }"/></td>
						<td><c:out value="${reserveList.emeCal }" /></td>
						<td><c:out value="${reserveList.mbrId }" /></td>
						<td><c:out value="${reserveList.creDttm }" /></td>
						<td><c:out value="${reserveList.payTypeNm }" /></td>
						<td>
							<c:choose>
								<c:when test="${reserveList.rsvtPgrNm eq '예약취소<br>(관리자취소)' }">
									예약취소<br>(관리자취소)
								</c:when>
								<c:otherwise>
									<c:if test="${reserveList.rsvtPgrNm eq '예약완료' }">예약번호<br/></c:if>
									<c:out value="${reserveList.rsvtPgrNm eq '예약완료' ? reserveList.fctMbrIdx: reserveList.rsvtPgrNm}" />
								</c:otherwise>
							</c:choose>								
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</tbody>
</table>