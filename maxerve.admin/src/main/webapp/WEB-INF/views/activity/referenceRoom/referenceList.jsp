<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
}

function fn_list(ctgrIdx){
	$("#pageIndex").val("1");
	$("#titl").val("");
	$("#creDttmOne").val("");
	$("#sortType").val("");
	$("#ctgrIdx").val(ctgrIdx);
	$('form[name=searchFm]').submit();
}

function fn_refInfo(refRoomIdx) {
	$("#refRoomIdx").val(refRoomIdx);
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

	$("#btnExcelDown").on("click",function(){
		$('form[name=searchFm]')
		 .attr({
			 target: '_blank',
			 action: '<c:out value="/activity/referenceRoom/referenceListExcel.do" />'
		 })
		 .submit();

		 $('form[name=searchFm]').removeAttr("target");
		 $('form[name=searchFm]').attr("action",'<c:out value="/activity/referenceRoom/referenceList.do" />');
	});

	$(document).ready(function(){
		$("#titl").val('<c:out value="${searchVO.titl}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');

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
<div class="location_area"><h3>자료실</h3></div>

<div class="subcon_area">
<h4>자료실 현황</h4>
	<div class="basicTbl02">
		<table>
			<tr>
				<c:forEach var="ctgrRefCntList" items="${ctgrRefCntList}" varStatus="varStatus">
					<th><c:out value="${ctgrRefCntList.ctgrIdxNm }" /></th>
				</c:forEach>
				<th><strong>Total</strong></th>
			</tr>
			<tr>
				<c:forEach var="ctgrRefCntList" items="${ctgrRefCntList}" varStatus="varStatus">
					<td>
					<a href="#none" onclick="fn_list('<c:out value="${ctgrRefCntList.ctgrIdx}" />')" >
					<c:out value="${ctgrRefCntList.ctgrRefCnt }" /><c:set var="totalCnt" value="${totalCnt+ctgrRefCntList.ctgrRefCnt }"/>
					</a>
					</td>
				</c:forEach>
				<td><a href="#none" onclick="fn_list('')" ><c:out value="${totalCnt }" /></a></td>
			</tr>
		</table>
	</div>

<form name="searchFm" method="post"  >
<div class="small_list">
<table>
	<tr>
		<th>제목</th>
		<td>
			<input type="text" name="titl" maxlength="50px" style="width:70%"/>
		</td>
		<th>분류</th>
		<td>
			<select id="ctgrIdx" name="ctgrIdx" style="width:120px">
				<option value="">전체</option>
				<c:forEach var="ctgList" items="${ctgList}" varStatus="varStatus">
					<option value="${ctgList.ctgrIdx}" <c:if test="${searchVO.ctgrIdx == ctgList.ctgrIdx}">selected="selected"</c:if> ><c:out value="${ctgList.ctgrNm}" /></option>
				</c:forEach>
			</select>
		</td>
		<td rowspan="2"><input type="submit" class="btnSearch" value="조회" /></td>
	</tr>
	<tr>
		<th>등록일</th>
		<td colspan="3">
			<input type="text" id="creDttmOne" name="creDttmOne" style="width:100px" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더"><span class="mr_20">~</span>
			<input type="text" id="creDttmTwo" name="creDttmTwo" style="width:100px" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더">
		</td>
	</tr>
</table>
</div>

<div class="stit mt_20 mb_10"><strong>자료실</strong>(<c:out value="${fn:length(referenceList)}" />)</div>
<button id="btnExcelDown">엑셀저장</button>
	<div class="basicTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width="50%"/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>제목</th>
				<th>분류</th>
				<th>등록자</th>
				<th>
					<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">등록일▽</a>
					<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">등록일△</a>
				</th>
				<th>
					<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">전시▽</a>
					<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">전시△</a>
				</th>
			</tr>
			<c:choose>
				<c:when test="${fn:length(referenceList) > 0 }">
					<c:forEach items="${referenceList}" var="referenceList" varStatus="varStatus">
						<tr onclick="fn_refInfo('<c:out value="${referenceList.refRoomIdx }" />')" onmouseover="this.style.backgroundColor='yellow'" onmouseout="this.style.backgroundColor=''" style="cursor:pointer">
								<td><c:out value="${referenceList.titl }" /></td>
								<td><c:out value="${referenceList.ctgrIdxNm }" /></td>
								<td><c:out value="${referenceList.ceoNm }" /><c:if test="${referenceList.oztnNm != ''}">/</c:if><c:out value="${referenceList.oztnNm }" /></td>
								<td><c:out value="${referenceList.creDttm }" /></td>
								<td <c:if test="${referenceList.useYn=='Y'}">class="emtxt"</c:if>><c:out value="${referenceList.useYn=='Y'?'ON':'OFF' }" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="5" align="center">조회결과가 없습니다.</td>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

	<input type="hidden" id="sortType" name="sortType" />
	<ul class='page'>
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>

	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
</form>
</div>

<form name="infoFm" method="post" action="<c:url value='referenceInfo.do'/>" >
	<input type="hidden" id="refRoomIdx" name="refRoomIdx" />
</form>
