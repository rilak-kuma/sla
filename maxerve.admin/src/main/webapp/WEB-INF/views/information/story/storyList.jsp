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

function fn_storyInfo(refRoomIdx){
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

	//등록폼호출
	$("#btnRegFm").on('click', function(){
		location.href="/information/story/storyInfo.do";
	});

	$(document).ready(function(){
		$("#titl").val('<c:out value="${searchVO.titl}" />');
		$("#oztnNm").val('<c:out value="${searchVO.oztnNm}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("#useYn").val('<c:out value="${searchVO.useYn}" />');
		if("${searchVO.ctgrIdx}"){
			$('#ctgrIdx option').each(function(i, v){
			  if($(this).val() === "${searchVO.ctgrIdx}"){
			    $(this).attr('selected',true);
			  }
			})
		}
		
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

<div class="location_area"><h3>활동이야기</h3></div>
<div class="subcon_area">
	<form name="searchFm" method="post" >
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" id="titl" name="titl" style="width:90%" maxlength="50"/></td>
				<td rowspan="4">
					<input type="submit" class="btnSearch" value="조회" />
				</td> 
			</tr>
			<tr>
				<th>게시회원</th>
				<td colspan="3"><input type="text" id="oztnNm" name="oztnNm" style="width:90%" maxlength="50"/></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td colspan="3">
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
				<th>분류</th>
				<td>
					<select id="ctgrIdx" name="ctgrIdx" >
						<option value=''>전체</option>
						<c:forEach items="${categoryDTOList }" var="item">
							<option value='${item.ctgrIdx }'> <c:out value="${item.ctgrNm }"/> </option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<input type="hidden" id="sortType" name="sortType" />
	</div>

	<div class="stit mt_20 mb_10">
		<strong>활동이야기<span class="num">( <c:out value="${paginationInfo.totalRecordCount}" /> )</span></strong>
		<div class="fr">
			<sec:authorize access="hasRole('023007001Y')">
				<button id="btnRegFm" class="btnBasic_blue">글등록</button>
			</sec:authorize>
		</div>
	</div>
	<div class="basicTbl">
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>카테고리</th>
					<th>제목</th>
					<th>
					<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">등록일시▽</a>
					<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">등록일시△</a>
					</th>
					<th>게시회원</th>
					<th>조회</th>
					<th>
						<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">상태▽</a>
						<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">상태△</a>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${storyList}" var="sList" varStatus="varStatus">
					<tr onclick="fn_storyInfo(${sList.refRoomIdx })">
						<td><c:out value="${sList.refRoomIdx }" /></td>
						<td><c:out value="${sList.ctgrNm }" /></td>
						<td><c:out value="${sList.titl }" /></td>
						<td><c:out value="${sList.creDttm }" /></td>
						<td><c:out value="${sList.oztnNm }" /></td>
						<td><c:out value="${sList.hitCnt }" /></td>
						<td><c:out value="${sList.useYn=='Y'?'ON':'OFF' }" /></td>
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

<form name="infoFm" method="post" action="<c:url value='/information/story/storyInfo.do'/>">
	<input type="hidden" id="refRoomIdx" name="refRoomIdx" />
</form>