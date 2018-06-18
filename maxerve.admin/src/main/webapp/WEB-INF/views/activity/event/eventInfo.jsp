<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/ckeditor.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/adapters/jquery.js"/>'></script>
<script type="text/javascript">
jQuery(function($){

	// ckeditor
	$('#body').ckeditor(ckConfig({
		width: 900,
		height: 420,
		readOnly:true
	}));

	// validate
	$('form[name=infoFm]').validate({
		rules: {
			useYn	: 'required'
		},
		messages: {
			useYn	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '상태는')
		},
		submitHandler: function(form) {
	 		var url = '<c:out value="/activity/event/updateEvent.json"/>';
			var submitData = {
					evtIdx	:	'<c:out value="${eventVO.evtIdx }" />',
					useYn : $("input[name=useYn]:checked").val()
			}
			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.href="/activity/event/eventList.do";
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
 		var url = '<c:out value="/activity/event/deleteEvent.json"/>';
		var submitData = {
				evtIdx	:	'<c:out value="${eventVO.evtIdx }" />'
		}
		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href="/activity/event/eventList.do";
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
		$('input[name=useYn][value=<c:out value="${eventVO.useYn}"/>]').prop('checked', true);
	});
});

</script>
<div class="location_area"><h3>이벤트</h3></div>

<form name="infoFm" method="post" >
<div class="subcon_area">
	<div class="basicTbl02">
		<table border="1">
			<tr>
				<td>카테고리</td>
				<td><c:out value="${eventVO.ctgrIdxNm }" /></td>
				<td>등록일시</td>
				<td><c:out value="${eventVO.creDttm }" /></td>
				<td>등록자</td>
				<td><c:out value="${eventVO.ceoNm }" /><c:if test="${eventVO.oztnNm ne null}">/</c:if><c:out value="${eventVO.oztnNm }" /></td>
			</tr>
			<tr>
				<td>최종수정일시</td>
				<td><c:out value="${eventVO.modDttm }" /></td>
				<td>최종수정자</td>
				<td><c:out value="${eventVO.modMngrMbrNm }" /><c:if test="${eventVO.modMngrMbrDpt ne null}">/</c:if><c:out value="${eventVO.modMngrMbrDpt }" /></td>
				<td>조회</td>
				<td><c:out value="${eventVO.hitCnt }" /></td>
			</tr>
			<tr>
				<td>상태</td>
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
			<td class="tit" colspan="2"><span class="period"><c:out value="${eventVO.ctgrIdxNm }" /></span><c:out value="${eventVO.evtTitl }" /></td>
		</tr>
		<tr>
			<td colspan="2"><c:out value="${eventVO.creDttm }" /><strong class="ml_5"><c:out value="${eventVO.ceoNm }" /><c:if test="${eventVO.oztnNm ne null}">/</c:if><c:out value="${eventVO.oztnNm }" /></strong></td>
		</tr>
		<tr>
			<th>일시</th>
			<td>
				<c:out value="${eventVO.evtSrtDttm }" /> ~ <c:out value="${eventVO.evtEndDttm }" />
			</td>
		</tr>
		<tr>
			<th>장소</th>
			<td>
				<c:out value="${eventVO.evtPlc }" />
			</td>
		</tr>
		<tr>
			<th>모집인원</th>
			<td>
				<c:out value="${eventVO.psct }" />
			</td>
		</tr>
		<tr>
			<th>설명</th>
			<td>
				<c:out value="${eventVO.evtInfo }" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea id="body">
					<c:out value="${eventVO.body }" />
				</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<img src="../../img/ico_file.png" alt="첨부파일"/>
				<c:if test="${fn:length(fileList) > 0 }">
					<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
						<span class="btnTag"><a href="#none" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><c:out value="${fileList.origFileNm}" /></a></span>
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<img src="../../img/ico_tag.png" alt="태그"/>
				<c:if test="${fn:length(tagList) > 0 }">
					<c:forEach items="${tagList}" var="tagList" varStatus="varStatus">
						#<c:out value="${tagList.tagNm}" />&nbsp;
					</c:forEach>
				</c:if>
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
					<p class="mt_20"><img src="<c:out value="${commentList.filePath}" />" alt=""/></p><!-- 이미지 나올때 -->
				</div>
			</div>
		</li>
	</c:forEach>
</c:if>
</ul>

<div class="btns_02 tar">
<sec:authorize access="hasRole('023004003Y')">
	<button id="btnConfirm" class="btnBasic_blue">확인</button>
	<button id="btnDelete" class="btnBasic_black">삭제</button>
</sec:authorize>
	<button id="btnCansle" class="btnBasic_black">목록</button>
</div>