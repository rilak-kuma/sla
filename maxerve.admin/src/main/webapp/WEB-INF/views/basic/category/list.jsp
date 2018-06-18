<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
#sample {
	display: none;
}
</style>
<script>
// 폼리셋
function resetForm() {
	$('form[name=form1]').get(0).reset();
	fn_fileRemove();
}

//파일태그 선택
function fn_fileSelect(){
	$('input[name=reqUploadFile]').trigger("click");
}

// 파일삭제
function fn_fileRemove() {
	$("#imgFileArea").empty();
}

//파일업로드 관련
function fn_fileupload(){
	$('body').append('<input class="hidden" data-input="false" type="file" name="reqUploadFile"/>');

	$('input[name=reqUploadFile]').fileupload({
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
            } else if (uploadFile.size > (20*1024*1024)) {
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
    			dispImg(file.filePath, file.origFileNm);
    		});
		}
	});
}

// 첨부파일 표시
function dispImg(filePath, origFileNm) {
	fn_fileRemove();

	var $sample = $('#sample .fileinfo').clone(true);
	$sample.attr('data-file-path', filePath);
	$sample.text(origFileNm);
	$sample.append('<span>x</span>');

	$('#imgFileArea').append($sample);
}

// 카테고리 타입 이동
function goCategoryType(ctgrTypCd) {
	location.href = '?ctgrTypCd=' + ctgrTypCd;
}

jQuery(function($){
	var ctgrTypCd = '<c:out value="${categoryDTO.ctgrTypCd}"/>';

	// validate
	$.validator.addMethod('imgCheck', function(value, element){
		return this.optional(element) || $('form[name=form1]').is(':has(.fileinfo)');
	}, 'img check');

	$('form[name=form1]').validate({
		ignore: [],
		rules: {
			ctgrNm: {
				required: true
				<c:if test="${categoryDTO.ctgrTypCd ne '009004' }">
				,imgCheck: true
				</c:if>
			}
		},
		messages: {
			ctgrNm: {
				required: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '카테고리명은'),
				imgCheck: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '이미지는')
			}
		},
		submitHandler: function(form) {
			var url;
			var submitData = $(form).serializeFormJSON();
			var ctgrTypCd = $('.tabmenu li.on').attr('data-ctgrTypCd');

			if ($('.left_box .add').is('.hidden')) {
				url = '<c:out value="/basic/category/update.json"/>';

				$.extend(submitData, {
					ctgrIdx: $('.left_box li.on').attr('data-ctgrIdx')
				});
			} else {
				url = '<c:out value="/basic/category/insert.json"/>';
			}

			submitData.ctgrImg = $(form).find('.fileinfo[data-file-path]').attr('data-file-path');

			var callback = function(data) {
				alert(getMessage('msg.save.success'));

				if (!$('.left_box .add').is('.hidden')) {
					location.reload();
				}
			}

			$.extend(submitData, {
				ctgrTypCd: ctgrTypCd
			});

 			ajaxSubmit(url, submitData, callback, null);
		}
	});

	// 추가
	$('#btnAdd').on('click', function(){
		if (!$('.left_box .add').is('.hidden')) {
			return;
		}

		$('.left_box .add').removeClass('hidden');

// 		var $li = $('#sample #sample1 li').clone(true);
// 		$li.removeAttr('id');

// 		$('.left_box ul').append($li);

		resetForm();

		// 카테고리 비선택
		$('.left_box li').removeClass('on');

		// 편집박스오픈
		$('.right_box').removeClass('hidden');

		return false;
	});

	// 카테고리 선택
	$('.left_box').on('click', 'li', function(){
		$('.left_box .add').addClass('hidden');

		resetForm();

		$('.left_box li').removeClass('on');
		$(this).addClass('on');

		ajaxSubmit('<c:url value="/basic/category/info.json"/>', {
			ctgrIdx: $(this).attr('data-ctgrIdx')
		}, function(data){
			console.log(data);

			// 편집박스오픈
			$('.right_box').removeClass('hidden');

			var $form = $('form[name=form1]');

			$form.find('input[name=ctgrNm]').val(data.categoryInfo.ctgrNm);

			if(data.fileInfo){
				dispImg(data.fileInfo.filePath, data.fileInfo.origFileNm);
			}


			$form.find('[name=spoYn]').val(data.categoryInfo.spoYn);
			$form.find('[name=refTypCd]').val(data.categoryInfo.refTypCd);
		});
	})
	// 카테고리명 입력
	.on('keydown keyup', 'input:text', function(){
		var _this = this;

		setTimeout(function(){
			$('form[name=form1] input[name=ctgrNm]').val($(_this).val());
		}, 300);
	});

	// 카테고리명 입력
	$('form[name=form1]').on('keydown keyup', 'input[name=ctgrNm]', function(){
		if ($('.left_box .add').is('.hidden')) {
			return;
		}

		var _this = this;

		setTimeout(function(){
			$('.left_box .add input:text').val($(_this).val());
		}, 300);
	})
	// 첨부삭제
	.on('click', '.fileinfo span', function(){
		fn_fileRemove();
	});

	// 저장
	$('#btnSave').on('click', function(){
		$('form[name=form1]').submit();
	});

	// 파일업로드
	$('#btnImg').on('click', function(){
		fn_fileSelect();

		return false;
	});

	// 삭제
	$('#btnDelete').on('click', function(){
		if (!confirm(getMessage('confirm.delete'))) {
			return;
		}

		ajaxSubmit('<c:url value="/basic/category/delete.json"/>', {
			ctgrTypCd: $('.tabmenu li.on').attr('data-ctgrTypCd'),
			ctgrIdx: $('.left_box li.on').attr('data-ctgrIdx')
		}, function(){
			alert(getMessage('msg.deleted'));
			location.reload();
		});
	});

	// 위
	$('#btnUp').on('click', function(){
		if (!$('.left_box').is(':has(li.on)')) {
			return;
		}

		var ctgrOrd = $('.left_box li').index($('.left_box li.on'));
		if (ctgrOrd == 0) {
			return;
		}

		ajaxSubmit('<c:url value="/basic/category/update_order.json"/>', {
			ctgrIdx: $('.left_box li.on').attr('data-ctgrIdx'),
			ctgrOrd: ctgrOrd,
			tCtgrIdx: $('.left_box li').eq(ctgrOrd-1).attr('data-ctgrIdx'),
			tCtgrOrd: ctgrOrd+1
		}, function(){
			$('.left_box li.on').after($('.left_box li').eq(ctgrOrd-1));
		});

		return false;
	});

	// 아래
	$('#btnDown').on('click', function(){
		if (!$('.left_box').is(':has(li.on)')) {
			return;
		}

		var ctgrOrd = $('.left_box li').index($('.left_box li.on'));
		if (ctgrOrd == $('.left_box li').length-1) {
			return;
		}

		ajaxSubmit('<c:url value="/basic/category/update_order.json"/>', {
			ctgrIdx: $('.left_box li.on').attr('data-ctgrIdx'),
			ctgrOrd: ctgrOrd+2,
			tCtgrIdx: $('.left_box li').eq(ctgrOrd+1).attr('data-ctgrIdx'),
			tCtgrOrd: ctgrOrd+1
		}, function(){
			$('.left_box li.on').before($('.left_box li').eq(ctgrOrd+1));
		});

		return false;
	});

	// 탭이동
	$('.tabmenu').on('click', 'li', function(){
		goCategoryType($(this).attr('data-ctgrTypCd'));

		return false;
	});

	// 탭설정
	$('.tabmenu li[data-ctgrTypCd=' + ctgrTypCd + ']').addClass('on');

	// 파일업로드 초기화
	fn_fileupload();
});
</script>
<div class="location_area"><h3>카테고리관리</h3></div>
<div class="subcon_area">
	<ul class="tabmenu">
	<c:forEach items="${typeList }" var="item">
		<li data-ctgrTypCd='<c:out value="${item.cmmnCd }"/>'><a href='#'><c:out value="${item.cmmnCdNm }"/></a></li>
	</c:forEach>
	</ul>
	<div class="cate_wrap">
		<div class='left_box'>
		<sec:authorize access="hasRole('023001003Y')">
			<div class="subtop">
				<button type="button" class="btnBasic" id='btnAdd'>추가</button>
				<div class="fr">
					<a href="#" id='btnUp'><img src="../../img/ico_up.png" alt="위로"></a>
					<a href="#" id='btnDown'><img src="../../img/ico_down.png" alt="아래로"></a>
				</div>
			</div>
		</sec:authorize>
			<ul class="menu_add">
			<c:forEach items="${categoryDTOList }" var="item">
				<li data-ctgrIdx='${item.ctgrIdx }'><a href='#'><c:out value="${item.ctgrNm }"/></a></li>
			</c:forEach>
			</ul>
			<div class="add hidden">
				<span>* 카테고리명을 입력하세요.</span>
				<input type="text" style="width:100%;">
			</div>
		</div>
		<div class='right_box hidden'>
			<form name='form1'>
			<c:if test="${categoryDTO.ctgrTypCd ne '009004' }">
				<input type='hidden' name='refTypCd' value='000'/>
			</c:if>
				<div class="basicTbl02">
					<table>
					<caption></caption>
					<colgroup>
						<col width="150px"/>
						<col width=""/>
					</colgroup>
						<tr>
							<th>카테고리명<span class="red">*</span></th>
							<td><input type="text" placeholder="" style="width:70%" name='ctgrNm'/></td>
						</tr>

					<c:if test="${categoryDTO.ctgrTypCd ne '009004' }">
						<tr>
							<th>이미지<span class="red">*</span></th>
							<td colspan="3">
								<button type="submit" class="btnBasic" id='btnImg'>이미지등록</button><span class="guide">( 135 * 135 )</span>
								<p class="mt_10" id='imgFileArea'></p>
							</td>
						</tr>
					</c:if>

					<c:if test="${categoryDTO.ctgrTypCd eq '009002' }">
						<tr>
							<th>후원설정<span class="red">*</span></th>
							<td>
								<select name='spoYn'>
									<option value='N'>비후원</option>
									<option value='Y'>후원</option>
								</select>
							</td>
						</tr>
					</c:if>
					<c:if test="${categoryDTO.ctgrTypCd eq '009004' }">
						<tr>
							<th>자료유형<span class="red">*</span></th>
							<td>
								<select name='refTypCd'>
								<c:forEach items="${commonCodeDTOList }" var="item">
									<option value='${item.cmmnCd }'><c:out value="${item.cmmnCdNm }"/></option>
								</c:forEach>
								</select>
							</td>
						</tr>
					</c:if>
					</table>
				</div>
				<!-- 버튼 -->
			<sec:authorize access="hasRole('023001003Y')">
				<div class="btns tar mb_40">
					<button type="button" class="btnBasic_blue" id='btnSave'>저장</button>
					<button type="button" class="btnBasic_black" id='btnDelete'>삭제</button>
				</div>
			</sec:authorize>
			</form>
		</div>
	</div>
</div>
<div id='sample'>
	<a href="#" class="fileinfo">썸네일이미지.jpg<span>x</span></a>
</div>