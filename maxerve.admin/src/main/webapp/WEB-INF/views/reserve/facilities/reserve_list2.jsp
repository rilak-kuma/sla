<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>

function fn_submit(pageIndex){
	$('form[name=infoFm]')
	.attr('action', '<c:url value="/reserve/facilities/reserve_list2.do"/>')
	.find('input[name=locTypCd]')
	.val('<c:out value="${searchVO.locTypCd}" />')
	.end()
	.find('input[name=pageIndex]')
	.val(pageIndex)
	.end()
	.submit();
}

function fn_sort(sortType, num){
	$("#sortType").val(sortType+"_"+num);
	$('form[name=searchFm]')
	.attr('src', '<c:url value="/reserve/facilities/reserve_list2.do"/>')
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
	      from = $( "#useStrDt" )
	        .datepicker({
	          defaultDate: "+1w",
	          changeMonth: true,
	          numberOfMonths: 1
	        }).on( "change", function() {
	          to.datepicker( "option", "minDate", getDate( this ) );
	        }),
	      to = $( "#useEndDt" ).datepicker({
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
		.attr("action",'<c:out value="/reserve/facilities/reserve_listExcel2.do" />')
		.submit();

		$('form[name=searchFm]')
		.removeAttr("target")
		.attr("action",'<c:out value="/reserve/facilities/reserve_list2.do" />');
	});	

	// 예약 상세
	$('#reserveTB').on('click', 'tr', function(){
		$('form[name=infoFm]')
			.find('input[name=locTypCd]').val('<c:out value="${searchVO.locTypCd}" />')
			.end()
			.append('<input type="hidden" name="fctMbrIdx"/>')
			.find('input[name=fctMbrIdx]').val($(this).attr('data-fct-rsvt-idx'))
			.end()
			.attr('action', '<c:out value="/reserve/facilities/reserve_info2.do" />')
			.submit();
	});

	$(document).ready(function(){
		$('form[name=searchFm]')
			.find('input[name=locTypCd]')
			.val('<c:out value="${searchVO.locTypCd}" />');

		$("#oztnNm").val('<c:out value="${searchVO.oztnNm}" />');
		$("#timNm").val('<c:out value="${searchVO.timNm}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		//$("#useStrDt").val('<c:out value="${searchVO.useStrDt}" />');
		//$("#useEndDt").val('<c:out value="${searchVO.useEndDt}" />');
		$("select[name=rsvtPgr]").val('<c:out value="${searchVO.rsvtPgr}" />');

		var sortType = '<c:out value="${searchVO.sortType}" />';

		if(sortType == 'sortFctMbrIdx_1'){
			$("#sortFctMbrIdx_1").hide();
			$("#sortOztnNm_2").hide();
			$("#sortTimNm_2").hide();
			$("#sortJonyType_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortCreDttm_2").hide();
			$("#sortPayType_2").hide();
			$("#sortRsvtPgr_2").hide();
		}else if(sortType == 'sortOztnNm_1'){
			$("#sortFctMbrIdx_2").hide();
			$("#sortOztnNm_1").hide();
			$("#sortTimNm_2").hide();
			$("#sortJonyType_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortCreDttm_2").hide();
			$("#sortPayType_2").hide();
			$("#sortRsvtPgr_2").hide();
		}else if(sortType == 'sortTimNm_1'){
			$("#sortFctMbrIdx_2").hide();
			$("#sortOztnNm_2").hide();
			$("#sortTimNm_1").hide();
			$("#sortJonyType_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortCreDttm_2").hide();
			$("#sortPayType_2").hide();
			$("#sortRsvtPgr_2").hide();
		}else if(sortType == 'sortJonyType_1'){
			$("#sortFctMbrIdx_2").hide();
			$("#sortOztnNm_2").hide();
			$("#sortTimNm_2").hide();
			$("#sortJonyType_1").hide();
			$("#sortMbrId_2").hide();
			$("#sortCreDttm_2").hide();
			$("#sortPayType_2").hide();
			$("#sortRsvtPgr_2").hide();
		}else if(sortType == 'sortMbrId_1'){
			$("#sortFctMbrIdx_2").hide();
			$("#sortOztnNm_2").hide();
			$("#sortTimNm_2").hide();
			$("#sortJonyType_2").hide();
			$("#sortMbrId_1").hide();
			$("#sortCreDttm_2").hide();
			$("#sortPayType_2").hide();
			$("#sortRsvtPgr_2").hide();
		}else if(sortType == 'sortCreDttm_1'){
			$("#sortFctMbrIdx_2").hide();
			$("#sortOztnNm_2").hide();
			$("#sortTimNm_2").hide();
			$("#sortJonyType_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortCreDttm_1").hide();
			$("#sortPayType_2").hide();
			$("#sortRsvtPgr_2").hide();
		}else if(sortType == 'sortPayType_1'){
			$("#sortFctMbrIdx_2").hide();
			$("#sortOztnNm_2").hide();
			$("#sortTimNm_2").hide();
			$("#sortJonyType_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortCreDttm_2").hide();
			$("#sortPayType_1").hide();
			$("#sortRsvtPgr_2").hide();
		}else if(sortType == 'sortRsvtPgr_1'){
			$("#sortFctMbrIdx_2").hide();
			$("#sortOztnNm_2").hide();
			$("#sortTimNm_2").hide();
			$("#sortJonyType_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortCreDttm_2").hide();
			$("#sortPayType_2").hide();
			$("#sortRsvtPgr_1").hide();
		}else{
			$("#sortFctMbrIdx_2").hide();
			$("#sortOztnNm_2").hide();
			$("#sortTimNm_2").hide();
			$("#sortJonyType_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortCreDttm_2").hide();
			$("#sortPayType_2").hide();
			$("#sortRsvtPgr_2").hide();
		}
		
		$('form[name=searchFm]').attr("action",'<c:out value="/reserve/facilities/reserve_list2.do" />');
		
		/*var length = $('button[name=btnFctRsvtSt]').length;
		if(length<2){ //달력스케줄 호출 버튼이 하나인 경우 전체스케줄 버튼 숨긴다.
			$('button[name=btnFctRsvtStAll]').hide();
		}*/
	});
});
</script>
<form:form commandName="reqFacilitiesMemberInfoVO2" name="infoFm" method="GET">
	<form:hidden path="locTypCd" id=""/>
	<form:hidden path="pageIndex" id=""/>
	<form:hidden path="oztnNm" id=""/>
	<form:hidden path="timNm" id=""/>
	<form:hidden path="rsvtPgr" id=""/>
	<form:hidden path="creDttmOne" id=""/>
	<form:hidden path="creDttmTwo" id=""/>
	<%-- <form:hidden path="useStrDt" id=""/>
	<form:hidden path="useEndDt" id=""/> --%>
	<form:hidden path="sortType" id=""/>
</form:form>

<div class="location_area"><h3><c:out value="${mainTitle }" /></h3></div>
<div class="subcon_area">
<form:form commandName="reqFacilitiesMemberInfoVO2" name="searchFm" method="GET">
		<form:hidden path="locTypCd"/>
		<div class="small_list">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width="500px"/>
				<col width="120px"/>
				<col width=""/>
				<col width="100px"/>
			</colgroup>		
			<tr>
				<th>단체명<br/>(성명)</th>
				<td><input type="text" id="oztnNm" name="oztnNm" style="width:300px" maxlength="50"/></td>
				<th>팀명</th>
				<td><input type="text" id="timNm" name="timNm" style="width:300px" maxlength="50"/></td>
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
				<th>진행상태</th>
				<td>
					<select name="rsvtPgr" style="width:30%;">
						<option value="">전체(멤버십만료 제외)</option>
						<c:forEach var="rsvtPgrs" items="${rsvtPgrs }">
							<option value='<c:out value="${rsvtPgrs.cmmnCd }" />'><c:out value="${rsvtPgrs.cmmnCdNm }" /></option>
						</c:forEach>
						<option value='<c:out value="020006" />'><c:out value="결제기한초과" /></option>
						<option value='<c:out value="020007" />'><c:out value="멤버십만료" /></option>
					</select>
				</td>
				<!-- <th>사용일</th>
				<td>
					<input type="text" id="useStrDt" name="useStrDt" readonly="readonly"/> ~
					<input type="text" id="useEndDt" name="useEndDt" readonly="readonly"/>
				</td> -->
			</tr>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong><c:out value="${subTitle }" /></strong><span class="num">(<c:out value="${paginationInfo.totalRecordCount}" />)</span>
		<div class="fr" >
			<button id="btnExcelDown" class="btnBasic_blue">엑셀저장</button>
		</div>
	</div>
	<div class="basicTbl">
		<input type="hidden" id="sortType" name="sortType" />
		<table>
			<caption></caption>
			<colgroup>
			    <col width="8%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="12%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
			</colgroup>
			<thead>
				<tr>
					<th>
						<a href="#none" id="sortFctMbrIdx_1" onclick="fn_sort('sortFctMbrIdx','1')" style="cursor:pointer">멤버십회원 번호△</a>
						<a href="#none" id="sortFctMbrIdx_2" onclick="fn_sort('sortFctMbrIdx','2')" style="cursor:pointer">멤버십회원 번호▽</a>
					</th>				    
					<th>
						<a href="#none" id="sortOztnNm_1" onclick="fn_sort('sortOztnNm','1')" style="cursor:pointer">단체명(성명)△</a>
						<a href="#none" id="sortOztnNm_2" onclick="fn_sort('sortOztnNm','2')" style="cursor:pointer">단체명(성명)▽</a>
					</th>				    
					<th>
						<a href="#none" id="sortTimNm_1" onclick="fn_sort('sortTimNm','1')" style="cursor:pointer">팀명△</a>
						<a href="#none" id="sortTimNm_2" onclick="fn_sort('sortTimNm','2')" style="cursor:pointer">팀명▽</a>
					</th>
					<th>
						<a href="#none" id="sortJonyType_1" onclick="fn_sort('sortJonyType','1')" style="cursor:pointer">가입유형△</a>
						<a href="#none" id="sortJonyType_2" onclick="fn_sort('sortJonyType','2')" style="cursor:pointer">가입유형▽</a>
					</th>				    
				    <th>대표휴대전화</th>
				    <th>비상연락망</th>
					<th>
						<a href="#none" id="sortMbrId_1" onclick="fn_sort('sortMbrId','1')" style="cursor:pointer">아이디(이메일)△</a>
						<a href="#none" id="sortMbrId_2" onclick="fn_sort('sortMbrId','2')" style="cursor:pointer">아이디(이메일)▽</a>
					</th>					
					<th>
						<a href="#none" id="sortCreDttm_1" onclick="fn_sort('sortCreDttm','1')" style="cursor:pointer">신청일△</a>
						<a href="#none" id="sortCreDttm_2" onclick="fn_sort('sortCreDttm','2')" style="cursor:pointer">신청일▽</a>
					</th>
					<th>
						<a href="#none" id="sortPayType_1" onclick="fn_sort('sortPayType','1')" style="cursor:pointer">결제유형△</a>
						<a href="#none" id="sortPayType_2" onclick="fn_sort('sortPayType','2')" style="cursor:pointer">결제유형▽</a>
					</th>
					<th>
						<a href="#none" id="sortRsvtPgr_1" onclick="fn_sort('sortRsvtPgr','1')" style="cursor:pointer">진행상태△</a>
						<a href="#none" id="sortRsvtPgr_2" onclick="fn_sort('sortRsvtPgr','2')" style="cursor:pointer">진행상태▽</a>
					</th>
				</tr>
			</thead>
			<tbody id="reserveTB">
				<c:choose>
					<c:when test="${fn:length(reserveList) > 0 }">
						<c:forEach items="${reserveList}" var="reserveList" varStatus="varStatus">
							<tr data-fct-rsvt-idx='<c:out value="${reserveList.fctMbrIdx }" />' >
								<td><c:out value="${reserveList.fctMbrIdx }" /></td>
							    <td><c:out value="${reserveList.oztnNm }" /></td>
							    <td><c:out value="${reserveList.timNm }" /></td>
								<td><c:out value="${reserveList.joinTypeNm }"/></td>
								<td><c:out value="${reserveList.ceoPhn }" /></td>
								<td><c:out value="${reserveList.emeCal }" /></td>
								<td><c:out value="${reserveList.mbrId }" /></td>
								<td><c:out value="${reserveList.creDttm }" /></td>
								<td><c:out value="${reserveList.payTypeNm }" /></td>
								<td>
									<c:choose>
										<c:when test="${reserveList.rsvtPgrNm eq '예약취소<br>(관리자취소)' }">
											예약취소<br>(관리자취소)
										</c:when>
										<c:otherwise>
											<c:if test="${reserveList.rsvtPgrNm eq '예약완료' }">예약번호<br/></c:if>
											<c:out value="${reserveList.rsvtPgrNm eq '예약완료' ? reserveList.fctMbrIdx: reserveList.rsvtPgrNm}" />
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