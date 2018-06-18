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
	    }, 200);
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
			fctRsvtIdx: 'required',
			rsvtPgr: 'required'
		},
		messages: {
			fctRsvtIdx: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '예약번호는'),
			rsvtPgr: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '진행상태는')
		},
		submitHandler: function(form) {
			var url = '<c:out value="/reserve/facilities/updateReserve.json"/>';
			var submitData = $(form).serializeFormJSON();
			var callback = function(data) {
				
				alert(getMessage('msg.save.success'));
				
				if(data.rsvtPgr == "020003" ){ //예약완료
					$("#rsvtSpan").html('예약번호 : '+data.fctRsvtIdx);
					$("#mngrMsgSpan").html('<span class="emtxt_02">'+data.mngrMsg+'</span>');
					$("#btnConfirm").hide();
				}else if(data.rsvtPgr == "020005"){ //예약취소
					$("#rsvtSpan").html('<span class="emtxt_02">예약취소: '+data.fctRsvtIdx+'</span>');
					
					$("#table1 #row2").show();
					
					if(data.rsvtPgrCau != ""){
						$("#rsvtPgrCauSpan").html('<span class="emtxt_02">관리자 취소: '+data.rsvtPgrCau+'</span>');
					}else{
						$("#rsvtPgrCauSpan").html('<span class="emtxt_02">신청자 취소</span>');
					}
				
					$("#mngrMsgSpan").html('<span class="emtxt_02">'+data.mngrMsg+'</span>');
					$("#btnConfirm").hide();
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
		$('form[name=infoFm]')
		.find('input[name=fctRsvtIdx]')
		.val('<c:out value="${reserveInfo.fctRsvtIdx}" />');

		$('form[name=infoFm]').submit();
	});

	//목록
	$('#btnGoList').on('click', function(){
		//window.history.back();
		location.replace("/reserve/facilities/reserve_list.do?rsvtPlcCd=${reserveInfo.rsvtPlcCd}&fctGubn=${fctGubn}");
	});
	
	//인쇄
	$('#btnPrint').on('click', function(){
		//window.open('/reserve/facilities/reserve_info.do?rsvtPlcCd=<c:out value="${reserveInfo.rsvtPlcCd}" />&fctRsvtIdx='+$(this).val()+'&fctType=1&isPopup=Y','newwin','width=1000, height=800, scrollbars=yes');
		openPrintWindow('<c:url value="/reserve/facilities/reserve_info.do?isPopup=Y&fctRsvtIdx='+$(this).val()+'&fctType=1&rsvtPlcCd="/>'+'<c:out value="${reserveInfo.rsvtPlcCd }"/>','btnPrint','width=1000, height=800, scrollbars=yes');
	});
	
	//초기값
	$(document).ready(function(){

		//상태콤보
		var $selectBox = '<select name="rsvtPgr" style="width:50%" >';
		<c:forEach var="rsvtPgrs" items="${rsvtPgrs }">
			$selectBox += '<option value=<c:out value="${rsvtPgrs.cmmnCd }" /> ><c:out value="${rsvtPgrs.cmmnCdNm }" /></option>';
		</c:forEach>
		$selectBox += '<option value=<c:out value="020006" /> ><c:out value="결제기한초과" /></option>';
		$selectBox += '</select>';

		$("#rsvtSpan").html($selectBox);
		$("select[name=rsvtPgr]").val('<c:out value="${reserveInfo.rsvtPgr}" />');
		
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
			$("#rsvtSpan").html('예약번호 : <c:out value="${reserveInfo.fctRsvtIdx}" />');
			$("#mngrMsgSpan").html('<span class="emtxt_02"><c:out value="${reserveInfo.mngrMsg}" /></span>');
			$("#btnConfirm").hide();
		</c:if>
		<c:if test="${reserveInfo.rsvtPgrNm eq '예약취소'}">
			$("#rsvtSpan").html('<span class="emtxt_02">예약취소: <c:out value="${reserveInfo.fctRsvtIdx}" /></span>');
			
			$("#table1 #row2").show();
			<c:choose>
				<c:when test="${reserveInfo.rsvtPgrCau ne ''}">$("#rsvtPgrCauSpan").html('<span class="emtxt_02">관리자 취소: <c:out value="${reserveInfo.rsvtPgrCau}" /></span>');</c:when>
				<c:otherwise>$("#rsvtPgrCauSpan").html('<span class="emtxt_02"><c:out value="신청자 취소" /></span>');</c:otherwise>
			</c:choose>
			
			$("#mngrMsgSpan").html('<span class="emtxt_02"><c:out value="${reserveInfo.mngrMsg}" /></span>');
			$("#btnConfirm").hide();
		</c:if>
		
		//alert("assOztnNm=${reserveInfo.assOztnNm}"+"oztnNm= ${reserveInfo.oztnNm}"+"mbrIdx= ${reserveInfo.mbrIdx}");
		
	});
});
</script>
<div class="location_area"><h3><c:out value="${mainTitle }" /></h3>
	<c:if test="${isPopup ne 'Y' }" > <!-- 팝업으로 호출한 경우 불필요 -->
	<div class="fr" style="position:relative; top:-27px;">
		<button id="btnPrint" class="btnBasic_blue"  value='<c:out value="${reserveInfo.fctRsvtIdx}" />'>인쇄</button>
	</div>
	</c:if>
</div>
<div class="subcon_area">
<form:form commandName="reqFacilitiesReserveVO" name="infoFm" method="post">
	<form:hidden path="fctRsvtIdx"/>
	<div class="basicTbl02">
		<table id="table1">
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>신청일</th>
				<td><c:out value="${reserveInfo.creDttm }" /></td>
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
				<td colspan="4">
				    <span id="rsvtPgrCauSpan"></span>
				</td>
			</tr>
			<tr>
				<th>관리자메모</th>
				<td colspan="4">
				    <span id="mngrMsgSpan"></span>
				</td>
			</tr>						
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>사용자정보</strong>
		<c:if test="${mainTitle.indexOf('야외공간') > 0 }" >
				<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
						<a href='#none' class="btnBasic_black fr" onclick='fileDownload(<c:out value="${fileList.origFilePath }"  />)' ><c:out value="${fileList.origFileNm }"  /></a>
				</c:forEach>
		</c:if>
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
		<strong>예약정보</strong>
	</div>
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
			<tr>
				<th><c:out value="${reserveInfo.fctNm}" /></th>
				<td>
				<c:out value="${reserveInfo.srtDttm}" /> ~ <c:out value="${reserveInfo.endDttm}" />
				<strong class="emtxt_02">(총 <c:out value="${reserveInfo.useHour}" /> 시간)</strong> = <strong class="emtxt_02"><c:out value="${reserveInfo.prc}" />원</strong>
				</td>
			</tr>
		<c:set var="eqpIsFree" value="Y" />	
		<c:if test="${!empty equipList }">
			<tr>
				<th>예약장비</th>
				<td>
				<c:forEach items="${equipList }" var="item" varStatus="status">
					<c:if test="${status.index gt 0 }">,</c:if>
					<c:out value="${item.eqpNm }(${item.eqpTcnt })"/>
					<!-- 장비 기본사용료 무료체크 -->
					<c:if test="${eqpIsFree eq 'Y' and item.eqpHourPrc !=0 or item.eqpHalfHourPrc !=0 }">
						<c:set var="eqpIsFree" value="N" />
					</c:if>
				</c:forEach>
				</td>
			</tr>
		</c:if>
 			<tr>
				<th>할인정보</th>
				<td>
				   <c:choose>
				      <c:when test="${reserveInfo.dftPrc eq '0' and eqpIsFree eq 'Y'}"> <!-- 기본사용료(시설,장비) 무료인 경우 -->
				             할인정보가 없습니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '212' or reserveInfo.assOztnNm eq '서울혁신센터'}">
				             서울혁신센터 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${mainTitle eq '맛동 예약현황'}">
				             할인정보가 없습니다.
				      </c:when>				      
				      <c:when test="${reserveInfo.mbrIdx eq '202' or reserveInfo.assOztnNm eq '마을공동체종합지원센터'}">
				             마을공동체종합지원센터 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '237' or reserveInfo.assOztnNm eq '서울시청년활동지원센터'}">
				             서울시청년활동지원센터 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '188' or reserveInfo.assOztnNm eq '청년허브'}">
				             청년허브 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '190' or reserveInfo.assOztnNm eq '서울시50플러스 서부캠퍼스'}">
				             서울시50플러스 서부캠퍼스 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '192' or reserveInfo.assOztnNm eq '서울시사회적경제지원센터'}">
				             서울시사회적경제지원센터 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '5891' or reserveInfo.assOztnNm eq '서울특별시협동조합지원센터'}">
				             서울특별시협동조합지원센터 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '215' or reserveInfo.assOztnNm eq '서울시여성가족재단 살림센터'}">
				             서울시여성가족재단 살림센터 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '6243' or reserveInfo.assOztnNm eq '서울학교협동조합협의회'}">
				             서울학교협동조합협의회 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '6729' or reserveInfo.assOztnNm eq '오디세이학교'}">
				             오디세이학교 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>
				      <c:when test="${reserveInfo.mbrIdx eq '6730' or reserveInfo.assOztnNm eq '서울특별시 은평 직장맘지원센터'}">
				             서울특별시 은평 직장맘지원센터 할인 <strong class="emtxt_02">(100%)</strong> 적용된 가격입니다.
				      </c:when>				      
				      <c:when test="${reserveInfo.mbrTypCd eq '015001' }">
				             입주단체 할인 <strong class="emtxt_02">(50%)</strong> 적용된 가격입니다.
				      </c:when>
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

	<c:if test="${mainTitle eq '모두모임방 예약현황' or mainTitle eq '극장동 예약현황' or mainTitle eq '야외공간 예약현황' or mainTitle eq '참여동 예약현황' or mainTitle eq '맛동 예약현황'}" >
	<div class="stit mt_20 mb_10">
		<strong>행사정보</strong>
	</div>
	</c:if>
	<div class="basicTbl02">
		<c:if test="${mainTitle eq '모두모임방 예약현황' or mainTitle eq '극장동 예약현황' or mainTitle eq '참여동 예약현황' or mainTitle eq '맛동 예약현황' }" >
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
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
				<th>담당자</th>
				<td>
					<c:out value="${reserveInfo.rsvtNm}" /> / <c:out value="${reserveInfo.rsvtPhnNmbr}" /> / <c:out value="${reserveInfo.rsvtEmil}" />
				</td>
			</tr>
			<tr>
				<th>사용인원</th>
				<td>
					<c:out value="${reserveInfo.rsvtPsct}" />
				</td>
			</tr>
			<tr>
				<th>행사명</th>
				<td>
					<c:out value="${reserveInfo.evtNm}" />
				</td>
			</tr>
			<tr>
				<th>행사내용</th>
				<td>
					<c:out value="${reserveInfo.evtInfo}" />
				</td>
			</tr>
			<tr>
				<th>참여계획서</th>
				<td>
					<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
							<a href='#none' onclick="fileDownload('<c:out value="${fileList.filePath }"  />')" ><c:out value="${fileList.origFileNm }"  /></a>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>신청서</th>
				<td>
					<c:if test="${!empty reserveInfo.aplyFilePath }">
						<a href='#none' onclick="fileDownload('<c:out value="${reserveInfo.aplyFilePath }"  />')" >신청서 다운로드</a>
					</c:if>
				</td>
			</tr>
		</table>
		</c:if>
		<c:if test="${mainTitle eq '야외공간 예약현황' }" >
		<table>
			<caption></caption>
			<colgroup>
				<col width="150px"/>
				<col width=""/>
			</colgroup>
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
				<th>담당자</th>
				<td>
					<c:out value="${reserveInfo.rsvtNm}" /> / <c:out value="${reserveInfo.rsvtPhnNmbr}" /> / <c:out value="${reserveInfo.rsvtEmil}" />
				</td>
			</tr>
			<tr>
				<th>행사명</th>
				<td>
					<c:out value="${reserveInfo.evtNm}" />
				</td>
			</tr>
			<tr>
				<th>행사내용</th>
				<td>
					<c:out value="${reserveInfo.evtInfo}" />
				</td>
			</tr>
			<tr>
				<th>참가인원</th>
				<td>
					<c:out value="${reserveInfo.rsvtPsct}" />
				</td>
			</tr>
			<tr>
				<th>전기사용</th>
				<td>
					<c:out value="${reserveInfo.powYn eq 'Y'? '사용':'미사용'}" />(<c:out value="${reserveInfo.powInfo}" />)
				</td>
			</tr>
			<tr>
				<th>현수막사용</th>
				<td>
					<c:out value="${reserveInfo.powYn eq 'Y'? '사용':'미사용'}" />(<c:out value="${reserveInfo.bnnrInfo}" />)
				</td>
			</tr>
			<tr>
				<th>홍보물부착</th>
				<td>
					<c:out value="${reserveInfo.prmYn eq 'Y'? '사용':'미사용'}" />(<c:out value="${reserveInfo.prmInfo}" /> )
				</td>
			</tr>
			<tr>
				<th>사용기자재</th>
				<td>
					<c:out value="${reserveInfo.useTnm}" />
				</td>
			</tr>
			<tr>
				<th>환경미화계획</th>
				<td>
					<c:out value="${reserveInfo.clnPlan}" />
				</td>
			</tr>
			<tr>
				<th>안전관리계획</th>
				<td>
					<c:out value="${reserveInfo.safeMngPlan}" />
				</td>
			</tr>
			<tr>
				<th>참여계획서</th>
				<td>
					<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
							<a href='#none' onclick="fileDownload('<c:out value="${fileList.filePath }"  />')" ><c:out value="${fileList.origFileNm }"  /></a>
					</c:forEach>
				</td>
			</tr>			
		</table>
		</c:if>
	</div>
</form:form>
</div>
<c:if test="${isPopup ne 'Y' }" > <!-- 팝업으로 호출한 경우 불필요 -->
<div class="tac">
	<button id="btnGoList" class="btnBasic_black">목록</button>
</div>
</c:if>