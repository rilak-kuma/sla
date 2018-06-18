<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script type="text/javascript">

function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]')
	.attr('action', '<c:url value="/information/newsletter/subscriberList.do"/>')
	.append('<input type="hidden" name="pageIndex" value="' + pageIndex + '"/>')
	.submit();
}

function fn_onSubmit(typeNm){
	var typeCd = "";
	<c:forEach var="scbLocCds" items="${scbLocCds }">
		if('<c:out value="${scbLocCds.cmmnCdNm}" />' == typeNm){
			typeCd = '<c:out value="${scbLocCds.cmmnCd }" />';
		}
	</c:forEach>

	$("select[name=scbLocCd]").val(typeCd);
	$("#keyword").val("");
	$("#creDttmOne").val("");
	$("#creDttmTwo").val("");

	$('form[name=searchFm]')
	.attr('action', '<c:url value="/information/newsletter/subscriberList.do"/>')
	.submit();
}

function fn_sort(sortType, num){
	$("#sortType").val(sortType+"_"+num);
	$('form[name=searchFm]')
	.attr('action', '<c:url value="/information/newsletter/subscriberList.do"/>')
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

	//엑셀 다운로드
	$("#btnExcelDown_One").on("click",function(){
		$('form[name=searchFm]')
		 .attr({
			 target: '_blank',
			 action: '<c:out value="/information/newsletter/subscriberListExcel.do" />'
		 })
		 .submit();

		 $('form[name=searchFm]').removeAttr("target");
		 $('form[name=searchFm]').attr("action",'<c:out value="/information/newsletter/subscriberList.do" />');
	});

	//발송대상자 엑셀 다운로드
	$("#btnExcelDown_Two").on("click",function(){
		$('form[name=searchFm]')
		 .attr({
			 target: '_blank',
			 action: '<c:url value="/information/newsletter/subscriberListExcel_NameEmail.do" />'
		 })
		 .submit();

		 $('form[name=searchFm]').removeAttr("target");
		 $('form[name=searchFm]').removeAttr("action");
	});

	//검색어 선택
	$("#keywordType").on("change",function(){
		$("#keyword").attr("name",$(this).val());
	});

	$(document).ready(function(){

		<c:if test="${searchVO.scbNm ne ''}">
			$("#keywordType").val("scbNm");
			$("#keyword").val('<c:out value="${searchVO.scbNm}" />');
			$("#keyword").attr("name","scbNm");
		</c:if>
		<c:if test="${searchVO.emil ne ''}">
			$("#keywordType").val("emil");
			$("#keyword").val('<c:out value="${searchVO.emil}" />');
			$("#keyword").attr("name","emil");
		</c:if>
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("select[name=scbLocCd]").val('<c:out value="${searchVO.scbLocCd}" />');

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

	$('form[name=searchFm]')
	.on('click', '.btnSearch', function(){
		$('form[name=searchFm]')
		.attr('action', '<c:url value="/information/newsletter/subscriberList.do"/>')
		.submit();

		return false;
	});

	$("#keywordType").trigger('change');

});
</script>

<div class="location_area"><h3>뉴스레터 구독자 관리</h3></div>
<div class="subcon_area">
	<h4>뉴스레터 구독현황</h4>

	<table class="detailTbl">
		<caption></caption>
		<colgroup>
			<col width="120px"/>
			<col width=""/>
			<col width="120px"/>
			<col width=""/>
			<col width="100px"/>
		</colgroup>
		<tr>
		    <th>전체 구독</th>
		    <td><a href="#mone" onclick="fn_onSubmit('');" ><c:out value="${subscriberVO.korSubscribCnt+subscriberVO.engSubscribCnt}" /></a></td>
			<th>국문</th>
			<td><a href="#mone" onclick="fn_onSubmit('국문');" ><c:out value="${subscriberVO.korSubscribCnt}" /></a></td>
			<th>영문</th>
			<td><a href="#mone" onclick="fn_onSubmit('영문');" ><c:out value="${subscriberVO.engSubscribCnt}" /></a></td>
		</tr>
	</table>

	<form:form commandName="searchVO" name="searchFm" method="GET">
		<form:hidden path="sortType"/>
		<div class="small_list mt_20">
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
					<th>검색어</th>
					<td>
						<select id="keywordType">
							<option value="emil"${!empty searchVO.emil ? ' SELECTED':'' }>이메일</option>
							<option value="scbNm"${!empty searchVO.scbNm ? ' SELECTED':'' }>성명</option>
						</select>
						<input type='text' id='keyword' maxlength='50' value='${!empty searchVO.emil ? searchVO.emil:searchVO.scbNm }'/>
					</td>
					<th>신청경로</th>
					<td>
						<select name="scbLocCd" style="width:100px">
							<option value="">전체</option>
							<c:forEach var="scbLocCds" items="${scbLocCds }">
								<option value='<c:out value="${scbLocCds.cmmnCd }" />'${subscriberVO.scbLocCd eq scbLocCds.cmmnCd ? ' SELECTED':'' }><c:out value="${scbLocCds.cmmnCdNm }" /></option>
							</c:forEach>
						</select>
					</td>
					<td rowspan="2">
						<input type="submit" class="btnSearch" value="조회" />
					</td>
				</tr>
				<tr>
					<th>구독신청일</th>
					<td colspan="3">
						<form:input path="creDttmOne" readonly="readonly"/> ~
						<form:input path="creDttmTwo" readonly="readonly"/>
					</td>
				</tr>
			</table>
		</div>

		<div class="stit mt_20 mb_10">
			<strong>구독자 목록</strong><span class="num">(<c:out value="${paginationInfo.totalRecordCount}" />)</span>
			<div class="fr">
				<button id="btnExcelDown_Two" class="btnBasic_blue">발송대상자 저장</button>
				<button id="btnExcelDown_One" class="btnBasic_black">엑셀저장</button>
			</div>
		</div>

		<div class="basicTbl">
			<table>
				<caption></caption>
				<colgroup>
					<col width=""/>
					<col width=""/>
					<col width=""/>
					<col width="15%"/>
				</colgroup>
				<tr>
					<th>성명</th>
					<th>이메일</th>
					<th>
						<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">구독신청일▽</a>
						<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">구독신청일△</a>
					</th>
					<th>
						<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">신청경로▽</a>
						<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">신청경로△</a>
					</th>
				</tr>
				<c:forEach items="${subscriberList}" var="subscriberList" varStatus="varStatus">
					<tr>
						<td><c:out value="${subscriberList.scbNm }" /></td>
						<td><c:out value="${subscriberList.emil }" /></td>
						<td><c:out value="${subscriberList.creDttm }" /></td>
						<td><c:out value="${subscriberList.scbLocCdNm }" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form:form>

	<ul class='page'>
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>

	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
</div>

