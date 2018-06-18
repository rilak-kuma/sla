<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">

	jQuery(function($){
		$("#btnExcelDown").on('click',function(){
			window.open("/member/currentStateExcel.do", "", "");
		});
	});

</script>

<div class="location_area"><h3>입주단체현황</h3></div>

<div class="subcon_area">
	<div class="mt_20 mb_10">
		<strong>입주중 단체</strong>
		<div class="fr">
			<button id="btnExcelDown" class="btnBasic">엑셀저장</button>
		</div>
	</div>
	<div class="insideTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width="50%"/>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
			<tr>
				<th>입주그룹</th>
				<th>단체수</th>
				<th>상근인원(명)</th>
			</tr>
			<c:set var="totGrpCnt" value="0" />
			<c:set var="totFtePsct" value="0" />
			<c:forEach items="${moveinList }" var="moveinList" varStatus="varStatus">
			<tr>
				<td><c:out value="${moveinList.mvinGrpCdNm }"/></td>
				<td><c:out value="${moveinList.grpCnt }"/></td>
				<td><c:out value="${moveinList.ftePsct }"/></td>
			</tr>
				<c:set var="totGrpCnt" value="${totGrpCnt+moveinList.grpCnt}" />
				<c:set var="totFtePsct" value="${totFtePsct+moveinList.ftePsct}" />
			</c:forEach>
			<tr style='background-color: #99CCFF;'>
				<td>전체</td>
				<td><c:out value="${totGrpCnt}" /></td>
				<td><c:out value="${totFtePsct}" /></td>
			</tr>
		</table>
	</div>

	<div class="mt_20 mb_10">
		<strong>입주 기간만료 단체</strong>
	</div>
	<div class="insideTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width="50%"/>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
			<tr>
				<th>입주그룹</th>
				<th>단체수</th>
				<th>상근인원(명)</th>
			</tr>
			<c:set var="totGrpCnt" value="0" />
			<c:set var="totFtePsct" value="0" />
			<c:forEach items="${expirationList }" var="expirationList" varStatus="varStatus">
			<tr>
				<td><c:out value="${expirationList.mvinGrpCdNm }"/></td>
				<td><c:out value="${expirationList.grpCnt }"/></td>
				<td><c:out value="${expirationList.ftePsct }"/></td>
			</tr>
				<c:set var="totGrpCnt" value="${totGrpCnt+expirationList.grpCnt}" />
				<c:set var="totFtePsct" value="${totFtePsct+expirationList.ftePsct}" />
			</c:forEach>
			<tr style='background-color: #99CCFF;'>
				<td>전체</td>
				<td><c:out value="${totGrpCnt}" /></td>
				<td><c:out value="${totFtePsct}" /></td>
			</tr>
		</table>
	</div>
</div>