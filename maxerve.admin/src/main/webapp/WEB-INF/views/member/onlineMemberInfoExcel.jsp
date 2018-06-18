<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<strong>가입정보</strong>
<table  border="1">
		<tr>
			<th>가입일 </th>
			<td style="mso-number-format:\@;"><c:out value="${memberVO.creDttm }" /></td>
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

<strong>멤버정보</strong>
<table  border="1">
		<tr>
			<th>아이디</th>
			<td colspan="3" style="mso-number-format:\@;"><c:out value="${memberVO.mbrId }" /></td>
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
			<td style="mso-number-format:\@;"><c:out value="${memberVO.ofcPhn }" /></td>
			<th>대표휴대전화</th>
			<td style="mso-number-format:\@;"><c:out value="${memberVO.ceoPhn }" /></td>
		</tr>
		<tr>
			<th>단체주소</th>
			<td colspan="3" style="mso-number-format:\@;"><c:out value="${memberVO.oztnAddr }" /></td>
		</tr>
		<tr>
			<th>블로그</th>
			<td colspan="3"><c:out value="${memberVO.blog }" /></td>
		</tr>
		</c:if>
</table>
<c:if test="${memberVO.mbrTypCd ne '015003' }">
<strong>부가정보</strong>
<table  border="1">
		<tr>
			<th>단체대표이미지</th>
			<td>
				<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
					<c:out value="${fileList.filePath}" />
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
			<td style="mso-number-format:\@;"><c:out value="${memberVO.locTypCdNm }" /></td>
		</tr>
		<tr>
			<th>위치타채널정보정보</th>
			<td style="mso-number-format:\@;">
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
</c:if>