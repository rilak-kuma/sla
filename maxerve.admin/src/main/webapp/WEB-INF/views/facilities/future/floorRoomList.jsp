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
//입주단체 팝업에서 받은 데이터 처리
function fn_getData(val_01, val_02, val_03){
	var idx = $('#floorBox').attr('data-ix');
	$('#floorBox tr').each(function(k){
		if(k == idx){
			$(this).find('input[name=locIdx]').val(val_01);
			$(this).find('input[name=fctNm]').val(val_02);
			$(this).find('input[name=url]').val(val_03);
		}
	});
}

//파일 다운로드
function fn_fileDown(){
	fileDownload("<c:out value='${fileInfo.filePath }'/>");
}

jQuery(function($){
	//validate
	$('form[name=floorFm]').validate({
		rules: {
			filePath : "required",
			fileInfo : "required"
		},
		messages: {
			filePath : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '층별안내이미지는'),
			fileInfo : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '이미지정보는')
		},
		submitHandler: function(form) {
			var url = '<c:out value="/facilities/future/updateFloorRoom.json"/>';
			var submitData = {
					flr : "<c:out value='${searchVO.flr}' />",
					filePath : $("input[name=filePath]").val(),
					fileInfo : $("input[name=fileInfo]").val(),
					roomList : []
				}

			$('#floorBox tr').each(function(k){

				if($(this).find('select[name=fctTypCd]').val() == '031002'){
					submitData.roomList.push({
						roomNm: $(this).find('input[name=roomNm]').val(),
						flr : "<c:out value='${searchVO.flr}' />",
						fctTypCd: $(this).find('select[name=fctTypCd]').val(),
						locIdx : $(this).find('select[name=locIdx]').val(),
						fctNm: "",
						fctCord : "",
						url: "",
						useYn: $(this).find('input#useYn:checked').val()
					});
				}else{
					submitData.roomList.push({
						roomNm: $(this).find('input[name=roomNm]').val(),
						flr : "<c:out value='${searchVO.flr}' />",
						fctTypCd: $(this).find('select[name=fctTypCd]').val(),
						locIdx : $(this).find('input[name=locIdx]').val(),
						fctNm: $(this).find('input[name=fctNm]').val(),
						fctCord : "",
						url: $(this).find('input[name=url]').val(),
						useYn: $(this).find('input#useYn:checked').val()
					});
				}
			});

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.reload();
			}
//  			console.log(submitData);
			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	//저장
	$('#btnConfirm').on('click', function(){
		$("form[name=floorFm]").submit();

		return false;
	});

	//층별 호실 조회
	$('.subcon_area .tabmenu')
	.on('click', 'li', function(){
		var ix = $(this).parent().find('li').index(this);

		var $form = $("form[name=searchFm]");
		$form.append("<input type='hidden' name='flr' value='"+(ix+2)+"' />");
		$form.action = '<c:out value="/facilities/future/floorRoomList.do"/>';
		$form.submit();
	});

	//사용여부
	$(".useYn").on('click',function(){
		if($(this).val() == 'Y'){
			$(this).next().removeAttr("checked");
		}
		if($(this).val() == 'N'){
			$(this).prev().removeAttr("checked");
		}
	});

	//시설구분
	$('select[name=fctTypCd]').on('change', function(){
		var ix = $('#floorBox tr').index($(this).parents('tr:first'));

		if($(this).val() == '031002'){
			$element = $('#samples #select').clone(true).removeAttr('id');
			$('#floorBox tr').each(function(k){
				if(k == ix){
					$(this).find('#fctNm').html($element);
					$(this).find('#url').html("");
				}
			});
		}else{
			$element_input_fctNm = $('#samples #floorElement #fctNm').clone(true);
			$element_input_url = $('#samples #floorElement #url').clone(true);

			if($(this).val() == '031003'){
				//alert($element_input_fctNm.html());
				$element_input_fctNm.find('input[name=fctNm]').removeAttr("class");
				$element_input_fctNm.find('input[name=fctNm]').removeAttr("readonly");
				$element_input_url.find('input[name=url]').removeAttr("readonly");
			}

			$('#floorBox tr').each(function(k){
				if(k == ix){
					$(this).find('#fctNm').html($element_input_fctNm);
					$(this).find('#url').html($element_input_url);
				}
			});
		}
	});

	//입주단체 팝업호출
	$('#floorBox').on('click', '.fctClass', function(){
		var ix = $('#floorBox tr').index($(this).parents('tr:first'));
		$('#floorBox').attr('data-ix', ix);
		window.open("/member/moveInOztnPopUp.do","입주단체 조회", 'width=400, height=600, scrollbars=yes');
	});

	//파일업로드
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
    			$("#floorImg").text(file.origFileNm+"(W:"+file.wdth+", H:"+file.hgth+" / "+file.fileSize+"byte)");
    			$("#imgFileArea").show();
    		});
		}
	});

	// 파일업로드
	$('#btnImg').on('click', function(){
		fn_fileSelect('imgFilePath');
	});

	//파일태그 선택
	function fn_fileSelect(fileId){
		$("#" + fileId).trigger("click");
	}

	$('#btnSerarchMap').on('click', function(){
		window.open('<c:url value="/facilities/future/floorAirView.do?flr=${searchVO.flr}"/>',"입주단체 조회","400","600");
	});

	//초기값
	$(document).ready(function(){
		$("#btnSerarchMap").text("미래청"+"<c:out value='${searchVO.flr}' />층 조감도");
		$("#spanFlrInfo").html("층별안내 이미지 "+"(미래청 <c:out value='${searchVO.flr}' />층)");
		$("input[name=filePath]").val("<c:out value='${fileInfo.filePath }'/>");

		<c:forEach items="${floorRoomList}" var="item">
			$element = $('#samples #floorElement').clone(true).removeAttr('id');

			<c:forEach var="fctTypes" items="${fctTypes }" >
				$element.find('select[name=fctTypCd]').append('<option value=<c:out value="${fctTypes.cmmnCd }" /> ><c:out value="${fctTypes.cmmnCdNm }" /></option>');
			</c:forEach>

			$element.find('input[name=roomNm]').val('<c:out value="${item.roomNm}" />');

			<c:if test="${item.fctTypCd eq '031002'}" >
				$select_element = $('#samples #select').clone(true).removeAttr('id');
				$element.find('#fctNm').html($select_element);
				$element.find('input[name=url]').parent().html('');
				$select_element.find('option[value=<c:out value="${item.locIdx}"/>]').prop('selected', true);
			</c:if>

			<c:if test="${item.fctTypCd ne '031002'}" >
				$element.find('input[name=locIdx]').val('<c:out value="${item.locIdx}" />');
				$element.find('input[name=fctNm]').val('<c:out value="${item.fctNm}"/>');
				$element.find('input[name=url]').val('<c:out value="${item.url}"/>');
			</c:if>

			<c:if test="${item.fctTypCd eq '031003'}" >
				$element.find('input[name=fctNm]').removeAttr("class");
				$element.find('input[name=fctNm]').removeAttr("readonly");
				$element.find('input[name=url]').removeAttr("readonly");
			</c:if>

			$element.find('select[name=fctTypCd] option[value=<c:out value="${item.fctTypCd}"/>]').prop('selected', true);
			$element.find('input#useYn[value=<c:out value="${item.useYn}"/>]').prop('checked', true);

			$('#floorBox').append($element);
		</c:forEach>

		<c:if test="${floorInfo.filePath ne ''}" >
			$(".picBox").show();
		</c:if>
	});
});



</script>
<form name="searchFm" method="get" ></form>

<div class="location_area"><h3>미래청 층별관리</h3></div>

<div class="subcon_area">

	<h4>층별 호실 정보</h4>

	<!-- 조회 테이블 -->
	<!-- 탭메뉴 -->
	<ul class="tabmenu">
	<c:forEach varStatus="varStatus" step="1" begin="2" end="6" >
	<c:set var="c">
		<c:if test="${searchVO.flr eq varStatus.index }"> class='on'</c:if>
	</c:set>
		<li ${c }><a href="#"><c:out value="${varStatus.index }" />층</a></li>
	</c:forEach>
	</ul>

	<button id='btnSerarchMap' class="btnBasic"></button>

	<form name="floorFm" method="post">
		<div class="basicTbl02">
			<!-- 신청형 테이블 -->
			<div class="basicTbl02">
				<table>
					<caption></caption>
					<colgroup>
						<col width="250px"/>
						<col width=""/>
					</colgroup>
					<tr>
						<th id="spanFlrInfo"></th>
						<td>
							<button id="btnImg" class="btnBasic">이미지등록</button>
							<input type="file" id="imgFilePath" name="reqUploadFile" class="file" data-input="false" />
							<input type="hidden" name="filePath" />
							<div id="imgFileArea" class="picBox fl">
								<a href="javascript:fn_fileDown();" id='floorImg' class='btnTag'><c:out value="${fileInfo.origFileNm }"/></a>
							</div>
							<input type="text" name="fileInfo" value="<c:out value='${floorInfo.fileInfo }' />" maxlength="200"/>
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 신청형 테이블 -->
		<div class="basicTbl02">

			<table>
				<thead>
					<tr>
						<th>호실</th>
						<th>시설구분</th>
						<th>명칭</th>
						<th>연결정보</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody id='floorBox'>
				</tbody>
			</table>
		</div>
	</form>

<sec:authorize access="hasRole('023008001Y')">
	<!-- 버튼 -->
	<div class="btns tar">
		<button id="btnConfirm" class="btnBasic_blue mb_10">저장</button>
	</div>
</sec:authorize>

</div>

<div id='samples' class='hidden'>
	<table>
		<tr id='floorElement'>
			<th><input type="text" name="roomNm" readonly="readonly" style='border:0;' size='10'/></th>
			<td>
				<select name="fctTypCd" >
				</select>
			</td>
			<td id="fctNm">
				<input type="hidden" name="locIdx" readonly="readonly" />
				<input type="text" class="fctClass" name="fctNm" readonly="readonly" />
			</td>
			<td id="url"><input type="text" name="url" readonly="readonly" /></td>
			<td>
				<input type="radio" id="useYn" class="useYn" value="Y" />ON
				<input type="radio" id="useYn" class="useYn" value="N" />OFF
			</td>
		</tr>
	</table>
	<div>
		<select id="select" name="locIdx">
			<c:forEach var="seminaList" items="${seminaList }" >
				<option value='<c:out value="${seminaList.fctMstIdx}" />'><c:out value="${seminaList.fctNm }" /></option>
			</c:forEach>
		</select>
	</div>
</div>