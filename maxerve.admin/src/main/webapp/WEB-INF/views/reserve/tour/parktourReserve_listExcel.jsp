<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<h2>방문 예약현황</h2>

<h5>방문 예약(<c:out value="${paginationInfo.totalRecordCount}" />)</h5>
<table border="1">
	<colgroup>
		<col />				<!-- 단체명 -->
		<col width="5%"/>	<!-- 인원 -->
		<col width="20%"/>	<!-- 투어일시 -->
		<col width="20%"/>	<!-- 투어확정일 -->
		<col width="10%"/>	<!-- 신청자명 -->
		<col />				<!-- 신청자 연락처 -->
		<col />				<!-- 신청일 --> 
		<col />				<!-- 진행상태 -->
	</colgroup>
	<thead>
		<tr>
			<th>단체명</th>
			<th>인원</th>
			<th>투어일시</th>
			<th>투어확정일</th>
			<th>신청자명</th>
			<th>신청자 연락처</th>
			<th>
				<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">신청일△</a>
				<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">신청일▽</a>
			</th>
			<th>
				<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">진행상태△</a>
				<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">진행상태▽</a>
			</th>
		</tr>
	</thead>
	<c:choose>
	<c:when test="${fn:length(reserveList) > 0 }">
	<tbody id="reserveTB">
		<c:forEach items="${reserveList}" var="reserveList" varStatus="varStatus">
			<tr data-tour-aply-idx='<c:out value="${reserveList.tourAplyIdx }" />' onmouseover="this.style.backgroundColor='yellow'" onmouseout="this.style.backgroundColor=''" style="cursor:pointer">
				<td><c:out value="${reserveList.oztnNm }" /></td>
				<td><c:out value="${reserveList.psct }" /></td>
				<td>
					<fmt:parseDate value="${reserveList.tourDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtTourDttm"/>
					<fmt:parseDate value="${reserveList.tourDttmEnd }" pattern="yyyy.MM.dd HH:mm" var="fmtTourDttmEnd"/>
                    <fmt:formatDate value="${fmtTourDttm }" pattern="yyyy년 MM월 dd일 (E) HH:mm"/><br/>
                    <%-- <fmt:formatDate value="${fmtTourDttmEnd }" pattern="~ HH:mm"/><br/> --%>
                    
					<fmt:parseDate value="${reserveList.tourSecondDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtTourSecondDttm"/>
					<fmt:parseDate value="${reserveList.tourSecondDttmEnd }" pattern="yyyy.MM.dd HH:mm" var="fmtTourSecondDttmEnd"/>
                    <fmt:formatDate value="${fmtTourSecondDttm }" pattern="yyyy년 MM월 dd일 (E) HH:mm"/>
                    <%-- <fmt:formatDate value="${fmtTourSecondDttmEnd }" pattern="~ HH:mm"/> --%>
                    
					<%-- <c:out value="${tourDttm }"/><c:out value="${tourDttmEnd }"/><br/>
					<c:out value="${tourSecondDttm }"/><c:out value="${tourSecondDttmEnd }"/> --%>
				</td>
				<td>
					<fmt:parseDate value="${reserveList.tourDsdDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtTourDsdDttm"/>
                    <fmt:formatDate value="${fmtTourDsdDttm }" pattern="yyyy년 MM월 dd일 (E) HH:mm"/><br/>
				</td>
				<td><c:out value="${reserveList.appyNm }" /></td>
				<td><c:out value="${reserveList.appyPhnNmbr }" /></td>
				<td>
					<fmt:parseDate value="${reserveList.creDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtCreDttm"/>
					<fmt:formatDate value="${fmtCreDttm }" pattern="yyyy년 MM월 dd일 HH시mm분"/>
					<%-- <c:out value="${creDttm }" /> --%>
				</td>
				<td>
					<c:out value="${reserveList.tourPgrNm eq '예약완료' ? '투어확정': reserveList.tourPgrNm}" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
	</c:when>
	<c:otherwise>
		<tbody>
			<tr>
				<td colspan="7" align="center">조회결과가 없습니다.</td>
			</tr>
		</tbody>
	</c:otherwise>
	</c:choose>
</table>