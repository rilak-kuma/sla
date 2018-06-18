<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
}

function fn_Info(fswTalkIdx) {
	$("#fswTalkIdx").val(fswTalkIdx);
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

	//등록폼호출
	$("#btnRegFm").on('click', function(){
		location.href="/activity/propose/fswInfo.do";
	});

	$(document).ready(function(){
		$("#creDttmOne").val("<c:out value='${searchVO.creDttmOne}'/>");
		$("#creDttmTwo").val("<c:out value='${searchVO.creDttmTwo}'/>");
		$("#titl").val("<c:out value='${searchVO.titl}'/>");
		$("#useYn").val('<c:out value="${searchVO.useYn}" />');
	});

});
</script>

<div class="location_area"><h3>아이디어/협업(탑)</h3></div>
<div class="subcon_area">
	<form:form commandName="searchVO" name='searchFm' method='post'>
	<div class="basicTbl02">
		<table>
		<caption></caption>
		<colgroup>
			<col width="150px"/>
			<col width=""/>
			<col width="150px"/>
			<col width=""/>
			<col width="150px"/>
			<col width=""/>
		</colgroup>
			<tr>
				<th>진행상태</th>
				<td>
					<form:select path="useYn" cssStyle="width:100px">
						<option value="">전체</option>
						<option value="Y">진행중</option>
						<option value="N">미진행</option>
					</form:select>
				</td>
				<td rowspan="3"><input type="submit" class="btnSearch ml_5" value="검색" /></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>
					<input type="text" id="creDttmOne" name="creDttmOne" readonly="readonly" style="width:100px"/><img src="../../img/ico_cal.png" alt="캘린더"><span class="mr_20">부터</span>
					<input type="text" id="creDttmTwo" name="creDttmTwo" readonly="readonly" style="width:100px"/><img src="../../img/ico_cal.png" alt="캘린더"><span>까지</span>
				</td>
			</tr>
			<tr>
				<th>검색어</th>
				<td><input id="titl" name="titl" style="width:70%"/></td>
			</tr>
		</table>
	</div>
	<div class="stit mt_20 mb_10">
		<strong>진행중</strong><span class="num">총 <fmt:formatNumber value="${fn:length(useFswList)}" groupingUsed="true"/>건</span>
	<sec:authorize access="hasRole('023004005Y')">
		<button id="btnRegFm" class="btnBasic_blue fr">등록</button>
	</sec:authorize>
	</div>

	<div class="basicTbl">
		<table>
		<caption></caption>
		<colgroup>
			<col width="10%"/>
			<col width="50%"/>
			<col width=""/>
			<col width=""/>
		</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>기간</th>
				<th>진행상태</th>
			</tr>
			<c:forEach items="${useFswList }" var="useFswList" varStatus="varStatus">
				<tr>
					<td><c:out value="${useFswList.ord }" /></td>
					<td class="tit"><a href="#none" onclick="fn_Info(${useFswList.fswTalkIdx })" ><c:out value="${useFswList.titl }" /></a></td>
					<td><c:out value="${useFswList.srtDt }" /> ~ <c:out value="${useFswList.endDt }" /></td>
					<td><c:out value="${useFswList.useYn == 'Y'?'ON':'OFF' }" /></td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(useFswList) == 0 }" >
				<tr>
					<td colspan="4">내용이 없습니다.</td>
				</tr>
			</c:if>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>전체</strong><span class="num">총 <fmt:formatNumber value="${sympathyTalkTotalCnt}" groupingUsed="true"/>건</span>
	</div>

	<div class="basicTbl">
		<table>
		<caption></caption>
		<colgroup>
			<col width="10%"/>
			<col width="50%"/>
			<col width=""/>
			<col width=""/>
		</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>기간</th>
				<th>진행상태</th>
			</tr>
			<c:forEach items="${fswList }" var="fswList" varStatus="varStatus">
			<tr>
				<td><c:out value="${fswList.rownum }" /></td>
				<td class="tit"><a href="#none" onclick="fn_Info(${fswList.fswTalkIdx })" ><c:out value="${fswList.titl }" /></a></td>
				<td><c:out value="${fswList.srtDt }" /> ~ <c:out value="${fswList.endDt }" /></td>
				<td><c:out value="${fswList.useYn == 'Y'?'ON':'OFF' }" /></td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(useFswList) == 0 }" >
				<tr>
					<td colspan="4">내용이 없습니다.</td>
				</tr>
			</c:if>
		</table>
	</div>

	<ul class="page">
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>

	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>

	</form:form>
</div>

<form name="infoFm" method="post" action="<c:url value='/activity/propose/fswInfo.do' />" >
	<input type="hidden" id="fswTalkIdx" name="fswTalkIdx" />
</form>