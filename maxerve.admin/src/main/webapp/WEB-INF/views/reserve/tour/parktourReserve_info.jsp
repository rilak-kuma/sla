<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
/* CSS로 마우스 드래그 방지 풀기 */
.subcon_area{-ms-touch-action:text}
.subcon_area{-webkit-user-select:text;-khtml-user-select:text;-moz-user-select:text;-o-user-select:text;user-select:text}
</style>
<script>

function openPrintWindow(url, name, specs) {
    //var agent = navigator.userAgent.toLowerCase();
    var userAgent = navigator.userAgent;
    var w = window.open(url, name, specs);
    
    //익스플로러
  	if(/Trident|MSIE/.test(userAgent) || /Edge/.test(userAgent)){
  	  
  	  //$(w).ready(function(){
  		w.document.attachEvent('onreadystatechange',function(){
  			//w.document.getElementsByTagName('button')[0].style.background = 'red';
  			var buttons = w.document.getElementsByTagName('button');
  	  	  	for(var i=0 ; i< buttons.length ; i++){
				buttons[i].style.display= 'none';
 	  	  	}
 	  	  	
  		    var sched	= setInterval(function() {
  		        if (w.document.readyState == 'complete') {
  		            clearInterval(sched);
  		            w.print();
  		            w.close();
  		        }
  		    }, 200);
  	  	});
  	  
  	  
  	}else{ // 이외브라우저
  	  
  	  $(w).on('load',function(){
	  	  $(w.document).find('button').css('display','none');
	  	  var sched	= setInterval(function() {
	        if (w.document.readyState == 'complete') {
	            clearInterval(sched);
	            w.print();
	            w.close();
	        }
	    }, 200);
  	  });
  	}
};

jQuery(function($){

	//엑셀저장
	$("#btnExcelDown").on('click', function(){
		window.open('<c:url value="/reserve/tour/parktourReserve_infoExcel.do?tourAplyIdx="/>'+'<c:out value="${parkTourInfo.tourAplyIdx }"/>','','');
	});
	
	//인쇄
	$("#parkTourPrint").on('click', function(){
	  	openPrintWindow('<c:url value="/reserve/tour/parktourReserve_info.do?isPopup=Y&tourAplyIdx="/>'+'<c:out value="${parkTourInfo.tourAplyIdx }"/>','parkTourPrint','width=1000, height=800, scrollbars=yes');
	});

	//저장
	$("#btnConfirm").on('click', function(){
		$('form[name=applyFm]').submit();
	});

	// validate
	$('form[name=applyFm]').validate({
		rules: {
			tourDsdDttm	: 'required',
			tourPgr		: 'required'
		},
		messages: {
			tourDsdDttm	: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '방문확정일은'),
			tourPgr		: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '진행상태는')
		},
		submitHandler: function(form) {
			var url = '<c:url value="/reserve/tour/updateParktourReserve_info.json"/>';
			var submitData = $(form).serializeFormJSON();
				submitData.tourAplyIdx = '<c:out value="${parkTourInfo.tourAplyIdx }"/>';
			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				}
			console.log(submitData);
			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	//목록
	$("#btnGoList").on('click', function(){
		//window.history.back();
	  location.replace("/reserve/tour/parktourReserve_list.do");
	});

	$(document).ready(function(){

		<c:forEach var="tourPgrs" items="${tourPgrs }">
			<c:if test='${tourPgrs.cmmnCd eq parkTourInfo.tourPgr}' >
				$("select[name=tourPgr]").append('<option value="${tourPgrs.cmmnCd }" selected="selected"><c:out value="${tourPgrs.cmmnCdNm}" /></option>');
				<c:if test='${tourPgrs.cmmnCdNm eq "신청취소"}' >
					$("#divPgr").html('<span class="emtxt_02"><c:out value="${tourPgrs.cmmnCd}" /></span>');
					break;
			</c:if>
			</c:if>
			<c:if test='${tourPgrs.cmmnCd ne parkTourInfo.tourPgr}' >
				$("select[name=tourPgr]").append('<option value="${tourPgrs.cmmnCd }" ><c:out value="${tourPgrs.cmmnCdNm}" /></option>');
			</c:if>
		</c:forEach>

	});
	
	$('#tourDsdDate, #tourDsdTime').on('click change', function(){
	  $('#tourDsdDttm').val($('#tourDsdDate option:selected').val()+' '+$('#tourDsdTime option:selected').val());
	});
});
</script>

<div class="location_area"><h3>방문 예약현황</h3></div>
<div class="subcon_area">
	<div class="mt_20 mb_10">
		<strong>방문 진행상세</strong>
		<div class="fr">
			<button id="parkTourPrint" class="btnBasic_blue">인쇄</button>
			<button id="btnExcelDown" class="btnBasic_blue">엑셀저장</button>
		</div>
	</div>
<form name="applyFm" method="post">
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>방문신청일</th>
				<td colspan="3">
					<fmt:parseDate var="tourDttm" value="${parkTourInfo.tourDttm }" pattern="yyyy-MM-dd HH:mm:ss" />
					<fmt:parseDate var="tourDttmEnd" value="${parkTourInfo.tourDttmEnd }" pattern="yyyy.MM.dd HH:mm" />
					<fmt:formatDate value="${tourDttm }" pattern="yyyy년MM월dd일(E) HH:mm" />
					<%-- <fmt:formatDate value="${tourDttmEnd }" pattern="~ HH:mm"/> --%><br/>
					
					<fmt:parseDate var="tourSecondDttm" value="${parkTourInfo.tourSecondDttm }" pattern="yyyy-MM-dd HH:mm:ss" />
					<fmt:parseDate var="tourSecondDttmEnd" value="${parkTourInfo.tourSecondDttmEnd }" pattern="yyyy.MM.dd HH:mm" />
					<fmt:formatDate value="${tourSecondDttm }" pattern="yyyy년MM월dd일(E) HH:mm" />
					<%-- <fmt:formatDate value="${tourSecondDttmEnd }" pattern="~ HH:mm"/> --%>
				</td>
			</tr>
			<tr>
				<th>방문확정일</th>
				<td>
					<input type="hidden" name="tourDttm" id="tourDttm" value="${parkTourInfo.tourDttm }">
					<input type="hidden" name="tourSecondDttm" id="tourSecondDttm" value="${parkTourInfo.tourSecondDttm }">
					<c:set value="${fn:split(parkTourInfo.tourDttm, ' ')}" var="splitDttm"></c:set>
					<c:set value="${fn:split(parkTourInfo.tourSecondDttm, ' ')}" var="splitSecondDttm"></c:set>
					<select id="tourDsdDate">
						<!-- 희망방문일 1안 -->
						<c:if test="${parkTourInfo.tourDttm ne '0000-00-00' }" >
							<fmt:parseDate var="tourDsdDttm" value="${parkTourInfo.tourDttm }" pattern="yyyy-MM-dd HH:mm:ss" />
							<option value='<c:out value="${splitDttm[0] }" />'><fmt:formatDate value="${tourDttm }" pattern="yyyy년MM월dd일(E)" /></option>
						</c:if>
						<c:if test="${parkTourInfo.tourDttm eq '0000-00-00' }" >
							<option value=''></option>
						</c:if>
						<!-- 희망방문일 2안 -->
						<c:if test="${parkTourInfo.tourSecondDttm ne '0000-00-00' }" >
							<fmt:parseDate var="tourSecondDttm" value="${parkTourInfo.tourSecondDttm }" pattern="yyyy-MM-dd HH:mm:ss" />
							<option value='<c:out value="${splitSecondDttm[0] }" />'><fmt:formatDate value="${tourSecondDttm }" pattern="yyyy년MM월dd일(E)" /></option>
						</c:if>
						<c:if test="${parkTourInfo.tourSecondDttm eq '0000-00-00' }" >
							<option value=''></option>
						</c:if>
						<!-- 현재월 + 2달 ex) 현재 10월이면 10월,11월,12월  -->
						<c:forEach var="tourScheduleList" items="${tourScheduleList }">
							<fmt:parseDate var="ScheduleDttm" value="${tourScheduleList.clndDt }" pattern="yyyy-MM-dd" />
							<option value='<c:out value="${tourScheduleList.clndDt }" />'><fmt:formatDate value="${ScheduleDttm }" pattern="yyyy년MM월dd일(E)" /></option>
						</c:forEach>
					</select>
					<select id="tourDsdTime">
						<c:forEach var="time" items="${tourScheduleTimeList }">
							<option value='<c:out value="${time }"/>' <c:if test="${ time eq splitDttm[1] }">selected="selected"</c:if> ><c:out value="${ fn:substring(time, 0, (fn:length(time)-3)) }"/></option>
						</c:forEach>
					</select>
				</td>
				<th>진행상태</th>
				<td>
					<div id="divPgr">
						<select name="tourPgr">
						</select>
					</div>
				</td>
			</tr>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>관리자 Comment</strong>
	</div>
	<div>
		<textarea rows="3" name="cmmt" style="width:100%"><c:out value="${parkTourInfo.cmmt}" /></textarea>
	</div>
<sec:authorize access="hasRole('023005004Y')">
	<div class="tac mt_10"><button id="btnConfirm" class="btnBasic_blue" >저장</button></div>
</sec:authorize>
</form>

	<div class="mt_20 mb_10">
		<strong>방문 신청정보</strong>
	</div>
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>기관명</th>
				<td><c:out value="${parkTourInfo.oztnNm}" /></td>
				<th>인원</th>
				<td><c:out value="${parkTourInfo.psct}" /></td>
			</tr>
			<tr>
				<th>기관 분류</th>
				<td colspan="3"><c:out value="${parkTourInfo.oztnTypCdNm}" /></td>
			</tr>
			<tr>
				<th>신청자</th>
				<td><c:out value="${parkTourInfo.appyNm}" /> / <c:out value="${parkTourInfo.appyOztnNm}" /> / <c:out value="${parkTourInfo.appyPhnNmbr}" /> / <c:out value="${parkTourInfo.appyEmil}" /></td>
				<th>인솔자</th>
				<td><c:out value="${parkTourInfo.ldrNm}" /> / <c:out value="${parkTourInfo.ldrOztnNm}" /> / <c:out value="${parkTourInfo.appyPhnNmbr}" /> / <c:out value="${parkTourInfo.appyEmil}" /></td>
			</tr>
			<tr>
				<th>주차</th>
				<td colspan="3">
					<c:forEach var="tourCarList" items="${tourCarList }" varStatus="status">
				  		<c:if test="${status.index > 0 }">/</c:if>[<c:out value="${tourCarList.carTypCdNm}" />] <c:out value="${tourCarList.carCnt}" /> 대
				  	</c:forEach>
				</td>
			</tr>
			<tr>
				<th>방문목적</th>
				<td colspan="3">[<c:out value="${parkTourInfo.tourPrpsCdNm}" />]<c:out value="${parkTourInfo.tourPrpsEtc}" /></td>
			</tr>
			<tr>
				<th>신청기관소개</th>
				<td colspan="3"><c:out value="${parkTourInfo.appyOztnItdc}" /></td>
			</tr>
			<tr>
				<th>기타</th>
				<td colspan="3"><c:out value="${parkTourInfo.etc}" /></td>
			</tr>
		</table>
	</div>

	<div class="mt_20 mb_10">
		<strong>방문자 명단</strong>
	</div>
	<div class="searchTbl tac">
		<table>
			<caption></caption>
			<colgroup>
				<col width=""/>
				<col width=""/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<thead>
				<tr>
					<th>성명</th>
					<th>단체명</th>
					<th>전화번호</th>
					<th>이메일</th>
				</tr>
			</thead>
			<tbody>
			  	<c:forEach var="tourAttendList" items="${tourAttendList }" varStatus="status">
				  	<tr>
				  		<td>
			  				<c:out value="${tourAttendList.attNm}" />
			  			</td>
				  		<td>
			  				<c:out value="${tourAttendList.oztnNm}" />
			  			</td>
				  		<td>
			  				<c:out value="${tourAttendList.phnNmbr}" />
			  			</td>
				  		<td>
			  				<c:out value="${tourAttendList.emil}" />
			  			</td>
			  		</tr>
		  		</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="tac mt_10">
		<button id="btnGoList" class="btnBasic_blue">목록</button>
	</div>
</div>