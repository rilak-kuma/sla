<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>

function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
}

function fn_sort(sortType, num){
	$("#sortType").val(sortType+"_"+num);
	$('form[name=searchFm]').submit();
}

function getDayLable(date, type){
  if(date != null && date != ""){
   
    var date = date.split(' ');
    if(type == 'A'){
	   var week = new Array('(일)', '(월)', '(화)', '(수)', '(목)', '(금)', '(토)'); 
	   var today = new Date(date).getDay(); 
	   var todayLabel = week[today]; 
	   return date +' '+ todayLabel;
    } else if( type == 'B'){
       return date[0];
    } else if( type == 'C'){
       return date[1];
    }
    
  } else {
    return;
  }
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

	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#tourDttmOne" )
	        .datepicker({
	          defaultDate: "+1w",
	          changeMonth: true,
	          numberOfMonths: 1
	        }).on( "change", function() {
	          to.datepicker( "option", "minDate", getDate( this ) );
	        }),
	      to = $( "#tourDttmTwo" ).datepicker({
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
	
	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#tourDsdDttmOne" )
	        .datepicker({
	          defaultDate: "+1w",
	          changeMonth: true,
	          numberOfMonths: 1
	        }).on( "change", function() {
	          to.datepicker( "option", "minDate", getDate( this ) );
	        }),
	      to = $( "#tourDsdDttmTwo" ).datepicker({
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

	$('#btnExcelDown').on('click', function(){
		$('form[name=searchFm]')
		.attr("target","blank")
		.attr("action",'<c:out value="/reserve/tour/parktourReserve_listExcel.do" />')
		.submit();

		$('form[name=searchFm]')
		.removeAttr("target")
		.attr("action",'<c:out value="/reserve/tour/parktourReserve_list.do" />');
	});

	// 예약 상세
	$('#reserveTB').on('click', 'tr', function(){
		$('form[name=infoFm]')
			.attr('action', '<c:out value="/reserve/tour/parktourReserve_info.do" />')
			.find('input[name=tourAplyIdx]')
			.val($(this).attr('data-tour-aply-idx'));

		$('form[name=infoFm]').submit();
	});

	//예약 현황 팝업
	$('#btnRsvtSt').on('click', function(){
		//alert("방문 예약현황 팝업");
		window.open('parktourReserveCalendarPop.do','newwin','width=1400, height=700, scrollbars=yes, resizable=yes, fullscreen=yes');
	});

	$(document).ready(function(){
		$("#oztnNm").val('<c:out value="${searchVO.oztnNm}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("#tourDttmOne").val('<c:out value="${searchVO.tourDttmOne}" />');
		$("#tourDttmTow").val('<c:out value="${searchVO.tourDttmTwo}" />');
		$("#tourDsdDttmOne").val('<c:out value="${searchVO.tourDsdDttmOne}" />');
		$("#tourDsdDttmTwo").val('<c:out value="${searchVO.tourDsdDttmTwo}" />');
		
		$("select[name=tourPgr]").val('<c:out value="${searchVO.tourPgr}" />');

		var sortType = '<c:out value="${searchVO.sortType}" />';

		if(sortType == 'sortOne_1'){
			$("#sortOne_1").hide();
			$("#sortTwo_2").hide();
		}else if(sortType == 'sortTwo_1'){
			$("#sortOne_2").hide();
			$("#sortTwo_1").hide();
		}else {
			$("#sortOne_2").hide();
			$("#sortTwo_2").hide();
		}

		$('form[name=searchFm]').attr("action",'<c:out value="/reserve/tour/parktourReserve_list.do" />');
	});
});
</script>

<div class="location_area"><h3>방문 예약현황</h3></div>
<form:form commandName="reqParkTourVO" name="infoFm" method="post">
	<form:hidden path="tourAplyIdx"/>
</form:form>
<div class="subcon_area">
<form:form commandName="reqParkTourVO" name="searchFm" method="post" >
	<div class="small_list" >
		<table>
			<tr>
				<th>단체명(성명)</th>
				<td><input type="text" id="oztnNm" name="oztnNm" style="width:90%" maxlength="50"/></td>
				<th>진행상태</th>
				<td>
					<select name="tourPgr">
						<option value="">전체</option>
						<c:forEach var="tourPgrs" items="${tourPgrs }">
							<option value='<c:out value="${tourPgrs.cmmnCd }" />'><c:out value="${tourPgrs.cmmnCdNm }" /></option>
						</c:forEach>
					</select>
				</td>
				<td rowspan="2">
					<input type="submit" class="btnSearch" value="조회" />
				</td>
			</tr>
			<tr>
				<th>신청일</th>
				<td>
					<input type="text" id="creDttmOne" name="creDttmOne" readonly="readonly"/> ~
					<input type="text" id="creDttmTwo" name="creDttmTwo" readonly="readonly"/>
				</td>
				<th>투어일</th>
				<td>
					<input type="text" id="tourDttmOne" name="tourDttmOne" readonly="readonly"/> ~
					<input type="text" id="tourDttmTwo" name="tourDttmTwo" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th>투어확정일</th>
				<td>
					<input type="text" id="tourDsdDttmOne" name="tourDsdDttmOne" readonly="readonly"/> ~
					<input type="text" id="tourDsdDttmTwo" name="tourDsdDttmTwo" readonly="readonly"/>
				</td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="sortType" name="sortType" />

	<div class="stit mt_20 mb_10">
		<strong>방문 예약</strong><span class="num">(<c:out value="${paginationInfo.totalRecordCount}" />)</span>
		<div class="fr">
			<button id="btnRsvtSt" class="btnBasic_blue">예약현황</button>
			<button id="btnExcelDown" class="btnBasic_blue">엑셀저장</button>
		</div>
	</div>

	<div class="basicTbl">
		<table>
			<colgroup>
				<col />				<!-- 단체명 -->
				<col width="5%"/>	<!-- 인원 -->
				<col width="20%"/>	<!-- 투어일시 -->
				<col width="20%"/>	<!-- 신청자명 -->
				<col width="10%"/>	<!-- 투어 확정일 -->
				<col />				<!-- 신청자 연락처 -->
				<col width="20%"/>	<!-- 신청일 --> 
				<col />				<!-- 진행상태 -->
			</colgroup>
			<thead>
				<tr>
					<th>단체명</th>
					<th>인원</th>
					<th>투어일시</th>
					<th>투어확정일</th>
					<th>신청자명</th>
					<th>신청자 연락처</th>
					<th>
						<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">신청일△</a>
						<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">신청일▽</a>
					</th>
					<th>
						<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">진행상태△</a>
						<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">진행상태▽</a>
					</th>
				</tr>
			</thead>
			<c:choose>
			<c:when test="${fn:length(reserveList) > 0 }">
			<tbody id="reserveTB">
				<c:forEach items="${reserveList}" var="reserveList" varStatus="varStatus" >
					<tr data-tour-aply-idx='<c:out value="${reserveList.tourAplyIdx }" />' >
						<td><c:out value="${reserveList.oztnNm }" /></td>
						<td><c:out value="${reserveList.psct }" /></td>
						<td>
							<fmt:parseDate value="${reserveList.tourDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtTourDttm"/>
							<fmt:parseDate value="${reserveList.tourDttmEnd }" pattern="yyyy.MM.dd HH:mm" var="fmtTourDttmEnd"/>
                            <fmt:formatDate value="${fmtTourDttm }" pattern="yyyy년 MM월 dd일 (E) HH:mm"/><br/>
                            <%-- <fmt:formatDate value="${fmtTourDttmEnd }" pattern="~ HH:mm"/><br/> --%>
                            
							<fmt:parseDate value="${reserveList.tourSecondDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtTourSecondDttm"/>
							<fmt:parseDate value="${reserveList.tourSecondDttmEnd }" pattern="yyyy.MM.dd HH:mm" var="fmtTourSecondDttmEnd"/>
                            <fmt:formatDate value="${fmtTourSecondDttm }" pattern="yyyy년 MM월 dd일 (E) HH:mm"/>
                            <%-- <fmt:formatDate value="${fmtTourSecondDttmEnd }" pattern="~ HH:mm"/> --%>
                            
							<%-- <c:out value="${tourDttm }"/><c:out value="${tourDttmEnd }"/><br/>
							<c:out value="${tourSecondDttm }"/><c:out value="${tourSecondDttmEnd }"/> --%>
						</td>
						<td>
							<fmt:parseDate value="${reserveList.tourDsdDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtTourDsdDttm"/>
                            <fmt:formatDate value="${fmtTourDsdDttm }" pattern="yyyy년 MM월 dd일 (E) HH:mm"/><br/>
						</td>
						<td><c:out value="${reserveList.appyNm }" /></td>
						<td><c:out value="${reserveList.appyPhnNmbr }" /></td>
						<td>
							<fmt:parseDate value="${reserveList.creDttm }" pattern="yyyy-MM-dd HH:mm:ss.S" var="fmtCreDttm"/>
							<fmt:formatDate value="${fmtCreDttm }" pattern="yyyy년 MM월 dd일 HH시mm분"/>
							<%-- <c:out value="${creDttm }" /> --%>
						</td>
						<td>
							<c:out value="${reserveList.tourPgrNm eq '예약완료' ? '투어확정': reserveList.tourPgrNm}" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
			</c:when>
			<c:otherwise>
				<tbody>
					<tr>
						<td colspan="8" align="center">조회결과가 없습니다.</td>
					</tr>
				</tbody>
			</c:otherwise>
			</c:choose>
		</table>
	</div>

	<ul class="page" id="pagination">
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>

	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
</form:form>
</div>