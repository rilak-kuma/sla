<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<table border="1">
	<caption></caption>
	<colgroup>
		<col width="150px"/>
		<col width=""/>
		<col width="150px"/>
		<col width=""/>
	</colgroup>
	<tr>
		<!-- <th>투어신청일</th> -->
		<th>방문신청일</th>
		<td colspan="3">
			<fmt:parseDate var="tourDttm" value="${parkTourInfo.tourDttm }" pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:parseDate var="tourDttmEnd" value="${parkTourInfo.tourDttmEnd }" pattern="yyyy.MM.dd HH:mm" />
			<fmt:formatDate value="${tourDttm }" pattern="yyyy년MM월dd일(E) HH:mm" /> ~ 18:00<br/>
			<%-- <fmt:formatDate value="${tourDttmEnd }" pattern="~ HH:mm"/><br/> --%>
			
			<fmt:parseDate var="tourSecondDttm" value="${parkTourInfo.tourSecondDttm }" pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:parseDate var="tourSecondDttmEnd" value="${parkTourInfo.tourSecondDttmEnd }" pattern="yyyy.MM.dd HH:mm" />
			<fmt:formatDate value="${tourSecondDttm }" pattern="yyyy년MM월dd일(E) HH:mm" /> ~ 18:00
			<%-- <fmt:formatDate value="${tourSecondDttmEnd }" pattern="~ HH:mm"/> --%>
		</td>
	</tr>
	<tr>
		<!-- <th>투어확정일</th> -->
		<th>방문확정일</th>
		<td>
			<c:if test="${parkTourInfo.tourDsdDttm ne '0000-00-00 00:00:00' }" >
				<fmt:parseDate var="tourDsdDttm" value="${parkTourInfo.tourDsdDttm }" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate value="${tourDsdDttm }" pattern="yyyy년MM월dd일(E) HH:mm" /> ~ 18:00
			</c:if>
		</td>
		<th>진행상태</th>
		<td>
			<span>
			<c:forEach items="${tourPgrs }" var="item">
			<c:if test="${item.cmmnCd eq parkTourInfo.tourPgr }"><c:out value="${item.cmmnCdNm }"/></c:if>
			</c:forEach>
			</span>
		</td>
	</tr>
</table>

<strong>관리자 Comment</strong>
<div>
	<c:out value="${parkTourInfo.cmmt}" />
</div>

<strong>방문 신청정보</strong>
<table border="1">
	<caption></caption>
	<colgroup>
		<col width="150px"/>
		<col width=""/>
		<col width="150px"/>
		<col width=""/>
	</colgroup>
	<tr>
		<!-- <th>단체명</th> -->
		<th>기관명</th>
		<td><c:out value="${parkTourInfo.oztnNm}" /></td>
		<th>인원</th>
		<td><c:out value="${parkTourInfo.psct}" /></td>
	</tr>
	<tr>
		<!-- <th>단체분류</th> -->
		<th>기관분류</th>
		<td colspan="3"><c:out value="${parkTourInfo.oztnTypCdNm}" /></td>
	</tr>
	<tr>
		<th>신청자</th>
		<td><c:out value="${parkTourInfo.appyNm}" /> / <c:out value="${parkTourInfo.appyOztnNm}" /> / <c:out value="${parkTourInfo.appyPhnNmbr}" /> / <c:out value="${parkTourInfo.appyEmil}" /></td>
		<th>인솔자</th>
		<td><c:out value="${parkTourInfo.ldrNm}" /> / <c:out value="${parkTourInfo.ldrOztnNm}" /> / <c:out value="${parkTourInfo.appyPhnNmbr}" /> / <c:out value="${parkTourInfo.appyEmil}" /></td>
	</tr>
	<tr>
		<th>주차</th>
		<td colspan="3">
			<c:forEach var="tourCarList" items="${tourCarList }" varStatus="status">
		  		<c:if test="${status.index > 0 }">/</c:if>[<c:out value="${tourCarList.carTypCdNm}" />] <c:out value="${tourCarList.carCnt}" /> 대
		  	</c:forEach>
		</td>
	</tr>
	<tr>
		<th>방문목적</th>
		<td colspan="3">[<c:out value="${parkTourInfo.tourPrpsCdNm}" />]<c:out value="${parkTourInfo.tourPrpsEtc}" /></td>
	</tr>
	<tr>
		<th>신청기관소개</th>
		<td colspan="3">[<c:out value="${parkTourInfo.appyOztnItdc}" />]<c:out value="${parkTourInfo.tourPrpsEtc}" /></td>
	</tr>
	<tr>
		<th>기타</th>
		<td colspan="3"><c:out value="${parkTourInfo.etc}" /></td>
	</tr>
</table>

<strong>방문자 명단</strong>
<table border="1">
	<caption></caption>
	<colgroup>
		<col width=""/>
		<col width=""/>
		<col width=""/>
		<col width=""/>
	</colgroup>
	<thead>
		<tr>
			<th>성명</th>
			<th>단체명</th>
			<th>전화번호</th>
			<th>이메일</th>
		</tr>
	</thead>
	<tbody>
	  	<c:forEach var="tourAttendList" items="${tourAttendList }" varStatus="status">
		  	<tr>
		  		<td>
	  				<c:out value="${tourAttendList.attNm}" />
	  			</td>
		  		<td>
	  				<c:out value="${tourAttendList.oztnNm}" />
	  			</td>
		  		<td>
	  				<c:out value="${tourAttendList.phnNmbr}" />
	  			</td>
		  		<td>
	  				<c:out value="${tourAttendList.emil}" />
	  			</td>
	  		</tr>
  		</c:forEach>
	</tbody>
</table>