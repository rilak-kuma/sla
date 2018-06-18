<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>


<script>
function goPage(num) {
	var $form = $('form[name=searchForm]');

	$form
	.find('input[name=page]')
	.val(num)
	.end()
	.attr('action', '?')
	.submit();
}

function goInsert(idx) {
	var $form = $('form[name=searchForm]');

	var $input = $form.find('input[name=mngrMbrIdx]');
	if (!$input.length) {
		var $input = $('<input type="hidden" name="mngrMbrIdx"/>');
		$form.append($input);
	}

	$input.val(idx);

	$form
	.attr('action', '<c:url value="/basic/account/insert.do"/>')
	.submit();
}

function goList() {
	var $form = $('form[name=searchForm]');

	if(!$form.find('input[name=searchType]:checked').length) {
		alert('검색타입을 선택해주세요.');
		return;
	}

	var $input = $form.find('input[name=searchText]');
	if (!$input.val()) {
		alert('검색어를 입력해주세요.');
		return;
	}

	$form
	.attr('action', '<c:url value="/basic/account/list.do"/>')
	.submit();
}

jQuery(function($){
	$('.useYn').each(function(){
		$(this).text($(this).text() == 'Y' ? '사용':'미사용');
	});

	$('#btnInsert').on('click', function(){
		goInsert();
	});

	$('#btnSearch').on('click', function(){
		goList();
	});
});
</script>
<c:set var="c1">
<c:if test="${reqManagerMemberVO.searchType eq '1' }"> CHECKED</c:if>
</c:set>
<c:set var="c2">
<c:if test="${reqManagerMemberVO.searchType eq '2' }"> CHECKED</c:if>
</c:set>
<div class="location_area"><h3>권한관리</h3></div>
<div class="subcon_area">
	<!-- 조회 테이블 -->
	<div class="searchTbl">
		<form name='searchForm' method='GET'>
		<table>
			<tr>
				<td>
				<input type='hidden' name='page' value='<c:out value="${reqManagerMemberVO.page }"/>'/>
				<input type='radio' name='searchType' value='1'${c1 }><label class="mr_10">이름</label>
				<input type='radio' name='searchType' value='2'${c2 }><label class="mr_10">아이디</label>
				<input type='text' name='searchText' value='<c:out value="${reqManagerMemberVO.searchText }"/>' style="width:70%" />
				<button id='btnSearch' class="btnBasic">검색</button>
				</td>
			</tr>
		</table>
		</form>
	</div>

	<!-- 버튼 -->
	<sec:authorize access="hasRole('023001002Y')">
		<div class="btns tar">
			<button id='btnInsert' class="btnBasic_blue mt_20 mb_10">신규등록</button>
		</div>
	</sec:authorize>

	<!-- 리스트형 테이블 -->
	<div class="basicTbl">
		<table>
		<caption></caption>
		<colgroup>
			<col width="10%"/>
			<col width="10%"/>
			<col width=""/>
			<col width="15%"/>
			<col width="15%"/>
			<col width="10%"/>
		</colgroup>
			<tr>
				<th>ID</th>
				<th>이름</th>
				<th>부서/팀</th>
				<th>이메일</th>
				<th>연락처</th>
				<th>사용여부</th>
			</tr>
			<c:forEach items="${memberList }" var="item" varStatus="status">
				<tr>
					<td><a href='javascript:goInsert(<c:out value="${item.mngrMbrIdx }"/>)'><c:out value="${item.mngrMbrId }"/></a></td>
					<td><c:out value="${item.mngrMbrNm }"/></td>
					<td><c:out value="${item.mngrMbrDpt }"/></td>
					<td><c:out value="${item.emil }"/></td>
					<td><c:out value="${item.phn }"/></td>
					<td class='useYn'><c:out value="${item.useYn }"/></td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(memberList) == 0 }" >
				<tr>
					<td colspan="6">내용이 없습니다.</td>
				</tr>
			</c:if>
		</table>
	</div>

	<ul class='page'>
		<ui:pagination paginationInfo="${reqManagerMemberVO.pageInfo }" type="image" jsFunction="goPage"/>
	</ul>
</div><!--subcon_area-->