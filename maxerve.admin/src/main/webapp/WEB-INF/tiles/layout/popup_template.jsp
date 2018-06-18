<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>서울혁신파크 관리</title>
<tiles:insertAttribute name="headScript" />
<style>
body {background: none;}
</style>
</head>
 <body>
	<img src="/img/loading.png" class="loading"/>
<script type="text/javascript">
pageLoad();
</script>
<% out.flush(); %>
  <tiles:insertAttribute name="body" />
 </body>
</html>