<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
// 초기값 설정
function init_data() {
<c:forEach items="${equipmentMasterDTOList}" var="item">
	$element = $('#samples #equipElement').clone(true).removeAttr('id');

	$element
	.find('input[id=useYn]')
	.attr('name', 'useYn_' + rand(1,100000));

	$element.attr('data-eqp-mst-idx', <c:out value="${item.eqpMstIdx}"/>);
	$element.find('input[name=eqpNm]').val('<c:out value="${item.eqpNm}"/>');
	$element.find('input#useYn[value=<c:out value="${item.useYn}"/>]').prop('checked', true);
	$element.find('input[name=eqpHourPrc]').val('<c:out value="${item.eqpHourPrc}"/>');
	$element.find('input[name=eqpHalfHourPrc]').val('<c:out value="${item.eqpHalfHourPrc}"/>');
	$element.find('input[name=eqpTcnt]').val('<c:out value="${item.eqpTcnt}"/>');

	$('#equipBox').prepend($element);
</c:forEach>
}

jQuery(function($){
	// 수량 숫자만
	$('input[name=eqpTcnt], input[name=eqpHourPrc], input[name=eqpHalfHourPrc]').onlyNumber();

	// validate
	$('form[name=form1]').validate({
		submitHandler: function(form) {
			var url = '<c:out value="/reserve/facilities/equip_insert.json"/>';
			var submitData = $(form).serializeFormJSON();

			submitData = {
				equipList: []
			};

			$('#equipBox table').each(function(k){
				submitData.equipList.push({
					eqpMstIdx: $(this).attr('data-eqp-mst-idx'),
					eqpNm: $(this).find('input[name=eqpNm]').val(),
					eqpHourPrc: $(this).find('input[name=eqpHourPrc]').val(),
					eqpHalfHourPrc: $(this).find('input[name=eqpHalfHourPrc]').val(),
					eqpTcnt: $(this).find('input[name=eqpTcnt]').val(),
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

	// 장비 추가
	$('#btnAdd').on('click', function(){
		$element = $('#samples #equipElement').clone(true).removeAttr('id');

		$element
		.find('input[id=useYn]')
		.attr('name', 'useYn_' + rand(1,100000));

		$('#equipBox').prepend($element);
	});

	// 저장
	$('#btnSave').on('click', function(){
		$('form[name=form1]').submit();
	});


	// 초기값 설정
	init_data();
});

//삭제
function fn_del(obj){
	$(obj).parents('table').remove();
}
</script>

<div class="location_area"><h3>대여장비관리</h3></div>

<div class="subcon_area">
	<!-- 버튼 -->
<sec:authorize access="hasRole('023005002Y')">
	<div class="btns tar">
		<button type="button" class="btnBasic_blue mt_20 mb_10" id='btnAdd'>장비추가</button>
	</div>
</sec:authorize>

	<form name='form1'>
	<div id='equipBox' class="basicTbl02"></div>
	</form>
<sec:authorize access="hasRole('023005002Y')">
	<div class="btns tar">
		<button id='btnSave' class="btnBasic_blue mb_10">저장</button>
	</div>
</sec:authorize>
</div>

<div id='samples' class='hidden'>
	<table id='equipElement'>
		<tr>
			<th>장비명</th>
			<td colspan='3'>
				<input type='text' name='eqpNm' />
				<div class="set_box">
					<input type='radio' id='useYn' value='Y' /> <label class="mr_10">ON</label>
					<input type='radio' id='useYn' value='N' /> <label class="mr_10">OFF</label>
				</div>
			</td>
			<td rowspan='2'><button id='btnDel' class="btnBasic" onclick="fn_del(this)">삭제</button></td>
		</tr>
		<tr>
			<th>수량</th>
			<td>
				<input type='text' name='eqpTcnt' size='3' /> 개
			</td>
			<th>이용료</th>
			<td>
				<input type='text' name='eqpHourPrc' size='9' /> 원 / 1시간당
				<input type='text' name='eqpHalfHourPrc' size='9' /> 원 / 30분당
			</td>
		</tr>
	</table>
</div>