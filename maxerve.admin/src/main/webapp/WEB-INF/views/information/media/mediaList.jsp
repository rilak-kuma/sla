<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
tr[data-top-yn=Y] {background-color: #EEE;}
</style>
<script type="text/javascript">

function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
}

function fn_mdaInfo(mdaIdx){
	$("#mdaIdx").val(mdaIdx);
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

	//등록폼호출
	$("#btnRegFm").on('click', function(){
		location.href="/information/media/mediaInfo.do";
	});

	//엑셀 다운로드
	$("#btnExcelDown").on("click",function(){
		$('form[name=searchFm]')
		 .attr({
			 target: '_blank',
			 action: '<c:url value="/information/media/mediaListExcel.do" />'
		 })
		 .submit();

		 $('form[name=searchFm]').removeAttr("target");
		 $('form[name=searchFm]').removeAttr("action");
	});

	$(document).ready(function(){
		$("#titl").val('<c:out value="${searchVO.titl}" />');
		$("#writer").val('<c:out value="${searchVO.writer}" />');
		$("#mngrMbrNm").val('<c:out value="${searchVO.mngrMbrNm}" />');
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
<div class="location_area"><h3>언론보도</h3></div>
<div class="subcon_area">
	<form name="searchFm" method="post" >
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>제목</th>
				<td><input type="text" id="titl" name="titl" style="width:90%" maxlength="50"/></td>
				<td rowspan="5">
					<input type="submit" class="btnSearch" value="조회" />
				</td>
			</tr>
			<tr>
				<th>기사작성자</th>
				<td><input type="text" id="writer" name="writer" style="width:90%" maxlength="50"/></td>
			</tr>
			<tr>
				<th>등록자</th>
				<td><input type="text" id="mngrMbrNm" name="mngrMbrNm" style="width:90%" maxlength="50"/></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>
					<input type="text" id="creDttmOne" name="creDttmOne" readonly="readonly"/> 부터
					<input type="text" id="creDttmTwo" name="creDttmTwo" readonly="readonly"/> 까지
				</td>
			</tr>
			<tr>
				<th>전시여부</th>
				<td>
					<select id="useYn" name="useYn" >
						<option value="">전체</option>
						<option value="Y">ON</option>
						<option value="N">OFF</option>
					</select>
				</td>
			</tr>
		</table>
		<input type="hidden" id="sortType" name="sortType" />
	</div>

	<div class="stit mt_20 mb_10">
		<strong>언론보도<span class="num">( <c:out value="${paginationInfo.totalRecordCount}" /> )</span></strong>
		<div class="fr">
		<sec:authorize access="hasRole('023007001Y')">
			<button id="btnRegFm" class="btnBasic_blue">글등록</button>
		</sec:authorize>
			<button id="btnExcelDown" class="btnBasic_black">엑셀저장</button>
		</div>
	</div>
	<div class="basicTbl">
		<table>
			<colgroup>
				<col width="10%">
				<col width="35%">
				<col width="10%">
				<col width="15%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>기사작성자</th>
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
			</thead>
			<tbody>
			<c:forEach items="${mdaList}" var="mList" varStatus="varStatus">
				<tr onclick="fn_mdaInfo(${mList.mdaIdx })">
					<td><c:out value="${mList.mdaIdx }" /></td>
					<td><c:out value="${mList.titl }" /></td>
					<td><c:out value="${mList.writer }" /></td>
					<td><c:out value="${mList.creDttm }" /></td>
					<td><c:out value="${mList.mngrMbrNm }" />/<c:out value="${mList.mngrMbrDpt }" /></td>
					<td><c:out value="${mList.hitCnt }" /></td>
					<td><c:out value="${mList.useYn=='Y'?'ON':'OFF' }" /></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
		<ul class='page'>
			<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
		</ul>

		<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
	</form>
</div>

<form name="infoFm" method="post" action="<c:url value='/information/media/mediaInfo.do'/>">
	<input type="hidden" id="mdaIdx" name="mdaIdx" />
</form>