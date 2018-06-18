<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]')
	.attr('action', '<c:url value="/activity/propose/proposeList.do"/>')
	.submit();
}

function fn_refInfo(prpIdx) {
	$("#prpIdx").val(prpIdx);
	$('form[name=infoFm]').submit();
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
});
</script>
<form name="infoFm" method="GET" action="/activity/propose/proposeInfo.do">
	<input type="hidden" id="prpIdx" name="prpIdx" />
</form>

<div class="location_area"><h3>아이디어/협업</h3></div>

<div class="subcon_area">
	<form:form commandName="searchVO" name="searchFm" method="GET">

	<!-- 조회 테이블 -->
	<div class="basicTbl02">
		<table>
			<caption></caption>
					<colgroup>
						<col width="120px"/>
						<col width=""/>
						<col width="100px"/>
					</colgroup>
			<tr>
				<th>등록일</th>
				<td>
					<form:input path="creDttmOne" readonly="readonly" cssStyle="width:100px"/><img src="../../img/ico_cal.png" alt="캘린더"><span class="mr_20">부터</span>
					<form:input path="creDttmTwo" readonly="readonly" cssStyle="width:100px"/><img src="../../img/ico_cal.png" alt="캘린더"><span>까지</span>
				</td>
				<td rowspan="2"><input type="submit" class="btnSearch" value="조회" /></td>
			</tr>
			<tr>
				<th>검색어</th>
				<td>
					<form:input path="titl" maxlength="50" placeholder="제목을 입력하세요" cssStyle="width:70%"/>
				</td>
			</tr>
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		총  <strong><fmt:formatNumber value="${proposeTotalCnt}" groupingUsed="true"/></strong>건
	</div>

	<!-- 리스트형 테이블 -->
	<div class="basicTbl">
		<table>
		<caption></caption>
		<colgroup>
			<col width=""/>
			<col width="50%"/>
			<col width=""/>
			<col width=""/>
			<col width=""/>
			<col width=""/>
		</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록자</th>
				<th>등록일</th>
				<th>댓글</th>
				<th>상태</th>
			</tr>
			<c:choose>
				<c:when test="${fn:length(proposeList) > 0 }">
					<c:forEach items="${proposeList}" var="proposeList" varStatus="varStatus">
						<tr onclick="fn_refInfo(${proposeList.prpIdx })" onmouseover="this.style.backgroundColor='yellow'" onmouseout="this.style.backgroundColor=''" style="cursor:pointer">
								<td><c:out value="${proposeList.rownum }" /></td>
								<td><c:out value="${proposeList.titl }" /></td>
								<td><c:out value="${proposeList.oztnNm }" /><c:if test="${proposeList.ceoNm != ''}">(<c:out value="${proposeList.ceoNm }" />)</c:if></td>
								<td><c:out value="${proposeList.creDttm }" /></td>
								<td><c:out value="${proposeList.commentCnt }" /></td>
								<td><c:out value="${proposeList.useYn=='Y'?'ON':'OFF' }" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="6" align="center">조회결과가 없습니다.</td>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

	<!--페이지번호-->
	<ul class='page'>
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>
	<form:hidden path="pageIndex"/>
	</form:form>
</div><!--subcon_area-->
