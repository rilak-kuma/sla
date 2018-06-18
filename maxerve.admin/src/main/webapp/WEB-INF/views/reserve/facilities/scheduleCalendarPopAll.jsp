<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
table th {width: 150px;}
ul {padding: 0; margin:0;}
ul, li {list-style: none;}

<c:set var='color' value='<%=new String[] {"#80F5FF","#00D8FF","#4374D9","#8041D9","#F361DC","#FF0000","#E5D85C","#6B9900","#FFBB00","#FF00DD"}%>' />

<c:forEach	items="${fctMstIdxAll }" var="fctMstIdx" varStatus="status">
td.reserved<c:out value="${fctMstIdx}"/> {background-color: <c:out value="${color[(status.count-1)%10]}"/>;}
</c:forEach>
</style>
<script>
function fnGoInfo(fctRsvtIdx){

	opener.$('form[name=infoFm]')
		.find('input[name=rsvtPlcCd]').val('<c:out value="${searchVO.rsvtPlcCd}" />')
		.end()
		.find('input[name=fctGubn]').val('<c:out value="${searchVO.fctGubn}" />')
		.end()
		.append('<input type="hidden" name="fctRsvtIdx"/>')
		.find('input[name=fctRsvtIdx]').val(fctRsvtIdx)
		.end()
		.attr('action', '<c:out value="/reserve/facilities/reserve_info.do" />')
		.submit();

	self.close();
}

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
	/*$('select[name=fctMstIdx]').on('change', function(){
		$("form[name=form]")
			.find("input[name=sumDay]")
			.val('')
			.end()
			.submit();
	});*/
	
<c:forEach	items="${fctMstIdxAll }" var="fctMstIdx" varStatus="status">
    /*var fctMstIdxA=${fctMstIdx}; // ex) 혁신광장 : fctMstIdxA=11, 피아노숲 : fctMstIdxA=12, 전봇대집 : fctMstIdxA=13
	var count = ${status.count-1};*/
	<c:set value="${reserveListAll[status.count-1]}" var="reserveListA" />
	<c:set value="${eqpListAll[status.count-1]}" var="eqpList"  />
    
	<c:forEach	items="${reserveListA }" var="reserveList" varStatus="status">
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
		//console.log('<c:out value="${reserveList.oztnNm }(${reserveList.assOztnNm })" />');
		if( "${reserveList.evtNm }" != ""){
			info += '행사명 : <c:out value="${reserveList.evtNm }" />';
		}
		//info += '예약번호 : <c:out value="${reserveList.fctRsvtIdx }" />';
	
		//예약장비(갯수)추가 2017/08/03 sbj  
		<c:forEach items="${eqpList}" var="list" varStatus="status">
			if("${list.fctRsvtMstIdx}" == "${reserveList.fctRsvtIdx}"){
			  info += '<br>'+'${list.eqpNm}'+' ('+ '${list.eqpCnt}' +')';
			}
		</c:forEach>
	
		//var $td11 = $('td[data-dttm11="' + dttm + '"]');
		var $td<c:out value="${fctMstIdx}"/> = $('td[data-dttm<c:out value="${fctMstIdx}"/>="' + dttm + '"]');
		
		//$td11.addClass('reserved11');
		$td<c:out value="${fctMstIdx}"/>.addClass('reserved<c:out value="${fctMstIdx}"/>');
		
		//예약현황
		if (info) {
			//$td11.attr('data-rsvt-idx11_1', '<c:out value="${reserveList.fctRsvtIdx }"/>');
			//$td11.attr('data-rsvt-idx11_2', '<c:out value="${reserveList.clndDt }_${reserveList.fctRsvtIdx }"/>');
			$td<c:out value="${fctMstIdx}"/>.attr('data-rsvt-idx<c:out value="${fctMstIdx}"/>_1', '<c:out value="${reserveList.fctRsvtIdx }"/>');
			$td<c:out value="${fctMstIdx}"/>.attr('data-rsvt-idx<c:out value="${fctMstIdx}"/>_2', '<c:out value="${reserveList.clndDt }_${reserveList.fctRsvtIdx }"/>');
		}
		
	    //$td11.append(info);
		$td<c:out value="${fctMstIdx}"/>.append(info);
		
	</c:forEach>

	/*$('td[data-rsvt-idx11_2]').each(function(){
	var $list = $('td[data-rsvt-idx11_2="' + $(this).attr('data-rsvt-idx11_2') + '"]');

	if ($list.length > 1) {
		$list.eq(0).attr('rowSpan', $list.length);
		$list.filter(':gt(0)').remove();
	}
    });*/	
	$('td[data-rsvt-idx<c:out value="${fctMstIdx}"/>_2]').each(function(){
		var $list = $('td[data-rsvt-idx<c:out value="${fctMstIdx}"/>_2="' + $(this).attr('data-rsvt-idx<c:out value="${fctMstIdx}"/>_2') + '"]');

		if ($list.length > 1) {
			$list.eq(0).attr('rowSpan', $list.length);
			$list.filter(':gt(0)').remove();
		}
	});
		
	/*$('td.reserved11')
	.on('click', function(){
		fnGoInfo($(this).attr('data-rsvt-idx11_1'));
		
		return false;
	});*/	
	$('td.reserved<c:out value="${fctMstIdx}"/>')
	.on('click', function(){
		fnGoInfo($(this).attr('data-rsvt-idx<c:out value="${fctMstIdx}"/>_1'));
		
		return false;
	});
	
	var dateSpan = "";
	dateSpan = $('th[data-clnd-dt]:first').attr('data-clnd-dt') + ' ~ ' +  $('th[data-clnd-dt]:last').attr('data-clnd-dt');

	$("#dateSpan").text(dateSpan);	
	
</c:forEach>	
	
});

</script>

<div class="location_area"><h3><c:out value="${mainTitle }" /> 예약현황
<%-- (<c:forEach	items="${fctNmAll }" var="fctNm" varStatus="status">
	<c:if test="${status.count ne 1 }">,</c:if>
<font color=<c:out value="${color[(status.count-1)%10]}"/> size="2"><c:out value="${fctNm}"/></font>
</c:forEach>
) --%>
</h3>
</div>
<div class="subcon_area">

	<c:set var="dowNm" value="${fn:split(' ,일,월,화,수,목,금,토', ',') }" />

	<div class="mt_20 mb_10 tac">
		<form:form commandName="reqFacilitiesMasterVO" name="form" method="GET">
			<form:hidden path="year"/>
			<form:hidden path="month"/>
			<form:hidden path="srtDay"/>
			<form:hidden path="sumDay"/>
			<form:hidden path="rsvtPlcCd"/>
			<form:hidden path="fctGubn"/>
			<form:hidden path="fctType"/>
			<form:hidden path="fctMstIdx"/>
			<form:hidden path="fctNm"/>
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
			</div> --%>
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
						    <%-- <c:forEach	items="${fctMstIdxAll }" var="fctMstIdx" varStatus="status"> --%>
							<th<c:if test="${!empty item.clndDt }"> data-clnd-dt='<c:out value="${item.clndDt }"/>'</c:if> colspan="${fn:length(fctMstIdxAll) }">
								<c:if test="${!empty item.clndDt }">
								<fmt:parseDate var="clndDate" value="${item.clndDt }" pattern="yyyy.MM.dd"  />
								<fmt:formatDate value="${clndDate }" pattern="M월 d일"/>(<c:out value="${dowNm[item.dow] }"/>)
								</c:if>
							</th>
							<%-- </c:forEach> --%>
						</c:forEach>
					</tr>
					<tr>
						<th></th>
						<c:forEach items="${calendarList }" var="item" varStatus="status" >
						    <c:forEach	items="${fctNmAll }" var="fctNm" varStatus="status">
								<%-- <th style="background-color: <c:out value="${color[(status.count-1)%10]}"/>"><c:out value="${fctNm }"/></th> --%>
							    <th><c:out value="${fctNm }"/></th>
							</c:forEach>
						</c:forEach>
					</tr>					
			</thead>
			<tbody>
			<c:forEach items="${timeLineList }" var="item">
				<tr>
					<td><c:out value="${item.tm }"/></td>
				<c:forEach items="${calendarList }" var="item1" varStatus="status" >
					<c:forEach	items="${fctMstIdxAll }" var="fctMstIdx" varStatus="status">
						<td data-dttm<c:out value="${fctMstIdx}"/>='<c:out value="${item1.clndDt }"/> <c:out value="${item.tm }"/>'></td>
					</c:forEach>
				</c:forEach>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>