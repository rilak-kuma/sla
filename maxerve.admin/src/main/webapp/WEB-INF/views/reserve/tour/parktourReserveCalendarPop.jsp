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

	
<c:forEach	items="${reserveList }" var="reserveList" varStatus="status">
	var dttm = '<c:out value="${reserveList.clndDt } ${reserveList.tm}" />';
	var tourDsdDttm = '<c:out value="${reserveList.tourDsdDttm }" />'; 
	var str = "";
	var tourPgrNm = '<c:out value="${reserveList.tourPgrNm }" />'; //예약진행상태 코드
	var oztnNm = '<c:out value="${reserveList.oztnNm }" />'; //단체명
	var appyNm = '<c:out value="${reserveList.appyNm }" />'; //신청자명 
	
	if(oztnNm != ""){
		if(appyNm == oztnNm ){
			str = oztnNm;
		}else if( appyNm !=""){
			str = oztnNm +"( "+ appyNm+" )";
		}else{
			str = oztnNm ;
		}
	}
	
	str += ('<br/>'+tourPgrNm);
	
	var info = '';
	info += tourDsdDttm +'<br/>';
	info += str+'<br/>';

	var $td = $('td[data-dttm="' + dttm + '"]');
	$td.addClass('reserved');

	if (info) {
		$td.attr('data-tour-idx', '<c:out value="${reserveList.tourAplyIdx }"/>');
		$td.attr('data-tour-idx2', '<c:out value="${reserveList.clndDt }_${reserveList.tourAplyIdx }"/>');
	}

	$td.append(info);
</c:forEach>

	var dateSpan = "";
	dateSpan = $('th[data-clnd-dt]:first').attr('data-clnd-dt') + ' ~ ' +  $('th[data-clnd-dt]:last').attr('data-clnd-dt');

	$("#dateSpan").text(dateSpan);

	$('td[data-tour-idx2]').each(function(){
		var $list = $('td[data-tour-idx2="' + $(this).attr('data-tour-idx2') + '"]');

		if ($list.length > 1) {
			$list.eq(0).attr('rowSpan', $list.length);
			$list.filter(':gt(0)').remove();
		}
	});
	
	/*
	$('td.reserved')
	.on('click', function(){
		//fnGoInfo($(this).attr('data-rsvt-idx'));
		
		return false;
	});
	*/
});

</script>

<div class="location_area"><h3><c:out value="${mainTitle }" />방문 예약현황</h3></div>
<div class="subcon_area">

	<c:set var="dowNm" value="${fn:split(' ,일,월,화,수,목,금,토', ',') }" />

	<div class="mt_20 mb_10 tac">
		<form:form commandName="ReqParkTourVO" name="form" method="GET">
			<form:hidden path="year"/>
			<form:hidden path="month"/>
			<form:hidden path="srtDay"/>
			<form:hidden path="sumDay"/>

			<%-- <div class="fl">
				<select  name="fctMstIdx" >
					<c:forEach var="facilitiesList" items="${facilitiesList }" >
						<c:set var="selected">
						<c:if test="${reqFacilitiesMasterVO.fctMstIdx eq facilitiesList.fctMstIdx }"> SELECTED</c:if>
						</c:set>

						<c:choose>
							<c:when test="${mainTitle.indexOf('미래청(회의/세미나)') == 0 }" >
								<c:if test="${facilitiesList.fctNm.indexOf('모임방') < 0 }" >
									<option value='<c:out value="${facilitiesList.fctMstIdx }" />'${selected }>
										<c:out value="${facilitiesList.fctNm }" />
									</option>
								</c:if>
							</c:when>
							<c:when test="${mainTitle.indexOf('모임방') > 0 }" >
								<c:if test="${facilitiesList.fctNm.indexOf('모임방') > 0 or facilitiesList.fctNm.indexOf('오픈스페이스') == 0 }" >
									<option value='<c:out value="${facilitiesList.fctMstIdx }" />'${selected }>
										<c:out value="${facilitiesList.fctNm }" />
									</option>
								</c:if>
							</c:when>
							<c:otherwise>
								<option value='<c:out value="${facilitiesList.fctMstIdx }" />'${selected }>
									<c:out value="${facilitiesList.fctNm }" />
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach> 
				</select>
			</div>--%>
		</form:form>
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