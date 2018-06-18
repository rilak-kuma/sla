<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
tbody.sms {
	display:none;
}
</style>
<script>
jQuery(function($){
	$('form[name=form1]').validate({
		rules: {
			siplRscCntt2_1: 'required',
			siplRscCntt2_2: 'required',
			siplRscCntt2_3: 'required',
			siplRscCntt2_5: 'required',
			siplRscCntt3_1: 'required',
			siplRscCntt3_2: 'required',
			siplRscCntt3_5: 'required',
			siplRscCntt5_1: 'required',
			siplRscCntt5_3: 'required',
			siplRscCntt5_5: 'required',
			siplRscCntt6_1: 'required',
			siplRscCntt6_2: 'required',
			siplRscCntt6_3: 'required',
			siplRscCntt6_5: 'required'			
		},
		messages: {
			siplRscCntt2_1: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '시설예약신청완료는'),
			siplRscCntt2_2: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '시설예약승인은'),
			siplRscCntt2_3: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '시설예약완료는'),
			siplRscCntt2_5: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '시설예약취소는'),			
			siplRscCntt3_1: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '투어예약신청완료는'),
			siplRscCntt3_2: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '투어예약승인은'),
			siplRscCntt3_5: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '투어예약취소는'),
			siplRscCntt5_1: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '장비예약신청완료는'),
			siplRscCntt5_3: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '장비예약완료는'),
			siplRscCntt5_5: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '장비예약취소는'),
			siplRscCntt6_1: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '멤버십예약신청완료는'),
			siplRscCntt6_2: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '멤버십예약승인은'),
			siplRscCntt6_3: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '멤버십예약완료는'),
			siplRscCntt6_5: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '멤버십예약취소는')			
		},
		submitHandler: function(form) {
			var url = '<c:url value="/basic/sms/insert.json"/>';
// 			var submitData = $(form).serializeFormJSON();

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
			}

			var submitData = {
					list: $(form).find('textarea').map(function(k){
						if (!$(this).prop('disabled')) {
							return {
								//siplRscIdx: ($(form).find('textarea').index(this) % 3)+1,
								siplRscIdx: $(this).attr('data-siplRscIdx'),
								siplRscTypCd: $(this).parents('.sms:first').attr('data-siplRscTypCd'),
								pgrCd: $(this).attr('data-pgrCd'),
								siplRscCntt: $(this).val()
							}
						}
					}).get()
			};

			//alert(JSON.stringify(submitData));
			console.log(submitData);

 			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	$('#btnSave').on('click', function(){
		$('form[name=form1]').submit();
	});

	$('input[name=reqSiplRscTypCd]').on('change', function(){
// 		$('form[name=form1] textarea').rules('remove');
		$('tbody.sms textarea').prop('disabled', true);

		if ($(this).val()) {
			$('tbody.sms').hide().filter('[data-siplRscTypCd=' + $(this).val() + ']').show();
		} else {
			$('tbody.sms').show();
		}


		$('tbody.sms:visible textarea').prop('disabled', false);
	});

	$('input[name=reqSiplRscTypCd]').eq(0).trigger('change');
});
</script>

<div class="location_area"><h3>SMS관리</h3></div>
<div class="subcon_area">
	<div class="searchTbl">
		<table>
			<tr>
				<td>
					<input type='radio' name='reqSiplRscTypCd' value='' CHECKED/><label class="mr_10">전체</label>
					<input type='radio' name='reqSiplRscTypCd' value='024002'/><label class="mr_10">시설예약</label>
					<input type='radio' name='reqSiplRscTypCd' value='024003'/><label class="mr_10">투어예약</label>
					<input type='radio' name='reqSiplRscTypCd' value='024005'/><label class="mr_10">장비예약(서울이노베이션팹랩)</label>
					<input type='radio' name='reqSiplRscTypCd' value='024006'/><label class="mr_10">멤버십예약</label>
				</td>
			</tr>
		</table>
	</div>

	<!-- 리스트형 테이블 -->
	<div class="basicTbl">
		<form name='form1'>
		<table>
			<caption></caption>
			<colgroup>
				<col width="10%"/>
				<col width="25%"/>
				<col width=""/>
			</colgroup>
			<thead>
				<tr>
					<th colspan='2'>서비스구분</th>
					<th>발송메세지</th>
				</tr>
			</thead>
			<tbody class='sms' data-siplRscTypCd='024002'>
				<tr>
					<td rowspan='4'>시설예약</td>
					<td>시설예약신청완료</td>
					<td><textarea style='width:100%' name='siplRscCntt2_1' data-siplRscIdx='1' data-pgrCd='020001'><c:out value="${list002[0].siplRscCntt }"/></textarea></td>
				</tr>
				<tr>
					<td>시설예약승인</td>
					<td><textarea style='width:100%' name='siplRscCntt2_2' data-siplRscIdx='2' data-pgrCd='020002'><c:out value="${list002[1].siplRscCntt }"/></textarea></td>
				</tr>
				<tr>
					<td>시설예약완료</td>
					<td><textarea style='width:100%' name='siplRscCntt2_3' data-siplRscIdx='3' data-pgrCd='020003'><c:out value="${list002[2].siplRscCntt }"/></textarea></td>
				</tr>
				<tr>
					<td>시설예약취소</td>
					<td><textarea style='width:100%' name='siplRscCntt2_5' data-siplRscIdx='4' data-pgrCd='020005'><c:out value="${list002[3].siplRscCntt }"/></textarea></td>
				</tr>
			</tbody>
			<tbody class='sms' data-siplRscTypCd='024003'>
				<tr>
					<td rowspan='3'>투어예약</td>
					<td>투어예약신청완료</td>
					<td><textarea style='width:100%' name='siplRscCntt3_1' data-siplRscIdx='1' data-pgrCd='020001'><c:out value="${list003[0].siplRscCntt }"/></textarea></td>
				</tr>
				<tr>
					<td>투어예약승인</td>
					<td><textarea style='width:100%' name='siplRscCntt3_2' data-siplRscIdx='2' data-pgrCd='020002'><c:out value="${list003[1].siplRscCntt }"/></textarea></td>
				</tr>
				<tr>
					<td>투어예약취소</td>
					<td><textarea style='width:100%' name='siplRscCntt3_5' data-siplRscIdx='3' data-pgrCd='020005'><c:out value="${list003[2].siplRscCntt }"/></textarea></td>
				</tr>
			</tbody>
			<tbody class='sms' data-siplRscTypCd='024005'>
				<tr>
					<td rowspan='3'>장비예약<br/>(서울이노베이션팹랩)</td>
					<td>장비예약신청완료</td>
					<td><textarea style='width:100%' name='siplRscCntt5_1' data-siplRscIdx='1' data-pgrCd='020001'><c:out value="${list005[0].siplRscCntt }"/></textarea></td>
				</tr>
				<tr>
					<td>장비예약완료</td>
					<td><textarea style='width:100%' name='siplRscCntt5_3' data-siplRscIdx='2' data-pgrCd='020003'><c:out value="${list005[1].siplRscCntt }"/></textarea></td>
				</tr>				
				<tr>
					<td>장비예약취소</td>
					<td><textarea style='width:100%' name='siplRscCntt5_5' data-siplRscIdx='3' data-pgrCd='020005'><c:out value="${list005[2].siplRscCntt }"/></textarea></td>
				</tr>
			</tbody>
			<tbody class='sms' data-siplRscTypCd='024006'>
				<tr>
					<td rowspan='4'>멤버십예약</td>
					<td>멤버십예약신청완료</td>
					<td><textarea style='width:100%' name='siplRscCntt6_1' data-siplRscIdx='1' data-pgrCd='020001'><c:out value="${list006[0].siplRscCntt }"/></textarea></td>
				</tr>
				<tr>
					<td>멤버십예약승인</td>
					<td><textarea style='width:100%' name='siplRscCntt6_2' data-siplRscIdx='2' data-pgrCd='020002'><c:out value="${list006[1].siplRscCntt }"/></textarea></td>
				</tr>				
				<tr>
					<td>멤버십예약완료</td>
					<td><textarea style='width:100%' name='siplRscCntt6_3' data-siplRscIdx='3' data-pgrCd='020003'><c:out value="${list006[2].siplRscCntt }"/></textarea></td>
				</tr>				
				<tr>
					<td>멤버십예약취소</td>
					<td><textarea style='width:100%' name='siplRscCntt6_5' data-siplRscIdx='4' data-pgrCd='020005'><c:out value="${list006[3].siplRscCntt }"/></textarea></td>
				</tr>
			</tbody>						
		</table>
		</form>
	</div>
	
	<!-- 버튼 -->
	<sec:authorize access="hasRole('023001005Y')">
		<div class="btns tar mb_40">
			<button type="button" class="btnBasic_blue" id='btnSave'>저장</button>
		</div>
	</sec:authorize>
</div>