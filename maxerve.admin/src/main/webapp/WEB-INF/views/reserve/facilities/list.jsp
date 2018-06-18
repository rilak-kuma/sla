<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<style>
#facilitiesBox li.on {
	color: red;
}
</style>

<script>
jQuery(function($){
	// 일정관리
	$('#btnInsert').on('click', function(){
		var url = '<c:out value="/reserve/facilities/insert.do"/>';

		if ($('form[name=form] input[name=rsvtPlcCd]').val() == '002003' && $('form[name=form] input[name=fctMstIdx]').val() == '20') {
			url = '<c:out value="/reserve/facilities/makerspace_class_list.do"/>';
		}

		$('form[name=form]')
		.attr('action', url)
		.submit();
	});

	// 시설 클릭
	$('#facilitiesBox').on('click', 'li', function(){
		$('form[name=form]')
		.attr('action', '?')
		.find('input[name=fctMstIdx]')
		.val($(this).attr('data-fct-mst-idx'));

		$('form[name=form]').submit();
	});
});
</script>
<form:form commandName="reqFacilitiesReserveVO" name="form" method="GET">
	<form:hidden path="rsvtPlcCd"/>
	<form:hidden path="fctMstIdx"/>
</form:form>

<ul id='facilitiesBox' class='small_list'>
<c:forEach items="${facilitiesMasterDTOList }" var="item">
<c:set var="c">
	<c:if test="${item.fctMstIdx eq reqFacilitiesReserveVO.fctMstIdx }"> class='on'</c:if>
</c:set>

<c:if test="${reqFacilitiesReserveVO.fctMstIdx eq '9' or reqFacilitiesReserveVO.fctMstIdx eq '10' }">
	<c:if test="${item.fctMstIdx eq '9' or item.fctMstIdx eq '10' }">
		<li data-fct-mst-idx='<c:out value="${item.fctMstIdx }"/>'${c }><c:out value="${item.fctNm }"/></li>
	</c:if>
</c:if>

<c:if test="${ reqFacilitiesReserveVO.fctMstIdx ne '9' and reqFacilitiesReserveVO.fctMstIdx ne '10' }">
	<c:if test="${item.fctMstIdx ne '9' and item.fctMstIdx ne '10' }">
		<li data-fct-mst-idx='<c:out value="${item.fctMstIdx }"/>'${c }><c:out value="${item.fctNm }"/></li>
	</c:if>
</c:if>
</c:forEach>
</ul>
<sec:authorize access="hasRole('023005002Y')">
<button id='btnInsert' class='btnBasic_black'>일정관리</button>
</sec:authorize>