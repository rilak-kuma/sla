<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tiles/layout/head_taglib.jsp" %>

<script type="text/javascript">
jQuery(function($){
	var url = $('#sideMenu .menu_left li a').filter(function(){
		return $(this).attr('href') != '#';
	}).eq(0).attr('href');

	location.href = url;
});
</script>
