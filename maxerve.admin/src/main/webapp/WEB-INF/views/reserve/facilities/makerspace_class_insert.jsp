<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
<!--
td ul,
ul input {
	padding: 0;
	margin: 0;
	vertical-align: top;
}

ul li {
	list-style-type: none;
}

#facilitiesBox li.on {
	color: red;
}

input#grpNm, input#formUrl {
	width: 130px;
}
-->
</style>
<script>
// 목록이동
function goList()
{
	$('form[name=form]')
	.attr('action', '<c:url value="/reserve/facilities/makerspace_class_list.do"/>')
	.submit();
}
function fn_test() {
	$("input[name=titl]").val("테스트일정");
	$("input[name=srtDt]").val("2017.04.03");
	$("input[name=endDt]").val("2017.04.25");
	$("input[name=useYn]:eq(0)").prop("checked",true);

	//
	$('#groupBox tr .curriculum').each(function(k){
		var grpOrd = k+1;

		$(this).find('li:has(#btnDelCurriculum)').each(function(i){
			$(this).find('input:eq(0)').val('2017.04.08')
			$(this).find('input:eq(1)').val('테스트 교육일자_' + i)
		});
	});
}

// 초기데이터 세팅
function initData() {
	var groupList = [];
	var curriculumList = {};

<c:if test="${!empty makerspaceClassGroupDTOList}">
<c:forEach items="${makerspaceClassGroupDTOList}" var="item" varStatus="status">
	var srtTm = '<c:out value="${item.srtTm}"/>';
	var endTm = '<c:out value="${item.endTm}"/>';

	$("select.srtTm:eq(<c:out value="${status.index}"/>)").val(srtTm);
	$("select.endTm:eq(<c:out value="${status.index}"/>)").val(endTm);

</c:forEach>
</c:if>


<c:if test="${!empty reqMakerspaceClassVO.mkspClsIdx}">
	<c:forEach items="${makerspaceClassMVO.groupList}" var="item">
	<c:if test="${!empty item.mkspClsGrpIdx }">
		groupList.push({
			mkspClsGrpIdx: <c:out value="${item.mkspClsGrpIdx}"/>,
			srtTm: '<c:out value="${item.srtTm}"/>',
			endTm: '<c:out value="${item.endTm}"/>',
			grpNm: '<c:out value="${item.grpNm}"/>',
			grpOrd: <c:out value="${item.grpOrd}"/>,
			prc: '<c:out value="${item.prc}"/>',
			psct: '<c:out value="${item.psct}"/>',
			formUrl: '<c:out value="${item.formUrl}"/>',
			mkspClsRsvtCnt: '<c:out value="${item.mkspClsRsvtCnt}"/>'
		});
	</c:if>
	</c:forEach>

	<c:forEach items="${makerspaceClassCurriculumDTOList}" var="item">
		if (!curriculumList[<c:out value="${item.grpOrd}"/>]) {
			curriculumList[<c:out value="${item.grpOrd}"/>] = [];
		}

		curriculumList[<c:out value="${item.grpOrd}"/>].push({
			mkspClsidx: <c:out value="${item.mkspClsIdx}"/>,
			crclDt: '<c:out value="${item.crclDt}"/>',
			crclNm: '<c:out value="${item.crclNm}"/>',
			crclText: '<c:out value="${item.crclText}"/>'
		});
	</c:forEach>
</c:if>

	$(groupList).each(function(k, v){
		if ($('#groupBox tr').length < v.grpOrd) {
			insertElement();
		}
		if (v.grpOrd === 0 && !$('#groupBox').is(':has(tr)')) {
			insertElement();
		}
	});

	$.each(curriculumList, function(k, v){
		if ($('#groupBox tr').length < k-0) {
			insertElement();
		}
		var $tr = $('#groupBox tr').eq(k-1);
		
		$(v).each(function(k1, v1){
			if($tr.find('.curriculum li').not(':has(#btnAddCurriculum)').length <= k1) {
				$tr.find('#btnAddCurriculum').trigger('click');
			}

			var $li = $tr.find('.curriculum li').not(':has(#btnAddCurriculum)').eq(k1);

			$li.find('input:eq(0)').val(v1.crclDt);
			$li.find('input:eq(1)').val(v1.crclNm);
		});
		//console.log(v[0].crclText);
		//소개글
		$tr.find('.curriculum li').find('textarea').val(v[0].crclText);
	});

	$(groupList).each(function(k, v){
		var ix = 0;

		if(v.srtTm == $("select.srtTm:eq(0)").val()){//오전
			ix = 1;
		}else{//오후
			ix = 2;
		}

		if (ix) {
			var $target;
			if (v.grpOrd) {
				$target = $('#groupBox tr').eq(v.grpOrd-1).find('.group' + ix);
			} else {
				$target = $('#groupBox').find('.allGroup').eq(ix-1);
			}

			$target.attr('data-mksp-cls-grp-idx', v.mkspClsGrpIdx);
			$target.find('#grpNm').val(v.grpNm);
			$target.find('#prc').val(v.prc);
			$target.find('#psct').val(v.psct);
			$target.find('#formUrl').val(v.formUrl).attr("placeholder","http(s)://");
			$target.find('#mkspClsRsvtCnt').text("신청 회원 수 " + v.mkspClsRsvtCnt +"명");
			$target.find("ul").removeClass("hidden");
			$target.find("#btnAddGroupCurriculum").addClass("hidden");
			$target.find("ul").data("data",v);
		}
	});
}

// 모집과정추가
function insertElement() {
	var $element = $('#samples #listElement').clone(true);

	$element.find('.group1').attr("data-grp-ord",($("#groupBox td.group1").length + 1));
	$element.find('.group2').attr("data-grp-ord",($("#groupBox td.group1").length + 1));
	var $curriculumElement = $('#samples #curriculumElement').clone(true);

	$element.removeAttr('id');
	$curriculumElement.removeAttr('id');

	$element.find('#btnAddCurriculum').parent().before($curriculumElement);

	if ($('#groupBox').is(':has(tr)')) {
		$element.find('.allGroup').remove();
	}

	$curriculumElement.find('input:first').datepicker();

	$('#groupBox').append($element);

	$('#groupBox .allGroup').attr('rowspan', $('#groupBox tr').length);

}

// 삭제
function goDelete() {
	if (!confirm(getMessage('confirm.delete'))) {
		return;
	}

	ajaxSubmit('<c:url value="/reserve/facilities/makerspace_class_delete.json"/>', {
		mkspClsIdx: $('form[name=form1] input[name=mkspClsIdx]').val()
	}, function(){
		alert(getMessage('msg.deleted'));
		goList();
	});
}

//그룹삭제
function fn_delCurriculum(obj) {
	var data = $(obj).parents("ul").data("data");
	if(data && data.mkspClsRsvtCnt && parseInt(data.mkspClsRsvtCnt) > 0){
		alert("예약된 회원이 " +data.mkspClsRsvtCnt+"명 있어서 삭제할수 없습니다.");
		return;
	}
	$(obj).parents("ul").addClass('hidden');
	$(obj).parents("td").find("#btnAddGroupCurriculum").removeClass("hidden");


	return false;
}
//그룹추가
function fn_addCurriculum(obj) {
	$(obj).prev("ul").removeClass('hidden');
	$(obj).addClass("hidden");
	return false;
}

jQuery(function($){
	// 달력
	$('input.calendar').datepicker();

	// 사용료, 인원 숫자만
	$('#samples').find('input#prc, input#psct').onlyNumber();
	
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

	// validate
	$('form[name=form1]').validate({
		rules: {
			titl: 'required',
			srtDt: 'required',
			endDt: 'required',
			useYn: 'required'
		},
		messages: {
			titl: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '제목은'),
			srtDt: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '모집시작일은'),
			endDt: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '모집종료일은'),
			useYn: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '사용여부는')
		},
		submitHandler: function(form) {
			var url = '<c:out value="/reserve/facilities/makerspace_class_insert.json"/>';
			var submitData = $(form).serializeFormJSON();
			var valid = true;
			var newValue = [];
			$('#groupBox tr .curriculum').each(function(k){
				var grpOrd = k+1;

				$(this).find('li:has(#btnDelCurriculum)').each(function(){
				  	if (!submitData.curriculumList) {
						submitData.curriculumList = [];
					}
				  
					if($(this).find('input:eq(0)').val() == ""){
						alert("교육과정 일자을 입력하세요.");
						$(this).find('input:eq(0)').focus();
						valid = false;
						return false; // break;
					}

					if($(this).find('input:eq(1)').val() == ""){
						alert("교육과정 제목을 입력하세요.");
						$(this).find('input:eq(1)').focus();
						valid = false;
						return false; // break;
					}

					if ($(this).find('input:eq(0)').val() && $(this).find('input:eq(1)').val()) {
						submitData.curriculumList.push({
							grpOrd: k+1,
							crclDt: $(this).find('input:eq(0)').val(),
							crclNm: $(this).find('input:eq(1)').val()
						});
					}
				});
				
				if($(this).find('textarea').val() == ""){
					alert("소개글을 입력하세요.");
					$(this).find('textarea').focus();
					valid = false;
					return false; // break;
				}
				
				newValue.push({
				    grpOrd : k+1,
					crclText : $(this).find('textarea').val()
				})
				//console.log(newValue);
			});
			
			for (var i = 0; i < newValue.length; i++) {
			  	//console.log(" i "+ i)
				  for (var j = 0; j < submitData.curriculumList.length ; j++) {
				  	//console.log(" j "+ j)
					  if(submitData.curriculumList[j].grpOrd === newValue[i].grpOrd){
					    submitData.curriculumList[j].crclText = newValue[i].crclText;
					  }
	       		  }
      		}
			  
			console.log(" submitData "+submitData);
			
			if(valid ==  false) return;


			if($('#groupBox tr .group1 ul:visible').length > 0 || $('#groupBox tr .group1').next("td.allGroup").find("ul:visible").length > 0){
				if($('.srtTm:eq(0)').val() == ""){
					alert("시간을 선택해 주세요.");
					$('.srtTm:eq(0)').focus();
					return;
				}

				if($('.endTm:eq(0)').val() == ""){
					alert("시간을 선택해 주세요.");
					$('.endTm:eq(0)').focus();
					return;
				}
			}

			if($('#groupBox tr .group2 ul:visible').length > 0 || $('#groupBox tr .group2').next("td.allGroup").find("ul:visible").length > 0){
				if($('.srtTm:eq(1)').val() == ""){
					alert("시간을 선택해 주세요.");
					$('.srtTm:eq(1)').focus();
					return;
				}

				if($('.endTm:eq(1)').val() == ""){
					alert("시간을 선택해 주세요.");
					$('.endTm:eq(1)').focus();
					return;
				}
			}

			$('#groupBox tr .group1 ul:visible').each(function(k){

				if($.trim($(this).find('#grpNm').val()) == ""){
					alert("단과반 제목을 입력하세요.");
					$(this).find('#grpNm').focus();
					valid = false;
					return false; // break;
				}

				if($.trim($(this).find('#prc').val()) == ""){
					alert("단과반 가격을 입력하세요.");
					$(this).find('#prc').focus();
					valid = false;
					return false; // break;
				}

				if($.trim($(this).find('#psct').val()) == ""){
					alert("단과반 정원을 입력하세요.");
					$(this).find('#psct').focus();
					valid = false;
					return false; // break;
				}

				if (!submitData.groupList) {
					submitData.groupList = [];
				}

				submitData.groupList.push({
					mkspClsGrpIdx: $(this).parent().attr('data-mksp-cls-grp-idx'),
					srtTm: $('.srtTm:eq(0)').val(),
					endTm: $('.endTm:eq(0)').val(),
					grpNm: $(this).find('#grpNm').val(),
					grpOrd: $(this).parents("td").attr("data-grp-ord"),
					prc: $(this).find('#prc').val(),
					psct: $(this).find('#psct').val(),
					formUrl: $(this).find('#formUrl').val()
				});
			});
			if(valid ==  false) return;


			if (!submitData.groupList) {
				submitData.groupList = [];
			}

			$('#groupBox tr .group2 ul:visible').each(function(k){
				if (!submitData.groupList) {
					submitData.groupList = [];
				}

				if($.trim($(this).find('#grpNm').val()) == ""){
					alert("단과반 제목을 입력하세요.");
					$(this).find('#grpNm').focus();
					valid = false;
					return false; // break;
				}

				if($.trim($(this).find('#prc').val()) == ""){
					alert("단과반 가격을 입력하세요.");
					$(this).find('#prc').focus();
					valid = false;
					return false; // break;
				}

				if($.trim($(this).find('#psct').val()) == ""){
					alert("단과반 정원을 입력하세요.");
					$(this).find('#psct').focus();
					valid = false;
					return false; // break;
				}

				submitData.groupList.push({
					mkspClsGrpIdx: $(this).parent().attr('data-mksp-cls-grp-idx'),
					srtTm: $('.srtTm:eq(1)').val(),
					endTm: $('.endTm:eq(1)').val(),
					grpNm: $(this).find('#grpNm').val(),
					grpOrd: $(this).parents("td").attr("data-grp-ord"),
					prc: $(this).find('#prc').val(),
					psct: $(this).find('#psct').val(),
					formUrl: $(this).find('#formUrl').val()
				});
			});
			if(valid ==  false) return;

			$('#groupBox tr .allGroup ul:visible').each(function(k){
				if (!submitData.groupList) {
					submitData.groupList = [];
				}

				if($.trim($(this).find('#grpNm').val()) == ""){
					alert("종합반 제목을 입력하세요.");
					$(this).find('#grpNm').focus();
					valid = false;
					return false; // break;
				}

				if($.trim($(this).find('#prc').val()) == ""){
					alert("종합반 가격을 입력하세요.");
					$(this).find('#prc').focus();
					valid = false;
					return false; // break;
				}

				if($.trim($(this).find('#psct').val()) == ""){
					alert("종합반 정원을 입력하세요.");
					$(this).find('#psct').focus();
					valid = false;
					return false; // break;
				}

				var groupIndex = $(this).attr('data-group-index');
				submitData.groupList.push({
					mkspClsGrpIdx: $(this).parent().attr('data-mksp-cls-grp-idx'),
					srtTm: $('.srtTm:eq(' + groupIndex + ')').val(),
					endTm: $('.endTm:eq(' + groupIndex + ')').val(),
					grpNm: $(this).find('#grpNm').val(),
					grpOrd: 0,
					prc: $(this).find('#prc').val(),
					psct: $(this).find('#psct').val(),
					formUrl: $(this).find('#formUrl').val()
				});
			});
			if(valid ==  false) return;

			console.log(submitData.groupList);

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				//goList();
			}
 			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	// 저장
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

	// 모집과정추가
	$('#btnAddGroup').on('click', function(){
		insertElement();
	});

	// 커리큘럼추가
	$('#btnAddCurriculum, #btnDelCurriculum').off('click');
	$('#groupBox')
	.on('click', '#btnAddCurriculum', function(){
		var $curriculumElement = $('#samples #curriculumElement').clone(true);
		$curriculumElement.removeAttr('id');

		$curriculumElement.find('input:first').datepicker();

		$(this).parent().before($curriculumElement);

		return false;
	})
	// 커리큘럼삭제
	.on('click', '#btnDelCurriculum', function(){
		if($(this).parents("ul").find("li").length == 2){
			$(this).parents("tr").remove();
		}else{
			$(this).parent().remove();
			return false;
		}
	});

	// 삭제
	$('#btnDelete').on('click', function(){
		goDelete();
	});

	// 목록
	$('#btnList').on('click', function(){
		goList();
	});

	// 초기 데이터 세팅
	initData();

	//of, off
// 	$('input[name=useYn]').on('change', function(){
// 		if($(this).val() == "Y"){
// 			$("input[type=text],select").prop("disabled","").prop("readOnly",false);
// 			$("button.btnBasic").show();
// 		}else{
// 			$("input[type=text],select").prop("disabled","disabled").prop("readOnly",true);
// 			$("button.btnBasic").hide();
// 		}
// 	});
// 	$('input[name=useYn]:checked').trigger("change");
});
</script>
<form:form commandName="reqMakerspaceClassVO" name="form" method="GET">
	<form:hidden path="rsvtPlcCd"/>
	<form:hidden path="fctMstIdx"/>
	<form:hidden path="page"/>
</form:form>

<div class="location_area"><h3>서울이노베이션팹랩 일정관리</h3></div>

<div class="subcon_area">

	<ul id='facilitiesBox' class='small_list'>
	<c:forEach items="${facilitiesMasterDTOList }" var="item">
	<c:set var="c">
		<c:if test="${item.fctMstIdx eq reqMakerspaceClassVO.fctMstIdx }"> class='on'</c:if>
	</c:set>
		<li data-fct-mst-idx='<c:out value="${item.fctMstIdx }"/>'${c }><c:out value="${item.fctNm }"/></li>
	</c:forEach>
	</ul>

	<form:form commandName="makerspaceClassMVO" name="form1">
	<form:hidden path="mkspClsIdx"/>

	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<table>
			<tr>
				<th>제목</th>
				<td>
					<form:input path='titl'/>
					<form:radiobutton path="useYn" value="Y"/> <label class="mr_10">ON</label>
					<form:radiobutton path="useYn" value="N"/> <label class="mr_10">OFF</label>
				</td>
			</tr>
			<tr>
				<th>모집기간</th>
				<td><form:input path='srtDt' class='calendar'/> ~ <form:input path='endDt' class='calendar'/></td>
			</tr>
		</table>
	</div>

	<div class="basicTbl02">
		<table>
			<colgroup>
				<col width="150px"/>
				<col width="200px"/>
				<col width="150px"/>
				<col width="150px"/>
				<col width="150px"/>
				<col width="150px"/>
			</colgroup>

			<tr>
				<th>교육일자</th>
				<th>교육과정</th>
				<th colspan="2">
					<select class='srtTm'>
					  	<option value=''>시간선택</option>
					<c:forEach var="i" begin="0" end="23" step="1">
					  	<c:set var="tmh" value="0${i }"/>
					  	<c:forEach var="j" begin="0" end="30" step="30">
					  	<c:set var="tmm" value="0${j }"/>
					  	<c:set var="optValue" value="${fn:substring(tmh, fn:length(tmh)-2, fn:length(tmh)) }:${fn:substring(tmm, fn:length(tmm)-2, fn:length(tmm)) }"/>
					  	<option value='${optValue }'>${optValue }</option>
					  	</c:forEach>
					</c:forEach>
					</select>
					 ~
					<select class='endTm'>
					  	<option value=''>시간선택</option>
					<c:forEach var="i" begin="0" end="23" step="1">
					  	<c:set var="tmh" value="0${i }"/>
					  	<c:forEach var="j" begin="0" end="30" step="30">
					  	<c:set var="tmm" value="0${j }"/>
					  	<c:set var="optValue" value="${fn:substring(tmh, fn:length(tmh)-2, fn:length(tmh)) }:${fn:substring(tmm, fn:length(tmm)-2, fn:length(tmm)) }"/>
					  	<option value='${optValue }'>${optValue }</option>
					  	</c:forEach>
					</c:forEach>
					</select>
				</th>
				<th colspan="2">
					<select class='srtTm'>
					  	<option value=''>시간선택</option>
						<c:forEach var="i" begin="0" end="23" step="1">
					  	<c:set var="tmh" value="0${i }"/>
					  	<c:forEach var="j" begin="0" end="30" step="30">
					  	<c:set var="tmm" value="0${j }"/>
					  	<c:set var="optValue" value="${fn:substring(tmh, fn:length(tmh)-2, fn:length(tmh)) }:${fn:substring(tmm, fn:length(tmm)-2, fn:length(tmm)) }"/>
					  	<option value='${optValue }'>${optValue }</option>
					  	</c:forEach>
					</c:forEach>
					</select>
					 ~
					<select class='endTm'>
					  	<option value=''>시간선택</option>
						<c:forEach var="i" begin="0" end="23" step="1">
					  	<c:set var="tmh" value="0${i }"/>
					  	<c:forEach var="j" begin="0" end="30" step="30">
					  	<c:set var="tmm" value="0${j }"/>
					  	<c:set var="optValue" value="${fn:substring(tmh, fn:length(tmh)-2, fn:length(tmh)) }:${fn:substring(tmm, fn:length(tmm)-2, fn:length(tmm)) }"/>
					  	<option value='${optValue }'>${optValue }</option>
					  	</c:forEach>
					</c:forEach>
					</select>
				</th>
			</tr>
			<tbody id='groupBox'></tbody>
		</table>
	</div>

	<!-- 버튼 -->
	<div class="btns tar">
		<button type="button" class="btnBasic_blue mt_20 mb_10" id='btnAddGroup'>모집과정추가</button>
	</div>

	<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width="40%"/>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>운영시간</th>
				<td><form:textarea path="oprTmInfo" maxlength="100" rows="2" cols="35" style="padding:2px 2px 2px 2px;"/></td>
				<th>예약가능회원</th>
				<td><form:textarea path="rsvtMbrInfo" maxlength="100" rows="2" cols="35" style="padding:2px 2px 2px 2px;"/></td>
			</tr>
			<tr>
				<th>모집정원</th>
				<td><form:input path="rcrtPsctInfo"/></td>
				<th>이용료</th>
				<td><form:input path="prcInfo"/></td>
			</tr>
			<tr>
				<th>시설정보</th>
				<td><form:textarea path="fctInfo" maxlength="300" style="width:90%;padding:2px 2px 2px 2px;" rows="3"/>&nbsp;<span id="fctInfoLength">0</span>/<span id="fctInfoMaxLength">300</span></td>
				<th>주의사항</th>
				<td><form:textarea path="caution" maxlength="500" style="width:90%;padding:2px 2px 2px 2px;" rows="3"/>&nbsp;<span id="cautionLength">0</span>/<span id="cautionMaxLength">500</span></td>
			</tr>			
		</table>
	</div>

	<!-- 버튼 -->
	<div class="btns tar">
	<sec:authorize access="hasRole('023005002Y')">
		<button type="button" class="btnBasic_blue mb_10" id='btnSave'>저장</button>
		<button type="button" class="btnBasic_black mb_10" id='btnDelete'>삭제</button>
	</sec:authorize>
		<button type="button" class="btnBasic_black mb_10" id='btnList'>목록</button>
	</div>

	</form:form>

</div>

<div id='samples' class='hidden'>
	<table>
		<tr id='listElement'>
			<td colspan="2" class='curriculum'>
				<ul>
					<li>
						<textarea rows="400" cols="400" style="width:260px; height:80px;"></textarea>
						<button class='btnBasic' id='btnAddCurriculum'>추가</button>
					</li>
				</ul>
			</td>
			<td class='group1'>
				<ul class="hidden">
					<li>
						<input type='text' id='grpNm' />
					</li>
					<li>
						<input type='text' id='prc' size='5' /> 원
					</li>
					<li>
						<input type='text' id='psct' size='2' /> 명
					</li>
					<li>
						<input type='text' id='formUrl' placeholder="http(s)://"/>
					</li>
					<li>
						<span id='mkspClsRsvtCnt'></span>
					</li>
					<li>
						<button class="btnBasic" id="btnDelGroupCurriculum" onclick="fn_delCurriculum(this)">X</button>
					</li>
				</ul>
				<button class="btnBasic" id="btnAddGroupCurriculum" onclick="fn_addCurriculum(this)">추가</button>
			</td>
			<td class='allGroup'>
				<ul data-group-index='0' class='hidden'>
					<li>
						<input type='text' id='grpNm' />
					</li>
					<li>
						<input type='text' id='prc' size='5' /> 원
					</li>
					<li>
						<input type='text' id='psct' size='2' /> 명
					</li>
					<li>
						<input type='text' id='formUrl' placeholder="http(s)://"/>
					</li>
					<li>
						<span id='mkspClsRsvtCnt'></span>
					</li>

					<li>
						<button class="btnBasic" id="btnDelGroupCurriculum" onclick="fn_delCurriculum(this)">X</button>
					</li>
				</ul>
				<button class="btnBasic" id="btnAddGroupCurriculum" onclick="fn_addCurriculum(this)">추가</button>
			</td>
			<td class='group2'>
				<ul class="hidden">
					<li>
						<input type='text' id='grpNm' />
					</li>
					<li>
						<input type='text' id='prc' size='5' /> 원
					</li>
					<li>
						<input type='text' id='psct' size='2' /> 명
					</li>
					<li>
						<input type='text' id='formUrl' placeholder="http(s)://"/>
					</li>
					<li>
						<span id='mkspClsRsvtCnt'></span>
					</li>
					<li>
						<button class="btnBasic" id="btnDelGroupCurriculum" onclick="fn_delCurriculum(this)">X</button>
					</li>
				</ul>
				<button class="btnBasic" id="btnAddGroupCurriculum" onclick="fn_addCurriculum(this)">추가</button>
			</td>
			<td class='allGroup'>
				<ul data-group-index='1' class='hidden'>
					<li>
						<input type='text' id='grpNm' />
					</li>
					<li>
						<input type='text' id='prc' size='5' /> 원
					</li>
					<li>
						<input type='text' id='psct' size='2' /> 명
					</li>
					<li>
						<input type='text' id='formUrl' placeholder="http(s)://"/>
					</li>
					<li>
						<span id='mkspClsRsvtCnt'></span>
					</li>
					<li>
						<button class="btnBasic" id="btnDelGroupCurriculum" onclick="fn_delCurriculum(this)">X</button>
					</li>
				</ul>
				<button class="btnBasic" id="btnAddGroupCurriculum" onclick="fn_addCurriculum(this)">추가</button>
			</td>
		</tr>
	</table>
	<ul>
		<li id='curriculumElement'>
			<input type='text' style='width: 100px;'/>
			<input type='text' style='width: 150px;'/>
			
			<button class='btnBasic' id='btnDelCurriculum'>X</button>
		</li>
	</ul>
</div>