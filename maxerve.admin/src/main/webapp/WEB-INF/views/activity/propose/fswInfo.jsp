<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<style>

.file {
	display: none;
}

.picBox {
	display:none;
}
</style>
<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/ckeditor.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/adapters/jquery.js"/>'></script>
<script type="text/javascript">
jQuery(function($){

	// ckeditor
	$('textarea[name=body]').ckeditor(ckConfig({
		width: 900,
		height: 420
	}));

	// 연관컨텐츠 addMethod
/* 	$.validator.addMethod('refDivCheck', function(value, element){
		var i = 0;
		$("div#refDiv p").each(function(){
			i++
		});
		return (i != 0);
	}, 'required'); */

	// validate
	$('form[name=infoFm]').validate({
		rules: {
			titl	: 'required',
			body	: 'required',
			srtDt	: 'required',
			endDt	: 'required',
			ord		: 'required'
		},
		messages: {
			titl	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '제목은'),
			body	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '내용은'),
			srtDt	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '시작일은'),
			endDt	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '종료일은'),
			ord		: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '노출순서는'),
		},
		submitHandler: function(form) {
			var i = 0;
			$("div#refDiv p").each(function(){
				i++
			});
			if(i == 0) return alert(getMessage('org.hibernate.validator.constraints.NotEmpty.message', '연관 게시물은'));

	 		var url = '<c:out value="/activity/propose/insert_fsw.json"/>';
			if("<c:out value='${fswTalkVO.fswTalkIdx}' />" != ""){
				url = '<c:out value="/activity/propose/update_fsw.json"/>';
			}
			//var submitData = $(form).serializeFormJSON();
			var submitData = {
				fswTalkIdx	: '<c:out value="${fswTalkVO.fswTalkIdx}" />',
				titl	: $("input[name=titl]").val(),
				body	: $("textarea[name=body]").val(),
				srtDt 	: $("input[name=srtDt]").val(),
				endDt 	: $("input[name=endDt]").val(),
				ord 	: $("select[name=ord]").val(),
				useYn	: $("input[name=useYn]:checked").val(),
				filePath	: $("input[name=filePath]").val(),
				tagNm	: $("input[name=tagNm]").val(),
				referenceList : []

			}

			$("#refDiv p").each(function(){
				submitData.referenceList.push({
					fswTalkIdx	: '<c:out value="${fswTalkVO.fswTalkIdx}" />',
					locTypCd	:	$(this).find("#locTypCd").val(),
					locIdx		:	$(this).find("#locIdx").val()
				});
			});

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.href="/activity/propose/fswList.do";
			}

			//console.log(submitData);
			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	//저장 or 수정
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

		$('form[name=infoFm]').submit();
	});

	//삭제
	$('#btnDelete').on('click', function(){
		if (!confirm(getMessage('confirm.delete'))) {
			return;
		}
 		var url = '<c:out value="/activity/propose/delete_fsw.json"/>';
		var submitData = {
				fswTalkIdx : "<c:out value='${fswTalkVO.fswTalkIdx}' />"
		}

		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href="/activity/propose/fswList.do";
		}

		ajaxSubmit(url, submitData, callback, null);
	});

	//목록
	$('#btnCansle').on('click', function(){
		window.history.back();
	});

	//첨부파일 이벤트연결
	$('#fileAdd').on('click', function(){
		fn_fileSelect('addFile');
	});

	//파일태그 선택
	function fn_fileSelect(fileId){
		$("#" + fileId).trigger("click");
	}

	//게시물 선택 팝업
	$("#btnSelRef").on('click', function(){
		window.open('<c:out value="/activity/propose/symTalkReferenceList.do"/>',"게시물 검색","width=600, height=470");
	});

	//첨부파일추가
	$('#addFile').fileupload({
		autoUpload: true,
		dataType: 'json',
		url: '<c:out value="/file/uploadFile.json"/>',
        add: function (e, data) {
            var uploadFile = data.files[0];
            if(uploadFile.name.length > 50){
				alert("원본 파일명은 50자 이하 입니다.");
				return;
				}
            var isValid = true;
            if (!(/doc|docx|xls?x|ppt?x|hwp|pdf|png|jpe?g|gif/i).test(uploadFile.name)) {
                alert('doc, docx, xls, xlsx, ppt, pptx, hwp, pdf, jpg, gif, jpg, gif 만 가능합니다');
                isValid = false;
            } else if (uploadFile.size > (20000000*4)) { // 5mb *4
                alert('파일 용량은 20메가를 초과할 수 없습니다.');
                isValid = false;
            }
            if (isValid) {
            	data.formData = {
					thumnailType : "CROP",
					maxWidth: 221,
					maxHeight: 147
				};
                data.submit();
            }
        },
        fail :function (e, data) {
        	try {var data = JSON.parse(data.jqXHR.responseText); if(data && data.msg){alert(data.msg);}} catch (e) { alert("파일 업로드 도중 에러가 발생했습니다.");};
        },
    	done: function (e, data) {
			var target = e.target.id;
    		$.each(data.result.files, function (index, file) {

    			var fileTxt = "<p><span class='btnTag'>"+ file.origFileNm + "</span>"
    						+ "<span class='guide'>(W:"+file.wdth+", H:"+file.hgth+" / "+file.fileSize+"byte)</span>"
    						+ "<input type='hidden' name='addFilePath' value='"+file.filePath+"' />"
    						+ "<button onclick=\"$(this).parent('p').remove();\" >X</button></p>";
    			$("#fileName").append(fileTxt);

    			$("#addFileArea").show();
    		});
		}
	});

	//태그추가
	$("#inputTag").keydown(function(event) {
		if ( event.keyCode === 32 ) {
			var inputTag = $(this).val().trim();;
			if(inputTag == ""){
				alert("입력값이 없습니다.");
			}else{
				$(this).val("");
				$("#tagDiv").append("<span class='btnTag'><input type='hidden' name='addTag' value='"+inputTag+"' readonly='readonly' />#"+inputTag+"<button onclick=\"$(this).parent('span').remove();\" >X</button></span>");
			}
		}
	});

	//달력
	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#srtDt" )
	        .datepicker({
	          defaultDate: "+1w",
	          changeMonth: true,
	          numberOfMonths: 1
	        }).on( "change", function() {
	          to.datepicker( "option", "minDate", getDate( this ) );
	        }),
	      to = $( "#endDt" ).datepicker({
	    	  defaultDate: "+1w",
	        changeMonth: true,
	        numberOfMonths: 1
	      }).on( "change", function() {
	        from.datepicker( "option", "maxDate", getDate( this ) );
	      });

	    function getDate( element ) {
	      var date;
	      try {
	        date = $.datepicker.parseDate( dateFormat, element.value );
	      } catch( error ) {
	        date = null;
	      }
	      return date;
	    }
	});

	$(document).ready(function(){
		$form = $('form[name=infoFm]');

		if ('<c:out value="${fswTalkVO.fswTalkIdx}" />' != ''){
			$form.find('input[name=titl]').val('<c:out value="${fswTalkVO.titl }" />');
			$form.find('select[name=ord] option[value=<c:out value="${fswTalkVO.ord}"/>]').prop('selected', true);
			$form.find('input[name=useYn][value=<c:out value="${fswTalkVO.useYn}"/>]').prop('checked', true);

			var confirmData = "";
			<c:forEach items="${SymTalkReferenceList}" var="SymTalkReferenceList" >
	 				confirmData +="<p>";
					confirmData +="<input type='hidden' id='locIdx' value=<c:out value='${SymTalkReferenceList.locIdx}' /> />";
					confirmData +="<input type='hidden' id='locTypCd' value=<c:out value='${SymTalkReferenceList.locTypCd}' /> />";
					confirmData +="(<c:out value='${SymTalkReferenceList.locTypCdNm}' />)";
					confirmData +="<c:out value='${SymTalkReferenceList.titl}' />";
					confirmData +="<input type='button' onclick='$(this).parent(\"p\").remove()' class='btnTag_del' />";
					confirmData +="</p>";
			</c:forEach>
			$("#refDiv").append(confirmData);
		}

		if ('<c:out value="${fileList.size()}" />' != '0'){
			$("#addFileArea").show();
		}

	});
});

</script>
<div class="location_area"><h3>아이디어/협업</h3></div>
<div class="subcon_area">
	<form name="infoFm" method="post" >
		<div class="basicTbl02">
			<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
				<tr>
					<th>노출순서<span class="red">*</span></th>
					<td>
						<select id="ord" name="ord" style="width:50px">
							<c:forEach begin="1" varStatus="varStatus" end="${fswTalkVO.maxOrd }">
								<option value='<c:out value="${varStatus.index }" />' ><c:out value="${varStatus.index }" /></option>
							</c:forEach>
						</select>
					</td>
					<th>전시여부<span class="red">*</span></th>
					<td>
						<input type="radio" name="useYn" value="Y"  checked="checked"/><label class="mr_10">ON</label>
						<input type="radio" name="useYn" value="N"  /><label>OFF</label>
					</td>
				</tr>
				<tr>
					<th>제목<span class="red">*</span></th>
					<td colspan="3"><input type="text" name="titl" maxlength="50" style="width:100%" /></td>
				</tr>
				<tr>
					<th>내용<span class="red">*</span></th>
					<td colspan="3"><textarea name="body"><c:out value="${fswTalkVO.body }" /></textarea></td>
				</tr>
				<tr>
					<th>연관콘텐츠 설정<span class="red">*</span></th>
					<td colspan="3">
						<button id="btnSelRef" class="btnBasic">게시물 검색</button>
						<div id="refDiv"></div>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3">
						<button id="fileAdd" class="btnBasic">파일 등록</button>
						<input type="file" id="addFile" name="reqUploadFile" class="file" data-input="false" />
						<div id="addFileArea" class="picBox">
							<span id='fileName'>
								<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
									<p>
										<input type="hidden" name="addFilePath" value="<c:out value='${fileList.filePath }'/>" />
										<a target="_blank" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" class="btnTag" ><c:out value="${fileList.origFileNm}" /></a>
										<span class="guide">(W: <c:out value="${fileList.wdth}"/>, H: <c:out value="${fileList.hgth}"/> / <c:out value="${fileList.fileSize }"/> byte)</span>
										<button onclick="$(this).parent('p').remove();">X</button>
									</p>
								</c:forEach>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<th>태그</th>
					<td colspan="3">
						<input type="text" id="inputTag" placeholder="입력 후 스페이스바를 누르면 추가됩니다." style="width:100%"/>
						<div id="tagDiv">
						<c:forEach items="${tagList}" var="tagList" varStatus="varStatus">
							<span class="btnTag">#<c:out value="${tagList.tagNm}"/>
								<input type="hidden" name="addTag" value="${tagList.tagNm}" readonly='readonly' />
								<button onclick="$(this).parent('span').remove();" >X</button>
							</span>
						</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
					<th>기간설정<span class="red">*</span></th>
					<td colspan="3">
						<input type="text" id="srtDt" name="srtDt" value="<c:out value='${fswTalkVO.srtDt }' />" style="width:100px" readonly="readonly"/>
						<img src="../../img/ico_cal.png" alt="캘린더"><span class="mr_20">부터</span>
						<input type="text" id="endDt" name="endDt" value="<c:out value='${fswTalkVO.endDt }' />" style="width:100px" readonly="readonly"/>
						<img src="../../img/ico_cal.png" alt="캘린더"><span>까지</span>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>

<div class="btns tar mb_40">
<sec:authorize access="hasRole('023004005Y')">
	<button id="btnConfirm" class="btnBasic_blue">확인</button>
	<c:if test="${fswTalkVO.fswTalkIdx ne '' && fswTalkVO.fswTalkIdx ne null}">
		<button id="btnDelete" class="btnBasic_black">삭제</button>
	</c:if>
</sec:authorize>
	<button id="btnCansle" class="btnBasic_black">취소</button>
</div>

