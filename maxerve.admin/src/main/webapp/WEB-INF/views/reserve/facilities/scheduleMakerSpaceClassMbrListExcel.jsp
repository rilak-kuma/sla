<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<h5>서울이노베이션팹랩 클래스 신청명단 - <c:out value="${searchVO.titl }" /></h5>

<table border="1">
	<thead>
		<tr>
			<th>클래스명</th>
			<th>성명</th>
			<th>아이디(이메일)</th>
			<th>휴대전화</th>
			<th>신청일</th>
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