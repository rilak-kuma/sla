<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script type="text/javascript">

function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]')
	.submit();
}

function fn_ntcInfo(ntcIdx){
	$("#rcrtRoomIdx").val(ntcIdx);
	$('form[name=infoFm]').submit();
}

function fn_sort(sortType, num){
	$("#sortType").val(sortType+"_"+num);
	$('form[name=searchFm]').submit();
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

	//엑셀 다운로드
	$("#btnExcelDown").on("click",function(){
		$('form[name=searchFm]')
		 .attr({
			 target: '_blank',
			 action: '<c:url value="/information/recruit/recruitListExcel.do" />'
		 })
		 .submit();

		 $('form[name=searchFm]').removeAttr("target");
		 $('form[name=searchFm]').removeAttr("action");
	});

	$(document).ready(function(){
		$("#titl").val('<c:out value="${searchVO.titl}" />');
		$("#ceoNm").val('<c:out value="${searchVO.ceoNm}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("#useYn").val('<c:out value="${searchVO.useYn}" />');

		var sortType = '<c:out value="${searchVO.sortType}" />';

		if(sortType == 'sortOne_1'){
			$("#sortOne_1").hide();
			$("#sortTwo_2").hide();
		}else if(sortType == 'sortTwo_1'){
			$("#sortOne_2").hide();
			$("#sortTwo_1").hide();
		}else{
			$("#sortOne_2").hide();
			$("#sortTwo_2").hide();
		}

	});

});
</script>

<div class="location_area"><h3>구인</h3></div>
<div class="subcon_area">
	<form name="searchFm" method="GET" action='<c:url value="/information/recruit/recruitList.do"/>' >
	<div class="small_list">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" id="titl" name="titl" style="width:90%" maxlength="50"/></td>
				<th>등록자</th>
				<td><input type="text" id=ceoNm name="ceoNm" style="width:90%" maxlength="50"/></td>
				<td rowspan="2"><input type="submit" class="btnSearch" value="조회" /></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>
					<input type="text" id="creDttmOne" name="creDttmOne" readonly="readonly" style="width:100px"/><img src="../../img/ico_cal.png" alt="캘린더"><span class="mr_20">부터</span>
					<input type="text" id="creDttmTwo" name="creDttmTwo" readonly="readonly" style="width:100px"/><img src="../../img/ico_cal.png" alt="캘린더"><span>까지</span>
				</td>
				<th>전시여부</th>
				<td>
					<select id="useYn" name="useYn" style="width:100px;" >
						<option value="">전체</option>
						<option value="Y">ON</option>
						<option value="N">OFF</option>
					</select>
				</td>
			</tr>
		</table>
	</div>

	<input type="hidden" id="sortType" name="sortType" />
	<div class="stit mt_20 mb_10">
		<strong>구인</strong>(<span class="num"><c:out value="${fn:length(recruitList)}" />)</span>
		<div class="fr">
			<button id="btnExcelDown" class="btnBasic_black">엑셀저장</button>
		</div>
	</div>

	<div class="basicTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width=""/>
				<col width="50%"/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>
				<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">등록일시▽</a>
				<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">등록일시△</a>
				</th>
				<th>등록자</th>
				<th>조회</th>
				<th>
					<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">상태▽</a>
					<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">상태△</a>
				</th>
			</tr>
			<c:choose>
				<c:when test="${fn:length(recruitList) > 0 }">
					<c:forEach items="${recruitList}" var="recruitList" varStatus="varStatus">
						<tr onclick="fn_ntcInfo(${recruitList.rcrtRoomIdx })" >
								<td><c:out value="${recruitList.rcrtRoomIdx}" /></td>
								<td><c:out value="${recruitList.titl }" /></td>
								<td><c:out value="${recruitList.creDttm }" /></td>
								<td><c:out value="${recruitList.ceoNm }" /><c:if test="${recruitList.oztnNm != ''}">/</c:if><c:out value="${recruitList.oztnNm }" /></td>
								<td><c:out value="${recruitList.hitCnt }" /></td>
								<td><c:out value="${recruitList.useYn=='Y'?'ON':'OFF' }" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="6" align="center">조회결과가 없습니다.</td>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

	<ul class='page'>
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>

	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
	</form>
</div>
<form name="infoFm" method="GET" action='<c:url value="/information/recruit/recruitInfo.do"/>'>
	<input type="hidden" id="rcrtRoomIdx" name="rcrtRoomIdx" />
</form>
