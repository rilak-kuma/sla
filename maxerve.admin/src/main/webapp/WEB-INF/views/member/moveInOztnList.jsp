<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">

function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
}

function fn_oztnInfo(mvinAplyIdx) {
	$("#mvinAplyIdx").val(mvinAplyIdx);
	$('form[name=moveInOztnInfo]').submit();
}

 function fn_sort(sortType, num){
	$("#sortType").val(sortType+"_"+num);
	$('form[name=searchFm]').submit();
}


 jQuery(function($){
	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#mvinSrtDtOne" )
	      .on('click',function(){
	    	  $(this).val("");
	    	  }).datepicker({
		          defaultDate: "+1w",
		          changeMonth: true,
		          numberOfMonths: 1
		        }).on( "change", function() {
		          to.datepicker( "option", "minDate", getDate( this ) );
		        }),
		      to = $( "#mvinSrtDtTwo" ).datepicker({
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

	 $( "#btnMvinReg" ).on('click', function(){
		 $('form[name=moveInOztnInfo]').submit();
	 });

	$("#btnExcelDown").on("click",function(){
		$('form[name=searchFm]')
		 .attr({
			 target: '_blank',
			 action: '<c:out value="/member/moveInOztnListExcel.do" />'
		 })
		 .submit();

		 $('form[name=searchFm]').removeAttr("target");
		 $('form[name=searchFm]').attr("action",'<c:out value="/member/moveInOztnList.do" />');
	});

	$('[data-mvin-aply-idx]').on('click', function(ev){
		if ($(ev.target).is('a')) {
			setTimeout(function(){
				$.unblockUI();
			}, 300);
		} else {
			fn_oztnInfo($(this).attr('data-mvin-aply-idx'));
			return false;
		}
	});

	$(document).ready(function(){
		$("#oztnNm").val('<c:out value="${searchVO.oztnNm}" />');
		$("#mvinSrtDtOne").val('<c:out value="${searchVO.mvinSrtDtOne}" />');
		$("#mvinSrtDtTwo").val('<c:out value="${searchVO.mvinSrtDtTwo}" />');
		$("select[name=mvinGrpCd]").val('<c:out value='${searchVO.mvinGrpCd}'/>');
		$("select[name=mvinYn]").val('<c:out value='${searchVO.mvinYn}'/>');

		<c:if test="${searchVO.onlineMb eq 'Y'}" >
			$("#onlineMb").prop("checked",true);
		</c:if>

		var sortType = '<c:out value="${searchVO.sortType}" />';
		if(sortType == 'sortOne_1'){
			$("#sortOne_1").hide();
			$("#sortTwo_2").hide();
			$("#sortThree_2").hide();
			$("#sortFour_2").hide();
		}else if(sortType == 'sortTwo_1'){
			$("#sortOne_2").hide();
			$("#sortTwo_1").hide();
			$("#sortThree_2").hide();
			$("#sortFour_2").hide();
		}else if(sortType == 'sortThree_1'){
			$("#sortOne_2").hide();
			$("#sortTwo_2").hide();
			$("#sortThree_1").hide();
			$("#sortFour_2").hide();
		}else if(sortType == 'sortFour_1'){
			$("#sortOne_2").hide();
			$("#sortTwo_2").hide();
			$("#sortThree_2").hide();
			$("#sortFour_1").hide();
		}else{
			$("#sortOne_2").hide();
			$("#sortTwo_2").hide();
			$("#sortThree_2").hide();
			$("#sortFour_2").hide();
		}
	});

 });

</script>

<div class="location_area"><h3>입주단체관리</h3></div>
<div class="subcon_area">
	<form name="searchFm">
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
				<th>입주상태</th>
				<td>
					<select name="mvinYn">
						<option value="">전체</option>
						<option value="Y">입주중</option>
						<option value="N">기간만료</option>
					</select>
				</td>
				<td rowspan="3">
					<input type="submit" class="btnSearch" value="조회" />
				</td>
			</tr>
			<tr>
				<th>입주일</th>
				<td>
					<input type="text" id="mvinSrtDtOne" name="mvinSrtDtOne" readonly="readonly" /><img src="../../img/ico_cal.png" alt="캘린더"> ~
					<input type="text" id="mvinSrtDtTwo" name="mvinSrtDtTwo" readonly="readonly" /><img src="../../img/ico_cal.png" alt="캘린더">
				</td>
				<th>입주그룹</th>
				<td>
					<select name="mvinGrpCd">
						<option value="">전체</option>
						<c:forEach items="${mvinGrpCds }" var="mvinGrpCds" varStatus="varStatus">
							<option value="${mvinGrpCds.cmmnCd }">${mvinGrpCds.cmmnCdNm }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<input type="checkbox" id="onlineMb" name="onlineMb" value="Y"/> 온라인멤버
				</th>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<div class="btns tar">
		<sec:authorize access="hasRole('023003001Y')">
			<!-- <button id="btnMvinReg" class="btnBasic_blue" >입주단체등록</button> -->
		</sec:authorize>
			<button id="btnExcelDown" class="btnBasic_blue" >엑셀저장</button>
		</div>
		<div class="stit mt_20 mb_10">
			<strong>입주단체</strong><span class="num">(<fmt:formatNumber value="${paginationInfo.totalRecordCount}" groupingUsed="true"/>)</span>
		</div>
		<div class="basicTbl">
			<table>
				<caption></caption>
				<tr>
					<th>단체명</th>
					<th>대표자명</th>
					<th>대표이메일</th>
					<th>대표휴대전화</th>
					<th>
						<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">단체형태▽</a>
						<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">단체형태△</a>
					</th>
					<th>
						<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">입주일▽</a>
						<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">입주일△</a>
					</th>
					<th>
						<a href="#none" id="sortThree_1" onclick="fn_sort('sortThree','1')" style="cursor:pointer">입주상태▽</a>
						<a href="#none" id="sortThree_2" onclick="fn_sort('sortThree','2')" style="cursor:pointer">입주상태△</a>
					</th>
					<th>
						<a href="#none" id="sortFour_1" onclick="fn_sort('sortFour','1')" style="cursor:pointer">온라인멤버▽</a>
						<a href="#none" id="sortFour_2" onclick="fn_sort('sortFour','2')" style="cursor:pointer">온라인멤버△</a>
					</th>
				</tr>
				<c:forEach items="${moveinList }" var="item" varStatus="varStatus">
				<tr data-mvin-aply-idx='<c:out value="${item.mvinAplyIdx }"/>'>
					<td><c:out value="${item.oztnNm }"/></td>
					<td><c:out value="${item.ceoNm }"/></td>
					<td><a href='mailto:<c:out value="${item.ceoEmil }"/>'><c:out value="${fn:replace(item.ceoEmil, '@', '<br/>@') }" escapeXml="false"/></a></td>
					<td><c:out value="${item.ceoPhn }"/></td>
					<td><c:out value="${item.oztnTypCdNm }"/></td>
					<td><c:out value="${item.mvinSrtDt }"/></td>
					<td><c:out value="${item.aplyPgrCdNm }"/></td>
					<td <c:if test="${item.onlineMb eq '미가입' }">class="stit"</c:if>><c:out value="${item.onlineMb }"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<input type="hidden" id="sortType" name="sortType" value='${param.sortType }' />
		<ul class='page'>
			<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
		</ul>

		<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
	</form>
</div>

<form name="moveInOztnInfo" action="<c:url value='/member/moveInOztnInfo.do' />" >
	<input type="hidden" id="mvinAplyIdx" name="mvinAplyIdx" value="" />
</form>