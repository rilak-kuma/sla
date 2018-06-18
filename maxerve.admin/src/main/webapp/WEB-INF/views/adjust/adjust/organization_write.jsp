<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
//입주단체 팝업에서 받은 데이터 처리
function fn_getData(val_01, val_02, val_03){
	var form = $(document).data('form');

	$(form)
	.find('input[name=mvinAplyIdx]').val(val_01)
	.end()
	.find('input[name=oztnNm]').val(val_02);

	var url = '<c:url value="/member/moveInOztnInfo.json"/>';
	var submitData = {
		mvinAplyIdx: val_01
	};

	var callback = function(data){
		$(form)
		.find('.ceoNm').text(data.moveInOztnInfo.ceoNm)
		.end()
		.find('.ceoEmil').text(data.moveInOztnInfo.ceoEmil)
		.end()
		.find('.crn').text(data.moveInOztnInfo.crn)
		.end()
		.find('.ceoPhn').text(data.moveInOztnInfo.ceoPhn);
	};

	ajaxSubmit(url, submitData, callback);
}

// 입주단체 팝업
function popMvin() {
	window.open("/member/moveInOztnPopUp.do","입주단체 조회","400","600");
}

// 무결성
function validate() {
	$('form[name=woodparkForm]').validate({
		ignore: [],
		rules: {
			useYn: 'required',
			srtDt: 'required',
			endDt: 'required'
		},
		messages: {
			useYn	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', 'ON OFF는'),
			srtDt	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '관리기간 시작일은'),
			endDt	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '관리기간 종료일은')
		},
	});

	$('form[name=makerspaceForm]').validate({
		ignore: [],
		rules: {
			useYn: 'required',
			srtDt: 'required',
			endDt: 'required'
		},
		messages: {
			useYn	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', 'ON OFF는'),
			srtDt	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '관리기간 시작일은'),
			endDt	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '관리기간 종료일은')
		},
	});
}

// 저장
function save() {
	var url = '<c:url value="/adjust/adjust/organization_insert.json"/>';
	var list = [];

	var valid = true;
	$('form').has('input[name=mvinAplyIdx]').each(function(){
		if ($(this).find('input[name=mvinAplyIdx]').val()) {
			valid = $(this).valid();

			if (valid) {
				list.push({
					locTypCd: $(this).find('input[name=locTypCd]').val(),
					mvinAplyIdx: $(this).find('input[name=mvinAplyIdx]').val(),
					srtDt: $(this).find('input[name=srtDt]').val(),
					endDt: $(this).find('input[name=endDt]').val(),
					useYn: $(this).find('input[name=useYn]:checked').val()
				});
			} else {
				return false;
			}
		}
	});

	var callback = function(data) {
		alert(getMessage('msg.save.success'));
	}

	if (list.length && valid) {
		ajaxSubmit(url, JSON.stringify(list), callback);
	}
}

jQuery(function($){
	// validate
	validate();

	// 달력
	$('.inp_cal').datepicker();

	$('.btn_cal')
	.on('click', function(){
		$(this).prev('.inp_cal').datepicker('show');
	});

	$('input[name=oztnNm]')
	// 입주단체
	.on('click', function(){
		$(document).data('form', this.form);

		popMvin();
	});

	$('#btnSave')
	// 저장
	.on('click', function(){
		save();

		return false;
	});
});
</script>
<div class="location_area"><h3>정산단체관리</h3></div>

<div class="subcon_area">
	<h4>우드파크</h4>

	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
	<form:form commandName="woodpark" name="woodparkForm">
	<input type='hidden' name='locTypCd' value='002004'/>
	<form:hidden path="mvinAplyIdx"/>

		<div>
			<input type='radio' name='useYn' value='Y'${woodpark.useYn eq 'Y' ? ' CHECKED':'' }> ON
			<input type='radio' name='useYn' value='N'${woodpark.useYn eq 'N' ? ' CHECKED':'' }> OFF
		</div>
		<table>
		<caption></caption>
		<colgroup>
			<col width="150px"/>
			<col width="*"/>
		</colgroup>
			<tr>
				<th>관리단체명</th>
				<td colspan='3'><form:input path="oztnNm" placeholder="" cssStyle="width:50%" readonly="true"/></td>
			</tr>
			<tr>
				<th>대표자명</th>
				<td class='ceoNm'><c:out value="${woodpark.ceoNm }"/></td>
				<th>이메일</th>
				<td class='ceoEmil'><c:out value="${woodpark.ceoEmil }"/></td>
			</tr>
			<tr>
				<th>사업자번호</th>
				<td class='crn'><c:out value="${woodpark.crn }"/></td>
				<th>대표휴대전화</th>
				<td class='ceoPhn'><c:out value="${woodpark.ceoPhn }"/></td>
			</tr>
			<tr>
				<th>관리기간</th>
				<td>
					<form:input path='srtDt' cssStyle="width:100px" cssClass='inp_cal'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
					<span class="mr_20"> ~ </span>
					<form:input path='endDt' cssStyle="width:100px" cssClass='inp_cal'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
				</td>
				<th>정산일</th>
				<td>정산월의 다음달 말일에 정산 처리함</td>
			</tr>
		</table>
	</form:form>
	</div>

	<h4>서울이노베이션팹랩</h4>

	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
	<form:form commandName="makerspace" name="makerspaceForm">
	<input type='hidden' name='locTypCd' value='002003'/>
	<form:hidden path="mvinAplyIdx"/>

		<div>
			<input type='radio' name='useYn' value='Y'${makerspace.useYn eq 'Y' ? ' CHECKED':'' }> ON
			<input type='radio' name='useYn' value='N'${makerspace.useYn eq 'N' ? ' CHECKED':'' }> OFF
		</div>
		<table>
		<caption></caption>
		<colgroup>
			<col width="150px"/>
			<col width="*"/>
		</colgroup>
			<tr>
				<th>관리단체명</th>
				<td colspan='3'><form:input path="oztnNm" placeholder="" cssStyle="width:50%" readonly="true"/></td>
			</tr>
			<tr>
				<th>대표자명</th>
				<td><c:out value="${makerspace.ceoNm }"/></td>
				<th>이메일</th>
				<td><c:out value="${makerspace.ceoEmil }"/></td>
			</tr>
			<tr>
				<th>사업자번호</th>
				<td><c:out value="${makerspace.crn }"/></td>
				<th>대표휴대전화</th>
				<td><c:out value="${makerspace.ceoPhn }"/></td>
			</tr>
			<tr>
				<th>관리기간</th>
				<td>
					<form:input path='srtDt' cssStyle="width:100px" cssClass='inp_cal'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
					<span class="mr_20"> ~ </span>
					<form:input path='endDt' cssStyle="width:100px" cssClass='inp_cal'/><img src="/img/ico_cal.png" alt="캘린더" class='btn_cal'>
				</td>
				<th>정산일</th>
				<td>정산월의 다음달 말일에 정산 처리함</td>
			</tr>
		</table>
	</form:form>
	</div>

	<!-- 버튼 -->
	<sec:authorize access="hasRole('023006002Y')">
	<div class="btns tar mb_40">
		<button type="button" id='btnSave' class="btnBasic_blue">저장</button>
	</div>
	</sec:authorize>
</div><!--subcon_area-->
