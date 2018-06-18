<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
function goList() {
	$('form[name=form]')
	.attr('action', '<c:url value="/basic/account/list.do"/>')
	.submit();
}

jQuery(function($){
	var mngrMbrIdx = '<c:out value="${memberInfo.mngrMbrIdx}"/>';

	$.validator.addMethod('idCheck', function(value, element){
		var saved = $(document).data('idCheck');

		return this.optional(element) || (saved && saved == $('input[name=mngrMbrId]').val());
	}, 'dual check');

	$('form[name=form1]').validate({
		rules: {
			mngrMbrNm: "required",
			mngrMbrId: {
				required: true,
				idCheck: true
			},
			emil: 'email',
			wrtYn: 'required',
			useYn: 'required'
		},
		messages: {
			mngrMbrNm: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '이름은'),
			mngrMbrId: {
				required: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '아이디는'),
				idCheck: getMessage('invalid.idCheck.rule')
			},
			emil: getMessage('org.hibernate.validator.constraints.Email.message'),
			wrtYn: getMessage('invalid.auth.require'),
			useYn: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '사용여부는')
		},
		submitHandler: function(form) {
			var url;
			var submitData = $(form).serializeFormJSON();

			if (mngrMbrIdx) {
				url = '<c:out value="/basic/account/update.json"/>';
				submitData.mngrMbrIdx = mngrMbrIdx;
			} else {
				url = '<c:out value="/basic/account/insert.json"/>';
			}

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				goList();
			}

			delete submitData['wrtYn'];
			delete submitData['mngrMbrPwdConfirm'];

			var mngrMnuList = [];
			$('input[name=wrtYn]:checked').each(function(){
				var mnuCd = $(this).attr('data-cd');

				if ($(this).val() == 'Y') {
					mngrMnuList.push({
						mnuCd: mnuCd,
						wrtYn: 'Y'
					});
				} else {
					var arr = $.grep(mngrMnuList, function(v){
						return v.mnuCd == mnuCd;
					});

					if (!arr.length) {
						mngrMnuList.push({
							mnuCd: mnuCd,
							wrtYn: 'N'
						});
					}
				}
			});

			submitData['mngrMnuList'] = mngrMnuList;

			console.log(submitData);

 			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	if (mngrMbrIdx) {
		var url = '<c:out value="/basic/account/menu_auth_list.json"/>';
		var submitData = {
			mngrMbrIdx: mngrMbrIdx
		};

		var callback = function(data) {
			$(data.menuAuthList).each(function(k,v){
				$('input[name=wrtYn][data-cd=' + v.mnuCd + '][value=' + v.wrtYn + ']').prop('checked', true);
			});
		}

		ajaxSubmit(url, submitData, callback, null);
	} else {
		$('input[name=mngrMbrPwd]').rules('add', {
			required: true,
			pwd: true,
			messages: {
				required: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '비밀번호는'),
				pwd: getMessage('invalid.password.rule')
			}
		});

		$('input[name=mngrMbrPwdConfirm]').rules('add', {
			required: true,
			equalOnly: $('[name=mngrMbrPwd]'),
			messages: {
				required: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '비밀번호 확인은'),
				equalOnly: getMessage('validator.password.not.equal')
			}
		});
	}



	$('#btnConfirm').on('click', function(){
		$('form[name=form1]').submit();
	});

	$('#btnCheckId').on('click', function(){
		$(document).removeData('idCheck');

		var mngrMbrId = $('input[name=mngrMbrId]').val();

		if (!mngrMbrId) {
			alert(getMessage('org.hibernate.validator.constraints.NotEmpty.message', '아이디는'));
			$('input[name=mngrMbrId]').focus();
			return;
		}

		var url = '<c:out value="/basic/account/check_id.json"/>';
		var submitData = {
				mngrMbrId: mngrMbrId
		}

		var callback = function(data) {
			$(document).data('idCheck', mngrMbrId);
			alert(getMessage('msg.member.id.valid'));
		}

		ajaxSubmit(url, submitData, callback, null);
	});

	$('#btnDelete').on('click', function() {
		if (confirm(getMessage('confirm.delete'))) {
			var url = '<c:out value="/basic/account/delete.json"/>';
			var submitData = {
					mngrMbrIdx: mngrMbrIdx
			}

			var callback = function(data) {
				alert(getMessage('msg.deleted'));
				goList();
			}

			ajaxSubmit(url, submitData, callback, null);
		}
	});

	$('#btnCancel').on('click', function() {
		/* var url = '<c:out value="/basic/account/list.do"/>';
		$(location).attr('href', url); */
		goList();
	});
});
</script>
<div class="location_area"><h3>권한관리</h3></div>

<c:set var="useYnY">
	<c:if test="${memberInfo.useYn eq 'Y' }"> CHECKED</c:if>
</c:set>
<c:set var="useYnN">
	<c:if test="${memberInfo.useYn eq 'N' }"> CHECKED</c:if>
</c:set>
<c:set var="star">
	<c:if test="${memberInfo eq null }"><span class="red">*</span></c:if>
</c:set>
<form:form commandName="reqManagerMemberVO" name="form">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchText"/>
</form:form>
<div class="subcon_area">
	<form name='form1'>
	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th>이름${star }</th>
				<td>
					<c:if test="${memberInfo eq null }">
						<input type='text' name='mngrMbrNm' />
					</c:if>
					<c:if test="${memberInfo ne null }">
						<c:out value="${memberInfo.mngrMbrNm }"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>아이디${star }</th>
				<td>
					<c:if test="${memberInfo eq null }">
						<input type='text' name='mngrMbrId' /> <button id='btnCheckId'>중복확인</button>
					</c:if>
					<c:if test="${memberInfo ne null }">
						<c:out value="${memberInfo.mngrMbrId }"/>
					</c:if>
				</td>
			</tr>
		<c:if test="${memberInfo eq null }">
			<tr>
				<th>비밀번호<span class="red">*</span></th>
				<td>
					<input type='password' name='mngrMbrPwd' size="20" />
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인<span class="red">*</span></th>
				<td>
					<input type='password' name='mngrMbrPwdConfirm' />
				</td>
			</tr>
		</c:if>
			<tr>
				<th>부서</th>
				<td>
					<input type='text' name='mngrMbrDpt' value='<c:out value="${memberInfo.mngrMbrDpt }"/>' />
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type='text' name='emil' value='<c:out value="${memberInfo.emil }"/>' />
				</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>
					<input type='text' name='phn' value='<c:out value="${memberInfo.phn }"/>' />
				</td>
			</tr>
			<tr>
				<th>권한메뉴<span class="red">*</span></th>
				<td>
					<table class="insideTbl">
						<tr>
							<th>1depth</th>
							<th>2depth</th>
							<th>모두가능</th>
							<th>읽기가능</th>
						</tr>
				<c:forEach items="${menuList }" var="item">
					<c:forEach items="${item.subList }" var="subItem" varStatus="status">
						<tr>
						<c:if test="${status.index eq '0' }">
							<td rowspan='${fn:length(item.subList) }'><c:out value="${item.cmmnCdNm }"/></td>
						</c:if>
							<td><c:out value="${subItem.cmmnCdNm }"/></td>
							<td><input type='checkbox' name='wrtYn' data-cd="${subItem.cmmnCd }" value='Y'></td>
							<td><input type='checkbox' name='wrtYn' data-cd="${subItem.cmmnCd }" value='N'></td>
						</tr>
					</c:forEach>
				</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<th>허용여부<span class="red">*</span></th>
				<td>
					<input type='radio' name='useYn' value='Y'${useYnY }/><label class="mr_10">허용</label>
					<input type='radio' name='useYn' value='N'${useYnN } /><label>미허용</label>
				</td>
			</tr>
		</table>
	</div>
	</form>
	<!-- 버튼 -->
	<div class="btns tar mb_40">
		<button type="button" class="btnBasic_blue" id='btnConfirm'>저장</button>
		<button type="button" class="btnBasic_black" id='btnDelete'>삭제</button>
		<button type="button" class="btnBasic_black" id='btnCancel'>목록</button>
	</div>
</div>