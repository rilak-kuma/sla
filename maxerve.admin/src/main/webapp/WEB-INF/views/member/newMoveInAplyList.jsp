<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">

 function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
 }

function fn_sort(sortType, num){
	$("#sortType").val(sortType+"_"+num);
	$('form[name=searchFm]').submit();
}

 jQuery(function($){

	 $( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#creDttmOne" )
	      .on('click',function(){
	    	  $(this).val("");
	    	  }).datepicker({
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
		$("#oztnNm").val('<c:out value="${searchVO.oztnNm}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("select[name=aplyPgrCd]").val('<c:out value="${searchVO.aplyPgrCd}"/>');

		var sortType = '<c:out value="${searchVO.sortType}" />';
		if(sortType == 'sortOne_1'){
			$("#sortOne_1").hide();
			$("#sortTwo_2").hide();
			$("#sortThree_2").hide();
		}else if(sortType == 'sortTwo_1'){
			$("#sortOne_2").hide();
			$("#sortTwo_1").hide();
			$("#sortThree_2").hide();
		}else if(sortType == 'sortThree_1'){
			$("#sortOne_2").hide();
			$("#sortTwo_2").hide();
			$("#sortThree_1").hide();
		}else{
			$("#sortOne_2").hide();
			$("#sortTwo_2").hide();
			$("#sortThree_2").hide();
		}

	});

 });

 function fn_oztnInfo(mvinAplyIdx) {
	$("#mvinAplyIdx").val(mvinAplyIdx);
	$('form[name=moveInOztnInfo]').submit();
}
</script>
<div class="location_area"><h3>신규입주신청</h3></div>
<div class="subcon_area">
	<form name="searchFm">
		<input type='hidden' name='sortType' id='sortType' value='<c:out value="${param.sortType }"/>'/>
			<table class="small_list">
				<caption></caption>
				<colgroup>
					<col width="120px"/>
					<col width=""/>
					<col width="120px"/>
					<col width=""/>
					<col width="100px"/>
				</colgroup>
				<tr>
					<th>단체명</th>
					<td><input type="text" id="oztnNm" name="oztnNm" /></td>
					<th>진행상태</th>
					<td>
						<select name="aplyPgrCd">
							<option value="" selected="selected">전체</option>
							<c:forEach var="aplyPgrCds" items="${aplyPgrCds }">
								<option value='<c:out value="${aplyPgrCds.cmmnCd }" />'><c:out value="${aplyPgrCds.cmmnCdNm }" /></option>
							</c:forEach>
						</select>
					</td>
					<td rowspan="2"><input type="submit" class="btnSearch" value="조회" /></td>
				</tr>
				<tr>
					<th>신청일</th>
					<td colspan="3">
						<input type="text" id="creDttmOne" name="creDttmOne" readonly="readonly" /><img src="../../img/ico_cal.png" alt="캘린더"> ~
						<input type="text" id="creDttmTwo" name="creDttmTwo" readonly="readonly" /><img src="../../img/ico_cal.png" alt="캘린더">
					</td>
				</tr>
			</table>

<div class="stit mt_20 mb_10">
	<strong>입주신청단체</strong><span class="num">(${paginationInfo.totalRecordCount })</span>
</div>

<div class="basicTbl">
	<table>
		<caption></caption>
		<tr>
			<th>단체명</th>
			<th>대표자명</th>
			<th>대표휴대전화</th>
			<th>
				<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">단체형태▽</a>
				<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">단체형태△</a>
			</th>
			<th>
				<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">신청일▽</a>
				<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">신청일△</a>
			</th>
			<th>
				<a href="#none" id="sortThree_1" onclick="fn_sort('sortThree','1')" style="cursor:pointer">진행상태▽</a>
				<a href="#none" id="sortThree_2" onclick="fn_sort('sortThree','2')" style="cursor:pointer">진행상태△</a>
			</th>
		</tr>
		<c:forEach items="${moveinList }" var="item" varStatus="varStatus">
			<tr onclick="fn_oztnInfo('${item.mvinAplyIdx }')">
				<td><c:out value="${item.oztnNm }" /></td>
				<td><c:out value="${item.ceoNm }"/></td>
				<td><c:out value="${item.ceoPhn }"/></td>
				<td><c:out value="${item.oztnTypCdNm }"/></td>
				<td><c:out value="${item.creDttm }"/></td>
				<td><c:out value="${item.aplyPgrCdNm }"/></td>
			</tr>
		</c:forEach>
	</table>
</div>
	<ul class="page" id="pagination">
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>

	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
</form>
</div>

<form name="moveInOztnInfo" action="<c:url value='/member/newMoveInAplyInfo.do' />">
	<input type="hidden" id="mvinAplyIdx" name="mvinAplyIdx" value="" />
</form>