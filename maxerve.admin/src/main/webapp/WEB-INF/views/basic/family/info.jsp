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
<script type="text/javascript">
$(document).ready(function(){
  	
  $('form[name=familyInfoForm]').validate({
		rules: {
			titl		: "required",
			url			: "required",
			ord			: "required"
		},
		messages: {
			titl	: 	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '제목은'),
			url		: 	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '호출경로는'),
			ord		:	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '노출순서는')
		},
		submitHandler: function(form) {
			//배너 이미지 체크
			if($("input[name=filePath]").val() == ""){
			  return alert(getMessage('org.hibernate.validator.constraints.NotEmpty.message', '첨부파일은'));
			}

			var url = '<c:out value="/basic/family/insertOrUpdateFamily.json"/>';
			var submitData = $('form').serializeFormJSON();
			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				$("form[name=searchFm]")
					.attr("action","<c:out value='/basic/family/list.do' />")
					.submit();
			}
			//console.log(submitData);
 			ajaxSubmit(url, submitData, callback, null);
		}
	});
  
	//이미지 파일업로드
	$('#imgFilePath').fileupload({
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
            if (!(/png|jpe?g|gif/i).test(uploadFile.name)) {
                alert('png, jpg, gif 만 가능합니다');
                isValid = false;
            } else if (uploadFile.size > (5*1024*1024)) { // 5mb *4
                alert('파일 용량은 5메가를 초과할 수 없습니다.');
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
    			$("input[name='filePath']").val(file.filePath);
    			$("#bannerImg").html("<span class='btnTag'>"+file.origFileNm+"</span><span class='guide'>( W:"+file.wdth+", H:"+file.hgth+" / "+file.fileSize+"byte )</span>");
    		  	$("#imgFileArea").show();
    		});
		}
	});

	// 파일업로드 이벤트
	$('#btnImg').on('click', function(){
	  console.log($("input[name='filePath']").val());
	  	if($("input[name='filePath']").val()){
	  	  alert('업로드 파일이 이미 존재합니다.');
	  	  return false;
	  	}
		$("#imgFilePath").trigger("click");
	});

	// 파일삭제
	$('#btnFileRemove').on('click', function(){
		$("#imgFileArea").hide();
		$("input[name='filePath']").val('');
		$("#bannerImg").html('');
		$("input[name=info]").val('');
	});
	
	//등록 or 수정
	$('#btnConfirm').on('click', function(){
		$("form[name=familyInfoForm]").submit();
	});

	//취소
	$('#btnListPg').on('click', function(){
		location.href = "/basic/family/list.do";
	});

	//삭제
	$('#btnDelete').on('click', function(){
		if (!confirm(getMessage('confirm.delete'))) {
			return;
		}
 		var url = '<c:out value="/basic/family/deleteFamily.json"/>';
		var submitData = {
				famIdx : '<c:out value="${familyVO.famIdx }"/>'
		}
		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href="/basic/family/list.do";
		}
		$('#btnImg').show();
		ajaxSubmit(url, submitData, callback, null);
	});
	
	//파일
	<c:forEach items='${fileList}' var='f' varStatus="status">
		$("input[name='filePath']").val('${f.filePath}');
		$("#bannerImg").html("<span class='btnTag'>"+('${f.origFileNm}')+"</span><span class='guide'>( W:"+('${f.wdth}')+", H:"+('${f.hgth}')+" / "+('${f.fileSize}')+"byte )</span>");
	  	$("#imgFileArea").show();
	</c:forEach>
});

</script>
<form name="searchFm" method="post"></form>
<div class="location_area"><h3>관련사이트</h3></div>
<div class="subcon_area">
<h4>상세/수정</h4>
<form name="familyInfoForm" id="familyInfoForm" method="post">
<input type="hidden" name="famIdx" value="${familyVO.famIdx }"/>
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>제목<span class="red">*</span></th>
				<td>
				<input type="text" name="titl" id="titl" value="${familyVO.titl }">
				</td>
			</tr>
				<tr>
				<th>URL<span class="red">*</span></th>
				<td>
					<input type="text" name="url" id="url" value="${familyVO.url }" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<th>노출순서<span class="red">*</span></th>
				<td>
				<select name="ord" id="ord">
					<%-- <c:forEach begin="1" end="${ord }" var="idx" varStatus="status">
						<option value="${idx }"  <c:if test="${familyVO.ord ne null && familyVO.ord eq idx }">selected=selected</c:if>>${idx}</option>
					</c:forEach> --%>
					<c:forEach begin="1" end="${ord+1 }" var="idx" varStatus="status">
						<option value="${idx }" <c:if test="${idx eq familyVO.ord}"> selected="selected" </c:if>>${idx}</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<th>이미지등록<span class="red">*</span></th>
				<td>
					<div id="div01" >
						<p><button id="btnImg" class="btnBasic">이미지등록</button><span class="guide" id="imgGuide"></span></p>
						<input type="file" id="imgFilePath" name="reqUploadFile" class="file" data-input="false" />
						<input type="hidden" name="filePath" />
						<div id="imgFileArea" class="picBox fl">
							<span id='bannerImg' style="cursor:pointer">
							</span>
							<button id="btnFileRemove" class="btnTag">X</button><!-- 등록이미지 삭제버튼 -->
							<!-- <p><input type="text" name="info" placeholder="이미지정보를 입력하세요."  maxlength="200" style="width:100%"/></p> -->
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</form>

<div class="btns_02 tar">
<%-- <sec:authorize access="hasRole('023002001Y')"> --%>
	<button id="btnConfirm" class="btnBasic_blue">확인</button>
	<c:if test="${familyVO.famIdx ne null && familyVO.famIdx ne ''}" >
		<button id="btnDelete" class="btnBasic_black">삭제</button>
	</c:if>
<%-- </sec:authorize> --%>
	<button id="btnListPg" class="btnBasic_black">취소</button>
</div>