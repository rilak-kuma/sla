<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
input[readonly] {background-color: #eee;}
</style>
<script>
// 초기값 설정
function init_data() {
	var facilitiesList = [];

<c:forEach items="${rentAreaFacilitiesList}" var="item">
	facilitiesList.push('<c:out value="${item.fctMstIdx}"/>');
</c:forEach>

	$('form[name=form1] input:checkbox[name=rtaFctList]').each(function(){
		if ($.inArray($(this).val(), facilitiesList) !== -1) {
			$(this).prop('checked', true);
		}
	});
	
	//alert("${reqRentAreaVO.mbrId }");  // 조회조건에서 가지고 온 '적용 회원아이디' 값
	//alert("${rentTheAreaDTO.mbrId }"); // 조회결과에서 가지고 온 '적용 회원아이디' 값
	if( "${rentTheAreaDTO.mbrId }" == ""){
		$('form[name=form1] input[name=mbrId]').val("${reqRentAreaVO.mbrId }");
	}
	
	//alert("${reqRentAreaVO.mvinAplyIdx}");
	if("${reqRentAreaVO.mvinAplyIdx}" !=""){ // 입주신청 인덱스 : 입주단체관리화면에서 넘어온 경우
		$('form[name=form1] input[name=mbrId]').prop("readonly",true);
	}
}

// 목록이동
function goList()
{
	var url = '<c:url value="/reserve/rent_area/list.do"/>';

	$('form[name=form]')
	.attr('action', url)
	.submit();
}

jQuery(function($){

	// 달력
	$('.calendar').datepicker();

	// validate
	$('form[name=form1]').validate({
		rules: {
			titl: 'required',
			rtaSrtDt: 'required',
			rtaSrtTm: 'required',
			rtaEndDt: 'required',
			rtaEndTm: 'required',
			useYn: 'required',
			rtaFctList: 'required'
		},
		messages: {
			titl: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '제목은'),
			rtaSrtDt: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '시작기간은'),
			rtaSrtTm: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '시작시간은'),
			rtaEndDt: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '종료기간은'),
			rtaEndTm: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '종료시간은'),
			useYn: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '사용여부는'),
			rtaFctList: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '대관장소는')
		},
		submitHandler: function(form) {
			var url = '<c:out value="/reserve/rent_area/insert.json"/>';
			var submitData = $(form).serializeFormJSON();

			if (typeof submitData.rtaFctList === 'string') {
				submitData.rtaFctList = [submitData.rtaFctList];
			}

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				goList();
			}

// 			console.log(submitData);

 			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	$('#btnSave').on('click', function(){
		
		var fctNm = ""; //선택된 대관장소들(ex : fctNm = "작은이야기방1,큰이야기방1,혁신광장")
		var comma = false;
		$('form[name=form1] input:checkbox[name=rtaFctList]').each(function(index){
			if( $(this).prop('checked') == true){ //선택된 대관
				if(comma == true){
					fctNm += ","+$(this).attr('data-fctNm');
				}else{
					fctNm += $(this).attr('data-fctNm');
					comma = true;
				}
			}
		});
		$('form[name=form1] input[name=fctNm]').val(fctNm);
		$('form[name=form1]').submit();
	});

	$('#btnCancel').on('click', function(){
		goList();
	});

	$('#btnDeleteRentArea').on('click', function(){
		if (confirm(getMessage('confirm.delete'))) {
			var url = '<c:url value="/reserve/rent_area/delete.json"/>';
			var submitData = {
					rtaIdx: $('form[name=form1] input[name=rtaIdx]').val()
			}

			var callback = function(data) {
				alert(getMessage('msg.deleted'));
				goList();
			}

//				console.log(submitData);

			ajaxSubmit(url, submitData, callback, null);
		}

		return false;
	});

	// 초기값 설정
	init_data();
});
</script>
<form:form commandName="reqRentAreaVO" name="form" method="GET">
	<form:hidden path="mbrId"/>
	<form:hidden path="oztnNm" id=""/>
    <form:hidden path="mvinAplyIdx"/> <!-- 입주단체관리 화면에서 넘어 온 경우, 다시 입주단체관리 화면으로 되돌아가기 위함 -->
	<form:hidden path="page"/>
	<form:hidden path="sortType"/>
</form:form>

<div class="location_area"><h3>대관일정(대관제한) 관리</h3></div>

<c:if test="${!empty rentTheAreaDTO.rtaIdx }">
<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${rentTheAreaDTO.rtaSrtDttm }" var="rtaSrtDttm"/>
<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${rentTheAreaDTO.rtaEndDttm }" var="rtaEndDttm"/>
<fmt:formatDate value="${rtaSrtDttm }" pattern="yyyy.MM.dd" var="rtaSrtDt"/>
<fmt:formatDate value="${rtaSrtDttm }" pattern="HH:mm" var="rtaSrtTm"/>
<fmt:formatDate value="${rtaEndDttm }" pattern="yyyy.MM.dd" var="rtaEndDt"/>
<fmt:formatDate value="${rtaEndDttm }" pattern="HH:mm" var="rtaEndTm"/>
</c:if>

<div class="subcon_area">
	<form:form commandName="rentTheAreaDTO" name="form1">
	<form:hidden path="mvinAplyIdx" /> <!-- 입주단체관리 화면에서 넘어 온 경우, 다시 입주단체관리 화면으로 되돌아가기 위함 -->
	<form:hidden path="rtaIdx"/>

	<div class="mt_20 mb_10">
		<strong>안내메일 : </strong>
		<span>&nbsp;&nbsp;
			<form:radiobutton path="sendMail" value="Y" checked="checked"/> <label class="mr_10">발송</label>
			<form:radiobutton path="sendMail" value="N"/> <label class="mr_10">미발송</label>
			&nbsp;(&nbsp;발송 선택 후, ON 으로 저장하면 적용 회원아이디로 '대관제한안내' 메일이 발송됩니다. 단, OFF 또는 적용 회원아이디가 ALL 인 경우 미발송&nbsp;)
		</span>		
	</div>
	
	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>제목(제한사유)</th>
				<td>
					<form:input path="titl" style="width:300px"/>
					<form:radiobutton path="useYn" value="Y"/> <label class="mr_10">ON</label>
					<form:radiobutton path="useYn" value="N"/> <label class="mr_10">OFF</label>
				</td>
			</tr>
			<tr>
				<th>대관기간</th>
				<td>
					<input type='text' name="rtaSrtDt" class="calendar" value='<c:out value="${rtaSrtDt }"/>'/>
					<select name="rtaSrtTm">
						<option value=''>시간선택</option>
					<c:forEach var="i" begin="0" end="23" step="1">
						<c:set var="tmh" value="0${i }"/>
						<c:forEach var="j" begin="0" end="30" step="30">
						<c:set var="tmm" value="0${j }"/>
						<c:set var="optValue" value="${fn:substring(tmh, fn:length(tmh)-2, fn:length(tmh)) }:${fn:substring(tmm, fn:length(tmm)-2, fn:length(tmm)) }"/>
					  	<option value='${optValue }'<c:if test="${rtaSrtTm eq optValue }"> SELECTED</c:if>>${optValue }</option>
					  	</c:forEach>
					</c:forEach>
					</select>
					~
					<input type='text' name="rtaEndDt" class="calendar" value='<c:out value="${rtaEndDt }"/>'/>
					<select name="rtaEndTm">
						<option value=''>시간선택</option>
					<c:forEach var="i" begin="0" end="23" step="1">
						<c:set var="tmh" value="0${i }"/>
						<c:forEach var="j" begin="0" end="30" step="30">
						<c:set var="tmm" value="0${j }"/>
						<c:set var="optValue" value="${fn:substring(tmh, fn:length(tmh)-2, fn:length(tmh)) }:${fn:substring(tmm, fn:length(tmm)-2, fn:length(tmm)) }"/>
					  	<option value='${optValue }'<c:if test="${rtaEndTm eq optValue }"> SELECTED</c:if>>${optValue }</option>
					  	</c:forEach>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>대관장소</th>
				<td><input type="hidden" id="fctNm" name="fctNm" value="">
				<c:forEach items="${facilitiesMasterDTOList }" var="item">
					<input type='checkbox' name='rtaFctList' value='<c:out value="${item.fctMstIdx }"/>' data-fctNm="${item.fctNm }"/>
					<c:out value="${item.fctNm }"/>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th>적용 회원아이디</th>
				<td>
					<form:input path="mbrId" style="width:300px"/>&nbsp;(&nbsp;ALL 인 경우 모든 회원에게 적용됨을 의미, 미입력시 ALL 로 저장됩니다.&nbsp;)
				</td>
			</tr>			
		</table>

	</div>
	</form:form>

	<!-- 버튼 -->
	<div class="btns tar">
	<sec:authorize access="hasRole('023005002Y')">
		<button type="button" class="btnBasic_blue mb_10" id='btnSave'>저장</button>
		<button type="button" class="btnBasic_blue mb_10" id='btnDeleteRentArea'>삭제</button>
	</sec:authorize>
		<button type="button" class="btnBasic_black mb_10" id='btnCancel'>취소</button>
	</div>
</div>