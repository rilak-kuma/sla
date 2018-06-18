<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
#facilitiesBox li.on {
	color: red;
}

/* 버튼추가 */
.btnDel{display:inline-block; padding:2px 7px 4px 17px; color:#fff; font-size:12px; background:#414141 url(../img/ico_del.png) left 50% no-repeat; background-size:22px 22px;}
.btnAdd{display:inline-block; padding:2px 7px 4px 7px; color:#fff; font-size:12px; background:#0f889d;}
</style>
<script>
// 목록이동
function goList()
{
	var url = '<c:url value="/reserve/facilities/list.do"/>';
	var rsvtPlcCd = $('form[name=form] input[name=rsvtPlcCd]').val();
	
	if (rsvtPlcCd == '002004') {
		url = '<c:url value="/reserve/facilities/woodpark_list.do"/>';
	} else if(rsvtPlcCd == '002007') {
		url = '<c:url value="/reserve/facilities/parktour_list.do"/>';
	} else if (rsvtPlcCd == '002001' || rsvtPlcCd == '002002' 
	    || rsvtPlcCd == '002003' || rsvtPlcCd == '002006' 
	    || rsvtPlcCd == '002008' || rsvtPlcCd == '002009'
      	|| rsvtPlcCd == '002010') {
	  
		url = '<c:url value="/reserve/facilities/insert.do"/>';
	}

	$('form[name=form]')
	.attr('action', url)
	.submit();
}

function timeSort(a, b) {
	var srtTmA = a.srtTm;
	var srtTmB = b.srtTm;
	var endTmA = a.endTm;
	var endTmB = b.endTm;

	var rtn;

	if (srtTmA > srtTmB) {
		rtn = 1;
	} else if (srtTmA < srtTmB) {
		rtn = -1;
	} else if (endTmA > endTmB) {
		rtn = 1;
	} else if (endTmA < endTmB) {
		rtn = -1;
	} else {
		rtn = 0;
	}

	return rtn;
}

// 일정 초기설정
function initSchedule() {
	var scheduleList = [];

	<c:forEach items="${facilitiesScheduleDTOList}" var="item">
		scheduleList.push({
			srtTm: '<c:out value="${item.srtTm}"/>',
			endTm: '<c:out value="${item.endTm}"/>',
			fctDow: '<c:out value="${item.fctDow}"/>'
		});
	</c:forEach>

	var timeList = [];

	$(scheduleList).each(function(k, v){
		if($.inArray(v.srtTm+'-'+v.endTm, timeList) == -1) {
			timeList.push(v.srtTm+'-'+v.endTm);
		}
	});

	timeList = $(timeList).map(function(k, v){
		var ar = v.split('-');

		return {
			srtTm: ar[0],
			endTm: ar[1]
		};
	}).get();

	timeList.sort(timeSort);

	// 설정된 시간값에 따라 동적 생성
	$(timeList).each(function(k, v){
		if ($('#scheduleBox li').length <= k) {
			addTimeList();
		}

		$('#scheduleBox li').eq(k).find('select:eq(0)').val(v.srtTm);
		$('#scheduleBox li').eq(k).find('select:eq(1)').val(v.endTm);
	});

	// 저장된 요일정보를 매칭하여 표시
	$(scheduleList).each(function(k, v){
		var ix = -1;
		$(timeList).each(function(k1, v1){
			if (v.srtTm === v1.srtTm && v.endTm === v1.endTm) {
				ix = k1;
				return false;
			}
		});

		if (ix > -1) {
			$('#scheduleBox li').eq(ix).find('input:checkbox[value=' + v.fctDow + ']').prop('checked', true);
		}
	});

	// 설정된 스케쥴이 없으면 기본적으로 하나 출력
	if (!$('#scheduleBox').is(':has(li)')) {
		addTimeList();
	}
/*
	002001	미래청(회의/세미나)
	002002	야외공간
	002003	서울이노베이션팹랩(구 메이커스페이스)
	002005	모두모임방
	002006	미래청(녹음/운동)
	002008	극장동
	002009	맛동
	002010	참여동
 */
	var fctNm = "${facilitiesMasterDTO.fctNm}";
	var rsvtPlcCd = $('form[name=form] input[name=rsvtPlcCd]').val();

	if($('#scheduleBox li').length < 2) {
		if(rsvtPlcCd == '002001' || rsvtPlcCd == '002002' || rsvtPlcCd == '002003' || rsvtPlcCd == '002005'|| rsvtPlcCd == '002006' || rsvtPlcCd == '002008' || rsvtPlcCd == '002009' || rsvtPlcCd == '002010'){
			addTimeList();
		}
	}
}

// 장비초기설정
function initEquip() {
	var equipList = [];
<c:forEach items="${facilitiesEquipList}" var="item">
	equipList.push('<c:out value="${item.eqpMstIdx}"/>');
</c:forEach>

	$('form[name=form1] input:checkbox[name=equipList]').each(function(){
		if ($.inArray($(this).val(), equipList) !== -1) {
			$(this).prop('checked', true);
		}
	});
}

// 일정추가
function addTimeList() {
	var $element = $('#samples #timeElement').clone(true);

	$element.removeAttr('id');

	$('#scheduleBox').append($element);
}

jQuery(function($){
	// 달력
	$('input[name=useSrtDt], input[name=useEndDt]').datepicker({
		defaultDate: '1m'
	});

	// 사용가능인원, 면적 숫자만, 이용료 숫자만
	$('form[name=form1]')
	.find('input[name=psblMinPsct], input[name=psblMaxPsct], input[name=fctFoa], input[name=dftPrc]')
	.onlyNumber();
	
	//시설정보, 주의사항 글자수 표시
	$('#fctInfoLength').text($('textarea[name=fctInfo]').val().length);
	$('#cautionLength').text($('textarea[name=caution]').val().length);
	
	// 시설정보, 주의사항 글자수 표시
	$('textarea[name=fctInfo], textarea[name=caution]')
	.on('keydown', function(){
		
		var _this = this;
		setTimeout(function(){
			$('#'+_this.name+'Length').text($(_this).val().length);
		}, 100);
		
	});

	var validate_rules, validate_messages;

	if ($('form[name=form] input[name=rsvtPlcCd]').val() == '002004' || $('form[name=form] input[name=rsvtPlcCd]').val() == '002007') {
		validate_rules = {
			fctNm: 'required',
			useSrtDt: 'required',
			useEndDt: 'required',
			psblMaxPsct: 'required'
		};

		validate_messages = {
			fctNm: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '제목은'),
			useSrtDt: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '적용시작일은'),
			useEndDt: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '적용종료일은'),
			psblMaxPsct: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '사용가능최대인원은'),
		};
	} else {
		validate_rules = {
			useSrtDt: 'required',
			useEndDt: 'required',
			psblMinPsct: 'required',
			psblMaxPsct: 'required'
		};

		validate_messages = {
			useSrtDt: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '적용시작일은'),
			useEndDt: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '적용종료일은'),
			psblMinPsct: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '사용가능최소인원은'),
			psblMaxPsct: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '사용가능최대인원은'),
		};
	}

	// validate
	$('form[name=form1]').validate({
		rules: validate_rules,
		messages: validate_messages,
		submitHandler: function(form) {
			var url = '<c:out value="/reserve/facilities/insert.json"/>';
			var submitData = $(form).serializeFormJSON();

			$(form).find('#scheduleBox li').each(function(){
				var _this = this;

				$(this).find('input:checkbox:checked').each(function(){
					if (!submitData.facilitiesScheduleList) {
						submitData.facilitiesScheduleList = [];
					}
					submitData.facilitiesScheduleList.push({
						srtTm: $(_this).find('select:eq(0)').val(),
						endTm: $(_this).find('select:eq(1)').val(),
						fctDow: $(this).val()
					});
				});
			});

			if (!submitData.rsvtPlcCd) {
				submitData.rsvtPlcCd = $('form[name=form] input[name=rsvtPlcCd]').val();
			}

			if (submitData.equipList && typeof submitData.equipList === 'string') {
				submitData.equipList = [submitData.equipList];
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
		$('form[name=form1]').submit();
	});

	// 시설 클릭
	$('#facilitiesBox').on('click', 'li', function(){
		var url = '?';

		if ($('form[name=form] input[name=rsvtPlcCd]').val() == '002003' && $(this).attr('data-fct-mst-idx') == '20') {
			url = '<c:out value="/reserve/facilities/makerspace_class_list.do"/>';
		}

		$('form[name=form]')
		.attr('action', url)
		.find('input[name=fctMstIdx]')
		.val($(this).attr('data-fct-mst-idx'));

		$('form[name=form]').submit();
	});

	// 일정추가
	$('#btnAddSchedule').on('click', function(){
		addTimeList();

		return false;
	});

	$('#btnDeleteParktour').on('click', function(){
		if (confirm(getMessage('confirm.delete'))) {
			var url = '<c:url value="/reserve/facilities/parktour_delete.json"/>';
			var submitData = {
					fctMstIdx: $('form[name=form] input[name=fctMstIdx]').val()
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

	// 일정 초기설정
	initSchedule();

	// 장비초기설정
	initEquip();
	
	//+추가 숨기기 sbj 관리자 조작으로 오류날 가능성때문에 방문시 숨김
	<c:if test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002007' }"> /* 방문 */
	if($('#scheduleBox').find('li').length){
	  $('#btnAddSchedule').css('display','none');
	}
	</c:if>
});

//일정삭제
function fn_del(obj){
	$(obj).parent().remove();
}
</script>
<form:form commandName="reqFacilitiesMasterVO" name="form" method="GET">
	<form:hidden path="rsvtPlcCd"/>
	<form:hidden path="fctMstIdx"/>
</form:form>

<c:choose>
	<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002001' }">
		<c:choose>
			<c:when test="${reqFacilitiesMasterVO.fctMstIdx eq '9' or reqFacilitiesMasterVO.fctMstIdx eq '10' or reqFacilitiesMasterVO.fctMstIdx eq '43' }">
				<div class="location_area"><h3>모두모임방 일정관리</h3></div>
			</c:when>
			<c:otherwise>
				<div class="location_area"><h3>미래청(회의/세미나) 일정관리</h3></div>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002002' }">
		<div class="location_area"><h3>야외공간 일정관리</h3></div>
	</c:when>
	<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002003' }">
		<div class="location_area"><h3>서울이노베이션팹랩 일정관리</h3></div>
	</c:when>
</c:choose>

<div class="subcon_area">

	<c:if test="${reqFacilitiesMasterVO.rsvtPlcCd ne '002004' and reqFacilitiesMasterVO.rsvtPlcCd ne '002007' }">
	<ul id='facilitiesBox' class='small_list'>
	<c:forEach items="${facilitiesMasterDTOList }" var="item">
	<c:set var="c">
		<c:if test="${item.fctMstIdx eq reqFacilitiesMasterVO.fctMstIdx }"> class='on'</c:if>
	</c:set>
	<c:if test="${reqFacilitiesMasterVO.fctMstIdx eq '9' or reqFacilitiesMasterVO.fctMstIdx eq '10' or reqFacilitiesMasterVO.fctMstIdx eq '43' }">
		<c:if test="${item.fctMstIdx eq '9' or item.fctMstIdx eq '10' or item.fctMstIdx eq '43' }">
			<li data-fct-mst-idx='<c:out value="${item.fctMstIdx }"/>'${c }><c:out value="${item.fctNm }"/></li>
		</c:if>
	</c:if>

	<c:if test="${ reqFacilitiesMasterVO.fctMstIdx ne '9' and reqFacilitiesMasterVO.fctMstIdx ne '10' and reqFacilitiesMasterVO.fctMstIdx ne '43' }">
		<c:if test="${item.fctMstIdx ne '9' and item.fctMstIdx ne '10' and item.fctMstIdx ne '43' }">
			<li data-fct-mst-idx='<c:out value="${item.fctMstIdx }"/>'${c }><c:out value="${item.fctNm }"/></li>
		</c:if>
	</c:if>
	</c:forEach>
	</ul>
	</c:if>
	<form:form commandName="facilitiesMasterDTO" name="form1">
	<form:hidden path="fctMstIdx"/>
	<form:hidden path="rsvtPlcCd"/>

<%-- <c:choose>
	<c:when test="${facilitiesMasterDTO.rsvtPlcCd eq '002001' }"><input type='hidden' name='rsvtUnit' value='30'/></c:when>
	<c:otherwise><input type='hidden' name='rsvtUnit' value='60'/></c:otherwise>
</c:choose> --%>
<input type='hidden' name='rsvtUnit' value='${facilitiesMasterDTO.rsvtUnit}'/>

<c:if test="${reqFacilitiesMasterVO.rsvtPlcCd ne '002004' and reqFacilitiesMasterVO.rsvtPlcCd ne '002007' }">

	<div class="stit mt_20 mb_10">
		<strong><c:out value="${facilitiesMasterDTO.fctNm }"/></strong>
		<div class="set_box">
			<form:hidden path="fctNm"/>
			<form:radiobutton path="useYn" value="Y"/> <label class="mr_10">ON</label>
			<form:radiobutton path="useYn" value="N"/> <label class="mr_10">OFF</label>
		</div>
	</div>

</c:if>
<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width="40%"/>
				<col width="160px"/>
				<col width=""/>
			</colgroup>
		<c:if test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002004' or reqFacilitiesMasterVO.rsvtPlcCd eq '002007' }">
			<tr>
				<th>제목</th>
				<td colspan='3'>
					<form:input path="fctNm"/>
					<form:radiobutton path="useYn" value="Y"/> <label class="mr_10">ON</label>
					<form:radiobutton path="useYn" value="N"/> <label class="mr_10">OFF</label>
				</td>
			</tr>
		</c:if>
			<tr>
				<th>적용기간</th>
				<td>
					<form:input path="useSrtDt"/>
					~
					<form:input path="useEndDt"/>
				</td>
				<th>대관일정(대관제한)<br/>관리여부</th>
				<td>
				    <form:radiobutton path="rtaYn" value="Y"/> <label class="mr_10">예</label>
			        <form:radiobutton path="rtaYn" value="N"/> <label class="mr_10">아니오</label>
				</td>
			</tr>
			<tr>
				<th>
			<c:choose>
				<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002003' }">예약가능회원</c:when>
				<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002007' }">투어인원</c:when>
				<c:otherwise>사용가능인원</c:otherwise>
			</c:choose>
				</th>
				<td colspan='3'>
			<c:choose>
				<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002004' or reqFacilitiesMasterVO.rsvtPlcCd eq '002007' }">
					<form:input path="psblMaxPsct"/> 명 / 1회
				</c:when>
				<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002003'}">
					<form:input path="psblMinPsct"/>
					~
					<form:input path="psblMaxPsct"/>
					명
				</c:when>
				<c:otherwise>
					<form:input path="psblMinPsct"/>
					~
					<form:input path="psblMaxPsct"/>
					명 / 면적 <form:input path="fctFoa"/> m<sup>2</sup>
				</c:otherwise>
			</c:choose>
				</td>
			</tr>
			<tr>
				<th>
					운영일정
				<c:if test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002004' or reqFacilitiesMasterVO.rsvtPlcCd eq '002007' }">
					<button id='btnAddSchedule' class='btnAdd'>+추가</button>
				</c:if>
				</th>
				<td colspan='3'>
					<ul id='scheduleBox'></ul>
				</td>
			</tr>
		<c:if test="${reqFacilitiesMasterVO.rsvtPlcCd ne '002004' and reqFacilitiesMasterVO.rsvtPlcCd ne '002007' }">
			<tr>
				<th>최소예약시간</th>
				<td>
					<form:select path="minTm">
			<c:choose>
				<c:when test="${facilitiesMasterDTO.rsvtPlcCd eq '002001' or facilitiesMasterDTO.rsvtPlcCd eq '002010' }">
					<form:option value="30">30분</form:option>
					<form:option value="60">1시간</form:option>
					<form:option value="120">2시간</form:option>
					<form:option value="180">3시간</form:option>
					<form:option value="240">4시간</form:option>
					<form:option value="300">5시간</form:option>
				</c:when>
				<c:otherwise>
					<form:option value="60">1시간</form:option>
					<form:option value="120">2시간</form:option>
					<form:option value="180">3시간</form:option>
					<form:option value="240">4시간</form:option>
					<form:option value="300">5시간</form:option>
				</c:otherwise>
			</c:choose>
					</form:select>
				</td>
				<th>예약단위</th>
				<td>
			<c:choose>
				<c:when test="${facilitiesMasterDTO.rsvtUnit lt 60 }">
					<c:out value="${facilitiesMasterDTO.rsvtUnit }"/> 분
				</c:when>
				<c:otherwise>
				    <fmt:formatNumber value="${facilitiesMasterDTO.rsvtUnit/60}" pattern="#,#0.###" var="_rsvtUnit"/>
					<c:out value="${_rsvtUnit}"/> 시간
				</c:otherwise>
			</c:choose>
				</td>
			</tr>
			<tr>
				<th>이용료</th>
				<td colspan='3'>
					<form:input path="dftPrc"/> /
			<c:choose>
			    <%-- <c:when test="${reqFacilitiesMasterVO.fctMstIdx eq '9' or reqFacilitiesMasterVO.fctMstIdx eq '10' }">1시간당</c:when> 모두모임방1,2
				<c:when test="${facilitiesMasterDTO.rsvtPlcCd eq '002001' or facilitiesMasterDTO.rsvtPlcCd eq '002010' }">30분당</c:when> 미래청(회의/세미나),참여동
				<c:otherwise>1시간당</c:otherwise> --%>
				<c:when test="${facilitiesMasterDTO.rsvtUnit lt 60 }">
					<c:out value="${facilitiesMasterDTO.rsvtUnit }"/>분당
				</c:when>
				<c:otherwise>
				    <fmt:formatNumber value="${facilitiesMasterDTO.rsvtUnit/60}" pattern="#,#0.###" var="_rsvtUnit"/>
					<c:out value="${_rsvtUnit}"/>시간당
				</c:otherwise>				
			</c:choose>
				</td>
			</tr>
		<c:if test="${facilitiesMasterDTO.rsvtPlcCd ne '002003' }">
			<tr>
				<th>대여장비</th>
				<td colspan='3'>
				<c:forEach items="${equipmentMasterDTOList }" var="item">
				<c:if test="${item.useYn eq 'Y' }">
					<input type='checkbox' name='equipList' value='<c:out value="${item.eqpMstIdx }"/>' />
					<c:out value="${item.eqpNm }"/>
				</c:if>
				</c:forEach>
				</td>
			</tr>
		</c:if>
		</c:if>
			<tr>
				<th>운영시간</th>
				<td><form:textarea path="oprTmInfo" maxlength="100" rows="2" cols="35" style="padding:2px 2px 2px 2px;"/></td>
				<th>예약가능회원</th>
				<td><form:textarea path="rsvtMbrInfo" maxlength="100" rows="2" cols="35" style="padding:2px 2px 2px 2px;"/></td>
			</tr>
			<tr>
				<th>담당자</th>
				<td><form:textarea path="mngrNm" maxlength="100" rows="2" cols="35" style="padding:2px 2px 2px 2px;"/></td>
				<th>특이사항</th>
				<td><form:textarea path="rmk" maxlength="500" rows="2" cols="35" style="padding:2px 2px 2px 2px;"/></td>
			</tr>
			<tr>
				<th>시설정보</th>
				<td><form:textarea path="fctInfo" maxlength="300" style="width:90%;padding:2px 2px 2px 2px;" rows="3"/>&nbsp;<span id="fctInfoLength">0</span>/<span id="fctInfoMaxLength">300</span></td>
				<th>주의사항</th>
				<td><form:textarea path="caution" maxlength="500" style="width:90%;padding:2px 2px 2px 2px;" rows="3"/>&nbsp;<span id="cautionLength">0</span>/<span id="cautionMaxLength">500</span></td>
			</tr>			
	<c:choose>
		<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002003' }">
			<tr>
				<th>최대출력사이즈</th>
				<td><form:input path="maxOtpSize"/></td>
				<th>최대예약시간</th>
				<td colspan='3'><form:input path="tnm"/></td>
			</tr>
		</c:when>
		<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002004' }">
			<tr>
				<th>이용료</th>
				<td colspan='3'><form:input path="prcInfo"/></td>
			</tr>
		</c:when>
		<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002007' }">
			<tr>
				<th>신청기한</th>
				<td colspan='3'><form:input path="tnm"/></td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<th>기자재</th>
				<td colspan='3'><form:input path="tnm" style="width:42%"/></td>
			</tr>
		</c:otherwise>
	</c:choose>
		</table>
	</div>

	</form:form>

	<!-- 버튼 -->
<c:choose>
	<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002004' or reqFacilitiesMasterVO.rsvtPlcCd eq '002007' }">
			<div class="btns tar">
			<sec:authorize access="hasRole('023005005Y')">
				<button type="button" class="btnBasic_blue mb_10" id='btnSave'>저장</button>
				<button type="button" class="btnBasic_blue mb_10" id='btnDeleteParktour'>삭제</button>
			</sec:authorize>
				<button type="button" class="btnBasic_black mb_10" onClick='javascript:history.back();'>취소</button>
			</div>
	</c:when>
	<c:otherwise>
		<sec:authorize access="hasRole('023005002Y')">
			<div class="btns tar">
				<button type="button" class="btnBasic_blue mb_10" id='btnSave'>저장</button>
			</div>
		</sec:authorize>
	</c:otherwise>
</c:choose>
</div>

<div id='samples' class='hidden'>
	<ul>
		<li id='timeElement'>
			<select>
				<option value=''>시간선택</option>
				<c:forEach var="i" begin="0" end="23" step="1">
					<c:set var="tmh" value="0${i }"/>
					<c:forEach var="j" begin="0" end="30" step="30">
						<c:set var="tmm" value="0${j }"/>
						<c:set var="optValue" value="${fn:substring(tmh, fn:length(tmh)-2, fn:length(tmh)) }:${fn:substring(tmm, fn:length(tmm)-2, fn:length(tmm)) }"/>
						<option value="${optValue }">${optValue }</option>
					</c:forEach>
				</c:forEach>
			</select>
			~
			<select>
			<!-- 2017-8-25 sbj 방문은 종료시간이 17:00 -->
				<option value=''>시간선택</option>
				<c:choose>
					<c:when test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002007' }">  <!-- 방문 -->
						<option value="17:00">17:00</option>
					</c:when>
					<c:otherwise>
						<c:forEach var="i" begin="0" end="23" step="1">
							<c:set var="tmh" value="0${i }"/>
							<c:forEach var="j" begin="0" end="30" step="30">
								<c:set var="tmm" value="0${j }"/>
								<c:set var="optValue" value="${fn:substring(tmh, fn:length(tmh)-2, fn:length(tmh)) }:${fn:substring(tmm, fn:length(tmm)-2, fn:length(tmm)) }" />
								<option value="${optValue }">${optValue }</option>
							</c:forEach>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</select>
			<input type='checkbox' value='2' /> 월
			<input type='checkbox' value='3' /> 화
			<input type='checkbox' value='4' /> 수
			<input type='checkbox' value='5' /> 목
			<input type='checkbox' value='6' /> 금
			<input type='checkbox' value='7' /> 토
			<input type='checkbox' value='1' /> 일
			<input type='checkbox' value='0' /> 법정공휴일
		<c:if test="${reqFacilitiesMasterVO.rsvtPlcCd eq '002004' }">
			<button id='btnDeleteSchedule' class='btnDel' onclick="fn_del(this)">삭제</button>
		</c:if>
		</li>
	</ul>
</div>