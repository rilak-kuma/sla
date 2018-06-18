<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/ckeditor.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/ckeditor_4.5.10_full/adapters/jquery.js"/>'></script>
<style>
textarea {width:900px; height:420px;}
</style>
<script type="text/javascript">
jQuery(function($){
	//소제목 체크
 	$.validator.addMethod("subTitl", function(value, element){
		var subTitl1 = $("input[name=subTitl1]").val();
		var subTitl2 = $("input[name=subTitl2]").val();
		var subTitl3 = $("input[name=subTitl3]").val();

		return (subTitl1 != '')  || (subTitl2 != '') || (subTitl3 != '');
	}, 'required')

	//등록 or 수정 validate
	$('form[name=newsletterFm]').validate({
		rules: {
			newsLttrNo : "required",
			titl : "required",
			subTitl1 : "subTitl",
			body : "required"
		},
		messages: {
			newsLttrNo : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '제목은'),
			titl : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '제목은'),
			subTitl1 : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '소제목 하나는'),
			body : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '내용은')
		},
		submitHandler: function(form) {

			var url = '<c:out value="/information/newsletter/insertNewsletter.json"/>';
			var submitData = $(form).serializeFormJSON();
			submitData.newsLttrNo = submitData.newsLttrNo+"호";
			submitData.useYn = $("input[name=useYn]:checked").val();

			if("<c:out value='${newsletterVO.newsLttrIdx}' />" != ""){
				url = '<c:out value="/information/newsletter/updateNewsletter.json"/>';
				submitData.newsLttrIdx = "<c:out value='${newsletterVO.newsLttrIdx}' />";
			}

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.href='<c:out value="/information/newsletter/newsletterList.do"/>';
			}

			ajaxSubmit(url, submitData, callback, null);
		}
	});

	//등록 or 수정
	$('#btnConfirm').on('click', function(){
	  	var filePaths = [];
		$("input[name=addFilePath]").each(function(){
			filePaths.push($(this).val());
		});
		
		$("#addFileArea").append("<input type='hidden' name='filePath' value='"+filePaths+"' />");
		
		$("form[name=newsletterFm]").submit();
	});

	//삭제
	$('#btnDelete').on('click', function(){
		var url = '<c:out value="/information/newsletter/deleteNewsletter.json"/>';
		var submitData = {
				newsLttrIdx : '<c:out value='${newsletterVO.newsLttrIdx}' />'
		}

		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href="/information/newsletter/newsletterList.do";
		}

		ajaxSubmit(url, submitData, callback, null);
	});

	//목록
	$('#btnCansle').on('click', function(){
		window.history.back();
	});

	//발행호
	$("#newsLttrNo").on('blur',function(){
		var newsLttrNo = $("#newsLttrNo").val();
		if(newsLttrNo.length > 0){
			/*
			if(newsLttrNo.indexOf("호") < 0 ){
				$("#newsLttrNo").val(newsLttrNo+"호");
			}else{
				$("#newsLttrNo").val(newsLttrNo);
			}
			 */
			$("#newsLttrNo").val(newsLttrNo);
		}else{
			$("#newsLttrNo").val('<c:out value="${newsletterInfo.newsLttrNo }" />');
		}
	});
	//발행호 숫자키만 입력받기
	$("#newsLttrNo").on('keydown',function(e){
		var event = e || window.event;
		var keycode = event.keyCode || e.which;
		if (keycode == 8 || (keycode >= 35 && keycode <= 40) || (keycode >= 46 && keycode <= 57) || (keycode >= 96 && keycode <= 105)) {
		return true;
		} else {
		return false;
		}
	});

	$('#btnEditor')
	.on('click', function(){
		// ckeditor
		$('textarea[name=body]').ckeditor(ckConfig({
			width: 900,
			height: 420
		}));

		$(this).parents('tr:first').remove();

		return false;
	});

	//초기값
	$(document).ready(function(){
		var $form = $("form[name=newsletterFm]");

		$form.find('input[name=newsLttrNo]').val($form.find('input[name=newsLttrNo]').val().replace('호', ''));

		<c:if test="${newsletterVO.useYn eq 'N'}" >
			$form.find('input[name=useYn][value="N"]').prop('checked', true);
		</c:if>

		<c:if test="${newsletterVO.pcYn eq 'Y'}" >
			$form.find('#pcYn').attr("checked",true);
		</c:if>

		<c:if test="${newsletterVO.mblYn eq 'Y'}" >
			$form.find('#mblYn').attr("checked",true);
		</c:if>
	});
	
	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#newsLttrDttm" )
	        .datepicker({
	          defaultDate: "+1w",
	          changeMonth: true,
	          numberOfMonths: 1
	        }).on( "change", function() {
	          from.datepicker( "option", "minDate", getDate( this ) );
	        })
	
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
            var pattern = /(\.|\/)(gif|jpe?g|png)$/i;
            if (!(pattern).test(uploadFile.name)) {
                alert('jpg, gif, jpg, gif 만 가능합니다');
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

    			var fileTxt = "<span class='stit'>"+ file.origFileNm
					//+ file.origFileNm+"<span class='guide'>( W:"+file.wdth+", H:"+file.hgth+" / "+file.fileSize+"byte )</span>"
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

});
</script>

<div class="location_area"><h3>뉴스레터 관리</h3></div>
<div class="subcon_area">
	<form:form commandName="newsletterVO" name="newsletterFm">
		<div class="basicTbl02">
			<table>
				<caption></caption>
				<colgroup>
					<col width="120px"/>
					<col width=""/>
					<col width="120px"/>
					<col width=""/>
					<col width="100px"/>
				</colgroup>
				<tr>
					<th>번호</th>
					<td><c:out value="${newsletterVO.newsLttrIdx }" /></td>
					<th>등록일시</th>
					<td><c:out value="${newsletterVO.creDttm }" /></td>
					<th>등록자</th>
					<td><c:out value="${creMngrMbrNm }" />/<c:out value="${creMngrMbrDpt }" /></td>
				</tr>
				<tr>
					<th>조회</th>
					<td><c:out value="${newsletterVO.hitCnt }" /></td>
					<th>최종수정일시</th>
					<td><c:out value="${newsletterVO.modDttm }" /></td>
					<th>수정자</th>
					<td><c:out value="${modMngrMbrNm }" />/<c:out value="${modMngrMbrDpt }" /></td>
				</tr>
				<tr>
					<th>전시여부</th>
					<td>
						<Strong><input type="radio" name="useYn" value="Y" checked="checked"/>ON</Strong>
						<Strong class="ml_5"><input type="radio" name="useYn" value="N"/>OFF</Strong>
					</td>
					<th></th><td></td>
					<th>스크린</th>
					<td>
						<span><input type="checkbox" id="pcYn" name="pcYn" value="Y"/>PC</span>
						<span class="ml_5"><input type="checkbox" id="mblYn" name="mblYn" value="Y"/>모바일</span>
					</td>
				</tr>
			</table>
		</div>

		<table class="detailTbl">
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>발행호</th>
				<td colspan="3">
					<form:input path="newsLttrNo" placeholder="숫자만 입력 가능"/>
				</td>
			</tr>
			<tr>
				<th>발행일</th>
				<td colspan="3">
					<form:input path="newsLttrDttm" id="newsLttrDttm" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					<form:input path="titl" placeholder="50자 이내 입력" maxlength="50" style="width:50%;" />
				</td>
			</tr>
			<tr>
				<th rowspan="3" >소제목</th>
				<td colspan="3">
					<form:input path="subTitl1" placeholder="15자 이내 입력" maxlength="15" style="width:50%;" />
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<form:input path="subTitl2" placeholder="15자 이내 입력" maxlength="15" style="width:50%;" />
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<form:input path="subTitl3" placeholder="15자 이내 입력" maxlength="15" style="width:50%;" />
				</td>
			</tr>
			<tr>
				<th>이미지 첨부</th>
				<td colspan="2">
					<input type="file" id="addFile" name="reqUploadFile" class="file" data-input="false" /><br/>
					<div id="addFileArea" class="picBox fl">
						<span id="fileName">
							<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
								<span>
									<input type="hidden" name="addFilePath" value="<c:out value='${fileList.filePath }'/>" />
									<a href="#none" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><span class='btnTag'><c:out value="${fileList.origFileNm}" /></span></a>
									<button onclick="$(this).parent('span').remove();" class='btnTag' >X</button>
								</span>
							</c:forEach>
						</span>
					</div>
				</td>
				<td>
					<button id="fileAdd" class="btnBasic_blue">추가</button>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input type='button' id='btnEditor' value='에디터편집' />
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<form:textarea path="body"/>
				</td>
			</tr>
		</table>
	</form:form>
</div>

<div class="btns tac">
<sec:authorize access="hasRole('023007002Y')">
	<button id="btnConfirm" class="btnBasic_blue">확인</button>
	<c:if test="${newsletterVO.newsLttrIdx ne '' && newsletterVO.newsLttrIdx ne null}" >
		<button id="btnDelete" class="btnBasic_black">삭제</button>
	</c:if>
</sec:authorize>
	<button id="btnCansle" class="btnBasic_black">목록</button>
</div>