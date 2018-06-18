<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
 function fn_submit(pageIndex){
		$("#pageIndex").val(pageIndex);
		$('form[name=searchFm]').submit();
 }

 function fn_info(mbrIdx){
	$("#mbrIdx").val(mbrIdx);
	$("form[name=infoFm]").attr("action","<c:out value='/member/onlineMemberInfo.do' />").submit();
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

	$("#btnSearch").on("click",function(){
		$('form[name=searchFm]').submit();
	});

	$("#btnExcelDown, #btnAllExcelDown").on("click",function(e){
		var id = e.target.getAttribute('id');
		var action = '<c:out value="/member/onlineMemberListExcel.do" />';
        if ( id != 'btnExcelDown' ){
        	action = '<c:out value="/member/onlineAllMemberListExcel.do" />'
        } 
		$('form[name=searchFm]')
		 .attr({
			 target: '_blank',
			 action: action
		 })
		 .submit();

		 $('form[name=searchFm]').removeAttr("target");
		 $('form[name=searchFm]').attr("action",'<c:out value="/member/onlineMemberList.do" />');
	});	

	$(document).ready(function(){
		$("#oztnNm").val('<c:out value="${searchVO.oztnNm}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("select[name=mbrTypCd]").val('<c:out value="${searchVO.mbrTypCd}" />');
		$("select[name=mvinGrpCd]").val('<c:out value="${searchVO.mvinGrpCd}" />');

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
		$("#sortType").val(sortType);
		$('form[name=searchFm]').attr("action",'<c:out value="/member/onlineMemberList.do" />');
	});
 });
 </script>
 <div class="location_area"><h3>온라인멤버</h3></div>
 <div class="subcon_area">
	<form name="searchFm" method="post" >
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
				<td><input type="text" name="oztnNm"/></td>
				<th>멤버구분</th>
				<td>
					<select name="mbrTypCd">
						<option value="">전체</option>
						<option value="015001">입주멤버</option>
						<option value="015002">비입주멤버</option>
						<option value="015003">시민멤버</option>
<%-- 						<c:forEach items="${mbrTypCds }" var="mbrTypCds" varStatus="varStatus"> --%>
<%-- 							<option value="${mbrTypCds.cmmnCd }">${mbrTypCds.cmmnCdNm }</option> --%>
<%-- 						</c:forEach> --%>
					</select>
				</td>
				<td rowspan="2"><input type="submit" class="btnSearch" value="조회" /></td>
			</tr>
			<tr>
				<th>가입일</th>
				<td>
					<input type="text" id="creDttmOne" name="creDttmOne" readonly="readonly" /><img src="../../img/ico_cal.png" alt="캘린더"> ~
					<input type="text" id="creDttmTwo" name="creDttmTwo" readonly="readonly" /><img src="../../img/ico_cal.png" alt="캘린더">
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
		</table>

		<div class="stit mt_20 mb_10">
			<strong>온라인멤버(<c:out value="${paginationInfo.totalRecordCount }"/>)</strong>
			<div class="fr">
				<button id="btnExcelDown" class="btnBasic_blue">엑셀저장</button>
				<button id="btnAllExcelDown" class="btnBasic_blue">엑셀저장( 전체 )</button>
			</div>
		</div>
		<div class="basicTbl">
			<table>
				<caption></caption>
				<colgroup>
					<col width="25%"/>
					<col width=""/>
					<col width=""/>
					<col width="13%"/>
					<col width=""/>
					<col width=""/>
					<col width=""/>
				</colgroup>
				<tr>
					<th>아이디</th>
					<th>단체명(성명)</th>
					<th>대표자명</th>
					<th>대표휴대전화</th>
					<th>
						<a href="#none" id="sortOne_1" onclick="fn_sort('sortOne','1')" style="cursor:pointer">멤버구분△</a>
						<a href="#none" id="sortOne_2" onclick="fn_sort('sortOne','2')" style="cursor:pointer">멤버구분▽</a>
					</th>
					<th>입주그룹</th>
					<th>
						<a href="#none" id="sortTwo_1" onclick="fn_sort('sortTwo','1')" style="cursor:pointer">가입일△</a>
						<a href="#none" id="sortTwo_2" onclick="fn_sort('sortTwo','2')" style="cursor:pointer">가입일▽</a>
					</th>
				</tr>
				<c:forEach items="${onlineMemberList }" var="onlineMemberList" varStatus="varStatus">
				<tr onclick="fn_info(${onlineMemberList.mbrIdx })" onmouseover="this.style.backgroundColor='yellow'" onmouseout="this.style.backgroundColor=''" style="cursor:pointer" >
					<td>${onlineMemberList.mbrId }</td>
					<td><c:out value="${onlineMemberList.oztnNm }"/></td>
					<td><c:out value="${onlineMemberList.ceoNm }"/></td>
					<td><c:out value="${onlineMemberList.ceoPhn }"/></td>
					<td>
					<c:choose>
						<c:when test="${onlineMemberList.mbrTypCdNm eq '입주업체' }">입주멤버</c:when>
						<c:when test="${onlineMemberList.mbrTypCdNm eq '비입주업체' }">비입주멤버</c:when>
						<c:when test="${onlineMemberList.mbrTypCdNm eq '혁신가' }">시민멤버</c:when>
						<c:otherwise><c:out value="${onlineMemberList.mbrTypCdNm }"/></c:otherwise>
					</c:choose>
					</td>
					<td><c:out value="${onlineMemberList.mvinGrpCdNm }" /></td>
					<td><c:out value="${onlineMemberList.creDttm }"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<input type="hidden" id="sortType" name="sortType" />
		<ul class="page" id="pagination">
			<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
		</ul>

		<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
	</form>
</div>
<form name="infoFm" method="post" >
	<input type="hidden" id="mbrIdx" name="mbrIdx" value=""/>
</form>