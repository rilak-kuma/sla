<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
}

function fn_refInfo(pjtIdx) {
	$("#pjtIdx").val(pjtIdx);
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

	$(document).ready(function(){
		$("#pjtTitl").val('<c:out value="${searchVO.pjtTitl}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("#useYn").val('<c:out value="${searchVO.useYn}" />');

	});

});
</script>

<div class="location_area"><h3>프로젝트</h3></div>

<form name="searchFm" method="post" >
<div class="subcon_area">
<!-- 신청형 테이블 -->
	<div class="basicTbl02">
		<input type="hidden" id="sortType" name="sortType" />
		<table>
			<caption></caption>
			<colgroup>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width=""/>
				<col width="120px"/>
				<col width=""/>
				<col width="100px"/>
			</colgroup>
			<tr>
				<th>카테고리</th>
				<td>
					<select id="ctgrIdx" name="ctgrIdx">
						<option value="">전체</option>
						<c:forEach var="ctgList" items="${ctgList}" varStatus="varStatus">
							<option value="${ctgList.ctgrIdx}" <c:if test="${searchVO.ctgrIdx == ctgList.ctgrIdx}">selected="selected"</c:if> ><c:out value="${ctgList.ctgrNm}" /></option>
						</c:forEach>
					</select>
				</td>
				<th>진행상태</th>
				<td>
					<select id="useYn" name="useYn">
						<option value="">전체</option>
						<option value="Y">진행중</option>
						<option value="N">진행완료</option>
					</select>
				</td>
				<td rowspan="3"><input type="submit" value="조회" class="btnSearch" /></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td colspan="3">
					<input type="text" id="creDttmOne" name="creDttmOne" style="width:100px" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더"><span class="mr_20">부터</span>
					<input type="text" id="creDttmTwo" name="creDttmTwo" style="width:100px" readonly="readonly"/><img src="../../img/ico_cal.png" alt="캘린더">까지
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					<input type="text" id="pjtTitl" name="pjtTitl" maxlength="50" style="width:70%"/>
				</td>
			</tr>
		</table>
	</div>
	<div class="stit mt_20 mb_10">
	<strong>프로젝트</strong><span class="num">총   <fmt:formatNumber value="${projectTotalCnt}" groupingUsed="true"/> 건</span>
	<table class="basicTbl">
		<colgroup>
			<col width=""/>
			<col width=""/>
			<col width="50%"/>
			<col width=""/>
			<col width=""/>
			<col width=""/>
		</colgroup>
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>제목</th>
			<th>등록자</th>
			<th>등록일</th>
			<th>진행상태</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(projectList) > 0 }">
				<c:forEach items="${projectList}" var="projectList" varStatus="varStatus">
					<tr onclick="fn_refInfo(${projectList.pjtIdx })" onmouseover="this.style.backgroundColor='yellow'" onmouseout="this.style.backgroundColor=''" style="cursor:pointer">
							<td><c:out value="${projectList.rownum }" /></td>
							<td class="tit"><c:out value="${projectList.ctgrIdxNm }" /></td>
							<td><c:out value="${projectList.pjtTitl }" /></td>
							<td><c:out value="${projectList.ceoNm }" /><c:if test="${projectList.oztnNm != ''}">/</c:if><c:out value="${projectList.oztnNm }" /></td>
							<td><c:out value="${projectList.creDttm }" /></td>
							<td>
								<span <c:if test="${projectList.useYn=='Y'}">class="emtxt"</c:if>>
									<c:out value="${projectList.useYn=='Y'?'진행중':'진행완료' }" />
								</span>
							</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<td colspan="6">조회결과가 없습니다.</td>
			</c:otherwise>
		</c:choose>
	</table>
	</div>

	<ul class='page'>
		<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
	</ul>
	<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>
</div>
</form>

<form name="infoFm" method="post" action="<c:out value='/activity/project/projectInfo.do'/>">
	<input type="hidden" id="pjtIdx" name="pjtIdx" />
</form>
