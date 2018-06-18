<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
	th {
		background-color: #EEEEEE;
	}
	label {
		background-color: #FFFF22;
	}
</style>
<h1>층별 호실 정보</h1>
<table border="1">
	<thead>
		<tr>
			<th>2층</th>
			<th>3층</th>
			<th>4층</th>
			<th>5층</th>
			<th>6층</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>
				<ul>
					<c:forEach var="floorList" items="${floorList }" varStatus="varStatus">
						<c:if test="${floorList.flr eq '2' }">
							<li><label><c:out value="${floorList.roomNm }" />호 </label> <c:out value="${floorList.fctNm eq ''?'공실' : floorList.fctNm }" /></li>
						</c:if>
					</c:forEach>
				</ul>
			</td>
			<td>
				<ul>
					<c:forEach var="floorList" items="${floorList }" varStatus="varStatus">
						<c:if test="${floorList.flr eq '3' }">
							<li><label><c:out value="${floorList.roomNm }" />호 </label> <c:out value="${floorList.fctNm eq ''?'공실' : floorList.fctNm }" /></li>
						</c:if>
					</c:forEach>
				</ul>
			</td>
			<td>
				<ul>
					<c:forEach var="floorList" items="${floorList }" varStatus="varStatus">
						<c:if test="${floorList.flr eq '4' }">
							<li><label><c:out value="${floorList.roomNm }" />호 </label> <c:out value="${floorList.fctNm eq ''?'공실' : floorList.fctNm }" /></li>
						</c:if>
					</c:forEach>
				</ul>
			</td>
			<td>
				<ul>
					<c:forEach var="floorList" items="${floorList }" varStatus="varStatus">
						<c:if test="${floorList.flr eq '5' }">
							<li><label><c:out value="${floorList.roomNm }" />호 </label> <c:out value="${floorList.fctNm eq ''?'공실' : floorList.fctNm }" /></li>
						</c:if>
					</c:forEach>
				</ul>
			</td>
			<td>
				<ul>
					<c:forEach var="floorList" items="${floorList }" varStatus="varStatus">
						<c:if test="${floorList.flr eq '6' }">
							<li><label><c:out value="${floorList.roomNm }" />호 </label> <c:out value="${floorList.fctNm eq ''?'공실' : floorList.fctNm }" /></li>
						</c:if>
					</c:forEach>
				</ul>
			</td>
		</tr>
	</tbody>
</table>