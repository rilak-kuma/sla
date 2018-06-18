<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.qtip.css"/>" />
<script type="text/javascript" src='<c:url value="/js/jquery.qtip.js"/>'></script>
<script type="text/javascript">

jQuery(function($){

	$("select[name=flr]").on("change",function(){
		location.href = '?flr=' + $(this).find('option:selected').val();
	});

	var elem = $(this),
    map = elem.parent(),
    image = $('img[usemap="#'+map.attr('name')+'"]');

	// 이미지툴팁
	$('map[name=FMap] area[alt]').each(function () {
        var elem = $(this),
            map = elem.parent(),
            image = $('img[usemap="#'+map.attr('name')+'"]');

        elem.qtip({
            content: {
                attr: 'alt' // No content? Grab the content from the 'alt' attribute
            },
            position: {
				my: 'bottom left',
				at: 'top right',
				adjust: {
					y: 13,
					x:-50
				}
            },
            show: {
                event: 'click mouseover',
                solo: true
            },
            hide: {
                event: 'mouseleave',
                delay: 500
            },
            style: {
                classes: 'qtip-tipsy',
				tip: {
					corner: 'bottom center',
					mimic: 'bottom left',
					width:8,
					height:17
				  }
            }
        });
    });

});

</script>

<h5>미래청 조감도</h5>
<div>
	<select name="flr">
	<c:forEach varStatus="varStatus" step="1" begin="2" end="6" >
		<c:set var="c">
			<c:if test="${param.flr eq varStatus.index }"> SELECTED</c:if>
		</c:set>
		<option value="${varStatus.index }"${c }>미래청 ${varStatus.index }층</option>
	</c:forEach>
	</select>
</div>

<img src='<spring:eval expression="@propertiesService['upload.url.root']" /><c:out value="${floorInfo.filePath }"/>' name="FMap" border="0" usemap="#FMap" />

<map name="FMap">
<c:forEach items="${floorInfoList }" var="item">
	<area shape="rect" coords='<c:out value="${item.fctCord }"/>' alt='<c:out value="${item.fctNm }"/>' style='border:0;'/>
</c:forEach>
</map>
