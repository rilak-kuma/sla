<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp"%>
<script type="text/javascript">
jQuery(function($){
	// 초기값 삭제
	$('#simpleResourceBox .tag_list a').each(function(){
		var tx = $(this).text();

		$('#allTagBox a').filter(function(){
			return $(this).text() == tx;
		}).remove();
	});

	// 드래그설정
	$('#allTagBox a').draggable({
		revert: 'invalid',
		helper: 'clone'
	});

	// 드롭설정
	$('#simpleResourceBox .menu_add li').droppable({
		drop: function(event, ui) {
			$(this)
			.find('.tag_list')
			.empty()
			.append($(ui.draggable).removeClass('btnTag'));
		}
	})
	// 항목삭제
	.on('click', 'a', function(){
		$('#allTagBox').append($(this).addClass('btnTag'));

		return false;
	});

	// 저장
	$('#btnSave')
	.on('click', function(){
		var url = '<c:url value="/activity/referenceRoom/insertPopularTag.json"/>';
		var submitData = {
			list: []
		};

		$('#simpleResourceBox .tag_list').each(function(k){
			submitData.list.push({
				siplRscIdx: k+1,
				siplRscTypCd: '024004',
				pgrCd: '000',
				siplRscCntt: $(this).find('a').text()
			});
		});

		var callback = function(data) {
			alert(getMessage('msg.save.success'));
			document.location.reload();
		}

// 		console.log(submitData);

		ajaxSubmit(url, JSON.stringify(submitData), callback, null);
	});
});

</script>
<div class="location_area"><h3>자료실 인기 태그</h3></div>
<div class="subcon_area">
	<div class="cate_wrap">
		<div id='simpleResourceBox' class="left_box">
			<p>인기태그 자료실 검색영역에 노출됩니다.</p>
			<ul class="menu_add">
			<c:forEach begin="0" end="7" var="i" step="1">
				<li>
					<span>${i+1 }</span>
					<span class='tag_list'>
					<c:if test="${!empty simpleResourceDTOList and fn:length(simpleResourceDTOList) > i }">
						<a href='#'><c:out value="${simpleResourceDTOList[i].siplRscCntt }" /></a>
					</c:if>
					</span>
				</li>
			</c:forEach>
			</ul>
		<sec:authorize access="hasRole('023004006Y')">
			<div class="mb_10 tar">
				<button id='btnSave' class="btnBasic">저장</button>
			</div>
		</sec:authorize>
		</div>
		<div id='allTagBox' class='right_box'>
			<div class="basicTbl02">
			<table>
				<tr>
					<td>
					<c:forEach items="${tagDTOList }" var="item" varStatus="status">
						<a href='#' class="btnTag"><c:out value="${item.tagNm }" /></a>
					</c:forEach>
					</td>
				</tr>
			</table>
			</div>
		</div>
	</div>
</div>