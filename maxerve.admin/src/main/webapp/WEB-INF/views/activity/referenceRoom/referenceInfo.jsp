<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script type="text/javascript">
jQuery(function($){
	// validate
	$('form[name=infoFm]').validate({
		rules: {
			useYn	: 'required'
		},
		messages: {
			useYn	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '상태는')
		},
		submitHandler: function(form) {
	 		var url = '<c:out value="/activity/referenceRoom/updateReference.json"/>';
			var submitData = {
					refRoomIdx	:	'<c:out value="${referenceVO.refRoomIdx }" />',
					useYn 		:	$("input[name=useYn]:checked").val()
			}
			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.href="/activity/referenceRoom/referenceList.do";
			}
			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	//수정
	$('#btnConfirm').on('click', function(){
		$('form[name=infoFm]').submit();
	});

	//삭제
	$('#btnDelete').on('click', function(){
		if (!confirm(getMessage('confirm.delete'))) {
			return;
		}
 		var url = '<c:out value="/activity/referenceRoom/deleteReference.json"/>';
		var submitData = {
				refRoomIdx	:	'<c:out value="${referenceVO.refRoomIdx }" />'
		}
		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href="/activity/referenceRoom/referenceList.do";
		}

		ajaxSubmit(url, submitData, callback, null);
	});

	//목록
	$('#btnCansle').on('click', function(){
		window.history.back();
	});

	//댓글삭제
	$('input[name=btnDelCommnet]').on('click', function(){
		if (!confirm(getMessage('confirm.delete'))) {
			return;
		}
 		var url = '<c:out value="/comment/deleteComment.json"/>';
		var submitData = {
				cmmtIdx : this.value
		}
		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.reload();
		}
		ajaxSubmit(url, submitData, callback, null);
	});

	$(document).ready(function(){
		$('input[name=useYn][value=<c:out value="${referenceVO.useYn}"/>]').prop('checked', true);
	});
});

</script>

<div class="location_area"><h3>자료실</h3></div>
<form name="infoFm" method="post" >
<div class="subcon_area">
	<div class="basicTbl02">
		<table>
			<tr>
				<td>분류</td>
				<td><c:out value="${referenceVO.ctgrIdxNm }" /></td>
				<td>등록일시</td>
				<td><c:out value="${referenceVO.creDttm }" /></td>
				<td>등록자</td>
				<td><c:out value="${referenceVO.ceoNm }" /><c:if test="${referenceVO.oztnNm ne null}">/</c:if><c:out value="${referenceVO.oztnNm }" /></td>
			</tr>
			<tr>
				<td>조회</td>
				<td><c:out value="${referenceVO.hitCnt }" /></td>
				<td>최종수정일시</td>
				<td><c:out value="${referenceVO.modDttm }" /></td>
				<td>최종수정자</td>
				<td><c:out value="${referenceVO.modMngrMbrNm }" /><c:if test="${referenceVO.modMngrMbrDpt ne null}">/</c:if><c:out value="${referenceVO.modMngrMbrDpt }" /></td>
			</tr>
			<tr>
				<td>전시여부</td>
				<td>
					<input type="radio" name="useYn" value="Y" checked="checked" />ON
					<input type="radio" name="useYn" value="N" />OFF
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>

	<table class="detailTbl mt_20">
		<caption></caption>
		<colgroup>
			<col style="width:150px;">
			<col style="">
		</colgroup>
		<tr>
			<td class="tit"><c:out value="${referenceVO.titl }" /></td>
		</tr>
		<tr>
			<td>
				<c:out value="${referenceVO.creDttm }" />
				<strong class="ml_5">
					<c:out value="${referenceVO.oztnNm }" /><c:if test="${referenceVO.ceoNm ne null}">/</c:if><c:out value="${referenceVO.ceoNm }" />
				</strong>
			</td>
		</tr>
		<tr>
			<td>
				<c:out value="${referenceVO.body }" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<td>
				<img src="../../img/ico_file.png" alt="첨부파일"/>
				<c:choose>
					<c:when test='${referenceVO.ctgrIdxNm eq "영상" && referenceVO.yutbYn eq "Y"}'>
						<c:choose>
							<c:when test="${fn:length(yutbUrlList) > 0 }">
								<c:forEach items="${yutbUrlList}" var="yutbUrlList" varStatus="varStatus">
									<iframe width="640" height="360" src="https://www.youtube.com/embed/<c:out value="${yutbUrlList.yutbUrl}" />"  ></iframe>
								</c:forEach>
							</c:when>
							<c:otherwise>유투브 URL 없음</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${referenceVO.ctgrIdxNm eq '영상' && referenceVO.yutbYn eq 'N'}">
						<c:choose>
							<c:when test="${fn:length(fileList) > 0 }">
								<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
									<a href="#none" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><c:out value="${fileList.origFileNm}" /></a>
								</c:forEach>
							</c:when>
							<c:otherwise>영상파일없음</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${referenceVO.ctgrIdxNm eq '이미지'}">
						<c:choose>
							<c:when test="${fn:length(fileList) > 0 }">
								<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
									<a href="#none" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><c:out value="${fileList.origFileNm}" /></a>
								</c:forEach>
							</c:when>
							<c:otherwise>이미지파일없음</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${fn:length(fileList) > 0 }">
								<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
									<a href="#none" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><c:out value="${fileList.origFileNm}" /></a>
								</c:forEach>
							</c:when>
							<c:otherwise>첨부파일없음</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<img src="../../img/ico_tag.png" alt="태그"/>
				<c:forEach items="${tagList}" var="item" varStatus="varStatus">
					#<c:out value="${item.tagNm}" />
				</c:forEach>
			</td>
		</tr>
	</table>
</div>
</form>

<div class="stit mt_20 mb_10">
	댓글<strong><fmt:formatNumber value="${fn:length(commentList)}" groupingUsed="true"/></strong>개
</div>
<ul class="voteCommLst"><!-- 댓글 리스트 -->
<c:if test="${fn:length(commentList) > 0 }">
	<c:forEach items="${commentList}" var="commentList" varStatus="varStatus">
		<li>
			<div class="commBox">
				<div class="commInfo">
					<span class="nick"><c:out value="${commentList.oztnNm}" /></span>
					<span class="time"><c:out value="${commentList.modDttm}" /></span>
					<button name="btnDelCommnet" value="${commentList.cmmtIdx}"><img src="../../img/ico_del.png" alt="삭제"></button><!-- 삭제버튼 -->
				</div>
				<div class="recon">
					<c:out value="${commentList.body}" />
					<p class="mt_20"><img src="<spring:eval expression="@propertiesService['upload.url.root']" /><spring:eval expression="@propertiesService['upload.thumb.prefix']" /><c:out value="${commentList.filePath}" />" alt=""/></p><!-- 이미지 나올때 -->
				</div>
			</div>
		</li>
	</c:forEach>
</c:if>
</ul>

<div class="btns_02 tar">
<sec:authorize access="hasRole('023004004Y')">
	<button id="btnConfirm" class="btnBasic_blue">확인</button>
	<button id="btnDelete" class="btnBasic_black">삭제</button>
</sec:authorize>
	<button id="btnCansle" class="btnBasic_black">목록</button>
</div>