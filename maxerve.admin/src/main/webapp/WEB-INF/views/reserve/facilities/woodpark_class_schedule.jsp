<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
table th {width: 150px;}
ul {padding: 0; margin:0;}
ul, li {list-style: none;}

/* 일정관리 테이블 */
.scheduleTbl{width:100%; table-layout:fixed; font-size:12px;}
.scheduleTbl th{padding:5px; border-bottom:1px solid #bbbbbb; border-right:1px solid #bbbbbb; color:#fff;  background-color:#414141; }
.scheduleTbl th.sat{color:#23c2b3; }
.scheduleTbl th.time{color:#b3c223;}
.scheduleTbl th:last-child{border-right:none;}
.scheduleTbl td{padding:12px 5px; border-right:1px solid #bbbbbb; border-bottom:1px solid #bbbbbb; text-align:center;}
.scheduleTbl td:last-child{border-right:none;}
.scheduleTbl td.none{ background-color:#ebebeb; }
.scheduleTbl td.time{ background:#dcf2f0;
</style>
<script>
// 초기설정
function init_data()
{

	var useSrtDt = '<c:out value="${facilitiesMasterDTO.useSrtDt}"/>';
	var useEndDt = '<c:out value="${facilitiesMasterDTO.useEndDt}"/>';

	$('table tr:last-child').each(function(){
	<c:forEach items="${timeList}" var="item">
		var $element = $('#samples #timeListElement').clone('true').removeAttr('id');

		$element.attr({
			'data-srt-tm': '<c:out value="${item.srtTm}"/>',
			'data-end-tm': '<c:out value="${item.endTm}"/>'
		});
		$element.text('<c:out value="${item.srtTm}"/> ~ <c:out value="${item.endTm}"/>');
		$(this).find('ul:first').append($element);

		$(this).find('ul:gt(0)').each(function(k){
			var $tr = $(this).parents('tr:first').prev('tr');

			if ($tr.find('th').eq(k+1).is('[data-clnd-dt]')) {
				var $element = $('#samples #classScheduleElement').clone(true).removeAttr('id');

				$element.find('select').attr({
					'data-srt-tm': '<c:out value="${item.srtTm}"/>',
					'data-end-tm': '<c:out value="${item.endTm}"/>',
					'data-clnd-dt': $tr.find('th').eq(k+1).attr('data-clnd-dt')
				});

				var clndDt = $element.find("select[name=wdpkClsIdx]").attr("data-clnd-dt");

				if(useSrtDt <= clndDt && useEndDt >= clndDt){
					$(this).append($element);
				}
			}
		});
	</c:forEach>

	<c:forEach items="${facilitiesScheduleDTOList}" var="item">
		$(this)
		.find('td').eq(<c:out value="${item.fctDow}"/>)
		.find('select[data-srt-tm="<c:out value="${item.srtTm}"/>"][data-end-tm="<c:out value="${item.endTm}"/>"]')
		.attr('data-fct-dow', '<c:out value="${item.fctDow}"/>');
	</c:forEach>
	});

	$('select[name=wdpkClsIdx]').not('[data-fct-dow]').parent().html("&nbsp;")

//선택된 select 박스 값 매핑
<c:forEach items="${woodparkClassScheduleMVOList}" var="item">
	$('select[name=wdpkClsIdx][data-clnd-dt="<c:out value="${item.clndDt}"/>"][data-srt-tm="<c:out value="${item.srtTm}"/>"][data-end-tm="<c:out value="${item.endTm}"/>"] option[value=<c:out value="${item.wdpkClsIdx}"/>]')
	.prop('selected', true);
</c:forEach>
}

jQuery(function($){

	// validate
	$('form[name=form1]').validate({
		submitHandler: function(form) {
			var url = '<c:out value="/reserve/facilities/woodpark_class_schedule_insert.json"/>';
			var submitData = $(form).serializeFormJSON();

			submitData = {
				fctMstIdx: $('form[name=form]').find('input[name=fctMstIdx]').val(),
				year: $('form[name=form]').find('input[name=year]').val(),
				month: $('form[name=form]').find('input[name=month]').val(),
				woodparkClassScheduleList: []
			};

			$(form).find('select[name=wdpkClsIdx]').each(function(){
				if ($(this).val()) {
					submitData.woodparkClassScheduleList.push({
						clndDt: $(this).attr('data-clnd-dt'),
						srtTm: $(this).attr('data-srt-tm'),
						endTm: $(this).attr('data-end-tm'),
						wdpkClsIdx: $(this).val()
					});
				}
			});

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				location.reload();
			}

			console.log(submitData);
//return;
 			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});


	// 달력이동
	$('form[name=form1]').on('change', 'select[name=year], select[name=month]', function(){
		$('form[name=form]')
		.find('input[name=year]').val($('form[name=form1] select[name=year]').val())
		.end()
		.find('input[name=month]').val($('form[name=form1] select[name=month]').val())
		.end()
		.submit();
	});

	// 저장
	$('#btnSave').on('click', function(){
		$('form[name=form1]').submit();
	});

	// 초기설정
	init_data();
});
</script>
<c:set var="dowNm" value="${fn:split(' ,일,월,화,수,목,금,토', ',') }" />
<form:form commandName="reqFacilitiesMasterVO" name="form" method="GET">
	<form:hidden path="fctMstIdx"/>
	<form:hidden path="year"/>
	<form:hidden path="month"/>
	<form:hidden path="useSrtDt"/>
	<form:hidden path="useEndDt"/>
</form:form>

<div class='subcon_area'>
<form:form commandName="reqFacilitiesMasterVO" name="form1">
	<form:select path="year">
		<c:forEach begin="2015" end="2020" step="1" var="item">
		<form:option value="${item }"><c:out value="${item }"/></form:option>
		</c:forEach>
	</form:select>
	<form:select path="month">
		<form:option value="01">1월</form:option>
		<form:option value="02">2월</form:option>
		<form:option value="03">3월</form:option>
		<form:option value="04">4월</form:option>
		<form:option value="05">5월</form:option>
		<form:option value="06">6월</form:option>
		<form:option value="07">7월</form:option>
		<form:option value="08">8월</form:option>
		<form:option value="09">9월</form:option>
		<form:option value="10">10월</form:option>
		<form:option value="11">11월</form:option>
		<form:option value="12">12월</form:option>
	</form:select>

	<c:forEach items="${calendarDTOList }" var="item" varStatus="status">
	<c:if test="${status.index mod 7 eq 0 }">
	<table border='1' class='scheduleTbl'>
		<thead>
			<tr>
				<th class='time'>시간</th>
	</c:if>
				<th${item.dow eq '7' ? ' class=\"sat\"':''}<c:if test="${!empty item.clndDt }"> data-clnd-dt='<c:out value="${item.clndDt }"/>'</c:if>>
					<c:if test="${!empty item.clndDt }">
					<fmt:parseDate pattern="yyyy.MM.dd" value="${item.clndDt }" var="clndDate" />
					<fmt:formatDate value="${clndDate }" pattern="M월 d일"/>(<c:out value="${dowNm[item.dow] }"/>)
					</c:if>
				</th>
	<c:if test="${status.index mod 7 eq 6 }">
			</tr>
			<tr>
				<td><ul></ul></td>
				<td><ul></ul></td>
				<td><ul></ul></td>
				<td><ul></ul></td>
				<td><ul></ul></td>
				<td><ul></ul></td>
				<td><ul></ul></td>
				<td><ul></ul></td>
			</tr>
		</thead>
	</table>
	</c:if>
	</c:forEach>
</form:form>

<sec:authorize access="hasRole('023005002Y')">
	<div class='btns tar mb_40'>
		<button id='btnSave' class='btnBasic_blue'>저장</button>
	</div>
</sec:authorize>
</div>

<div id='samples' class='hidden'>
	<ul>
		<li id='timeListElement'></li>
		<li id='classScheduleElement'>
			<select name='wdpkClsIdx'>
			<c:forEach items="${woodparkClassDTOList }" var="item">
				<option value='<c:out value="${item.wdpkClsIdx }"/>'><c:out value="${item.clsNm }"/></option>
			</c:forEach>
			<option value='' selected="selected">대관</option>
			</select>
		</li>
	</ul>
</div>