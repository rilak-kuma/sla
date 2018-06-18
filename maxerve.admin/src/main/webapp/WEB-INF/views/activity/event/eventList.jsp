<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
}

function fn_refInfo(evtIdx) {
	$("#evtIdx").val(evtIdx);
	$('form[name=infoFm]').submit();
}

jQuery(function($){

	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#creDttmOne" )
	        .datepicker({
	          defaultDate: "+1w",
	          changeMonth: true,
	          numberOfMonths: 1
	        }).on( "change", function() {
	          to.datepicker( "option", "minDate", getDate( this ) );
	        }),
	      to = $( "#creDttmTwo" ).datepicker({
	    	  defaultDate: "+1w",
	        changeMonth: true,
	        numberOfMonths: 1
	      }).on( "change", function() {
	        from.datepicker( "option", "maxDate", getDate( this ) );
	      });

	    function getDate( element ) {
	      var date;
	      try {
	        date = $.datepicker.parseDate( dateFormat, element.value );
	      } catch( error ) {
	        date = null;
	      }
	      return date;
	    }
	});

	$(document).ready(function(){
		$("#evtTitl").val('<c:out value="${searchVO.evtTitl}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("#useYn").val('<c:out value="${searchVO.useYn}" />');

	});

});
</script>

<div class="location_area"><h3>이벤트</h3></div>

<form name="searchFm" method="post" >
<div class="subcon_area">
	<div class="basicTbl02">
		<table>
			<tr>
				<th>카테고리</th>
				<td>
					<select id="ctgrIdx" name="ctgrIdx" style="width:100px">
						<option value="">전체</option>
						<c:forEach var="ctgList" items="${ctgList}" varStatus="varStatus">
							<option value="${ctgList.ctgrIdx}" <c:if test="${searchVO.ctgrIdx == ctgList.ctgrIdx}">selected="selected"</c:if> ><c:out value="${ctgList.ctgrNm}" /></option>
						</c:forEach>
					</select>
				</td>
				<th>진행상태</th>
				<td>
					<select id="useYn" name="useYn" style="width:100px">
						<option value="">전체</option>
						<option value="Y">진행중</option>
						<option value="N">진행완료</option>
					</select>
				</td>
				<td rowspan="3"><input type="submit" value="조회" class="btnSearch"/></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td colspan="3">
					<input type="text" id="creDttmOne" name="creDttmOne" style="width:100px" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더"><span class="mr_20">부터</span>
					<input type="text" id="creDttmTwo" name="creDttmTwo" style="width:100px" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더"><span>까지</span>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					<input type="text" name="evtTitl" maxlength="50" style="width:70%"/>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="sortType" name="sortType" />

	<div class="stit mt_20 mb_10">
		<strong>이벤트 </strong>총<strong><fmt:formatNumber value="${eventTotalCnt}" groupingUsed="true"/></strong>건
	</div>
	<div class="basicTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width=""/>
				<col width=""/>
				<col width="50%"/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>번호</th>
				<th>카테고리</th>
				<th>제목</th>
				<th>등록자</th>
				<th>등록일</th>
				<th>진행상태</th>
			</tr>
			<c:choose>
				<c:when test="${fn:length(eventList) > 0 }">
					<c:forEach items="${eventList}" var="eventList" varStatus="varStatus">
						<tr onclick="fn_refInfo(${eventList.evtIdx })" onmouseover="this.style.backgroundColor='yellow'" onmouseout="this.style.backgroundColor=''" style="cursor:pointer">
								<td><c:out value="${eventList.rownum }" />(${eventList.evtIdx })</td>
								<td><c:out value="${eventList.ctgrIdxNm }" /></td>
								<td><c:out value="${eventList.evtTitl }" /></td>
								<td><c:out value="${eventList.ceoNm }" /><c:if test="${eventList.oztnNm != ''}">/</c:if><c:out value="${eventList.oztnNm }" /></td>
								<td><c:out value="${eventList.creDttm }" /></td>
								<td><c:out value="${eventList.useYn=='Y'?'진행중':'진행완료' }" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="6" align="center">조회결과가 없습니다.</td>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

	<ul class="page">
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image'/>
	</ul>
	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
</div>
</form>

<form name="infoFm" method="post" action="<c:url value='/activity/event/eventInfo.do'/>">
	<input type="hidden" id="evtIdx" name="evtIdx" />
</form>
