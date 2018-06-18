<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">

	jQuery(function($){
		$("#btnExcelDown").on('click',function(){
			window.open("/member/onlineMemberStateExcel.do", "", "");
		});
	});

</script>
<div class="location_area"><h3>온라인 멤버 현황</h3></div>

<div class="subcon_area">
	<div class="stit mt_20 mb_10">
		<strong>온라인 멤버 현황</strong>
		<div class="fr">
			<button id="btnExcelDown" class="btnBasic_blue">엑셀저장</button>
		</div>
	</div>
	<div class="searchTbl">
		<table>
				<c:set var="length" value="${fn:length(onlineMemberState) }" />
				<c:forEach items="${onlineMemberState }" var="onlineMemberState" varStatus="varStatus">
					<c:choose>
						<c:when test="${varStatus.index < 1 }">
							<tr>
								<th rowspan="<c:out value='${length }'/>">전체 온라인멤버</th>
								<td rowspan="<c:out value='${length }'/>"><c:out value="${onlineMemberState.olineAllMbrCnt }" /> </td>
								<th><c:out value="${onlineMemberState.mbrTypCdNm }" /></th>
								<td><c:out value="${onlineMemberState.mbrTypeCnt }" /></td>
								<th>입주멤버직원</th>
								<td><c:out value="${onlineMemberState.invrMbrCnt }" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th><c:out value="${onlineMemberState.mbrTypCdNm }" /></th>
								<td colspan="3"><c:out value="${onlineMemberState.mbrTypeCnt }" /></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>온라인 입주멤버</strong>
	</div>
	
	<div class="basicTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width="34%"/>
				<col width="33%"/>
				<col width="33%"/>
			</colgroup>
			<thead>
				<tr>
					<th>입주그룹</th>
					<th>단체수</th>
					<th>입주멤버직원(명)</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="totGrpCnt" target="${totGrpCnt+grpCnt}" />
				<c:forEach items="${onlineMvinMbState }" var="onlineMvinMbState" varStatus="varStatus">
					<tr>
						<td><c:out value="${onlineMvinMbState.mvinGrpCdNm }" /></td>
						<td><c:out value="${onlineMvinMbState.grpCnt }" /></td>
						<td><c:out value="${onlineMvinMbState.ftePsct }" /></td>
					</tr>
					<c:set var="totGrpCnt" value="${totGrpCnt+onlineMvinMbState.grpCnt}" />
					<c:set var="totFtePsct" value="${totFtePsct+onlineMvinMbState.ftePsct}" />
				</c:forEach>
				<tr style='background-color: #99CCFF;'>
					<td>전체</td>
					<td><c:out value="${totGrpCnt}" /></td>
					<td><c:out value="${totFtePsct}" /></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>