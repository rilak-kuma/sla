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
jQuery(function($){
	//addMethod
/*  	$.validator.addMethod('fileCheck', function(value, element){
 		alert($("#span#bannerImg").text());
		return $("span#bannerImg").text().trim() != "";
	}, 'required'); */

	$('form[name=bannerFm]').validate({
		rules: {
			plfCd		: "required",
			ord			: "required",
			titl		: "required",
			info		: "required",
			useYn 		: "required",
			useSrtDt	: "required",
			useEndDt	: "required"
		},
		messages: {
			plfCd	:	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '대상 선택은'),
			ord		:	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '노출순서는'),
			titl	: 	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '제목은'),
			info	: 	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '배너설명은'),
			useYn	: 	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '전시 여부는'),
			useSrtDt	:	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '전시기간 시작일은'),
			useEndDt	:	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '전시기간 종료일은')
		},
		submitHandler: function(form) {
			//배너 이미지 체크
			if($("input[name=filePath]").val() == "") return alert(getMessage('org.hibernate.validator.constraints.NotEmpty.message', '첨부파일은'));

			var url = '<c:out value="/banner/insertMainBanner.json"/>';
			var submitData = $(form).serializeFormJSON();
			if ("<c:out value='${bannerVO.bnnrIdx}' />" != ""){
				submitData.bnnrIdx = '<c:out value="${bannerVO.bnnrIdx }"/>';
				url = '<c:out value="/banner/updateMainBanner.json"/>';
			}
			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				$("form[name=searchFm]").append("<input type='text' name='plfCd' value='"+$("input[name=plfCd]:checked").val()+"' /> />")
				$("form[name=searchFm]")
					.attr("action","<c:out value='/banner/mainBannerList.do' />")
					.submit();
			}
			//console.log(submitData);
 			ajaxSubmit(url, submitData, callback, null);
		}
	});

	//등록 or 수정
	$('#btnConfirm').on('click', function(){
		$("form[name=bannerFm]").submit();
	});

	//취소
	$('#btnListPg').on('click', function(){
		window.history.back();
	});

	//삭제
	$('#btnDelete').on('click', function(){
		if (!confirm(getMessage('confirm.delete'))) {
			return;
		}
 		var url = '<c:out value="/banner/deleteInnoBanner.json"/>';
		var submitData = {
				bnnrIdx : '<c:out value="${bannerVO.bnnrIdx }"/>'
		}
		var callback = function(data) {
			alert(getMessage('msg.deleted'));
			location.href="/banner/mainBannerList.do";
		}
		ajaxSubmit(url, submitData, callback, null);
	});

	//달력
	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#useSrtDt" )
	        .datepicker({
	          defaultDate: "+1w",
	          changeMonth: true,
	          numberOfMonths: 1
	        }).on( "change", function() {
	          to.datepicker( "option", "minDate", getDate( this ) );
	        }),
	      to = $( "#useEndDt" ).datepicker({
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

	//기간없음 선택시
	$('#always').on('click', function(){
		if($('#always').is(":checked") == true){
			$( "#useSrtDt" ).val("2016.01.01");
			$( "#useEndDt" ).val("9999.01.01");
			$( "#useLmtYn" ).val("Y");
		}else{
			$( "#useSrtDt" ).val("");
			$( "#useEndDt" ).val("");
			$( "#useLmtYn" ).val("N");
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
            } else if (uploadFile.size > (20*1024*1024)) { // 5mb *4
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
    			$("input[name='filePath']").val(file.filePath);
    			$("#bannerImg").html("<span class='btnTag'>"+file.origFileNm+"</span><span class='guide'>( W:"+file.wdth+", H:"+file.hgth+" / "+file.fileSize+"byte )</span>");
    			$("#imgFileArea").show();
    		});
		}
	});

	// 파일업로드 이벤트
	$('#btnImg').on('click', function(){
		fn_fileSelect('imgFilePath');
	});

	// 파일삭제
	$('#btnFileRemove').on('click', function(){
		$("#imgFileArea").hide();
		$("input[name='filePath']").val('');
		$("#bannerImg").html('');
		$("input[name=info]").val('');
	});

	//초기값
	$(document).ready(function(){
		if("<c:out value='${bannerVO.bnnrIdx }' />" != ""){

			$("input[name=plfCd]").each(function(index){
				if($(this).val() == "<c:out value='${bannerVO.plfCd }' />"){
					$(this).trigger("click");
				}
			});

			if("<c:out value='${bannerVO.useLmtYn}' />" == "Y") $('#always').trigger("click");

			if("<c:out value='${fileInfo.filePath}' />" != ""){
				//$("#imgBnn").trigger("click");
				$("input[name='filePath']").val("<c:out value='${fileInfo.filePath}' />");
				$("#bannerImg").html("<span class='btnTag'><c:out value='${fileInfo.origFileNm}' /></span><span class='guide'>( W:<c:out value='${fileInfo.wdth}'/>, H:<c:out value='${fileInfo.hgth}'/>/<c:out value='${fileInfo.fileSize}'/>byte )</span>");
				$("#imgFileArea").show();
			}
			<c:if test='${bannerVO.newWndYn ne "" }'>
				$('input[name=newWndYn][value=<c:out value="${bannerVO.newWndYn}"/>]').prop('checked', true);
			</c:if>
			$('input[name=useYn][value=<c:out value="${bannerVO.useYn}"/>]').prop('checked', true);
			$("input[name=titl]").val('<c:out value="${bannerVO.titl }"/>');
			$("input[name=info]").val('<c:out value="${bannerVO.info }"/>');
			$("input[name=link]").val('<c:out value="${bannerVO.link }" escapeXml="false"/>');
			$("input[name=useSrtDt]").val('<c:out value="${bannerVO.useSrtDt}"/>');
			$("input[name=useEndDt]").val('<c:out value="${bannerVO.useEndDt}"/>');
			$("select[name=ord]").val("<c:out value='${bannerVO.ord eq null? 1 : bannerVO.ord}' />");

		}else{
			$("#plfCd_0").trigger("click");
		}
	});

});

//파일태그 선택
function fn_fileSelect(fileId){
	$("#" + fileId).trigger("click");
}

//대상 플랫폼 선택(노출순서 셀렉트박스 변경)
function fn_plfCheck(valueA, valueB, plf){

	if(plf == '모바일'){
		$("#imgGuide").text('( 1920*1078/20MB (협의중) )');
	}else{
		$("#imgGuide").text('( 1920*1078/20MB 이하 )');
	}

	$( "#plfCd" ).val(valueA);

	var selectTxt	=	"<select id='ord' name='ord'>";
	for(var idx = 1 ; idx <= valueB ; idx++){
		selectTxt	+=	"<option value='"+idx+"'>"+idx+"</option>";
	}
		selectTxt	+=	"</select>";

	$("#ordDiv").html(selectTxt);
}

</script>
<form name="searchFm" method="post"></form>

<div class="location_area"><h3>메인 배너</h3></div>

<div class="subcon_area">
<h4>상세/수정</h4>
<form name="bannerFm" method="post">
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>대상<span class="red">*</span></th>
				<td>
				<c:forEach items="${ordList}" var="ordList" varStatus="varStatus">
					<input type="radio" id="plfCd_${varStatus.index }" name="plfCd" value="${ordList.plfCd}" onclick="fn_plfCheck('${ordList.plfCd}','${ordList.ord}','${ordList.plfCdNm }')" />
					<c:out value="${ordList.plfCdNm }"/>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th>노출순서<span class="red">*</span></th>
				<td id="ordDiv">

				</td>
			</tr>
			<tr>
				<th>제목<span class="red">*</span></th>
				<td>
					<input type="text" name="titl"  maxlength="50"/>
				</td>
			</tr>
			<tr>
				<th>배너등록<span class="red">*</span></th>
				<td>
					<!-- <input type="radio" id="imgBnn" value="01" name="radioB" checked="checked"/>이미지배너로 등록<br/> -->
					<!-- <input type="radio" id="bbsBnn" value="02" name="radioB" />게시물로 등록<br/> -->
					<div id="div01" >
						<p><button id="btnImg" class="btnBasic">이미지등록</button><span class="guide" id="imgGuide"></span></p>
						<input type="file" id="imgFilePath" name="reqUploadFile" class="file" data-input="false" />
						<input type="hidden" name="filePath" />
						<div id="imgFileArea" class="picBox fl">
							<span id='bannerImg' onclick="fileDownload('<c:out value="${fileList.filePath}" />')" style="cursor:pointer">
							</span>
							<button id="btnFileRemove" class="btnTag">X</button><!-- 등록이미지 삭제버튼 -->
							<p><input type="text" name="info" placeholder="배너정보를 입력하세요."  maxlength="200" style="width:100%"/></p>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th>링크</th>
				<td>
					<input type="text" name="link" />
				</td>
			</tr>
			<tr>
				<th>타겟</th>
				<td>
					<input type="radio" name="newWndYn" value="N" />페이지로 이동
					<input type="radio" name="newWndYn" value="Y" />새창으로 열림
				</td>
			</tr>
			<tr>
				<th>전시여부<span class="red">*</span></th>
				<td>
					<input type="radio" name="useYn" value="Y" checked="checked" />전시
					<input type="radio" name="useYn" value="N" />미전시
				</td>
			</tr>
			<tr>
				<th>전시기간<span class="red">*</span></th>
				<td>
					<input type="text" id="useSrtDt" name="useSrtDt" value="<c:out value="${bannerVO.useSrtDt}"/>" readonly="readonly"/>부터
					<input type="text" id="useEndDt" name="useEndDt" value="<c:out value="${bannerVO.useEndDt}"/>" readonly="readonly"/> 까지<br/>
					<input type="checkbox" id="always" /> 기간설정없음(무기한)
					<input type="hidden" id="useLmtYn" name="useLmtYn" value="N"/>
				</td>
			</tr>
		</table>
	</div>
</form>
</div>

<div class="btns_02 tar">
<sec:authorize access="hasRole('023002001Y')">
	<button id="btnConfirm" class="btnBasic_blue">확인</button>
	<c:if test="${bannerVO.bnnrIdx ne null && bannerVO.bnnrIdx ne ''}" >
		<button id="btnDelete" class="btnBasic_black">삭제</button>
	</c:if>
</sec:authorize>
	<button id="btnListPg" class="btnBasic_black">취소</button>
</div>