<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">

jQuery(function($){
	//validate
	$('form[name=floorFm]').validate({
		rules: {
			keyword : "required"
		},
		messages: {
			keyword : getMessage('org.hibernate.validator.constraints.NotEmpty.message', '검색어는')
		},
		submitHandler: function(form) {
			var url = "<c:out value='/member/moveInOztnPopUp.do' />";
			var submitData = $(form).serializeFormJSON();
			var callback = function(data) {

			}
 			//console.log(submitData);
			ajaxSubmit(url, submitData, callback, null);
		}
	});

	$("#dataElement").on('click',function(){
		opener.fn_getData($(this).find("input[name=mvinAplyIdx]").val(), $(this).find("input[name=oztnNm]").val(), $(this).find("input[name=blog]").val());
		self.close();
	});

	function initList() {
		var memberList = [];

		<c:forEach items="${memberList}" var="item">
			memberList.push({
				mvinAplyIdx: '<c:out value="${item.mvinAplyIdx}" />',
				oztnNm: '<c:out value="${item.oztnNm}" />',
				ceoNm: '<c:out value="${item.ceoNm}" />',
				blog: '<c:out value="${item.blog}" />'
			});
		</c:forEach>

		if (memberList.length) {
			$(memberList).each(function(k, v){
				var $element = $('#samples #dataElement').clone(true);
				$element.find("input[name=mvinAplyIdx]").val(v.mvinAplyIdx);
				$element.find("input[name=oztnNm]").val(v.oztnNm);
				$element.find("input[name=ceoNm]").val(v.ceoNm);
				$element.find("input[name=blog]").val(v.blog);
				$("#resultDiv").append($element);
			});
		} else {
			$("#resultDiv").text("조회 결과가 없습니다.");
		}
	}

	//초기값
	$(document).ready(function(){
		initList();
	});

});

</script>


<form name="searchFm" method="post" >
<input type="text" name="keyword" placeholder="단체명" value='<c:out value="${param.keyword }"/>' />
<input type="submit" value="조회"/>
</form>

<div id="resultDiv">

</div>

<div id='samples' class='hidden'>

		<span id="dataElement" >
			<input type="hidden" name="mvinAplyIdx" style="text-align: center;" />
			<input type="hidden" name="blog" style="text-align: center;" />
			<input type="text" name="oztnNm" style="text-align: center;" />
			<input type="text" name="ceoNm" style="text-align: center;" /><br/>
		</span>
</div>