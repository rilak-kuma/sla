<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
// 저장
function save() {
	var url = '<c:url value="/adjust/adjust/fees_insert.json"/>';
	var $form = $('form[name=form1]');
	var list = [{
		payMthd: 'CARD',
		fees: $form.find('input[name=card]').val()
	},{
		payMthd: 'BANK',
		fees: $form.find('input[name=bank]').val()
	},{
		payMthd: 'KAKAOPAY',
		fees: $form.find('input[name=kakaopay]').val()
	}];

	var callback = function(data) {
		alert(getMessage('msg.save.success'));
	}

	ajaxSubmit(url, JSON.stringify(list), callback);
}

jQuery(function($){
	$('#btnSave')
	// 저장
	.on('click', function(){
		save();

		return false;
	});
});
</script>
<div class="location_area"><h3>수수료관리</h3></div>

<div class="subcon_area">
	<h4>결제수수료</h4>

	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
	<form name='form1'>
		<table>
		<caption></caption>
		<colgroup>
			<col width="10%"/>
			<col width="20%"/>
			<col width="10%"/>
			<col width="20%"/>
			<col width="10%"/>
			<col width="*"/>
		</colgroup>
			<tr>
				<th>신용카드</th>
				<td><input type='text' name='card' value='${card }' size='5'/>%</td>
				<th>계좌이체</th>
				<td><input type='text' name='bank' value='${bank }' size='5'/>%</td>
				<th>카카오페이</th>
				<td><input type='text' name='kakaopay' value='${kakaopay }' size='5'/>%</td>
			</tr>
		</table>
	</form>
	</div>

	<!-- 버튼 -->
	<sec:authorize access="hasRole('023006002Y')">
	<div class="btns tar mb_40">
		<button type="button" id='btnSave' class="btnBasic_blue">저장</button>
	</div>
	</sec:authorize>
</div><!--subcon_area-->
