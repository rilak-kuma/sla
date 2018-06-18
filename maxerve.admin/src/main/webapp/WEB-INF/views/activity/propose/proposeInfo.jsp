<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/ckeditor.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/adapters/jquery.js"/>'></script>
<script type="text/javascript">
jQuery(function($){

	// ckeditor
	$('textarea[name=body]').ckeditor(ckConfig({
		width: 900,
		height: 420
	}));

	// validate
	$('form[name=infoFm]').validate({
		rules: {
			useYn	: 'required',
			body	: 'required'
		},
		messages: {
			useYn	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '상태는'),
			body	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '내용은')
		},
		submitHandler: function(form) {
	 		var url = '<c:out value="/activity/propose/updatePropose.json"/>';
	 		$(form).find("input[name=prpIdx]").val('<c:out value="${proposeVO.prpIdx }" />');
			var submitData = $(form).serializeFormJSON();
				submitData.useYn = $("input[name=useYn]:checked").val();
			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.href="/activity/propose/proposeList.do";
			}

			//console.log(submitData);
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
 		var url = '<c:out value="/activity/propose/deletePropose.json"/>';
		var submitData = {
				prpIdx	:	'<c:out value="${proposeVO.prpIdx }" />'
		}
		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href="/activity/propose/proposeList.do";
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
		$('input[name=useYn][value=<c:out value="${proposeVO.useYn}"/>]').prop('checked', true);
	});
});

</script>

<div class="location_area"><h3>아이디어/협업</h3></div>

<div class="subcon_area">
<form name="infoFm" method="post" >
<input type="hidden" name="prpIdx" />
<div class="basicTbl02">
	<table>
	<caption></caption>
	<colgroup>
		<col width="150px"/>
		<col width="40%"/>
		<col width="150px"/>
		<col width="*"/>
	</colgroup>
		<tr>
			<th>등록일시</th>
			<td><c:out value="${proposeVO.creDttm }" /></td>
			<th>등록자</th>
			<td><c:out value="${proposeVO.ceoNm }" /><c:if test="${proposeVO.ceoNm ne ''}">/</c:if><c:out value="${proposeVO.oztnNm }" /></td>
		</tr>
		<tr>
			<th>최종수정일시</th>
			<td><c:out value="${proposeVO.modDttm }" /></td>
			<th>최종수정자</th>
			<td><c:out value="${proposeVO.modMngrMbrNm }" /><c:if test="${proposeVO.modMngrMbrDpt ne null}">/<c:out value="${proposeVO.modMngrMbrDpt }" /></c:if></td>
		</tr>
		<tr>
			<th>조회</th>
			<td><c:out value="${proposeVO.hitCnt }" /></td>
			<th>상태</th>
			<td>
				<input type="radio" name="useYn" value="Y" checked="checked"/><label class="mr_10">ON</label>
				<input type="radio" name="useYn" value="N" /><label>OFF</label>
			</td>
		</tr>
	</table>
</div>

<table class="detailTbl mt_20">
	<caption></caption>
	<tr>
		<td class="tit"><c:out value="${proposeVO.titl }" /></td>
	</tr>
	<tr>
		<td><c:out value="${proposeVO.creDttm }" /><strong class="ml_5"><c:out value="${proposeVO.ceoNm }" /><c:if test="${proposeVO.ceoNm ne ''}">/</c:if><c:out value="${proposeVO.oztnNm }" /></strong></td>
	</tr>
	<tr>
		<td>
			<textarea name="body" >
				<c:out value="${proposeVO.body }" />
			</textarea>
		</td>
	</tr>
	<tr>
		<td>
			<img src="../../img/ico_file.png" alt="첨부파일"/>
			<c:if test="${fn:length(fileList) > 0 }">
				<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
					<a href="#none" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><c:out value="${fileList.origFileNm}" /></a>
				</c:forEach>
			</c:if>
		</td>
	</tr>
	<tr>
		<td>
			<img src="../../img/ico_tag.png" alt="태그"/>
			<c:if test="${fn:length(tagList) > 0 }">
				<c:forEach items="${tagList}" var="tagList" varStatus="varStatus">
					<span class="btnTag">#<c:out value="${tagList.tagNm}" /></span>
				</c:forEach>
			</c:if>
		</td>
	</tr>
</table>
</form>
</div>
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
						<button name="btnDelCommnet" value="${commentList.cmmtIdx}" class="ml_5"><img src="../../img/ico_del.png" alt="삭제"></button>
					</div>
					<div class="recon">
						<c:out value="${commentList.body}" />
						<p class="mt_20"><c:out value="${commentList.filePath}" /></p><!-- 이미지 나올때 -->
					</div>
				</div>
			</li>
		</c:forEach>
	</c:if>
</ul>
<!-- 버튼 -->
<div class="btns_02 tar">
<sec:authorize access="hasRole('023004001Y')">
	<button id="btnConfirm" class="btnBasic_blue">확인</button>
	<button id="btnDelete" class="btnBasic_black">삭제</button>
</sec:authorize>
	<button id="btnCansle" class="btnBasic_black">목록</button>
</div>
