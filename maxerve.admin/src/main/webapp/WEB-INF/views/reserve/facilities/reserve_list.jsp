<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>

function fn_submit(pageIndex){
	$('form[name=infoFm]')
	.attr('action', '<c:url value="/reserve/facilities/reserve_list.do"/>')
	.find('input[name=rsvtPlcCd]')
	.val('<c:out value="${searchVO.rsvtPlcCd}" />')
	.end()
	.find('input[name=fctGubn]')
	.val('<c:out value="${searchVO.fctGubn}" />')
	.end()
	.find('input[name=pageIndex]')
	.val(pageIndex)
	.end()
	.submit();
}

function fn_sort(sortType, num){
	$("#sortType").val(sortType+"_"+num);
	$('form[name=searchFm]')
	.attr('src', '<c:url value="/reserve/facilities/reserve_list.do"/>')
	.submit();
}

jQuery(function($){

	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#creDttmOne" )
	        .datepicker({
	          defaultDate: "+0w",
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
	      from = $( "#strDttm" )
	        .datepicker({
	          defaultDate: "+1w",
	          changeMonth: true,
	          numberOfMonths: 1
	        }).on( "change", function() {
	          to.datepicker( "option", "minDate", getDate( this ) );
	        }),
	      to = $( "#endDttm" ).datepicker({
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
		.attr("action",'<c:out value="/reserve/facilities/reserve_listExcel.do" />')
		.submit();

		$('form[name=searchFm]')
		.removeAttr("target")
		.attr("action",'<c:out value="/reserve/facilities/reserve_list.do" />');
	});	

	// 예약 상세
	$('#reserveTB').on('click', 'tr', function(){
		$('form[name=infoFm]')
			.find('input[name=rsvtPlcCd]').val('<c:out value="${searchVO.rsvtPlcCd}" />')
			.end()
			.find('input[name=fctGubn]').val('<c:out value="${searchVO.fctGubn}" />')
			.end()
			.append('<input type="hidden" name="fctRsvtIdx"/>')
			.find('input[name=fctRsvtIdx]').val($(this).attr('data-fct-rsvt-idx'))
			.end()
			.attr('action', '<c:out value="/reserve/facilities/reserve_info.do" />')
			.submit();
	});

	//시설 예약 현황 팝업
	$('button[name=btnFctRsvtSt]').on('click', function(){
		window.open('scheduleCalendarPop.do?rsvtPlcCd=<c:out value="${searchVO.rsvtPlcCd}" />&fctMstIdx='+$(this).val()+'&fctType=1&fctGubn=<c:out value="${searchVO.fctGubn}" />','newwin','width=1000, height=800, scrollbars=yes, resizable=yes');
	});
	
	//시설 예약 현황 팝업(전체스케줄)
	$('button[name=btnFctRsvtStAll]').on('click', function(){
		var fctMstIdx = "";
		var fctNm = "";
		$('button[name=btnFctRsvtSt]').each(function(index){
			if(index == 0){
				fctMstIdx += this.value;
				fctNm += $(this).text().trim();
			}else{
				fctMstIdx +=","+this.value;
				fctNm +=","+$(this).text().trim();
			}
		});
		window.open('scheduleCalendarPopAll.do?rsvtPlcCd=<c:out value="${searchVO.rsvtPlcCd}" />&fctMstIdx='+fctMstIdx+'&fctNm='+encodeURIComponent(fctNm)+'&fctType=1&fctGubn=<c:out value="${searchVO.fctGubn}" />','newwin','width=1400, height=900, scrollbars=yes, resizable=yes, fullscreen=yes');
	});	

	$(document).ready(function(){
		$('form[name=searchFm]')
			.find('input[name=rsvtPlcCd]')
			.val('<c:out value="${searchVO.rsvtPlcCd}" />')
			.find('input[name=fctGubn]')
	        .val('<c:out value="${searchVO.fctGubn}" />');

		$("#oztnNm").val('<c:out value="${searchVO.oztnNm}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("#strDttm").val('<c:out value="${searchVO.srtDttm}" />');
		$("#endDttm").val('<c:out value="${searchVO.endDttm}" />');
		$("select[name=rsvtPgr]").val('<c:out value="${searchVO.rsvtPgr}" />');

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

		$('form[name=searchFm]').attr("action",'<c:out value="/reserve/facilities/reserve_list.do" />');
		
		var length = $('button[name=btnFctRsvtSt]').length;
		if(length<2){ //달력스케줄 호출 버튼이 하나인 경우 전체스케줄 버튼 숨긴다.
			$('button[name=btnFctRsvtStAll]').hide();
		}
	});
});
</script>
<form:form commandName="reqFacilitiesReserveVO" name="infoFm" method="GET">
	<form:hidden path="rsvtPlcCd" id=""/>
	<form:hidden path="fctGubn" id=""/>
	<form:hidden path="pageIndex" id=""/>
	<form:hidden path="oztnNm" id=""/>
	<form:hidden path="rsvtPgr" id=""/>
	<form:hidden path="creDttmOne" id=""/>
	<form:hidden path="creDttmTwo" id=""/>
	<form:hidden path="srtDttm" id=""/>
	<form:hidden path="endDttm" id=""/>
	<form:hidden path="sortType" id=""/>
</form:form>

<div class="location_area"><h3><c:out value="${mainTitle }" /></h3></div>
<div class="subcon_area">
<form:form commandName="reqFacilitiesReserveVO" name="searchFm" method="GET">
		<form:hidden path="rsvtPlcCd"/>
		<form:hidden path="fctGubn"/>
		<div class="small_list">
		<table>
			<tr>
				<th>단체명<br/>(성명)</th>
				<td><input type="text" id="oztnNm" name="oztnNm" style="width:90%" maxlength="50"/></td>
				<th>진행상태</th>
				<td>
					<select name="rsvtPgr" style="width:30%;">
						<option value="">전체</option>
						<c:forEach var="rsvtPgrs" items="${rsvtPgrs }">
							<option value='<c:out value="${rsvtPgrs.cmmnCd }" />'><c:out value="${rsvtPgrs.cmmnCdNm }" /></option>
						</c:forEach>
						<option value='<c:out value="020006" />'><c:out value="결제기한초과" /></option>
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
				<th>사용일</th>
				<td>
					<input type="text" id="strDttm" name="srtDttm" readonly="readonly"/> ~
					<input type="text" id="endDttm" name="endDttm" readonly="readonly"/>
				</td>
			</tr>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong><c:out value="${subTitle }" /></strong><span class="num">(<c:out value="${paginationInfo.totalRecordCount}" />)</span>
		<div class="fr" >
		    <button name="btnFctRsvtStAll" class="btnBasic_blue">전체스케줄</button>
			<c:choose>
				<c:when test="${mainTitle.indexOf('미래청(회의/세미나)') == 0 }" >
					<c:forEach var="facilitiesList" items="${facilitiesList }" >
						<c:if test="${facilitiesList.fctNm.indexOf('모임방') < 0 and facilitiesList.fctNm.indexOf('오픈스페이스') < 0 }" >
							<button name="btnFctRsvtSt" class="btnBasic_blue" value='<c:out value="${facilitiesList.fctMstIdx }" />'>
							    <c:out value="${facilitiesList.fctNm }" />
							</button>
						</c:if>
					</c:forEach>
				</c:when>			
				<c:when test="${mainTitle.indexOf('모임방') > 0 }" >
					<c:forEach var="facilitiesList" items="${facilitiesList }" >
						<c:if test="${facilitiesList.fctNm.indexOf('모임방') > 0 or facilitiesList.fctNm.indexOf('오픈스페이스') == 0 }" >
							<button name="btnFctRsvtSt" class="btnBasic_blue" value='<c:out value="${facilitiesList.fctMstIdx }" />'>
							    <c:out value="${facilitiesList.fctNm }" />
							</button>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="facilitiesList" items="${facilitiesList }">
							<button name="btnFctRsvtSt" class="btnBasic_blue"  value='<c:out value="${facilitiesList.fctMstIdx }" />'>
								<c:out value="${facilitiesList.fctNm }" />
							</button>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<button id="btnExcelDown" class="btnBasic_blue">엑셀저장</button>
		</div>
	</div>
	<div class="basicTbl">
		<input type="hidden" id="sortType" name="sortType" />
		<table>
			<caption></caption>
			<colgroup>
				<col width="8%"/>
				<col width="25%"/>
				<col width=""/>
				<col width="30%"/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<thead>
				<tr>
					<th>예약번호</th>
					<th>아이디</th>
					<th>단체명(성명)</th>
					<th>사용일시</th>
					<th>
						<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">시설△</a>
						<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">시설▽</a>
					</th>
					<th>
						<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">신청일△</a>
						<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">신청일▽</a>
					</th>
					<th>
						<a href="#none" id="sortThree_1" onclick="fn_sort('sortThree','1')" style="cursor:pointer">진행상태△</a>
						<a href="#none" id="sortThree_2" onclick="fn_sort('sortThree','2')" style="cursor:pointer">진행상태▽</a>
					</th>
				</tr>
			</thead>
			<tbody id="reserveTB">
				<c:choose>
					<c:when test="${fn:length(reserveList) > 0 }">
						<c:forEach items="${reserveList}" var="reserveList" varStatus="varStatus">
							<tr data-fct-rsvt-idx='<c:out value="${reserveList.fctRsvtIdx }" />' >
								<td><c:out value="${reserveList.fctRsvtIdx }"/></td>
								<td><c:out value="${reserveList.mbrId }" /></td>
								<td><c:out value="${reserveList.oztnNm }" /></td>
								<td><c:out value="${reserveList.srtDttm }" /> ~ <c:out value="${reserveList.endDttm }" /></td>
								<td><c:out value="${reserveList.fctNm }" /></td>
								<td><c:out value="${reserveList.creDttm }" /></td>
								<td>
									<c:choose>
										<c:when test="${reserveList.rsvtPgrNm eq '예약취소<br>(관리자취소)' }">
											예약취소<br>(관리자취소)
										</c:when>
										<c:otherwise>
											<c:if test="${reserveList.rsvtPgrNm eq '예약완료' }">예약번호<br/></c:if>
											<c:out value="${reserveList.rsvtPgrNm eq '예약완료' ? reserveList.fctRsvtIdx: reserveList.rsvtPgrNm}" />
										</c:otherwise>
									</c:choose>								
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	</div>
	<ul class="page" id="pagination">
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>
</form:form>
</div>