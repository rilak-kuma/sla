<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script>

jQuery(function($){

	//목록(뒤로)
	$('#btnCansle').on('click', function(){
		self.close();
	});
	
	//액샐저장
	$('#btnExcelDown').on('click', function(){
		$("form[name=infoFm]")
		.find("input[name=mkspClsIdx]").val('<c:out value="${searchVO.mkspClsIdx}" />')
		.end()
		.find("input[name=srtTm]").val('<c:out value="${searchVO.srtTm}" />')
		.end()
		.find("input[name=titl]").val('<c:out value="${searchVO.titl}" />')
		.end()
		.attr('action','<c:out value="scheduleMakerSpaceClassMbrListExcel.do" />')
		.attr('target','_blank')
		.submit();
	});
	
	// 초기설정
	$(document).ready(function(){
		
		<c:forEach	items="${classList }" var="classList" varStatus="varstatus">
			var i = 0;
			var addHtml = '<tr>';
			var id = 'td'+'<c:out value="${varstatus.index}" />';
			
			addHtml += '<td id='+id+'><c:out value="${classList.grpNm}" /></td>';
				
			<c:forEach	items="${mkspClsMbrList }" var="mkspClsMbrList" >
				<c:if test="${classList.mkspClsGrpIdx eq mkspClsMbrList.mkspClsGrpIdx}" >
					i++;
					if(i == 1){
						addHtml +='<td><c:out value="${mkspClsMbrList.ceoNm }" /></td>';
						addHtml +='<td><c:out value="${mkspClsMbrList.mbrId }" /></td>';
						addHtml +='<td><c:out value="${mkspClsMbrList.ceoPhn }" /></td>';
						addHtml +='<td><c:out value="${mkspClsMbrList.creDttm }" /></td>';
						addHtml +='</tr>';
					}else{
						addHtml +='<tr>';
						addHtml +='<td><c:out value="${mkspClsMbrList.ceoNm }" /></td>';
						addHtml +='<td><c:out value="${mkspClsMbrList.mbrId }" /></td>';
						addHtml +='<td><c:out value="${mkspClsMbrList.ceoPhn }" /></td>';
						addHtml +='<td><c:out value="${mkspClsMbrList.creDttm }" /></td>';
						addHtml +='</tr>';
					}
				</c:if>
			</c:forEach>
			if(i == 0){
				addHtml += '<td colspan="5">명단이 없습니다.</td>';
				addHtml += '</tr>';
			}
			
			$('#tbody').append(addHtml);
			
			if(i > 1){
				$('#'+id).attr('rowspan',i);
			}
		</c:forEach>

	});
	
});
</script>

<form:form commandName="reqMakerspaceClassVO" name="infoFm" method="GET">
	<form:hidden path="mkspClsIdx"/>
	<form:hidden path="titl"/>
	<form:hidden path="srtTm"/>
</form:form>

<div class="subcon_area">
	<div class="stit mt_20 mb_10">
		<strong>서울이노베이션팹랩 클래스 신청명단 - <c:out value="${searchVO.titl }" /></strong>
		<button id="btnExcelDown" class="btnBasic_black fr">엑셀저장</button>
	</div>

	<div class="insideTbl">
		<table>
			<thead>
				<tr>
					<th>클래스명</th>
					<th>성명</th>
					<th>아이디(이메일)</th>
					<th>휴대전화</th>
					<th>신청일</th>
				</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
		</table>
	</div>
</div>
<div class="tac">
	<button id="btnCansle" class="btnBasic_black">닫기</button>
</div>