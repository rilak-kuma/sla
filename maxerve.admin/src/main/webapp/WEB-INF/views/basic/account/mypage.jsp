<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
.newPwd { display: none; }
</style>
<script>
jQuery(function($){
	$('form[name=reqMypage]').validate({
		rules: {
			mngrMbrPwd: "required",
			mngrMbrPwdNew:{
				required: true,
				preventEqual: $('[name=mngrMbrPwd]'),
				pwd: true
			} ,
			mngrMbrPwdConfirm: {
				required: true,
				equalOnly: $('[name=mngrMbrPwdNew]')
			},
			emil: 'email'
		},
		messages: {
			mngrMbrPwd: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '비밀번호는'),
			mngrMbrPwdNew: {
				required: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '새 비밀번호는'),
				preventEqual: getMessage('validator.password.equals.establish'),
				pwd: getMessage('invalid.password.rule')
			},
			mngrMbrPwdConfirm: getMessage('validator.password.not.equal'),
			emil: getMessage('org.hibernate.validator.constraints.Email.message')
		},
		submitHandler: function(form) {
			var url = '<c:out value="/basic/account/updateMyinfo.json"/>';
			var submitData = $(form).serializeFormJSON();

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.reload();
			}

			ajaxSubmit(url, submitData, callback, null);
		}
	});

	$('#btnConfirm').on('click', function(){
		$('form[name=reqMypage]').submit();
	});

	$('#btnChgPwd').on('click', function(){
		$('.newPwd').toggle();

		if ($('.newPwd').is(':visible')) {
// 			$('form[name=reqMypage] input[name=mngrMbrPwdNew]').rules('add', {
// 				required: true,
// 				messages: {
// 					required: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '비밀번호 확인은')
// 				}
// 			});

// 			$('form[name=reqMypage] input[name=mngrMbrPwdConfirm]').rules('add', {
// 				passwordConfirm: true
// 			});

			$('form[name=reqMypage]').find('input[name=mngrMbrPwdNew],input[name=mngrMbrPwdConfirm]')
			.prop('disabled', false);
		} else {
// 			$('form[name=reqMypage] input[name=mngrMbrPwdNew]').rules('remove', 'required');
// 			$('form[name=reqMypage] input[name=mngrMbrPwdConfirm]').rules('remove', 'passwordConfirm');

			$('form[name=reqMypage]').find('input[name=mngrMbrPwdNew],input[name=mngrMbrPwdConfirm]')
			.val('')
			.prop('disabled', true);
		}
	});
});
</script>
<div class="location_area"><h3>정보수정</h3></div>
<div class="subcon_area">
<form name='reqMypage'>
<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th>이름</th>
				<td><c:out value="${info.mngrMbrNm }"/></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><c:out value="${info.mngrMbrId }"/></td>
			</tr>
			<tr>
				<th>비밀번호<span class="red">*</span></th>
				<td><input type="password" name='mngrMbrPwd' placeholder="영문, 숫자 포함한 8자 이상" style="width:50%"/> <button type="button" id='btnChgPwd'>비밀번호 변경</button></td>
			</tr>
			<tr class="newPwd">
				<th>새 비밀번호<span class="red">*</span></th>
				<td><input type="password" name='mngrMbrPwdNew' placeholder="영문, 숫자 포함한 8자 이상" style="width:50%" size="20"/></td>
			</tr>
			<tr class="newPwd">
				<th>새 비밀번호 확인<span class="red">*</span></th>
				<td><input type="password" name='mngrMbrPwdConfirm' placeholder="영문, 숫자 포함한 8자 이상" style="width:50%"/></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type='text' name='emil' value='<c:out value="${info.emil }"/>' style="width:50%" /></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type='text' name='phn' value='<c:out value="${info.phn }"/>' style="width:50%" /></td>
			</tr>
			<tr>
				<th>부서</th>
				<td><input type='text' name='mngrMbrDpt' value='<c:out value="${info.mngrMbrDpt }"/>' style="width:50%" /></td>
			</tr>
		</table>
	</div>
</form>
<!-- 버튼 -->
	<div class="btns tar mb_40">
		<button id='btnConfirm' class="btnBasic_blue">확인</button>
		<button id='btnCancel' class="btnBasic_black">취소</button>
	</div>
</div><!--subcon_area-->