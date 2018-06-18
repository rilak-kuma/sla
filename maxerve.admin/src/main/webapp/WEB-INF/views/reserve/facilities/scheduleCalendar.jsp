<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
table th {width: 150px;}
ul {padding: 0; margin:0;}
ul, li {list-style: none;}

td.reserved {background-color: #CCFFFF;}
</style>
<script>
jQuery(function($){

	// 달력이동
	$('form[name=form1]').on('change', 'select[name=year], select[name=month]', function(){
		$('form[name=form]')
		.find('input[name=year]').val($('form[name=form1] select[name=year]').val())
		.end()
		.find('input[name=month]').val($('form[name=form1] select[name=month]').val())
		.end()
		.submit();
	});

	//다음(다음주)
	$('span#next').on('click', function(){
		$("form[name=form]")
			.find("input[name=sumDay]")
			.val(7);

		$("form[name=form]").submit();
	});

	//이전(저번주)
	$('span#prev').on('click', function(){
		$("form[name=form]")
			.find("input[name=sumDay]")
			.val(-7);
		$("form[name=form]").submit();
	});

	//오늘(이번주)
	$('span#toDay').on('click', function(){
		$("form[name=form]")
			.find("input[name=sumDay]")
			.val('');
		$("form[name=form]").submit();
	});

	//시설선택
	$('span#fctSelect').on('click', function(){
		$("form[name=form]")
			.find("input[name=sumDay]")
			.val('')
			.end()
			.find("input[name=fctMstIdx]")
			.val($(this).attr('data-mst-idx'))
			.end()
			.submit();
	});

	$("form[name=form]")
		.find("input[name=fctMstIdx]").val('<c:out value="${searchVO.fctMstIdx}" />')
		.end()
		.find("input[name=year]").val('<c:out value="${searchVO.year}" />')
		.end()
		.find("input[name=month]").val('<c:out value="${searchVO.month}" />')
		.end()
		.find("input[name=srtDay]").val('<c:out value="${searchVO.srtDay}" />')
		.end()
		.find("input[name=sumDay]").val('<c:out value="${searchVO.sumDay}" />')
		.end();

	<c:forEach	items="${reserveList }" var="reserveList" varStatus="status">
		var dttm = '<c:out value="${reserveList.clndDt } ${reserveList.tm}" />';
		var srtDttm = '<c:out value="${reserveList.srtDttm }" />'; //ex)2017.09.07 09:00
		var endDttm = '<c:out value="${reserveList.endDttm }" />'; //ex)2017.09.07 11:00
	    
		if( srtDttm.split(' ')[0] == endDttm.split(' ')[0]){ //시작일과 종료일이 같은날인 경우 시간만 표시
			srtDttm = srtDttm.split(' ')[1];
			endDttm = endDttm.split(' ')[1];
		}
		
		var str = "";
		var oztnNm = '<c:out value="${reserveList.oztnNm }" />';
		var assOztnNm = '<c:out value="${reserveList.assOztnNm }" />';
		if(oztnNm != ""){
			if(assOztnNm == oztnNm ){
				str = oztnNm;
			}else if( assOztnNm !=""){
				str = oztnNm +"( "+ assOztnNm+" )";
			}else{
				str = oztnNm ;
			}
		}
		
		var info = '';
		info += srtDttm+' ~ '+endDttm+'<br/>';
		info += str+'<br/>';
		if( "${reserveList.evtNm }" != ""){
			info += '행사명 : <c:out value="${reserveList.evtNm }" />';
		}
		//info += '예약번호 : <c:out value="${reserveList.fctRsvtIdx }" />';
		var $td = $('td[data-dttm="' + dttm + '"]');
		$td.addClass('reserved');

		if (info) {
			$td.attr('data-rsvt-idx', '<c:out value="${reserveList.fctRsvtIdx }"/>');
		}

		$td.append(info);
	</c:forEach>

	var dateSpan = "";
	dateSpan = $('th[data-clnd-dt]:first').attr('data-clnd-dt') + ' ~ ' +  $('th[data-clnd-dt]:last').attr('data-clnd-dt');

	$("#dateSpan").text(dateSpan);

	<c:forEach var="facilitiesList" items="${facilitiesList }">
		<c:if test="${facilitiesList.fctMstIdx eq searchVO.fctMstIdx}" >
			$("#subTitl").text('<c:out value="${facilitiesList.fctNm }" />');
		</c:if>
	</c:forEach>

	$('.insideTbl td[data-rsvt-idx]').each(function(){
		if ($(this).is('.remove')) {
			return;
		}

		var ix = $(this).parent().find('td').index(this);
		var $list = $('.insideTbl tbody tr td:nth-child(' + (ix+1) + ')[data-rsvt-idx=' + $(this).attr('data-rsvt-idx') + ']');

		if ($list.length > 1) {
			$list.not(this).addClass('remove');
			$(this).attr('data-rowspan', $list.length);
		}
	});

	$('.insideTbl td.remove').remove();
	$('.insideTbl td[data-rowspan]').each(function(){
		$(this).attr('rowspan', $(this).attr('data-rowspan'));
	});

	// 예약장비목록 출력
	$('td[data-rsvt-idx]').each(function(k, v){
		var url = '<c:url value="/reserve/facilities/reserveEquipList.json"/>';
		var opt = {
				fctRsvtIdx: $(v).attr('data-rsvt-idx')
		};
		var callback = function(data) {
			if (data.list && data.list.length) {
				var s = '';
				$(data.list).each(function(k1, v1){
					s += s ? ',':'<br>';

					s += v1.eqpNm + '(' + v1.eqpTcnt + ')';
				});

				$(v).append(s);
			}
		}

		ajaxSubmit(url, opt, callback);
	});

});
</script>

<div class="location_area"><h3><c:out value="${mainTitle }" /> 예약현황</h3></div>
<div class="subcon_area">
	<ul class="small_list">
		<c:forEach var="facilitiesList" items="${facilitiesList }">
			<c:if test="${facilitiesList.fctNm.indexOf('모임방') < 0 }" >
			<li>
				<span id="fctSelect" data-mst-idx='<c:out value="${facilitiesList.fctMstIdx }" />' <c:if test='${facilitiesList.fctMstIdx eq searchVO.fctMstIdx}' >style="color:red" </c:if> >
					<c:out value="${facilitiesList.fctNm }" />
				</span>
			</li>
			</c:if>
		</c:forEach>
	</ul>

	<c:set var="dowNm" value="${fn:split(' ,일,월,화,수,목,금,토', ',') }" />
	<form:form commandName="reqFacilitiesMasterVO" name="form" method="GET">
		<form:hidden path="fctMstIdx"/>
		<form:hidden path="year"/>
		<form:hidden path="month"/>
		<form:hidden path="srtDay"/>
		<form:hidden path="sumDay"/>
		<form:hidden path="rsvtPlcCd"/>
	</form:form>

	<div class="mt_20 mb_10 tac">
		<strong id="subTitl" class="fl"></strong>
		<strong id="dateSpan"></strong >
		<div class="fr">
			<span id="toDay">오늘</span> | <span id="prev">◀이전</span> | <span id="next">다음▶</span>
		</div>
	</div>

	<div class="insideTbl">
		<table>
			<thead>
					<tr>
						<th>시간</th>
						<c:forEach items="${calendarList }" var="item" varStatus="status" >
							<th<c:if test="${!empty item.clndDt }"> data-clnd-dt='<c:out value="${item.clndDt }"/>'</c:if>>
								<c:if test="${!empty item.clndDt }">
								<fmt:parseDate var="clndDate" value="${item.clndDt }" pattern="yyyy.MM.dd"  />
								<fmt:formatDate value="${clndDate }" pattern="M월 d일"/>(<c:out value="${dowNm[item.dow] }"/>)
								</c:if>
							</th>
						</c:forEach>
					</tr>
			</thead>
			<tbody>
			<c:forEach items="${timeLineList }" var="item">
				<tr>
					<td><c:out value="${item.tm }"/></td>
				<c:forEach items="${calendarList }" var="item1" varStatus="status" >
					<td data-dttm='<c:out value="${item1.clndDt }"/> <c:out value="${item.tm }"/>'></td>
				</c:forEach>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>