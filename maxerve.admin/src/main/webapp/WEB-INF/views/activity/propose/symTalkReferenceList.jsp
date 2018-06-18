<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<script type="text/javascript">
function fn_submit(pageIndex){
	$("#pageIndex").val(pageIndex);
	$('form[name=searchFm]').submit();
}

jQuery(function($){

	//저장
	$("#btnConfirm").on('click', function(){
		var confirmData = "";
		$(":checkbox").each(function(){
			if(this.checked){
 				confirmData +="<p>";
				confirmData +="<input type='hidden' id='locIdx' value='"+$(this).parent().find("#locIdx").val()+"' />";
				confirmData +="<input type='hidden' id='locTypCd' value='"+$(this).parent().find("#locTypCd").val()+"' />";
				confirmData +="("+$(this).parent().find("#locTypCdNm").val()+")";
				confirmData +=$(this).parent().find("#titl").val();
				confirmData +="<input type='button' onclick='$(this).parent(\"p\").remove()' class='btnTag_del' />";
				confirmData +="</p>";
			}
		});
		opener.$("#refDiv").append(confirmData);

		self.close();
	});

	//취소
	$("#btnCansle").on('click', function(){
		self.close();
	});

	$(document).ready(function(){

		var tbody = [];
		<c:forEach items="${selectSymTalkReferenceList }" var="referenceList" varStatus="varStatus">

			var data_loc_idx 	= ' <input type="hidden" id="locIdx" value=<c:out value="${referenceList.locIdx}" /> />';
			var data_loc_typ 	= ' <input type="hidden" id="locTypCd" value=<c:out value="${referenceList.locTypCd}" /> />';
			var data_loc_typNm 	= ' <input type="hidden" id="locTypCdNm" value=<c:out value="${referenceList.locTypCdNm}" /> />';
			var data_titl 		= ' <input type="hidden" id="titl" value="<c:out value="${referenceList.titl}"  />"  />';

			tbody.push('<tr>');
			tbody.push('<td><input type="checkbox" />'+data_loc_idx+data_loc_typ+data_loc_typNm+data_titl+'</td>');
			tbody.push('<td><c:out value="${referenceList.locTypCdNm }" /></td>');
			tbody.push('<td><c:out value="${referenceList.titl }" /></td>');
			var ceoNm = "";
			if('<c:out value="${referenceList.oztnNm }" />' == ""){
				ceoNm = '<c:out value="${referenceList.ceoNm }" />';
			}else{
				ceoNm = '(<c:out value="${referenceList.ceoNm }" />)';
			}
			tbody.push('<td><c:out value="${referenceList.oztnNm }" />'+ceoNm+'</td>');
			tbody.push('<td><c:out value="${referenceList.cmmtCnt }" /></td>');
			tbody.push('</tr>');
		</c:forEach>

		$("tbody").html(tbody);
	});

});
</script>
<div class="subcon_area">
	<form name="searchFm" method="post"  >
		<div class="insideTbl">
			<table>
				<thead>
					<tr>
						<th>선택</th>
						<th>구분</th>
						<th>제목</th>
						<th>등록자</th>
						<th>댓글</th>
					</tr>
				</thead>
				<tbody id="tbody">

				</tbody>
			</table>
		</div>

		<ul class='page'>
			<ui:pagination paginationInfo = "${paginationInfo}" jsFunction="fn_submit" type='image' />
		</ul>
		<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}"/>

		<div class="tac mt_10">
			<button id="btnConfirm" class="btnBasic_blue">저장</button><button id="btnCansle" class="btnBasic_black">취소</button>
		</div>
	</form>
</div>