<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<h2><c:out value="${mainTitle }" /></h2>

<h5><c:out value="${subTitle }" />(<c:out value="${paginationInfo.totalRecordCount}" />)</h5>
<table border="1">
	<caption></caption>
	<colgroup>
		<col width="10%"/>
		<col width="15%"/>
		<col width="10%"/>
		<col width="20%"/>
		<col width="15%"/>
		<col width="15%"/>
		<col width="15%"/>
	</colgroup>
	<thead>
		<tr>
			<th>예약번호</th>
			<th>아이디</th>
			<th>단체명(성명)</th>
			<th>사용일시</th>
			<th>
				<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">시설△</a>
				<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">시설▽</a>
			</th>
			<th>
				<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">신청일△</a>
				<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">신청일▽</a>
			</th>
			<th>
				<a href="#none" id="sortThree_1" onclick="fn_sort('sortThree','1')" style="cursor:pointer">진행상태△</a>
				<a href="#none" id="sortThree_2" onclick="fn_sort('sortThree','2')" style="cursor:pointer">진행상태▽</a>
			</th>
		</tr>
	</thead>
	<tbody id="reserveTB">
		<c:choose>
			<c:when test="${fn:length(reserveList) > 0 }">
				<c:forEach items="${reserveList}" var="reserveList" varStatus="varStatus">
					<tr data-fct-rsvt-idx='<c:out value="${reserveList.fctRsvtIdx }" />' >
						<td><c:out value="${reserveList.fctRsvtIdx }"/></td>
						<td><c:out value="${reserveList.mbrId }" /></td>
						<td><c:out value="${reserveList.oztnNm }" /></td>
						<td><c:out value="${reserveList.srtDttm }" /> ~ <c:out value="${reserveList.endDttm }" /></td>
						<td><c:out value="${reserveList.fctNm }" /></td>
						<td><c:out value="${reserveList.creDttm }" /></td>
						<td>
							<c:choose>
								<c:when test="${reserveList.rsvtPgrNm eq '예약취소<br>(관리자취소)' }">
									예약취소(관리자취소)
								</c:when>
								<c:when test="${reserveList.rsvtPgrNm eq '예약완료' }">
									예약번호(<c:out value="${reserveList.fctRsvtIdx}" />)
								</c:when>								
								<c:otherwise>
									<c:out value="${reserveList.rsvtPgrNm}" />
								</c:otherwise>
							</c:choose>								
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</tbody>
</table>