<%@  page language="java" contentType="application/vnd.ms-excel; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	th {
		background-color: #EEEEEE;
	}
</style>
<h1>입주단체</h1>
<h3>입주정보</h3>
<table  border="1" style='border: 1px solid black;'>
	<tr>
		<th>입주기간</th>
		<td><c:out value="${moveInOztnInfo.mvinSrtDt }" /> ~ <c:out value="${moveInOztnInfo.mvinEndDt }" /></td>
	</tr>
	<tr>
		<th>온라인멤버</th>
		<td><c:out value="${moveInOztnInfo.onlineMb }" /></td>
	</tr>
	<tr>
		<th>입주장소</th>
		<td><c:out value="${moveInOztnInfo.mvinPlc }" /></td>
	</tr>
	<tr>
		<th>입주상태</th>
		<td><c:out value="${moveInOztnInfo.aplyPgrCdNm }" /></td>
	</tr>
</table>

<h3>단체정보</h3>
<table  border="1" style='border: 1px solid black;'>
	<tr>
		<th>단체명</th>
		<td><c:out value="${moveInOztnInfo.oztnNm }" /></td>
	</tr>
	<tr>
		<th>입주그룹</th>
		<td>
		<c:forEach items="${mvinGrpCds }" var="mvinGrpCds" varStatus="varStatus">
					<c:if test="${mvinGrpCds.cmmnCd eq moveInOztnInfo.mvinGrpCd }"><c:out value="${mvinGrpCds.cmmnCdNm }" /></c:if>
		</c:forEach>
		</td>
	</tr>
	<tr>
		<th>단체형태</th>
		<td>
			<c:forEach items="${oztnTypCds }" var="oztnTypCds" varStatus="varStatus">
				<c:if test="${oztnTypCds.cmmnCd eq moveInOztnInfo.oztnTypCd }">
					<c:out value="${oztnTypCds.cmmnCdNm }" />
				</c:if>
			</c:forEach>
			<c:if test="${moveInOztnInfo.oztnTypEtc ne ''}">(<c:out value="${moveInOztnInfo.oztnTypEtc }" />)</c:if>
		</td>
	</tr>
	<tr>
		<th>업종</th>
		<td>
			<c:forEach items="${ctgrIdxList }" var="ctgrIdxList" varStatus="varStatus">
						<c:forEach items="${moveInApplyCtgrList }" var="moveInApplyCtgrs" varStatus="varStatus">
							<c:if test="${ctgrIdxList.ctgrIdx eq moveInApplyCtgrs.ctgrIdx }">
							<c:out value="${ctgrIdxList.ctgrNm }"/>
							</c:if>
						</c:forEach>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<th>주요활동(서비스/제품)</th>
		<td><c:out value="${moveInOztnInfo.svcAct }" /></td>
	</tr>
	<tr>
		<th>대표자 성명</th>
		<td><c:out value="${moveInOztnInfo.ceoNm }" /></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.ceoEmil }" /></td>
	</tr>
	<tr>
		<th>휴대전화</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.ceoPhn }" /></td>
	</tr>
	<tr>
		<th>상근인원</th>
		<td><c:out value="${moveInOztnInfo.ftePsct }" /></td>
	</tr>
	<tr>
		<th>사업자등록번호</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.CRN }" /></td>
	</tr>
	<tr>
		<th>설립일</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.estDt }" /></td>
	</tr>
	<tr>
		<th>단체주소</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.oztnAddr }" /></td>
	</tr>
	<tr>
		<th>홈페이지</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.hpg }" /></td>
	</tr>
	<tr>
		<th>연락처(사무실) 전화번호</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.ofcPhn }" /></td>
	</tr>
	<tr>
		<th>팩스번호</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.ofcFax }" /></td>
	</tr>
	<tr>
		<th>담당자 성명</th>
		<td><c:out value="${moveInOztnInfo.mngrNm }" /></td>
	</tr>
	<tr>
		<th>휴대전화</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.mngrPhn }" /></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td style="mso-number-format:\@;"><c:out value="${moveInOztnInfo.mngrEmil }" /></td>
	</tr>
	<tr>
		<th>주요활동/연혁</th>
		<td><c:out value="${moveInOztnInfo.mainAct }" /></td>
	</tr>
	<tr>
		<th>단체(팀)소개</th>
		<td><c:out value="${moveInOztnInfo.oztnItdc }" /></td>
	</tr>
	<tr>
		<th>단체(팀)에서 생각하는 사회혁신</th>
		<td><c:out value="${moveInOztnInfo.sclInv }" /></td>
	</tr>
	<tr>
		<th>해결하고자하는 사회문제</th>
		<td><c:out value="${moveInOztnInfo.sclIsu }" /></td>
	</tr>
	<tr>
		<th>문제해결아이디어</th>
		<td><c:out value="${moveInOztnInfo.isuIdea }" /></td>
	</tr>
	<tr>
		<th>기대효과</th>
		<td><c:out value="${moveInOztnInfo.epctEft }" /></td>
	</tr>
	<tr>
		<th>단체(팀)의 핵심역량</th>
		<td><c:out value="${moveInOztnInfo.oztnCcpc }" /></td>
	</tr>
	<tr>
		<th>주요 사업(활동)계획</th>
		<td><c:out value="${moveInOztnInfo.bssPlan }" /></td>
	</tr>
	<tr>
		<th>입주 시 협업계획</th>
		<td><c:out value="${moveInOztnInfo.cprPlan }" /></td>
	</tr>
	<tr>
		<th>공유가능자원, 필요자원</th>
		<td><c:out value="${moveInOztnInfo.cmmnRsc }" /></td>
	</tr>
</table>
