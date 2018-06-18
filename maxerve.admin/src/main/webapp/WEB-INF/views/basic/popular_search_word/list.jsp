<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script>
jQuery(function($){
	$('form[name=form1]').validate({
		rules: {
			siplRscCntt: "required"
		},
		messages: {
			siplRscCntt: getMessage('org.hibernate.validator.constraints.NotEmpty.message', '인기검색어는')
		},
		submitHandler: function(form) {
			var url = '<c:url value="/basic/popular_search_word/insert.json"/>';
			var submitData = $(form).serializeFormJSON();

			var callback = function(data) {
				alert(getMessage('msg.save.success'));
				document.location.reload();
			}


			submitData = $(submitData.siplRscCntt).map(function(k, v){
				if (v) {
					return v;
				}
			}).get();

			submitData = {
				list: $(submitData).map(function(k, v){
					return {
						siplRscIdx: k+1,
						siplRscTypCd: '024001',
						pgrCd: '000',
						siplRscCntt: v
					}
				}).get()
			};

// 			console.log(submitData);

 			ajaxSubmit(url, JSON.stringify(submitData), callback, null);
		}
	});

	$('#btnSave').on('click', function(){
		$('form[name=form1]').submit();
	});
});
</script>
<div class="location_area"><h3>인기검색어</h3></div>
<div class="subcon_area">
	<div class="basicTbl">
	<form name='form1'>
		<table>
			<caption></caption>
			<colgroup>
				<col width="15%"/>
				<col width=""/>
			</colgroup>
			<c:forEach var="i" begin="0" end="9" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td><input type='text' style='width:100%' name='siplRscCntt' value='<c:out value="${list[i].siplRscCntt }"/>'/></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	</div>

	<!-- 버튼 -->
<sec:authorize access="hasRole('023001004Y')">
	<div class="btns tar mb_40">
		<button type="button" class="btnBasic_blue" id='btnSave'>저장</button>
	</div>
</sec:authorize>
</div>