<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">

function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]')
	.attr('action', '<c:url value="/information/newsletter/newsletterList.do"/>')
	.submit();
}

function fn_sort(sortType, num){
	$("#sortType").val(sortType+"_"+num);
	$('form[name=searchFm]')
	.attr('action', '<c:url value="/information/newsletter/newsletterList.do"/>')
	.submit();
}

//뉴스레터 상세
function fn_info(value){
	$("form[name=infoFm]").find("input[name=newsLttrIdx]").val(value);
	$("form[name=infoFm]")
		.attr('action', '<c:out value="/information/newsletter/newsletterInfo.do" />')
	 	.submit();
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

	//등록창
	$("#btnRegist").on('click',function(){
		$("form[name=infoFm]")
		 .attr('action', '<c:out value="/information/newsletter/newsletterInfo.do" />')
		 .submit();
	});

	$(document).ready(function(){

		$("#titl").val('<c:out value="${searchVO.titl}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("select[name=useYn]").val('<c:out value="${searchVO.useYn}" />');

		var sortType = '<c:out value="${searchVO.sortType}" />';
		$("select[name=sortType]").val(sortType);
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

		$('form[name=searchFm]').attr("action",'<c:out value="/information/newsletter/newsletterList.do" />');
	});

});

</script>

<div class="location_area"><h3>뉴스레터 관리</h3></div>
<div class="subcon_area">
	<form name="searchFm" method="GET" >
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width=""/>
				<col width="100px"/>
			</colgroup>
			<tr>
				<th>제목</th>
				<td><input type="text" id="titl" name="titl" style="width:100%" maxlength="50"/></td>
				<td rowspan="3">
					<input type="submit" class="btnSearch" value="조회" />
				</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>
					<input type="text" id="creDttmOne" name="creDttmOne" style="width:100px" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더"> ~
					<input type="text" id="creDttmTwo" name="creDttmTwo" style="width:100px" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더">
				</td>
			</tr>
			<tr>
				<th>전시여부</th>
				<td>
					<select id="useYn" name="useYn" style="width:100px">
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
		<strong>뉴스레터 목록</strong><span class="num">(<c:out value="${paginationInfo.totalRecordCount }" />)</span>
		<div class="fr">
		<sec:authorize access="hasRole('023007002Y')">
			<button id="btnRegist" class="btnBasic_blue">뉴스레터 등록</button>
		</sec:authorize>
		</div>
	</div>
	<div class="basicTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width=""/>
				<col width="40%"/>
				<col width="20%"/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>발행호</th>
				<th>제목</th>
				<th>
					<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">등록일시▽</a>
					<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">등록일시△</a>
				</th>
				<th>등록자</th>
				<th>조회</th>
				<th>
					<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">전시▽</a>
					<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">전시△</a>
				</th>
			</tr>
			<c:forEach items="${newsletterList}" var="newsletterList" >
				<tr onclick='fn_info(<c:out value="${newsletterList.newsLttrIdx }" />)' onmouseover="this.style.backgroundColor='yellow'" onmouseout="this.style.backgroundColor=''" style="cursor:pointer">
					<td><c:out value="${newsletterList.newsLttrNo }" /></td>
					<td><c:out value="${newsletterList.titl }" /></td>
					<td><c:out value="${newsletterList.creDttm }" /></td>
					<td><c:out value="${newsletterList.mngrMbrNm }" /></td>
					<td><c:out value="${newsletterList.hitCnt }" /></td>
					<td><c:out value="${newsletterList.useYn eq 'Y' ? 'ON' : 'OFF' }" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
		<ul class='page'>
			<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
		</ul>

		<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
	</form>
</div>
<form:form commandName="reqNewsLetterVO" name="infoFm" method="GET">
	<form:hidden path="newsLttrIdx"/>
</form:form>
