<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/ckeditor.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/adapters/jquery.js"/>'></script>
<script type="text/javascript">
jQuery(function($){

	// ckeditor
	$('textarea[id=body]').ckeditor(ckConfig({
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
	 		var url = '<c:out value="/activity/project/updateProject.json"/>';
			var submitData = {
					pjtIdx	: '<c:out value="${projectVO.pjtIdx}" />',
					useYn	: $("input[name=useYn]:checked").val()
			}
			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.href="/activity/project/projectList.do";
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
 		var url = '<c:out value="/activity/project/deleteProject.json"/>';
		var submitData = {
				pjtIdx : '<c:out value="${projectVO.pjtIdx}" />'
		}
		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href="/activity/project/projectList.do";
		}

		ajaxSubmit(url, submitData, callback, null);
	});

	//목록
	$('#btnCansle').on('click', function(){
		window.history.back();
	});

	//댓글삭제
	$('button[name=btnDelCommnet]').on('click', function(){
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
		$('input[name=useYn][value=<c:out value="${projectVO.useYn}"/>]').prop('checked', true);
	});
});

</script>
<div class="location_area"><h3>프로젝트</h3></div>

<form name="infoFm" method="post" >
	<div class="subcon_area">
	<div class="basicTbl02">
		<table border="1">
			<tr>
				<th>카테고리</th>
				<td><c:out value="${projectVO.ctgrIdxNm }" /></td>
				<th>등록일시</th>
				<td><c:out value="${projectVO.creDttm }" /></td>
				<th>등록자</th>
				<td><c:out value="${projectVO.ceoNm }" /><c:if test="${projectVO.oztnNm ne null}">/</c:if><c:out value="${projectVO.oztnNm }" /></td>
			</tr>
			<tr>
				<th>최종수정일시</th>
				<td><c:out value="${projectVO.modDttm }" /></td>
				<th>최종수정자</th>
				<td><c:out value="${projectVO.modMngrMbrNm }" /><c:if test="${projectVO.modMngrMbrDpt ne null}">/</c:if><c:out value="${projectVO.modMngrMbrDpt }" /></td>
				<th>조회</th>
				<td><c:out value="${projectVO.hitCnt }" /></td>
			</tr>
			<tr>
				<th>상태</th>
				<td>
					<input type="radio" name="useYn" value="Y" checked="checked" /><label class="mr_10">ON</label>
					<input type="radio" name="useYn" value="N" /><label>OFF</label>
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
			<td class="tit" colspan="2">
				<span class="period"><c:out value="${projectVO.ctgrIdxNm }" /></span><c:out value="${projectVO.pjtTitl }" />
			</td>
		</tr>
		<tr>
			<td colspan="2"><c:out value="${projectVO.creDttm }" /><strong class="ml_5"><c:out value="${projectVO.ceoNm }" /><c:if test="${projectVO.oztnNm ne null}">/</c:if><c:out value="${projectVO.oztnNm }" /></strong></td>
		</tr>
		<tr>
			<td>후원기간</td>
			<td>
				<c:out value="${projectVO.srtDttm }" /> ~ <c:out value="${projectVO.endDttm }" />
			</td>
		</tr>
		<tr>
			<td>후원목표</td>
			<td>
				<strong class="emtxt"><c:out value="${projectVO.spoAom }" />원</strong>
			</td>
		</tr>
		<tr>
			<td>설명</td>
			<td>
				<c:out value="${projectVO.pjtInfo }" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea id="body">
					<c:out value="${projectVO.body }" />
				</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<img src="../../img/ico_file.png" alt="첨부파일"/>
				<c:if test="${fn:length(fileList) > 0 }">
					<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
						<a href="#none" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><c:out value="${fileList.origFileNm}" /></a>
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

<!-- 버튼 -->
<div class="btns_02 tar">
<sec:authorize access="hasRole('023004002Y')">
	<button id="btnConfirm" class="btnBasic_blue">확인</button>
	<button id="btnDelete" class="btnBasic_black">삭제</button>
</sec:authorize>
	<button id="btnCansle" class="btnBasic_black">목록</button>
</div>