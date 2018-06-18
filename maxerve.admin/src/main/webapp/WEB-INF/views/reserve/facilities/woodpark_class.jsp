<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
// 초기값 설정
function init_data() {
<c:if test="${fn:length(woodparkClassDTOList) > 0}">
<c:forEach items="${woodparkClassDTOList}" var="item">
	$element = $('#samples #classElement').clone(true).removeAttr('id');

	$element
	.find('input[id=useYn]')
	.attr('name', 'useYn_' + rand(1,100000));

	$element.attr('data-wdpk-cls-idx', <c:out value="${item.wdpkClsIdx}"/>);
	$element.find('input[name=clsNm]').val('<c:out value="${item.clsNm}" escapeXml="false" />');
	$element.find('input#useYn[value=<c:out value="${item.useYn}"/>]').prop('checked', true);
	$element.find('select[name=usePrcTypCd]').val('<c:out value="${item.usePrcTypCd}"/>');
	$element.find('input[name=usePrc]').val('<c:out value="${item.usePrc}"/>');
	$element.find('input[name=mvinYn][value=<c:out value="${item.mvinYn}"/>]').prop('checked', true).trigger('change');
	$element.find('input[name=mvinPrc]').val('<c:out value="${item.mvinPrc}"/>');

	$('#classBox').prepend($element);

	$element.find('input[name=mvinYn]').trigger('change');
</c:forEach>
</c:if>
}

jQuery(function($){
	// validate
	$('form[name=form1]').validate({
		submitHandler: function(form) {
			var url = '<c:out value="/reserve/facilities/woodpark_class_insert.json"/>';
			var submitData = $(form).serializeFormJSON();

			submitData = {
				woodparkClassList: []
			};

			$('#classBox table').each(function(k){
				submitData.woodparkClassList.push({
					wdpkClsIdx: $(this).attr('data-wdpk-cls-idx'),
					clsNm: $(this).find('input[name=clsNm]').val(),
					usePrcTypCd: $(this).find('select[name=usePrcTypCd]').val(),
					usePrc: $(this).find('input[name=usePrc]').val(),
					mvinYn: $(this).find('input[name=mvinYn]').prop('checked') ? 'Y':'N',
					mvinPrc: $(this).find('input[name=mvinPrc]').val(),
					useYn: $(this).find('input#useYn:checked').val()
				});
			});

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.reload();
			}

// 			console.log(submitData);

 			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	// 클래스 추가
	$('#btnAdd').on('click', function(){
		$element = $('#samples #classElement').clone(true).removeAttr('id');

		$element
		.find('input[id=useYn]')
		.attr('name', 'useYn_' + rand(1,100000));

		$('#classBox').prepend($element);
	});

	// 저장
	$('#btnSave').on('click', function(){
		$('form[name=form1]').submit();
	});

	// 입주멤버 이용료 체크
	$('#classBox').on('change', 'input[name=mvinYn]', function(){
		$(this).parents('table:first').find('input[name=mvinPrc]').prop('disabled', !$(this).prop('checked'));
	});

	// 삭제
	$('.btnDelete')
	.on('click', function(){
		$(this).parents('table:first').remove();

		return false;
	});

	// 초기값 설정
	init_data();
});
</script>
<sec:authorize access="hasRole('023005002Y')">
<div class='stit mt_20 mb_10'>
	<div class='fr'>
		<button id='btnAdd' class='btnBasic_blue'>클래스추가</button>
	</div>
</div>
</sec:authorize>
<form name='form1'>
<div id='classBox'></div>
</form>
<sec:authorize access="hasRole('023005002Y')">
<div class='btns tar mb_40'>
	<button id='btnSave' class='btnBasic_blue'>저장</button>
</div>
</sec:authorize>
<div id='samples' class='hidden'>
	<table border='1' id='classElement'>
		<tr>
			<th>클래스명</th>
			<td colspan='3'>
				<input type='text' name='clsNm' />
				<input type='radio' id='useYn' value='Y' /> ON
				<input type='radio' id='useYn' value='N' /> OFF
				<button class='btnBasic_blue btnDelete'>삭제</button>
			</td>
		</tr>
		<tr>
			<th>이용료</th>
			<td>
				<select name='usePrcTypCd'>
					<option value=''>선택</option>
				<c:forEach items="${commonCodeDTOList }" var="item">
					<option value='<c:out value="${item.cmmnCd }"/>'><c:out value="${item.cmmnCdNm }"/></option>
				</c:forEach>
				</select>
				<input name='usePrc' /> 원
			</td>
			<th>
				<input type='checkbox' name='mvinYn' value='Y'/> 입주멤버 이용료
			</th>
			<td>
				<input type='text' name='mvinPrc' DISABLED />
			</td>
		</tr>
	</table>
</div>