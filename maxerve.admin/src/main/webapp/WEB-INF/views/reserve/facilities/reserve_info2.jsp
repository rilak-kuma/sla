<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>

label {
	background-color: #A9A9A9;
}

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

//미사용( openPrintWindow() 함수로 대체)
function openPrintWindow2(url, name, specs) {
  	var w = window.open(url, name, specs);
  	$(w).ready(function() {
  		
  		//w.document.getElementById('btnPrint').style.display= 'none';
		//w.document.getElementById('btnExcelDown').style.display= 'none';
		//w.document.getElementById('btnConfirm').style.display= 'none';
		//w.document.getElementById('btnGoList').style.display= 'none';
		
	    var sched	= setInterval(function() {
	        if (w.document.readyState == 'complete') {
	            clearInterval(sched);
	            w.print();
	            w.close();
	        }
	    }, 2000);
  	});

  	/*w.onload = function(){
  		
		w.document.getElementById('btnPrint').style.display= 'none';
		//w.document.getElementById('btnExcelDown').style.display= 'none';
		w.document.getElementById('btnConfirm').style.display= 'none';
		w.document.getElementById('btnGoList').style.display= 'none';
		
	    var sched	= setInterval(function() {
	        if (w.document.readyState == 'complete') {
	            clearInterval(sched);
	            w.print();
	            w.close();
	        }
	    }, 200);
    }*/
};

jQuery(function($){

	// validate
	$('form[name=infoFm]').validate({
		rules: {
			fctMbrIdx: 'required',
			rsvtPgr: 'required'
		},
		messages: {
			fctMbrIdx: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '멤버십회원 번호는'),
			rsvtPgr: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '진행상태는')
		},
		submitHandler: function(form) {
			var url = '<c:out value="/reserve/facilities/updateReserve2.json"/>';
			
			$("select[name=rsvtPgr]").prop('disabled', false); // 예약완료인 경우 'disabled'=true 이기에 false 로 바꾸지 않으면 submitData 에 포함되지 않는다.
			
			var submitData = $(form).serializeFormJSON();
			var callback = function(data) {
				
				alert(getMessage('msg.save.success'));
				//alert(JSON.stringify(data));
				
				if(data.rsvtPgr == "020003" ){ //예약완료
					$("select[name=rsvtPgr]").prop('disabled', true);
					//$("#rsvtSpan").html('예약번호 : '+data.fctMbrIdx);
					//$("#mngrMsgSpan").html('<span class="emtxt_02">'+data.mngrMsg+'</span>');
					//$("#btnConfirm").hide();
				}else if(data.rsvtPgr == "020005"){ //예약취소
					$("#rsvtSpan").html('<span class="emtxt_02">예약취소: '+data.fctMbrIdx+'</span>');
					
					$("#table1 #row2").show();
					
					if(data.rsvtPgrCau != ""){
						$("#rsvtPgrCauSpan").html('<span class="emtxt_02">관리자 취소: '+data.rsvtPgrCau+'</span>');
					}else{
						$("#rsvtPgrCauSpan").html('<span class="emtxt_02">신청자 취소</span>');
					}
				
					$("#useStrEndDtspan").html('<span class="emtxt_02">'+data.facilitiesMemberDTO2.useStrDt+' ~ '+data.facilitiesMemberDTO2.useEndDt+'</span>');
					$("#mngrMsgSpan").html('<span class="emtxt_02">'+data.mngrMsg+'</span>');
					//$("#btnConfirm").hide();
				 }
				
				/*if(data.rsvtPgr == "020003" || data.rsvtPgr == "020005"){ //예약완료 또는 예약취소
					location.reload(); //새로고침
				}*/
			}

// 			console.log(submitData);
 			ajaxSubmit(url, submitData, callback, null);
		}
	});

	//저장
	$('#btnConfirm').on('click', function(){
		var rsvtPgr = $("select[name=rsvtPgr]").val();
		
		if( rsvtPgr =="020002" || rsvtPgr =="020003"){ //승인완료, 예약완료
			if($("input[name=useStrDt]").val().trim()=="" || $("input[name=useEndDt]").val().trim()==""){
				alert("사용일을 입력하세요.");
				return;
			}
		}
		
		if($("input[name=mngrMsg]").val().length > 100){
			alert("관리자메모는 100자 이하로 입력하세요.(현재 "+$("input[name=mngrMsg]").val().length+"자)");
			return;
		}		
		
		if( rsvtPgr == "020005" ){ //예약취소
			
			if($("input[name=rsvtPgrCau]").val().trim()==""){
				alert("예약취소 사유를 입력하세요.");
				$("input[name=rsvtPgrCau]").focus();
				return;
			}
			
			if($("input[name=rsvtPgrCau]").val().length > 100){
				alert("예약취소사유는 100자 이하로 입력하세요.(현재 "+$("input[name=rsvtPgrCau]").val().length+"자)");
				return;
			}

			var confirmMsg = "취소 하시겠습니까?";
			if(!confirm(confirmMsg)){
				return;
			}
		}else{ //예약취소가 아닌 경우
			$("input[name=rsvtPgrCau]").val(""); //예약취소 사유는 지운다.
		}
		
		if( rsvtPgr == "020006" ){
			alert("임의로 결제기한초과 상태로 변경할 수 없습니다.");
			return;
		}
		if( rsvtPgr == "020007" ){
			alert("임의로 멤버십만료 상태로 변경할 수 없습니다.");
			return;
		}		
		$('form[name=infoFm]')
		.find('input[name=fctMbrIdx]')
		.val('<c:out value="${reserveInfo.fctMbrIdx}" />');

		$('form[name=infoFm]').submit();
	});

	//목록
	$('#btnGoList').on('click', function(){
		//window.history.back();
		location.replace("/reserve/facilities/reserve_list2.do?locTypCd=${reserveInfo.locTypCd}");
	});
	
	//인쇄
	$('#btnPrint').on('click', function(){
		//window.open('/reserve/facilities/reserve_info.do?locTypCd=<c:out value="${reserveInfo.locTypCd}" />&fctMbrIdx='+$(this).val()+'&fctType=1&isPopup=Y','newwin','width=1000, height=800, scrollbars=yes');
		openPrintWindow('<c:url value="/reserve/facilities/reserve_info2.do?isPopup=Y&fctMbrIdx='+$(this).val()+'&fctType=1&locTypCd="/>'+'<c:out value="${reserveInfo.locTypCd }"/>','btnPrint','width=1200, height=800, scrollbars=yes, resizable=yes');
	});
	
	//초기값
	$(document).ready(function(){

		//상태콤보
		var $selectBox = '<select name="rsvtPgr" style="width:50%" >';
		<c:forEach var="rsvtPgrs" items="${rsvtPgrs }">
			$selectBox += '<option value=<c:out value="${rsvtPgrs.cmmnCd }" /> ><c:out value="${rsvtPgrs.cmmnCdNm }" /></option>';
		</c:forEach>
		$selectBox += '<option value=<c:out value="020006" /> ><c:out value="결제기한초과" /></option>';
		$selectBox += '<option value=<c:out value="020007" /> ><c:out value="멤버십만료" /></option>';
		$selectBox += '</select>';

		$("#rsvtSpan").html($selectBox);
		$("select[name=rsvtPgr]").val('<c:out value="${reserveInfo.rsvtPgr}" />');

		//사용일 input
		var $useStrDtInput = '<input type="text" class="cal" id="useStrDt" name="useStrDt" value="<c:out value="${reserveInfo.useStrDt }"/>" style="width:100px"/><img class="btnCal" src="../../img/ico_cal.png" alt="캘린더">';
		var $useEndDtInput = '<input type="text" class="cal" id="useEndDt" name="useEndDt" value="<c:out value="${reserveInfo.useEndDt }"/>" style="width:100px"/><img class="btnCal" src="../../img/ico_cal.png" alt="캘린더">';
		$("#useStrEndDtspan").html($useStrDtInput+" ~ "+$useEndDtInput);
		
		//예약취소사유 input
		var $rsvtPgrCauInput = '<input type="text" name="rsvtPgrCau" value="<c:out value="${reserveInfo.rsvtPgrCau }"/>" style="width:100%"/>';
		$("#rsvtPgrCauSpan").html($rsvtPgrCauInput);
		$("#table1 #row2").hide();
		
		//관리자메모 input
		var $mngrMsgInput = '<input type="text" name="mngrMsg" value="<c:out value="${reserveInfo.mngrMsg }"/>" style="width:100%"/>';
		$("#mngrMsgSpan").html($mngrMsgInput);
		
		//상태 변경시
		$("select[name=rsvtPgr]").change(function(){
	        if( $(this).val() == "020005" ){ //예약취소
	        	$("#table1 #row2").show();
	        }else{
	        	$("#table1 #row2").hide();
	        }
	    });

		<c:if test="${reserveInfo.rsvtPgrNm eq '예약완료'}">
		    $("select[name=rsvtPgr]").prop('disabled', true);
			//$("#rsvtSpan").html('예약번호 : <c:out value="${reserveInfo.fctMbrIdx}" />');
			//$("#mngrMsgSpan").html('<span class="emtxt_02"><c:out value="${reserveInfo.mngrMsg}" /></span>');
			//$("#btnConfirm").hide();
		</c:if>
		<c:if test="${reserveInfo.rsvtPgrNm eq '예약취소'}">
			$("#rsvtSpan").html('<span class="emtxt_02">예약취소: <c:out value="${reserveInfo.fctMbrIdx}" /></span>');
			
			$("#table1 #row2").show();
			<c:choose>
				<c:when test="${reserveInfo.rsvtPgrCau ne ''}">$("#rsvtPgrCauSpan").html('<span class="emtxt_02">관리자 취소: <c:out value="${reserveInfo.rsvtPgrCau}" /></span>');</c:when>
				<c:otherwise>$("#rsvtPgrCauSpan").html('<span class="emtxt_02"><c:out value="신청자 취소" /></span>');</c:otherwise>
			</c:choose>
			
			$("#useStrEndDtspan").html('<span class="emtxt_02"><c:out value="${reserveInfo.useStrDt} ~ ${reserveInfo.useEndDt}" /></span>');
			$("#mngrMsgSpan").html('<span class="emtxt_02"><c:out value="${reserveInfo.mngrMsg}" /></span>');
			$("#btnConfirm").hide();
		</c:if>
		
		//alert("assOztnNm=${reserveInfo.assOztnNm}"+"oztnNm= ${reserveInfo.oztnNm}"+"mbrIdx= ${reserveInfo.mbrIdx}");
		
		//주사용 장비 체크
		var mainEqpList = [];
		<c:forEach var="mainEqp_" items="${mainEqpList }">
			mainEqpList.push('<c:out value="${mainEqp_}"/>');
		</c:forEach>

		$('form[name=infoFm] input:checkbox[name=mainEqp]').each(function(){
			if ($.inArray($(this).val(), mainEqpList) !== -1) {
				$(this).prop('checked', true);
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
		
		$('.btnCal')
		.on('click', function(){
			$(this).prev('.cal').datepicker('show');
			
			return false;
		});		
		
	});
});
</script>
<div class="location_area"><h3><c:out value="${mainTitle }" /></h3>
	<c:if test="${isPopup ne 'Y' }" > <!-- 팝업으로 호출한 경우 불필요 -->
	<div class="fr" style="position:relative; top:-27px;">
		<button id="btnPrint" class="btnBasic_blue"  value='<c:out value="${reserveInfo.fctMbrIdx}" />'>인쇄</button>
	</div>
	</c:if>
</div>
<div class="subcon_area">
<form:form commandName="reqFacilitiesMemberInfoVO2" name="infoFm" method="post">
	<form:hidden path="fctMbrIdx"/>
	<div class="basicTbl02">
		<table id="table1">
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width="330px"/>
				<col width="120px"/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>신청일</th>
				<td><c:out value="${reserveInfo.creDttm }" /></td>
				<th>사용일</th>
				<td>
				    <span id="useStrEndDtspan"></span>
				</td>				
				<th>상태</th>
				<td>
					<span id="rsvtSpan"></span>
				</td>
				<td>
					<c:if test="${isPopup ne 'Y' }" > <!-- 팝업으로 호출한 경우 불필요 -->
					<button id="btnConfirm" class="btnBasic_blue">저장</button>
					</c:if>
				</td>
			</tr>
			<tr id="row2">
				<th>예약취소사유</th>
				<td colspan="6">
				    <span id="rsvtPgrCauSpan"></span>
				</td>
			</tr>
			<tr>
				<th>관리자메모</th>
				<td colspan="6">
				    <span id="mngrMsgSpan"></span>
				</td>
			</tr>						
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>회원정보</strong>
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
				<th>멤버구분</th>
				<td><c:out value="${reserveInfo.mbrTypCdNm }" /></td>
				<th>아이디(이메일)</th>
				<td><c:out value="${reserveInfo.mbrId}" /></td>
			</tr>
			<tr>
				<th>단체명(성명)</th>
				<td>
					<c:choose>
				      <c:when test="${reserveInfo.assOztnNm eq reserveInfo.oztnNm}">
				            <c:out value="${reserveInfo.oztnNm}" />
				      </c:when>
				      <c:when test="${reserveInfo.assOztnNm !=''}">
				            <c:out value="${reserveInfo.assOztnNm} ( ${reserveInfo.oztnNm} )" />
				      </c:when>				      
				      <c:otherwise>
				            <c:out value="${reserveInfo.oztnNm}" />
				      </c:otherwise>
				   </c:choose>				
				</td>
				<th>대표휴대전화</th>
				<td><c:out value="${reserveInfo.ceoPhn}" /></td>
			</tr>
		</table>
	</div>
	
	<div class="stit mt_20 mb_10">
		<strong>결제정보( <c:out value="${reserveInfo.locTypCdNm}" /> )</strong>
	</div>
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
 			<tr>
				<th>할인정보</th>
				<td>
				   <c:choose>
				      <c:when test="${reserveInfo.prc eq '0'} "> <!-- 기본사용료 무료인 경우 -->
				             할인정보가 없습니다.
				      </c:when>
				      <%-- <c:when test="${reserveInfo.mbrIdx eq '212' or reserveInfo.assOztnNm eq '서울혁신센터'}">
				             서울혁신센터 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when> --%>
				      <c:otherwise>
				              할인정보가 없습니다.
				      </c:otherwise>
				   </c:choose>
				</td>
			</tr>		
			<tr>
				<td colspan="2" class="total tar">
					<strong>총 결제금액 <span class="emtxt"><c:out value="${reserveInfo.prc}" />원</span></strong>
				</td>
			</tr>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>신청정보</strong>
	</div>
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>멤버십회원 번호</th>
				<td>
					<c:out value="${reserveInfo.fctMbrIdx}" />
				</td>
			</tr>			
			<tr>
				<th>단체명(성명)</th>
				<td>
					<c:choose>
				      <c:when test="${reserveInfo.assOztnNm eq reserveInfo.oztnNm}">
				            <c:out value="${reserveInfo.oztnNm}" />
				      </c:when>
				      <c:when test="${reserveInfo.assOztnNm !=''}">
				            <c:out value="${reserveInfo.assOztnNm} ( ${reserveInfo.oztnNm} )" />
				      </c:when>				      
				      <c:otherwise>
				            <c:out value="${reserveInfo.oztnNm}" />
				      </c:otherwise>
				   </c:choose>
				</td>
			</tr>
			<tr>
				<th>팀명(Team)</th>
				<td>
					<c:out value="${reserveInfo.timNm}" />
				</td>
			</tr>
			<tr>
				<th>비상연락망<br/>(Emergency Call)</th>
				<td>
					<c:out value="${reserveInfo.emeCal}" />
				</td>
			</tr>
			<tr>
				<th>사용희망 시작일</th>
				<td>
					<strong class="emtxt_02"><c:out value="${reserveInfo.hopUseStrDt}" /></strong>
				</td>
			</tr>
			<tr>
				<th>주사용 장비<br/>(Main Equipment to Use)</th>
				<td>
				    <c:forEach var="mainEqpListCommon" items="${mainEqpListCommon }">
						<input type="checkbox" name="mainEqp" id='mainEqp_<c:out value="${mainEqpListCommon.cmmnCd }"/>' value="<c:out value="${mainEqpListCommon.cmmnCd }"/>" disabled />
						<c:out value="${mainEqpListCommon.cmmnCdNm }"/>&nbsp;&nbsp;						
					</c:forEach><br/>
					기타 : <c:out value="${reserveInfo.mainEqpEtc}" />
				</td>
			</tr>
			<tr>
				<th>주사용 일정</th>
				<td>
					<c:out value="${reserveInfo.mainSch}" />
				</td>
			</tr>
			<tr>
				<th>주사용 재료<br/>(Main Material to Use)</th>
				<td>
					<c:out value="${reserveInfo.mainMat}" />
				</td>
			</tr>
			<tr>
				<th>가입유형</th>
				<td>
					<c:out value="${reserveInfo.joinTypeNm}" />
				</td>
			</tr>															
			<tr>
				<th>프로젝트 계획서</th>
				<td>
					<c:if test="${!empty reserveInfo.aplyFilePath }">
						<a href='#none' onclick="fileDownload('<c:out value="${reserveInfo.aplyFilePath }"  />')" >프로젝트 계획서 다운로드</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>프로젝트 추가자료</th>
				<td>
					<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
							<a href='#none' onclick="fileDownload('<c:out value="${fileList.filePath }"  />')" ><c:out value="${fileList.origFileNm }"  /></a>
					</c:forEach>
				</td>
			</tr>			
			<tr>
				<th>결제방식</th>
				<td>
					<c:out value="${reserveInfo.payTypeNm}" />
				</td>
			</tr>			
		</table>
	</div>	
</form:form>
</div>
<c:if test="${isPopup ne 'Y' }" > <!-- 팝업으로 호출한 경우 불필요 -->
<div class="tac">
	<button id="btnGoList" class="btnBasic_black">목록</button>
</div>
</c:if>