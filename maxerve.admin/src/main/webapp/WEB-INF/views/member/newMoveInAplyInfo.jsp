<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
textarea[readonly], input[readonly] {background-color: #eee;}
input.cal {background-color: white;}
input.cal[disabled] {background-color: #eee;}
</style>
<script type="text/javascript">
function goList() {
	history.back();
}

jQuery(function($){
	// 단체형태 기타를 맨끝으로
	var $label = $('input[name=oztnTypCds][value=014007]').parent();
	$label.parent().append($label);


 	$.validator.addMethod("oztnTypEtcCheck", function(value, element){
		var checkEtc = $("input[name=oztnTypCd]").val();
		checkEtc = checkEtc.substring(checkEtc.length-1,checkEtc.length);

		var trueCheck = true;
		if(checkEtc == 7 && $("input[name=oztnTypEtc]").val() == "" ){
			trueCheck = false;
		}
		return (trueCheck == true);
	}, 'required')

	$('form[name=form]').validate({
		rules: {
			oztnTypCd : "required",
			oztnTypEtc : "oztnTypEtcCheck",
			svcAct : "required",
			ceoEmil : "email",
			oztnAddr : "required",
			ofcPhn : "required",
			ofcFax : "required",
			mngrNm : "required",
			mngrPhn : "required",
			mngrEmil : "email",
			mvinGrpCd : "required",
			ctgrIdx : "required"
		},
		messages: {
			oztnTypCd : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '단체형태 선택은'),
			oztnTypEtc : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '단체형태 기타 내용은'),
			svcAct : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '주요활동은'),
			ceoEmil : getMessage('org.hibernate.validator.constraints.Email.message', '대표자 이메일은'),
			oztnAddr : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '단체주소는'),
			ofcPhn : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '사무실연락처는'),
			ofcFax : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '사무실팩스는'),
			mngrNm : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '담장자 성명은'),
			mngrPhn : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '담장자 휴대전화는'),
			mngrEmil : getMessage('org.hibernate.validator.constraints.Email.message', '담당자 이메일은'),
			mvinGrpCd : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '입주그룹 선택은'),
			ctgrIdx : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '업종선택은')
		},
		submitHandler: function(form) {
			$(form).append('<input type="hidden" name="mvinAplyIdx" value=<c:out value="${moveInOztnInfo.mvinAplyIdx }" /> />');
			var url = '<c:out value="/member/updateNewMoveInOztnInfo.json"/>';
			var submitData = $(form).serializeFormJSON();

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
// 				window.location.reload();
				goList();
			}

			ajaxSubmit(url, submitData, callback, null);
		}
	});

 	$('form[name=form]')
 	// 업종선택
	.on('change', 'input[name=ctgrIdxList]', function(){
		if ($(this).prop('checked') && $('form[name=form] input[name=ctgrIdxList]:checked').length > 3) {
			alert('업종은 최대 3개까지만 가능합니다.');
			$(this).prop('checked', false);
		}
	});


	$('#btnConfirm').on('click', function(){
		var ctgrIdxList = [];
		$("input[type='checkbox']:checked").each(function(index){
			ctgrIdxList.push($(this).val());
		});
		$("input[name=ctgrIdx]").val(ctgrIdxList);

		$("form[name=form]").submit();
	});

	$("input[name=oztnTypCds]").on("click",function(){
		$("input[type='radio']").each(function(){
			if($(this).is(':checked') == true){
				$("input[name=oztnTypCd]").val($(this).val());
				var checkEtc = $(this).val();
				if(checkEtc.substring(checkEtc.length-1,checkEtc.length) < 7){
					$("input[name=oztnTypEtc]").prop("readonly",true);
					$("input[name=oztnTypEtc]").val("");
				}else{
					$("input[name=oztnTypEtc]").prop("readonly",false);
				}
			}
		});
	});

	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#mvinSrtDt" )
	        .datepicker({
	          defaultDate: "+1w",
	          changeMonth: true,
	          numberOfMonths: 1
	        }).on( "change", function() {
	          to.datepicker( "option", "minDate", getDate( this ) );
	        }),
	      to = $( "#mvinEndDt" ).datepicker({
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

	$('form[name=aplyFm]').validate({
		rules: {
			aplyPgrCd	:	"required"
		},
		messages: {
			aplyPgrCd	:	getMessage('org.hibernate.validator.constraints.NotEmpty.message', '진행상태 선택은')
		},
		submitHandler: function(form) {
			$(form).append('<input type="hidden" name="mvinAplyIdx" value=<c:out value="${moveInOztnInfo.mvinAplyIdx }" /> />');
			var url = '<c:out value="/member/updateNewMoveInAply.json"/>';
			var submitData = $(form).serializeFormJSON();

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				goList();
			}

			ajaxSubmit(url, submitData, callback, null);
		}
	});

	$('form[name=aplyFm]')
	// 진행상태변경
	.on('change', 'select[name=aplyPgrCd]', function(){
		if ($(this).find('option:selected').val() == '022003') {
			$('form[name=aplyFm] input[name=mvinSrtDt]').prop('disabled', false).rules('add', {
				required: true,
				messages: {
					required: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '입주기간 시작일은')
				}
			});

			$('form[name=aplyFm] input[name=mvinEndDt]').prop('disabled', false).rules('add', {
				required: true,
				messages: {
					required: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '입주기간 종료일은')
				}
			});

			$('form[name=aplyFm] input[name=mvinPlc]').prop('disabled', false).rules('add', {
				required: true,
				messages: {
					required: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '입주장소는')
				}
			});
		} else {
			$('form[name=aplyFm]')
			.find('input[name=mvinSrtDt], input[name=mvinEndDt], input[name=mvinPlc]')
			.prop('disabled', true)
			.rules('remove', 'required');
		}
	});

	$("#btnExcelDown").on('click',function(){
		window.open('<c:url value="newMoveInAplyInfoExcel.do?mvinAplyIdx=${moveInOztnInfo.mvinAplyIdx }" />', "", "");

		return false;
	});

	$('#btnAplyConfirm').on('click', function(){
		$("form[name=aplyFm]").submit();

		return false;
	});

	$('#btnListPg').on('click', function(){
		window.history.back();

		return false;
	});

	$('.btnCal')
	.on('click', function(){
		if (!$(this).prev('.cal').prop('disabled')) {
			$(this).prev('.cal').datepicker('show');
		}

		return false;
	});
});

</script>
<div class="location_area"><h3>신규입주신청</h3></div>
<div class="subcon_area">
	<div class="stit mt_20 mb_10">
		<strong>입주정보</strong>
		<div class="fr">
			<button id="btnExcelDown" class="btnBasic_blue">엑셀저장</button>
		</div>
	</div>

	<form name="aplyFm" method="post" >
		<div class="basicTbl02">
			<table>
				<caption></caption>
				<colgroup>
					<col width="120px"/>
					<col width=""/>
					<col width="120px"/>
					<col width=""/>
				</colgroup>
				<tr>
					<th>입주기간</th>
					<td>
						<input type="text" class='cal' id="mvinSrtDt" name="mvinSrtDt" value="${moveInOztnInfo.mvinSrtDt }" readonly="readonly" DISABLED /><img class='btnCal' src="../../img/ico_cal.png" alt="캘린더"><span class="mr_20">부터</span>
						<input type="text" class='cal' id="mvinEndDt" name="mvinEndDt" value="${moveInOztnInfo.mvinEndDt }" readonly="readonly" DISABLED /><img class='btnCal' src="../../img/ico_cal.png" alt="캘린더"><span>까지</span>
					</td>
					<th>온라인멤버</th>
					<td> <c:out value="${moveInOztnInfo.onlineMb }" /></td>
				</tr>
				<tr>
					<th>입주장소</th>
					<td><input type="text" id="mvinPlc" name="mvinPlc" value="${moveInOztnInfo.mvinPlc }" DISABLED /></td>
					<th>진행상태</th>
					<td>
						<select name="aplyPgrCd">
							<c:forEach items="${aplyPgrCds }" var="aplyPgrCds" varStatus="varStatus">
										<option value="${aplyPgrCds.cmmnCd }" <c:if test="${aplyPgrCds.cmmnCd eq moveInOztnInfo.aplyPgrCd }"> selected="selected" </c:if> >${aplyPgrCds.cmmnCdNm }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</div>

		<div class="stit mt_20 mb_10">
			<strong>관리자 메모</strong>
		</div>
		<div>
			<textarea rows="3" name="mngrCmmt" style="width:100%"><c:out value="${moveInOztnInfo.mngrCmmt }"/></textarea>
		</div>
	</form>
<sec:authorize access="hasRole('023003002Y')">
	<div class="btns tac mb_40">
		<button id="btnAplyConfirm" class="btnBasic_blue">저장</button>
	</div>
</sec:authorize>

	<div class="stit mt_20 mb_10">
		<strong>단체정보</strong>
	</div>
	<form name="form" method="post" >
		<div class="basicTbl02">
			<table>
				<caption></caption>
				<colgroup>
					<col width="120px"/>
					<col width=""/>
					<col width="120px"/>
					<col width=""/>
				</colgroup>
				<tr>
					<th>단체명<span class="red">*</span></th>
					<td><c:out value="${moveInOztnInfo.oztnNm }" /></td>
					<th>입주그룹<span class="red">*</span></th>
					<td>
						<select name="mvinGrpCd">
							<c:forEach items="${mvinGrpCds }" var="mvinGrpCds" varStatus="varStatus">
										<option value="<c:out value='${mvinGrpCds.cmmnCd }'/>" <c:if test="${mvinGrpCds.cmmnCd eq moveInOztnInfo.mvinGrpCd }"> selected="selected" </c:if> ><c:out value='${mvinGrpCds.cmmnCdNm }'/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>단체형태<span class="red">*</span></th>
					<td colspan="3">
						<c:forEach items="${oztnTypCds }" var="oztnTypCds" varStatus="varStatus" end="10">
							<label class="mr_10"><input type="radio" name="oztnTypCds" value="${oztnTypCds.cmmnCd }" <c:if test="${oztnTypCds.cmmnCd eq moveInOztnInfo.oztnTypCd }"> checked="checked" </c:if> />${oztnTypCds.cmmnCdNm }
								<c:if test="${oztnTypCds.cmmnCdNm eq '기타' }">
									<input type="text" name="oztnTypEtc" placeholder="기타 단체형태를 입력하세요." value="<c:out value='${moveInOztnInfo.oztnTypEtc }'/>"${moveInOztnInfo.oztnTypCd eq '014007' ? '': ' READONLY' } />
								</c:if>
							</label>
						</c:forEach>
						<input type="hidden" name="oztnTypCd" value="<c:out value='${moveInOztnInfo.oztnTypCd }'/>" />
					</td>
				</tr>
				<tr>
					<th>업종<span class="red">*</span></th>
					<td colspan="3">
						<c:forEach items="${ctgrIdxList }" var="ctgrIdxList" varStatus="varStatus">
							<label class="mr_10">
								<input type="checkbox" name="ctgrIdxList" value="<c:out value='${ctgrIdxList.ctgrIdx }'/>"
									<c:forEach items="${moveInApplyCtgrList }" var="moveInApplyCtgrs" varStatus="varStatus">
										<c:if test="${ctgrIdxList.ctgrIdx eq moveInApplyCtgrs.ctgrIdx }"> checked="checked" </c:if>
									</c:forEach>
								 />${ctgrIdxList.ctgrNm }
							</label>
						</c:forEach>
						<input type="hidden" name="ctgrIdx" value="" />
					</td>
				</tr>
				<tr>
					<th>주요활동<span class="red">*</span><br/>(서비스/제품)</th>
					<td colspan="3">
						<textarea rows="" cols="100" id="svcAct" name="svcAct"><c:out value="${moveInOztnInfo.svcAct }"/></textarea>
					</td>
				</tr>
				<tr>
					<th rowspan="3">대표자<span class="red">*</span></th>
					<td>성명<input type="text" name="ceoNm" value="<c:out value='${moveInOztnInfo.ceoNm }'/>" readonly="readonly"/></td>
					<th>상근인원</th>
					<td><input type="text" name="ftePsct" value="<c:out value='${moveInOztnInfo.ftePsct }'/>" /></td>
				</tr>
				<tr>
					<td>이메일<input type="text" name="ceoEmil" value="<c:out value='${moveInOztnInfo.ceoEmil }'/>" READONLY /></td>
					<th>사업자<br/>등록번호</th>
					<td><input type="text" name="CRN" value="<c:out value='${moveInOztnInfo.CRN }'/>" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>휴대전화<input type="text" name="ceoPhn" value="<c:out value='${moveInOztnInfo.ceoPhn }'/>" READONLY /></td>
					<th>설립일</th>
					<td><input type="text" name="estDt" value="<c:out value='${moveInOztnInfo.estDt }'/>" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더"></td>
				</tr>
				<tr>
					<th>단체주소<span class="red">*</span></th>
					<td><input type="text" name="oztnAddr" value="<c:out value='${moveInOztnInfo.oztnAddr }'/>" /></td>
					<th>홈페이지</th>
					<td><input type="text" name="hpg" value="<c:out value='${moveInOztnInfo.hpg }'/>" /></td>
				</tr>
				<tr>
					<th rowspan="3">
						연락처<span class="red">*</span><br/>
						(사무실)
					</th>
					<td>전화번호<input type="text" name="ofcPhn" value="<c:out value='${moveInOztnInfo.ofcPhn }'/>" /></td>
					<th rowspan="3">
						담당자<span class="red">*</span>
					</th>
					<td>성명<input type="text" name="mngrNm" value="<c:out value='${moveInOztnInfo.mngrNm }'/>" /></td>
				</tr>
				<tr>
					<td>팩스번호<input type="text" name="ofcFax" value="<c:out value='${moveInOztnInfo.ofcFax }'/>" /></td>
					<td>휴대전화<input type="text" name="mngrPhn" value="<c:out value='${moveInOztnInfo.mngrPhn }'/>" /></td>
				</tr>
				<tr>
					<td></td>
					<td>이메일<input type="text" name="mngrEmil" value="<c:out value='${moveInOztnInfo.mngrEmil }'/>" /></td>
				</tr>
				<tr>
					<th>주요활동<br/>/연혁</th>
					<td colspan="3">
						<textarea readonly="readonly" style="width: 100%">
							<c:out value="${moveInOztnInfo.mainAct}" />
						</textarea>
					</td>
				</tr>
			</table>
		</div>
		<div class="stit mt_20">
			단체(팀)소개
			<div class="fr">
				<c:out value="${moveInOztnInfo.oztnItdc.length() }" />/500자
			</div>
		</div>
		<div class="small_list">
			<c:out value="${moveInOztnInfo.oztnItdc}" />
		</div>
		<div class="stit mt_20">
			단체(팀)에서 생각하는 사회혁신
			<div class="fr">
				<c:out value="${moveInOztnInfo.sclInv.length() }" />/500자
			</div>
		</div>
		<div class="small_list">
			<c:out value="${moveInOztnInfo.sclInv}" />
		</div>
		<div class="stit mt_20">
			해결하고자하는 사회문제
			<div class="fr">
				<c:out value="${moveInOztnInfo.sclIsu.length() }" />/500자
			</div>
		</div>
		<div class="small_list">
			<c:out value="${moveInOztnInfo.sclIsu}" />
		</div>
		<div class="stit mt_20">
			문제해결 아이디어
			<div class="fr">
				<c:out value="${moveInOztnInfo.isuIdea.length() }" />/500자
			</div>
		</div>
		<div class="small_list">
			<c:out value="${moveInOztnInfo.isuIdea}" />
		</div>
		<div class="stit mt_20">
			기대효과
			<div class="fr">
				<c:out value="${moveInOztnInfo.epctEft.length() }" />/500자
			</div>
		</div>
		<div class="small_list">
			<c:out value="${moveInOztnInfo.epctEft}" />
		</div>
		<div class="stit mt_20">
			단체(팀)의 핵심역량
			<div class="fr">
				<c:out value="${moveInOztnInfo.oztnCcpc.length() }" />/500자
			</div>
		</div>
		<div class="small_list">
			<c:out value="${moveInOztnInfo.oztnCcpc}" />
		</div>
		<div class="stit mt_20">
			주요 사업(활동)계획
			<div class="fr">
				<c:out value="${moveInOztnInfo.bssPlan.length() }" />/500자
			</div>
		</div>
		<div class="small_list">
			<c:out value="${moveInOztnInfo.bssPlan}" />
		</div>
		<div class="stit mt_20">
			입주 시 협업계획
			<div class="fr">
				<c:out value="${moveInOztnInfo.cprPlan.length() }" />/500자
			</div>
		</div>
		<div class="small_list">
			<c:out value="${moveInOztnInfo.cprPlan}" />
		</div>
		<div class="stit mt_20">
			공유가능자원, 필요자원
			<div class="fr">
				<c:out value="${moveInOztnInfo.cmmnRsc.length() }" />/500자
			</div>
		</div>
		<div class="small_list">
			<c:out value="${moveInOztnInfo.cmmnRsc}" />
		</div>

		<p>첨부서류</p>
		<p>- 필수 : 번인등기부등본, 주민등록증, 사업자등록증 중 해당 서류</p>
		<p>- 선택 : 사용료 감면 단체 증빙서류, 단체 소개서</p>
		<c:choose>
			<c:when test="${fn:length(fileList) > 0 }">
				<c:forEach items="${fileList}" var="fileList" varStatus="varStatus">
					<span class="btnTag"><a href="#none" onclick="fileDownload('<c:out value="${fileList.filePath}" />')" ><c:out value="${fileList.origFileNm}" /></a></span>
				</c:forEach>
			</c:when>
			<c:otherwise><p >첨부파일없음</p></c:otherwise>
		</c:choose>
	</form>
</div>
<div class="btns tac mb_40">
<sec:authorize access="hasRole('023003002Y')">
	<button id="btnConfirm" class="btnBasic_blue">저장</button>
</sec:authorize>
	<button id="btnListPg" class="btnBasic_black">목록</button>
</div>