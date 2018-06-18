<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
input[readonly] {background-color: #eee;}
</style>
<script>
function goPage(pg) {
	$('form[name=form]')
	.attr('action', '/reserve/rent_area/list.do')
	.find('input[name=page]')
	.val(pg)
	.end()
	.submit();
}

function fn_sort(sortType, num){
	$("#sortType").val(sortType+"_"+num);
	$('form[name=searchFm]')
	.attr('src', '<c:url value="/reserve/rent_area/list.do"/>')
	.submit();
}

jQuery(function($){
	
	// 대관등록
	$('#btnInsert').on('click', function(){
		$('form[name=form]')
		.attr('action', '<c:url value="/reserve/rent_area/insert.do"/>')
		.submit();
	});

	// 대관수정
	$('#reserveTB').on('click', 'tr', function(){
		$('form[name=form]')
		.attr('action', '<c:url value="/reserve/rent_area/insert.do"/>')
		.append('<input type="hidden" name="rtaIdx" value="' + $(this).attr('data-rta-idx') + '"/>')
		.submit();
	});
	
	//입주단체관리로 돌아가기
	$('#btnCancel').on('click', function(){
		$('form[name=form]')
		.attr('action', '<c:url value="/member/moveInOztnInfo.do"/>')
		.submit();
	});	
	
	$(document).ready(function(){
		
		$('form[name=searchFm]')
			.find('input[name=mbrId]')
			.val('<c:out value="${reqRentAreaVO.mbrId}" />')
			.end()
			.find('input[name=oztnNm]')
			.val('<c:out value="${reqRentAreaVO.oztnNm}" />')
			.end();
		
		//alert("${reqRentAreaVO.mvinAplyIdx}"); //입주신청 인덱스
		
		if("${reqRentAreaVO.mvinAplyIdx}" !=""){ // 입주신청 인덱스 : 입주단체관리화면에서 넘어온 경우
			$('form[name=searchFm] input[name=mbrId]').prop("readonly",true);
			$('form[name=searchFm] input[name=oztnNm]').prop("readonly",true);
		}

		/*$("#oztnNm").val('<c:out value="${searchVO.oztnNm}" />');
		$("#creDttmOne").val('<c:out value="${searchVO.creDttmOne}" />');
		$("#creDttmTwo").val('<c:out value="${searchVO.creDttmTwo}" />');
		$("#strDttm").val('<c:out value="${searchVO.srtDttm}" />');
		$("#endDttm").val('<c:out value="${searchVO.endDttm}" />');
		$("select[name=rsvtPgr]").val('<c:out value="${searchVO.rsvtPgr}" />');*/

		var sortType = '<c:out value="${reqRentAreaVO.sortType}" />';

        if(sortType == 'sortTitl_1'){
        	$("#sortTitl_1").hide();
			$("#sortRtaSrtDttm_2").hide();
			$("#sortCreDttm_2").hide();
			$("#sortUseYn_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortAssOztnNm_2").hide();
			$("#sortOztnNm_2").hide();
		}else if(sortType == 'sortRtaSrtDttm_1'){
        	$("#sortTitl_2").hide();
			$("#sortRtaSrtDttm_1").hide();			
			$("#sortCreDttm_2").hide();
			$("#sortUseYn_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortAssOztnNm_2").hide();
			$("#sortOztnNm_2").hide();
	   }else if(sortType == 'sortCreDttm_1'){
       	    $("#sortTitl_2").hide();
		    $("#sortRtaSrtDttm_2").hide();		   
			$("#sortCreDttm_1").hide();
			$("#sortUseYn_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortAssOztnNm_2").hide();
			$("#sortOztnNm_2").hide();
		}else if(sortType == 'sortUseYn_1'){
        	$("#sortTitl_2").hide();
			$("#sortRtaSrtDttm_2").hide();			
			$("#sortCreDttm_2").hide();
			$("#sortUseYn_1").hide();
			$("#sortMbrId_2").hide();
			$("#sortAssOztnNm_2").hide();
			$("#sortOztnNm_2").hide();			
		}else if(sortType == 'sortMbrId_1'){
        	$("#sortTitl_2").hide();
			$("#sortRtaSrtDttm_2").hide();			
			$("#sortCreDttm_2").hide();
			$("#sortUseYn_2").hide();
			$("#sortMbrId_1").hide();
			$("#sortAssOztnNm_2").hide();
			$("#sortOztnNm_2").hide();
		}else if(sortType == 'sortOztnNm_1'){
        	$("#sortTitl_2").hide();
			$("#sortRtaSrtDttm_2").hide();			
			$("#sortCreDttm_2").hide();
			$("#sortUseYn_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortAssOztnNm_2").hide();
			$("#sortOztnNm_1").hide();
		}else if(sortType == 'sortAssOztnNm_1'){
        	$("#sortTitl_2").hide();
			$("#sortRtaSrtDttm_2").hide();			
			$("#sortCreDttm_2").hide();
			$("#sortUseYn_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortAssOztnNm_1").hide();
			$("#sortOztnNm_2").hide();			
		}else{
        	$("#sortTitl_2").hide();
			$("#sortRtaSrtDttm_2").hide();			
			$("#sortCreDttm_2").hide();
			$("#sortUseYn_2").hide();
			$("#sortMbrId_2").hide();
			$("#sortAssOztnNm_2").hide();
			$("#sortOztnNm_2").hide();			
		}

		$('form[name=searchFm]').attr("action",'/reserve/rent_area/list.do');
		
	});
	
});
</script>
<form:form commandName="reqRentAreaVO" name="form" method="GET" >
    <form:hidden path="mbrId" id=""/>
    <form:hidden path="oztnNm" id=""/>
    <form:hidden path="mvinAplyIdx" id=""/> <!-- 입주신청 인덱스 : 입주단체관리 화면에서 넘어 온 경우, 다시 입주단체관리 화면으로 되돌아가기 위함 -->
	<form:hidden path="page" id=""/>
	<form:hidden path="sortType" id=""/>
</form:form>

<div class="location_area"><h3>대관일정(대관제한) 관리</h3></div>

<div class="subcon_area">
<form:form commandName="reqRentAreaVO" name="searchFm" method="GET">
		<form:hidden path="mvinAplyIdx"/> <!-- 입주신청 인덱스 : 입주단체관리 화면에서 넘어 온 경우, 다시 입주단체관리 화면으로 되돌아가기 위함 -->
		<div class="small_list">
		<table>
			<tr>
				<th>단체명<br/>(성명)</th>
				<td><input type="text" id="oztnNm" name="oztnNm" style="width:90%" maxlength="50"/></td>
				<th>적용 회원아이디</th>
				<td><input type="text" id="mbrId" name="mbrId" style="width:300px"/>&nbsp;(ALL 인 경우 모든 회원에게 적용된 경우를 의미)</td>
				<td rowspan="2">
					<input type="submit" class="btnSearch" value="조회" />
				</td>
			</tr>					
		</table>
	</div>

	<div class="stit mt_20 mb_10">
		<strong>대관일정(대관제한)</strong><span class="num">(<c:out value="${reqRentAreaVO.pageInfo.totalRecordCount}" />)</span>
		<div class="fr" >
			<sec:authorize access="hasRole('023005002Y')">
			<!-- 버튼 -->
			<button type="button" class="btnBasic_blue" id='btnInsert'>일정등록</button>
			</sec:authorize>
			<c:if test="${reqRentAreaVO.mvinAplyIdx ne null and reqRentAreaVO.mvinAplyIdx ne ''}"> <!-- 입주신청 인덱스 : 입주단체관리화면에서 넘어온 경우 -->
			<button type="button" class="btnBasic_blue" id='btnCancel'>입주단체관리로 돌아가기</button>
			</c:if>
		</div>
	</div>

	<!-- 리스트형 테이블 -->
	<div class="basicTbl">
	    <input type="hidden" id="sortType" name="sortType"/>
		<table>
			<thead>
				<tr>
					<th>
						<a href="#none" id="sortTitl_1" onclick="fn_sort('sortTitl','1')" style="cursor:pointer">제목(제한사유)△</a>
						<a href="#none" id="sortTitl_2" onclick="fn_sort('sortTitl','2')" style="cursor:pointer">제목(제한사유)▽</a>
					</th>
					<th>
						<a href="#none" id="sortRtaSrtDttm_1" onclick="fn_sort('sortRtaSrtDttm','1')" style="cursor:pointer">대관기간△</a>
						<a href="#none" id="sortRtaSrtDttm_2" onclick="fn_sort('sortRtaSrtDttm','2')" style="cursor:pointer">대관기간▽</a>
					</th>
					<th>
						<a href="#none" id="sortCreDttm_1" onclick="fn_sort('sortCreDttm','1')" style="cursor:pointer">등록일△</a>
						<a href="#none" id="sortCreDttm_2" onclick="fn_sort('sortCreDttm','2')" style="cursor:pointer">등록일▽</a>
					</th>
					<th>
						<a href="#none" id="sortUseYn_1" onclick="fn_sort('sortUseYn','1')" style="cursor:pointer">진행상태△</a>
						<a href="#none" id="sortUseYn_2" onclick="fn_sort('sortUseYn','2')" style="cursor:pointer">진행상태▽</a>
					</th>
					<th>
						<a href="#none" id="sortOztnNm_1" onclick="fn_sort('sortOztnNm','1')" style="cursor:pointer">단체명(성명)△</a>
						<a href="#none" id="sortOztnNm_2" onclick="fn_sort('sortOztnNm','2')" style="cursor:pointer">단체명(성명)▽</a>
					</th>										
					<th>
						<a href="#none" id="sortMbrId_1" onclick="fn_sort('sortMbrId','1')" style="cursor:pointer">적용 회원아이디△</a>
						<a href="#none" id="sortMbrId_2" onclick="fn_sort('sortMbrId','2')" style="cursor:pointer">적용 회원아이디▽</a>
					</th>
					<th>
						<a href="#none" id="sortAssOztnNm_1" onclick="fn_sort('sortAssOztnNm','1')" style="cursor:pointer">소속단체△</a>
						<a href="#none" id="sortAssOztnNm_2" onclick="fn_sort('sortAssOztnNm','2')" style="cursor:pointer">소속단체▽</a>
					</th>					
				</tr>
			</thead>
			<tbody id="reserveTB">
			<c:forEach items="${rentTheAreaDTOList }" var="item">
			<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.rtaSrtDttm }" var="rtaSrtDttm" />
			<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.rtaEndDttm }" var="rtaEndDttm" />
			<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.creDttm }" var="creDttm" />
				<tr data-rta-idx='<c:out value="${item.rtaIdx }"/>'>
					<td><c:out value="${item.titl }"/></td>
					<td>
						<fmt:formatDate value="${rtaSrtDttm }" pattern="yyyy.MM.dd HH:mm"/>
						~
						<fmt:formatDate value="${rtaEndDttm }" pattern="yyyy.MM.dd HH:mm"/>
					</td>
					<td><fmt:formatDate value="${creDttm }" pattern="yyyy.MM.dd"/></td>
					<td>
					<c:if test="${item.useYn eq 'Y' }">ON</c:if>
					<c:if test="${item.useYn ne 'Y' }">OFF</c:if>
					</td>
					<td><c:out value="${item.oztnNm }"/></td>
					<td><c:out value="${item.mbrId }"/></td>
					<td><c:out value="${item.assOztnNm }"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<ul class='page' id="pagination">
		<ui:pagination paginationInfo="${reqRentAreaVO.pageInfo }" jsFunction="goPage" type='image' />
	</ul>
</form:form>	
</div>