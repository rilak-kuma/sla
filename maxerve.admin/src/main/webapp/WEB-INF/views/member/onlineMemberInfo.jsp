<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
/* CSS로 마우스 드래그 방지 풀기 */
.subcon_area{-ms-touch-action:text}
.subcon_area{-webkit-user-select:text;-khtml-user-select:text;-moz-user-select:text;-o-user-select:text;user-select:text}
</style>
<script type="text/javascript">
 jQuery(function($){

		$("#btnExcelDown").on('click',function(){
			$form = $("form[name=infoFm]");
			$form.html("<input type='hidden' name='mbrIdx' value=<c:out value='${memberVO.mbrIdx }' /> />")
				.attr("action","<c:url value='/member/onlineMemberInfoExcel.do' />")
				.submit();
		});

		$('#btnListPg').on('click', function(){
			window.history.back();
		});
 });
</script>

<div class="location_area"><h3>온라인멤버</h3></div>
<div class="subcon_area">
	<form name="infoFm" method="post" target="_blank"></form>
	<div class="stit mt_20 mb_10">
		<strong>가입정보</strong>
		<div class="fr">
			<button id="btnExcelDown" class="btnBasic_blue">엑셀저장</button>
		</div>
	</div>
	<div class="searchTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width=""/>
			</colgroup>
				<tr>
					<th>가입일</th>
					<td><c:out value="${memberVO.creDttm }" /></td>
					<th>멤버구분</th>
					<td><c:out value="${memberVO.mbrTypCdNm }" /></td>
				</tr>
				<tr>
					<th>
					<c:choose>
						<c:when test="${memberVO.mbrTypCd eq '015003' }">이름</c:when>
						<c:otherwise>단체명</c:otherwise>
					</c:choose>
					</th>
					<td><c:out value="${memberVO.oztnNm }" /></td>
					<th>
					<c:choose>
						<c:when test="${memberVO.mbrTypCd eq '015003' }"></c:when>
						<c:otherwise>등록직원</c:otherwise>
					</c:choose>
					</th>
					<td>
					<c:choose>
						<c:when test="${memberVO.mbrTypCd eq '015003' }"></c:when>
						<c:otherwise><c:out value="${memberVO.invrMbrCnt }" />명</c:otherwise>
					</c:choose>
					</td>
				</tr>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>멤버정보</strong>
	</div>
	<div class="searchTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width=""/>
			</colgroup>
				<tr>
					<th>아이디</th>
					<td colspan="3"><c:out value="${memberVO.mbrId }" /></td>
				</tr>
				<tr>
					<th>단체명</th>
					<td>
					<c:choose>
						<c:when test="${memberVO.mbrTypCd eq '015003' and not empty memberVO.assMbrIdx }"><c:out value="${assMemberVO.oztnNm }" /></c:when>
						<c:otherwise><c:out value="${memberVO.oztnNm }" /></c:otherwise>
					</c:choose>
					</td>
					<th>입주그룹</th>
					<td>
					<c:choose>
						<c:when test="${memberVO.mbrTypCd eq '015003' and not empty memberVO.assMbrIdx }"><c:out value="${assMemberVO.mvinGrpCdNm }" /></c:when>
						<c:otherwise><c:out value="${memberVO.mvinGrpCdNm }" /></c:otherwise>
					</c:choose>
					</td>
				</tr>
			<c:if test="${memberVO.mbrTypCd ne '015003' }">
				<tr>
					<th>업종</th>
					<td colspan="3"><c:out value="${memberVO.mbrCtgrCdNm }" /></td>
				</tr>
				<tr>
					<th>대표자성명</th>
					<td colspan="3"><c:out value="${memberVO.ceoNm }" /></td>
				</tr>
				<tr>
					<th>대표전화</th>
					<td><c:out value="${memberVO.ofcPhn }" /></td>
					<th>대표휴대전화</th>
					<td><c:out value="${memberVO.ceoPhn }" /></td>
				</tr>
				<tr>
					<th>단체주소</th>
					<td colspan="3"><c:out value="${memberVO.oztnAddr }" /></td>
				</tr>
				<tr>
					<th>블로그</th>
					<td colspan="3"><c:out value="${memberVO.blog }" /></td>
				</tr>
			</c:if>
		</table>
	</div>

	<c:if test="${memberVO.mbrTypCd ne '015003' }">
	<div class="stit mt_20 mb_10">
		<strong>부가정보</strong>
	</div>
	<div class="searchTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
			</colgroup>
				<tr>
					<th>단체대표이미지</th>
					<td>
						<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
							<span><img src='<c:out value="${fileList.filePath}" />' alt="단체대표이미지"></span>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>단체슬로건</th>
					<td><c:out value="${memberVO.oztnSlg }" /></td>
				</tr>
				<tr>
					<th>단체소개</th>
					<td><c:out value="${memberVO.oztnItdc }" /></td>
				</tr>
				<tr>
					<th>태그</th>
					<td>
						<c:forEach items="${tagList}" var="tagList" varStatus="varStatus">
							#<c:out value="${tagList.tagNm}" />&nbsp;
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>위치정보</th>
					<td><c:out value="${memberVO.locTypCdNm }" /></td>
				</tr>
				<tr>
					<th>위치타채널정보정보</th>
					<td>
						<c:forEach items="${mbChannelList }" var="mbChannelList" varStatus="varStatus">
							<a href="${mbChannelList.cnlUrl }"><c:out value="${mbChannelList.cnlTypCdNm }"/></a>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>뉴스레터</th>
					<td><c:out value="${memberVO.newsLttrYn }" /></td>
				</tr>
		</table>
	</div>
	</c:if>
<div class="mt_20 tac">
	<button id="btnListPg" class="btnBasic_black">목록</button>
</div>
</div>

