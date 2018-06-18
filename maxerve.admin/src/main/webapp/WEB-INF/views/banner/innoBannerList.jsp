<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
}

function fn_bannerInfo(bnnrIdx) {
	$("#bnnrIdx").val(bnnrIdx);
	$('form[name=bannerInfoFm]')
		.attr("action","<c:url value='/banner/innoBannerInfo.do' />")
		.submit();
}

jQuery(function($){

	$( function() {
	    var dateFormat = "yy.mm.dd",
	      from = $( "#useSrtDt" )
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

	//등록폼호출
	$("#btnRegFm").on('click', function(){
		$("#bnnrIdx").val("");
		$('form[name=bannerInfoFm]')
			.attr("action","<c:url value='/banner/innoBannerInfo.do' />")
			.submit();
	});

	$("input[name=radioA]").on('click', function(){
		$("input[name=radioA]").each(function(index){
			if($(this).is(':checked') == true){
				$( "#plfCd" ).val($(this).val());
			}
		});
	});

	$("input[name=radioB]").on('click', function(){
		$("input[name=radioB]").each(function(index){
			if($(this).is(':checked') == true){
				$( "#useYn" ).val($(this).val());
			}
		});
	});

	$(document).ready(function(){
		var plfCd = "<c:out value='${searchVO.plfCd}'/>";
		$("input[value="+plfCd+"]").trigger("click");
		$("#plfCd").val(plfCd);

		var useYn = "<c:out value='${searchVO.useYn}'/>";
		if(useYn == ""){
			$("#useAll").trigger("click");
		}else{
			$("input[value="+useYn+"]").trigger("click");
		}
		$("#useYn").val(useYn);

		$("#useSrtDt").val("<c:out value='${searchVO.useSrtDt}'/>");
		$("#useEndDt").val("<c:out value='${searchVO.useEndDt}'/>");

		$("#titl").val("<c:out value='${searchVO.titl}'/>");

	});

});
</script>
<div class="location_area"><h3>혁신멤버 배너</h3></div>

<form name="searchFm" method="post" >
<div class="subcon_area">
	<div class="basicTbl02">
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
				<col width="100px"/>
			</colgroup>
			<tr>
				<th>대상</th>
				<td>
					<c:forEach items="${plfList}" var="plfList" varStatus="varStatus">
						<input type="radio" name="plfCd" value="${plfList.cmmnCd }"/>
					<label class="mr_10">${plfList.cmmnCdNm }</label>
					</c:forEach>
				</td>
				<td rowspan="4"><input type="submit" class="btnSearch" value="검색" /></td>
			</tr>
			<tr>
				<th>전시여부</th>
				<td>
					<input type="radio" name="useYn" value="" checked="checked" /><label class="mr_10">전체</label>
					<input type="radio" name="useYn" value="Y" /><label class="mr_10">전시</label>
					<input type="radio" name="useYn" value="N" /><label>미전시</label>
				</td>
			</tr>
			<tr>
				<th>전시기간</th>
				<td>
					<input type="text" id="useSrtDt" name="useSrtDt" readonly="readonly" style="width:100px;"/><img src="../../img/ico_cal.png" alt="캘린더"><span class="mr_20">부터</span>
					<input type="text" id="useEndDt" name="useEndDt" readonly="readonly" style="width:100px;"/><img src="../../img/ico_cal.png" alt="캘린더"><span>까지</span>
				</td>
			</tr>
			<tr>
				<th>검색어</th>
				<td><input type="text" id="titl" name="titl" style="width:90%" maxlength="50"/></td>
			</tr>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>진행중</strong><span class="num">(<fmt:formatNumber value="${fn:length(useBannerList)}" groupingUsed="true"/>)</span>
	<sec:authorize access="hasRole('023002002Y')">
		<div class="fr">
			<button id="btnRegFm" class="btnBasic_blue">등록</button>
		</div>
	</sec:authorize>
	</div>

	<div class="basicTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width=""/>
				<col width="50%"/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>순서</th>
				<th>제목</th>
				<th>전시기간</th>
				<th>전시여부</th>
			</tr>
			<c:forEach items="${useBannerList }" var="useBannerList" varStatus="varStatus">
				<tr>
					<td><c:out value="${useBannerList.ord }" /></td>
					<td><a href="#none" onclick="fn_bannerInfo(${useBannerList.bnnrIdx })" ><c:out value="${useBannerList.titl }" /></a></td>
					<td class="bdr"><c:out value="${useBannerList.useSrtDt }" /> ~ <c:out value="${useBannerList.useEndDt }" /></td>
					<td><c:out value="${useBannerList.useYnNm }" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>전체</strong><span class="num">(<fmt:formatNumber value="${paginationInfo.totalRecordCount}" groupingUsed="true"/>)</span>
	</div>
	<div class="basicTbl">
		<table>
			<caption></caption>
			<colgroup>
				<col width=""/>
				<col width="50%"/>
				<col width=""/>
				<col width=""/>
			</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>전시기간</th>
				<th>전시여부</th>
			</tr>
			<c:forEach items="${bannerList }" var="bannerList" varStatus="varStatus">
				<tr>
					<td><c:out value="${bannerList.rownum }" /></td>
					<td><a href="#none" onclick="fn_bannerInfo(${bannerList.bnnrIdx })" ><c:out value="${bannerList.titl }" /></a></td>
					<td><c:out value="${bannerList.useSrtDt }" /> ~ <c:out value="${bannerList.useEndDt }" /></td>
					<td><c:out value="${bannerList.useYnNm }" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<ul class='page'>
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>

	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
</div>

</form>

<form name="bannerInfoFm" method="post"  >
	<input type="hidden" id="bnnrIdx" name="bnnrIdx" />
</form>