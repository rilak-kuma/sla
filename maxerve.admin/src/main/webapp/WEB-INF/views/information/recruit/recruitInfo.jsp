<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
jQuery(function($){

	//수정
	$('#btnConfirm').on('click', function(){
 		var url = '<c:out value="/information/recruit/updateRecruit.json"/>';
		var submitData = {
				useYn		:	$("input[name=useYn]:checked").val(),
				rcrtRoomIdx :	'<c:out value="${recruitVO.rcrtRoomIdx }" />'
		}
		var callback = function(data) {
			alert(getMessage('msg.save.success'));
			location.href="/information/recruit/recruitList.do";
		}
		ajaxSubmit(url, submitData, callback, null);
	});

	//삭제
	$('#btnDelete').on('click', function(){
 		var url = '<c:out value="/information/recruit/deleteRecruit.json"/>';
		var submitData = {
				rcrtRoomIdx :	'<c:out value="${recruitVO.rcrtRoomIdx }" />'
		}
		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href="/information/recruit/recruitList.do";
		}

		ajaxSubmit(url, submitData, callback, null);
	});

	//목록
	$('#btnCansle').on('click', function(){
		window.history.back();
	});

	$(document).ready(function(){
		$('input[name=useYn][value=<c:out value="${recruitVO.useYn}"/>]').prop('checked', true);
	});
});

</script>

<div class="location_area"><h3>구인</h3></div>
<div class="subcon_area">
	<form name="recruitFm" method="post" >
		<div class="basicTbl02">
			<table>
				<tr>
					<th>번호</th>
					<td><c:out value="${recruitVO.rcrtRoomIdx }" /></td>
					<th>등록일시</th>
					<td><c:out value="${recruitVO.creDttm }" /></td>
					<th>등록자</th>
					<td><c:out value="${recruitVO.ceoNm }" /><c:if test="${recruitVO.oztnNm ne null}">/</c:if><c:out value="${recruitVO.oztnNm }" /></td>
				</tr>
				<tr>
					<th>조회</th>
					<td><c:out value="${recruitVO.hitCnt }" /></td>
					<th>최종수정일시</th>
					<td><c:out value="${recruitVO.modDttm }" /></td>
					<th>최종수정자</th>
					<td><c:out value="${recruitVO.modMngrMbrNm }" /><c:if test="${recruitVO.modMngrMbrDpt ne null}">/</c:if><c:out value="${recruitVO.modMngrMbrDpt }" /></td>
				</tr>
				<tr>
					<th>전시여부</th>
					<td>
						<input type="radio" name="useYn" value="Y"  checked="checked"/>ON
						<input type="radio" name="useYn" value="N"  />OFF
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>

	<p></p>
	<table class="detailTbl">
		<tr>
			<td colspan="2"><h4><c:out value="${recruitVO.titl }" /></h4></td>
		</tr>
		<tr>
			<td colspan="2">
				<c:out value="${recruitVO.creDttm }" /><strong class="ml_5"><c:out value="${recruitVO.oztnNm }" /><c:if test="${recruitVO.ceoNm ne null}">/</c:if><c:out value="${recruitVO.ceoNm }" /></strong>
			</td>
		</tr>
		<tr>
			<th>모집분야</th>
			<td><c:out value="${recruitVO.fld }" /></td>
		</tr>
		<tr>
			<th>모집인원</th>
			<td><c:out value="${recruitVO.psct }" /></td>
		</tr>
		<tr>
			<th>모집정보</th>
			<td><c:out value="${recruitVO.body }" /></td>
		</tr>
		<tr>
			<th>급여</th>
			<td><c:out value="${recruitVO.sal }" /></td>
		</tr>
		<tr>
			<th>복리후생</th>
			<td><c:out value="${recruitVO.bnf }" /></td>
		</tr>
		<tr>
			<th>지원방법</th>
			<td>
				<c:if test="${recruitVO.appyEmilYn =='Y' }">
					이메일 접수 : <c:out value="${recruitVO.emil }" />
				</c:if>
				<c:if test="${recruitVO.appyVisYn =='Y' }">
					방문 접수 : <c:out value="${recruitVO.addr }" />
				</c:if>
				<c:if test="${recruitVO.appyPostYn =='Y' }">
					우편 접수 : <c:out value="${recruitVO.addr }" />
				</c:if>
			</td>
		</tr>
		<tr>
			<th>지원문의</th>
			<td><c:out value="${recruitVO.appyQstn }" /></td>
		</tr>
		<tr>
			<th>접수기간</th>
			<td><c:out value="${recruitVO.prdSrtDt }" /> ~ <c:out value="${recruitVO.prdRcrtYn eq 'Y'?'충원시':recruitVO.prdEndDt }" /> 까지</td>
		</tr>
		<tr>
			<th>업체정보</th>
			<td><c:out value="${recruitVO.blog }" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<img src="../../img/ico_file.png" alt="첨부파일"/>
				<c:choose>
					<c:when test="${fn:length(fileList) > 0 }">
						<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
							<a href="#none" class="btnTag" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><c:out value="${fileList.origFileNm}" /></a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<a href="#none" onclick="javascript:alert('첨부파일없음')" >첨부파일없음</a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	</form>
</div>
<div class="btns tac">
<sec:authorize access="hasRole('023007003Y')">
	<button id="btnConfirm" class="btnBasic_blue">수정</button>
	<button id="btnDelete" class="btnBasic_black">삭제</button>
</sec:authorize>
	<button id="btnCansle" class="btnBasic_black">목록</button>
</div>