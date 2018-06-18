<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/ckeditor.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/adapters/jquery.js"/>'></script>
<style>

.file {
	display: none;
}

.picBox {
	display:none;
}
</style>
<script type="text/javascript">
jQuery(function($){

	// ckeditor
	$('textarea[name=body]').ckeditor(ckConfig({
		width: 900,
		height: 420
	}));

	//공지 등록 or 수정
	$('form[name=noticeFm]').validate({
		rules: {
			titl : "required",
			body : "required"
		},
		messages: {
			titl : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '제목은'),
			body : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '내용은')
		},
		submitHandler: function(form) {
			var url = '<c:url value="/information/notice/insertNotice.json"/>';
			var submitData = $(form).serializeFormJSON();
				submitData.useYn = $("input[name=useYn]:checked").val();

			if("<c:out value='${noticeVO.ntcIdx}' />" != ""){
				url = '<c:url value="/information/notice/updateNotice.json"/>';
				submitData.ntcIdx = "<c:out value='${noticeVO.ntcIdx}' />";
			}

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.href="/information/notice/noticeList.do";
			}

			ajaxSubmit(url, submitData, callback, null);
		}
	});

	//태그추가
	$("input[name=inputTag]").keydown(function(event) {
		if ( event.keyCode === 32 ) {
			var inputTag = $(this).val().trim();
			if(inputTag == ""){
				alert("입력값이 없습니다.");
			}else{
				$(this).val("");
				$("#tagDiv").append("<span class='stit'><input type='hidden' name='addTag' value='"+inputTag+"' readonly='readonly' />"+inputTag+"<button onclick=\"$(this).parent('span').remove();\" class='btnTag'>X</button></span>");
			}
		}
	});

	//첨부파일추가
	$('#addFile').fileupload({
		autoUpload: true,
		dataType: 'json',
		url: '<c:url value="/file/uploadFile.json"/>',
        add: function (e, data) {
            var uploadFile = data.files[0];
			if(uploadFile.name.length > 50){
				alert("원본 파일명은 50자 이하 입니다.");
				return;
			}
            var isValid = true;
            var pattern = /(\.|\/)(gif|jpe?g|png|docx?|xlsx?|pptx?|hwp|pdf)$/i;
            if (!(pattern).test(uploadFile.name)) {
                alert('doc, docx, xls, xlsx, ppt, pptx, hwp, pdf, jpg, gif, jpg, gif 만 가능합니다');
                isValid = false;
            } else if (uploadFile.size > (20*1024*1024)) { // 5mb *4
                alert('파일 용량은 20메가를 초과할 수 없습니다.');
                isValid = false;
            }
            if (isValid) {
            	if ((/(\.|\/)(gif|jpe?g|png)$/i).test(uploadFile.name)) {
            		data.formData = {
        					thumnailType : "CROP",
        					maxWidth: 221,
        					maxHeight: 147
        				};
				}

                data.submit();
            }
        },
        fail :function (e, data) {
        	try {var data = JSON.parse(data.jqXHR.responseText); if(data && data.msg){alert(data.msg);}} catch (e) { alert("파일 업로드 도중 에러가 발생했습니다.");};
        },
    	done: function (e, data) {
			var target = e.target.id;
    		$.each(data.result.files, function (index, file) {

    			var fileTxt = "<span class='stit'>"
					+ file.origFileNm+"<span class='guide'>( W:"+file.wdth+", H:"+file.hgth+" / "+file.fileSize+"byte )</span>"
					+ "<input type='hidden' name='addFilePath' value='"+file.filePath+"' />"
					+ "<button onclick=\"$(this).parent('span').remove();\" class='btnTag'>X</button></span>";
				$("#fileName").append(fileTxt);

    			$("#addFileArea").show();
    		});
		}
	});

	//첨부파일 이벤트연결
	$('#fileAdd').on('click', function(){
		fn_fileSelect('addFile');
	});

	//등록 or 수정
	$('#btnConfirm').on('click', function(){
		var filePaths = [];
		$("input[name=addFilePath]").each(function(){
			filePaths.push($(this).val());
		});

		var tagNm = [];
		$("input[name=addTag]").each(function(){
			tagNm.push($(this).val());
		});

		$("#addFileArea").append("<input type='hidden' name='filePath' value='"+filePaths+"' />");
		$("#tagDiv").append("<input type='hidden' name='tagNm' value='"+tagNm+"' />");

		$("form[name=noticeFm]").submit();
	});

	//삭제
	$('#btnDelete').on('click', function(){
		var url = '<c:url value="/information/notice/deleteNotice.json"/>';
		var submitData = {
				ntcIdx : '<c:out value="${noticeVO.ntcIdx }" />'
		}

		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href='<c:url value="/information/notice/noticeList.do" />';
		}

		ajaxSubmit(url, submitData, callback, null);
	});

	//목록
	$('#btnCansle').on('click', function(){
		window.history.back();
	});

	$(document).ready(function(){
		var $form = $("form[name=noticeFm]");

		<c:if test="${noticeVO.useYn ne ''}" >
			$form.find('input[name=useYn][value=<c:out value="${noticeVO.useYn}"/>]').prop('checked', true);
		</c:if>
		<c:if test="${noticeVO.topYn eq 'Y'}" >
			$form.find('#topYn').attr("checked",true);
		</c:if>

		<c:if test="${noticeVO.pcYn eq 'Y'}" >
			$form.find('#pcYn').attr("checked",true);
		</c:if>

		<c:if test="${noticeVO.mblYn eq 'Y'}" >
			$form.find('#mblYn').attr("checked",true);
		</c:if>

// 		$form.find('input[name=titl]').val('<c:out value="${noticeVO.titl }" />');

		<c:if test="${fileList.size() > 0}" >
			$("#addFileArea").show();
		</c:if>

	});
});

//파일 선택
function fn_fileSelect(fileId){
	$("#" + fileId).trigger("click");
}

</script>

<div class="location_area"><h3>센터공지</h3></div>
<div class="subcon_area">
	<form:form commandName="noticeVO" name="noticeFm">
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width="120px"/>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width="150px"/>
			</colgroup>
			<tr>
				<th>번호</th>
				<td><c:out value="${noticeVO.ntcIdx }" /></td>
				<th>등록일시</th>
				<td><c:out value="${noticeVO.creDttm }" /></td>
				<th>등록자</th>
				<td><c:out value="${creMngrMbrNm },${creMngrMbrDpt }" /> </td>
			</tr>
			<tr>
				<th>조회</th>
				<td><c:out value="${noticeVO.hitCnt }" /></td>
				<th>최종수정일시</th>
				<td><c:out value="${noticeVO.modDttm }" /></td>
				<th>최종수정자</th>
				<td><c:out value="${modMngrMbrNm },${modMngrMbrDpt }" /> </td>
			</tr>
			<tr>
				<th>전시여부</th>
				<td>
					<input type="radio" name="useYn" value="Y"  checked="checked"/>ON
					<input type="radio" name="useYn" value="N"  />OFF
				</td>
				<th>상단노출</th>
				<td><input type="checkbox" id="topYn" name="topYn" value="Y" />상단노출함</td>
				<th>스크린</th>
				<td>
					<input type="checkbox" id="pcYn"  name="pcYn" value="Y" />PC
					<input type="checkbox" id="mblYn"  name="mblYn" value="Y" />모바일
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="5">
					<form:input path="titl" maxlength="50" cssStyle="width:90%"/>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<form:textarea path="body" cssStyle="width:100%"/>
				</td>
			</tr>
			<tr>
				<th rowspan="2">첨부문서</th>
				<td colspan="4">
					DOC, DOCX, XLS, XLSX, PPT, PPTX, HWP, PDF, JPG, GIF 확장자 첨부가능
					<input type="file" id="addFile" name="reqUploadFile" class="file" data-input="false" />
				</td>
				<td>
					<button id="fileAdd" class="btnBasic_blue">문서추가</button>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<div id="addFileArea" class="picBox fl">
						<span id="fileName">
							<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
								<span>
									<input type="hidden" name="addFilePath" value="<c:out value='${fileList.filePath }'/>" />
									<a href="#none" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><span class='btnTag'><c:out value="${fileList.origFileNm}" /></span></a><span class="guide">( W: <c:out value="${fileList.wdth}"/>, H: <c:out value="${fileList.hgth}"/> / <c:out value="${fileList.fileSize }"/> byte )</span>
									<button onclick="$(this).parent('span').remove();" class='btnTag' >X</button>
								</span>
							</c:forEach>
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<th rowspan="2">태그</th>
				<td colspan="5">
					<input type="text" name="inputTag" placeholder="태그를 입력하고 스페이스키를 누르면 등록됩니다." maxlength="50" style="width: 90%;"/>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<div id="tagDiv">
					<c:forEach items="${tagList}" var="tagList" varStatus="varStatus">
						<span>
							<input type="hidden" name="addTag" value="${tagList.tagNm}" readonly='readonly' />
							<c:out value="${tagList.tagNm}"/>
							<button onclick="$(this).parent('span').remove();" class='btnTag'>X</button>
						</span>
					</c:forEach>
					</div>
				</td>
			</tr>
		</table>
		</div>
	</form:form>
<div class="btns tac">
<sec:authorize access="hasRole('023007001Y')">
	<button id="btnConfirm" class="btnBasic_blue">확인</button>
	<c:if test="${noticeVO.ntcIdx ne '' && noticeVO.ntcIdx ne null}">
		<button id="btnDelete" class="btnBasic_black">삭제</button>
	</c:if>
</sec:authorize>
	<button id="btnCansle" class="btnBasic_black">취소</button>
</div>
</div>
